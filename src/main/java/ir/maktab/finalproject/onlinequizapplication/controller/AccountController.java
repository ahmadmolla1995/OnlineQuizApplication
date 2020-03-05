package ir.maktab.finalproject.onlinequizapplication.controller;

import ir.maktab.finalproject.onlinequizapplication.dto.UserLoginDTO;
import ir.maktab.finalproject.onlinequizapplication.dto.UserRegisterDTO;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountAlreadyExistsException;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.service.AccountService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @RequestMapping (value = "/register", method = RequestMethod.GET)
    public void register (UserRegisterDTO userRegisterDTO) throws AccountAlreadyExistsException {
        accountService.register(userRegisterDTO);
    }

    @RequestMapping (value = "/login", method = RequestMethod.GET)
    public void login (UserLoginDTO userLoginDTO) throws AccountNotFoundException {
        accountService.login(userLoginDTO);
    }

    @RequestMapping (value = "/userList/confirmAll", method = RequestMethod.POST)
    public void confirmAll () {
        accountService.confirmAll();
    }

    @RequestMapping (value = "/userList/rejectAll", method = RequestMethod.POST)
    public void rejectAll () {
        accountService.rejectAll();
    }
}
