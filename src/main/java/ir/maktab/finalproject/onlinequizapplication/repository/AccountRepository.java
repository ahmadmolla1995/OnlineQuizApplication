package ir.maktab.finalproject.onlinequizapplication.repository;

import ir.maktab.finalproject.onlinequizapplication.enumeration.AccountStatus;
import ir.maktab.finalproject.onlinequizapplication.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername (String username);
    Account findByUsernameAndPassword (String username, String password);
    List<Account> findAllByIdIn (List<Long> accountIdList);
    List<Account> findAllByAccountStatus (AccountStatus accountStatus);
}

