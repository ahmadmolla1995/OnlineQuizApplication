package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.dto.PersonRegisterCompletionDTO;
import ir.maktab.finalproject.onlinequizapplication.dto.PersonRegisterDTO;
import ir.maktab.finalproject.onlinequizapplication.dto.PersonSignInCompletionDTO;
import ir.maktab.finalproject.onlinequizapplication.mapper.PersonToPersonLoginCompletionDtoMapper;
import ir.maktab.finalproject.onlinequizapplication.mapper.PersonRegisterDtoToPersonMapper;
import ir.maktab.finalproject.onlinequizapplication.mapper.PersonToPersonRegisterCompletionDtoMapper;
import ir.maktab.finalproject.onlinequizapplication.model.Person;
import ir.maktab.finalproject.onlinequizapplication.repository.PersonRepository;
import ir.maktab.finalproject.onlinequizapplication.security.AuthenticationService;
import ir.maktab.finalproject.onlinequizapplication.dto.PersonLoginDTO;
import ir.maktab.finalproject.onlinequizapplication.enumeration.AccountStatus;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountAlreadyExistsException;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.model.Account;
import ir.maktab.finalproject.onlinequizapplication.repository.AccountRepository;
import ir.maktab.finalproject.onlinequizapplication.repository.RoleRepository;
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

    public PersonRegisterCompletionDTO signUp (PersonRegisterDTO personRegisterDTO) throws AccountAlreadyExistsException {
        if (accountRepository.findByUsername(personRegisterDTO.getUsername()) != null)
            throw new AccountAlreadyExistsException("There is an account with this username");

        Person person = PersonRegisterDtoToPersonMapper.mapper(personRegisterDTO);

        Account account = new Account(
                null,
                personRegisterDTO.getUsername(),
                personRegisterDTO.getPassword(),
                roleRepository.findByRoleType(personRegisterDTO.getRoleType()),
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

    public PersonSignInCompletionDTO signIn (PersonLoginDTO personDto) throws AccountNotFoundException {
        Account account = accountRepository.findByUsernameAndPassword(personDto.getUsername(), personDto.getPassword());

        if (account == null)
            throw new AccountNotFoundException("There isn't account with this username");

        account.setLastLoginDate(new Date());
        accountRepository.save(account);

        PersonSignInCompletionDTO person = PersonToPersonLoginCompletionDtoMapper.mapper(account.getPerson());
        AuthenticationService.setLoginUser(person);

        return person;
    }

    public void logout() {
        AuthenticationService.logoutUser();
    }

    public void confirm (Long accountID) {
        Optional<Account> account = accountRepository.findById(accountID);

        if (account.isPresent() && account.get().getAccountStatus().equals(AccountStatus.WAITING_CONFIRMATION)) {
            account.get().setAccountStatus(AccountStatus.CONFIRMED);
            accountRepository.save(account.get());
        }
    }

    public void reject (Long accountID) {
        Optional<Account> account = accountRepository.findById(accountID);

        if (account.isPresent() && account.get().getAccountStatus().equals(AccountStatus.WAITING_CONFIRMATION))
            accountRepository.deleteById(accountID);
    }

    public void confirmAll (List<Long> accountIdList) {
        List<Account> accounts = accountRepository.findAllByIdIn(accountIdList);

        for (Account account: accounts) {
            if (account.getAccountStatus().equals(AccountStatus.WAITING_CONFIRMATION)) {
                account.setAccountStatus(AccountStatus.CONFIRMED);
                accountRepository.save(account);
            }
        }
    }

    public void confirmAll () {
        List<Account> accounts = accountRepository.findAllByAccountStatus(AccountStatus.WAITING_CONFIRMATION);

        for (Account account: accounts) {
            account.setAccountStatus(AccountStatus.CONFIRMED);
            accountRepository.save(account);
        }
    }

    public void rejectAll (List<Long> accountIdList) {
        List<Account> accounts = accountRepository.findAllByIdIn(accountIdList);

        for (Account account: accounts) {
            if (account.getAccountStatus().equals(AccountStatus.WAITING_CONFIRMATION))
                accountRepository.delete(account);
        }
    }

    public void rejectAll() {
        List<Account> accounts = accountRepository.findAllByAccountStatus(AccountStatus.WAITING_CONFIRMATION);

        for (Account account: accounts) {
            accountRepository.delete(account);
        }
    }

    public List<Account> getAllAccounts(AccountStatus accountStatus) {
        return accountRepository.findAllByAccountStatus(accountStatus);
    }
}

