package ir.maktab.finalproject.onlinequizapplication.controller;

import ir.maktab.finalproject.onlinequizapplication.dto.*;
import ir.maktab.finalproject.onlinequizapplication.enumeration.AccountStatus;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountAlreadyExistsException;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.model.Account;
import ir.maktab.finalproject.onlinequizapplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "account")
public class AccountController {
    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @RequestMapping (value = "/signUp/signUpCheck", method = RequestMethod.POST)
    public String signUp (@ModelAttribute PersonRegisterDTO personRegisterDTO) throws AccountAlreadyExistsException {
        PersonRegisterCompletionDTO person = accountService.signUp(personRegisterDTO);

        if (person != null)
            return "redirect:/account/signIn";
        return null;
    }

    @RequestMapping (value = "/signIn/signInCheck", method = RequestMethod.POST)
    public String signIn (@ModelAttribute PersonLoginDTO personLoginDTO, ModelMap model) throws AccountNotFoundException {
        PersonSignInCompletionDTO person = accountService.signIn(personLoginDTO);
        model.addAttribute("personLoginDtoCompletion", person);

        if (person != null)
            return "redirect:/account/managerPanel";
        return null;
    }

    @RequestMapping (value = "/managerPanel/ListUnconfirmedAccounts", method = RequestMethod.GET)
    public String listUnconfirmedAccounts(ModelMap modelMap) {
        List<Account> unconfirmedAccounts = accountService.getAllAccounts(AccountStatus.WAITING_CONFIRMATION);
        modelMap.addAttribute("unconfirmed_accounts", unconfirmedAccounts);

        return "manager_panel";
    }

    @RequestMapping (value = "/managerPanel/viewAccountDetail")
    public String viewAccountDetail() {
        return "manager_panel";
    }

    @RequestMapping (value = "/managerPanel/confirmAccount", method = RequestMethod.POST)
    public String confirmAccount(@ModelAttribute AccountConfirmationDTO accountConfirmationDto) {
        accountService.confirm(accountConfirmationDto.getAccountID());

        return "redirect:/account/manager_panel";
    }

    @RequestMapping (value = "/managerPanel/rejectAccount", method = RequestMethod.POST)
    public String rejectAccount(@ModelAttribute AccountConfirmationDTO accountConfirmationDto) {
        accountService.reject(accountConfirmationDto.getAccountID());

        return "redirect:/account/manager_panel";
    }

    @RequestMapping (value = "/managerPanel/searchCourses")
    public String searchCourses(ModelMap modelMap) {
        return null;
    }
}
