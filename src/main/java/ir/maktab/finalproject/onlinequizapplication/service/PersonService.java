package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.repository.CourseRepository;
import ir.maktab.finalproject.onlinequizapplication.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService {
    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    private final CourseRepository courseRepository;


    public PersonService(PersonRepository personRepository, CourseRepository courseRepository) {
        this.personRepository = personRepository;
        this.courseRepository = courseRepository;
    }
}
