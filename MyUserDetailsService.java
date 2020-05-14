package ir.maktab.finalproject.onlinequizapplication.security;

import ir.maktab.finalproject.onlinequizapplication.model.Account;
import ir.maktab.finalproject.onlinequizapplication.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;
    public MyUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) {
        Optional<Account> account = Optional.ofNullable(accountRepository.findByUsername(userName));

        return account.map(MyUserDetails::new).get();
    }
}