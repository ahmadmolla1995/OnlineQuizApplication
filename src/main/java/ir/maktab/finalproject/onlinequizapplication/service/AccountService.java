package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.dto.*;
import ir.maktab.finalproject.onlinequizapplication.enumeration.AccountStatus;
import ir.maktab.finalproject.onlinequizapplication.enumeration.RoleType;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountAlreadyExistsException;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.exception.RoleNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.exception.WrongPasswordException;
import ir.maktab.finalproject.onlinequizapplication.mapper.PersonRegisterDtoToPersonMapper;
import ir.maktab.finalproject.onlinequizapplication.mapper.PersonToPersonLoginCompletionDtoMapper;
import ir.maktab.finalproject.onlinequizapplication.mapper.PersonToPersonRegisterCompletionDtoMapper;
import ir.maktab.finalproject.onlinequizapplication.model.Account;
import ir.maktab.finalproject.onlinequizapplication.model.Person;
import ir.maktab.finalproject.onlinequizapplication.repository.*;
import ir.maktab.finalproject.onlinequizapplication.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    private final RoleRepository roleRepository;


    public AccountService(AccountRepository accountRepository, PersonRepository personRepository, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }


    public PersonRegisterCompletionDTO signUp (PersonRegisterDTO personRegisterDTO) throws AccountAlreadyExistsException, RoleNotFoundException {
        if (accountRepository.findByUsername(personRegisterDTO.getUsername()) != null)
            throw new AccountAlreadyExistsException("There is an account with this username");

        Person person = PersonRegisterDtoToPersonMapper.mapper(personRegisterDTO);

        Account account = new Account(
                null,
                personRegisterDTO.getUsername(),
                personRegisterDTO.getPassword(),
                roleRepository.findByRoleType(RoleType.valueOf(personRegisterDTO.getRoleType())),
                AccountStatus.WAITING_CONFIRMATION,
                new Date(),
                null,
                true
        );

        account.setPerson(person);
        account = accountRepository.save(account);
        person.setAccount(account);
        person = personRepository.save(person);

        return PersonToPersonRegisterCompletionDtoMapper.mapper(person);
    }

    public PersonSignInCompletionDTO signIn (PersonLoginDTO personDto) throws AccountNotFoundException, WrongPasswordException {
        Account account = accountRepository.findByUsername(personDto.getUsername());

        if (account == null)
            throw new AccountNotFoundException("There isn't account with this username");
        if (!account.getPassword().equals(personDto.getPassword()))
            throw new WrongPasswordException("Password is wrong!");

        account.setLastLoginDate(new Date());
        accountRepository.save(account);

        PersonSignInCompletionDTO person = PersonToPersonLoginCompletionDtoMapper.mapper(account.getPerson());
        AuthenticationService.setLoginUser(person);

        return person;
    }

    public void logout() {
        AuthenticationService.logoutUser();
    }

    public void confirm (Long accountID) throws AccountNotFoundException {
        Optional<Account> account = accountRepository.findById(accountID);

        if (account.isPresent() && account.get().getAccountStatus().equals(AccountStatus.WAITING_CONFIRMATION)) {
            account.get().setAccountStatus(AccountStatus.CONFIRMED);
            accountRepository.save(account.get());
        }
        else
            throw new AccountNotFoundException("There isn't any account with this id!");
    }

    public void remove(Long accountID) throws AccountNotFoundException {
        Optional<Account> account = accountRepository.findById(accountID);

        if (account.isPresent() && account.get().getAccountStatus().equals(AccountStatus.WAITING_CONFIRMATION))
            accountRepository.deleteById(accountID);
        else
            throw new AccountNotFoundException("There isn't any account with this id!");
    }

    public void reject (Long accountID) throws AccountNotFoundException {
        Optional<Account> account = accountRepository.findById(accountID);

        if (account.isPresent() && account.get().getAccountStatus().equals(AccountStatus.WAITING_CONFIRMATION)) {
            account.get().setAccountStatus(AccountStatus.REJECTED);
            accountRepository.save(account.get());
        }

        else
            throw new AccountNotFoundException("There isn't any account with this id!");
    }

    public List<Account> getAllAccounts(AccountStatus accountStatus) {
        return accountRepository.findAllByAccountStatus(accountStatus);
    }

    public void editAccount(AccountEditDTO accountEditDTO) throws AccountNotFoundException {
        Optional<Account> account = accountRepository.findById(accountEditDTO.getAccountID());

        if (account.isPresent()) {
            account.get().setUsername(accountEditDTO.getUsername());
            account.get().getPerson().setFirstName(accountEditDTO.getFirstName());
            account.get().getPerson().setLastName(accountEditDTO.getLastName());
            account.get().setAccountStatus(accountEditDTO.getAccountStatus());
            account.get().setEnable(accountEditDTO.getEnable());
            account.get().getPerson().setAddress(accountEditDTO.getAddress());
            account.get().getPerson().setEmail(accountEditDTO.getEmail());
            account.get().getPerson().setHomeNumber(accountEditDTO.getHomeNumber());
            account.get().getPerson().setCellPhoneNumber(accountEditDTO.getCellPhoneNumber());

            accountRepository.save(account.get());
        }
        else
            throw new AccountNotFoundException("There isn't any account with this id!");
    }
}

