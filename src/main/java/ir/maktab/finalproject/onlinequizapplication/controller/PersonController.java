package ir.maktab.finalproject.onlinequizapplication.controller;

import ir.maktab.finalproject.onlinequizapplication.dto.PersonDTO;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountNotActivatedException;
import ir.maktab.finalproject.onlinequizapplication.service.PersonService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @RequestMapping (value = "/completeInformation", method = RequestMethod.POST)
    public void completeInformation (@RequestBody PersonDTO personDTO) throws AccountNotActivatedException {
        personService.CompleteRegistration(personDTO);
    }
}
