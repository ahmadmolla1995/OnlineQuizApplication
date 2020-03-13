package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    @Autowired
    private PersonRepository personRepository;

    public StudentService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
