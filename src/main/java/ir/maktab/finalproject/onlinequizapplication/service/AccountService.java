package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.security.AuthenticationService;
import ir.maktab.finalproject.onlinequizapplication.dto.UserLoginDTO;
import ir.maktab.finalproject.onlinequizapplication.dto.UserRegisterDTO;
import ir.maktab.finalproject.onlinequizapplication.enumeration.AccountStatus;
import ir.maktab.finalproject.onlinequizapplication.enumeration.RoleType;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountAlreadyExistsException;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.model.Account;
import ir.maktab.finalproject.onlinequizapplication.repository.AccountRepository;
import ir.maktab.finalproject.onlinequizapplication.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;


    public AccountService(AccountRepository accountRepository, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }


    public void register (UserRegisterDTO userRegisterDto) throws AccountAlreadyExistsException {
        if (accountRepository.findByUsername(userRegisterDto.getUsername()) != null)
            throw new AccountAlreadyExistsException("There is an account with this username");

        Account account = new Account(
            null,
            userRegisterDto.getUsername(),
            userRegisterDto.getPassword(),
            AccountStatus.DISABLE,
            new Date(),
            null,
            true
        );

        account.addRole(roleRepository.findByRoleType(RoleType.valueOf(userRegisterDto.getRole())));
    }

    public void login (UserLoginDTO userLoginDTO) throws AccountNotFoundException {
        Account account = accountRepository.findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        if (account == null)
            throw new AccountNotFoundException("There isn't account with this username");

        account.setLastLoginDate(new Date());
        accountRepository.save(account);

        AuthenticationService.setLoginUser(userLoginDTO);
    }

    public void logout() {
        AuthenticationService.logoutUser();
    }

    public void confirm (Long accountID) {
        Optional<Account> account = accountRepository.findById(accountID);

        if (account.isPresent() && account.get().getAccountStatus().equals(AccountStatus.WAITING_CONFIRMATION)) {
            account.get().setAccountStatus(AccountStatus.ENABLE);
            accountRepository.save(account.get());
        }
    }

    public void reject (Long accountID) {
        Optional<Account> account = accountRepository.findById(accountID);

        if (account.isPresent() && account.get().getAccountStatus().equals(AccountStatus.WAITING_CONFIRMATION)) {
            account.get().setAccountStatus(AccountStatus.REJECTED);
            accountRepository.save(account.get());
        }
    }

    public void confirmAll (List<Long> accountIdList) {
        List<Account> accounts = accountRepository.findAllByIdIn(accountIdList);

        for (Account account: accounts) {
            if (account.getAccountStatus().equals(AccountStatus.WAITING_CONFIRMATION)) {
                account.setAccountStatus(AccountStatus.ENABLE);
                accountRepository.save(account);
            }
        }
    }

    public void confirmAll () {
        List<Account> accounts = accountRepository.findAllByAccountStatus(AccountStatus.WAITING_CONFIRMATION);

        for (Account account: accounts) {
            account.setAccountStatus(AccountStatus.ENABLE);
            accountRepository.save(account);
        }
    }

    public void rejectAll (List<Long> accountIdList) {
        List<Account> accounts = accountRepository.findAllByIdIn(accountIdList);

        for (Account account: accounts) {
            if (account.getAccountStatus().equals(AccountStatus.WAITING_CONFIRMATION)) {
                account.setAccountStatus(AccountStatus.REJECTED);
                accountRepository.save(account);
            }
        }
    }

    public void rejectAll() {
        List<Account> accounts = accountRepository.findAllByAccountStatus(AccountStatus.WAITING_CONFIRMATION);

        for (Account account: accounts) {
            account.setAccountStatus(AccountStatus.REJECTED);
            accountRepository.save(account);
        }
    }
}
