package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.dto.PersonDTO;
import ir.maktab.finalproject.onlinequizapplication.enumeration.AccountStatus;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountNotActivatedException;
import ir.maktab.finalproject.onlinequizapplication.mapper.PersonDtoToPersonMapper;
import ir.maktab.finalproject.onlinequizapplication.model.Account;
import ir.maktab.finalproject.onlinequizapplication.model.Person;
import ir.maktab.finalproject.onlinequizapplication.repository.AccountRepository;
import org.springframework.stereotype.Service;


@Service
public class PersonService {
    private final AccountRepository accountRepository;

    public PersonService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public void CompleteRegistration(PersonDTO personDTO) throws AccountNotActivatedException {
        Account account = accountRepository.findByUsername(personDTO.getUsername());
        if (account != null) {
            if (account.getAccountStatus().equals(AccountStatus.DISABLE)) {
                Person person = PersonDtoToPersonMapper.mapper(personDTO, account.getPerson());

                account.setPerson(person);
                account.setAccountStatus(AccountStatus.WAITING_CONFIRMATION);

                accountRepository.save(account);
            }
        }
    }
}
