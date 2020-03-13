package ir.maktab.finalproject.onlinequizapplication.controller;

import ir.maktab.finalproject.onlinequizapplication.dto.*;
import ir.maktab.finalproject.onlinequizapplication.enumeration.AccountStatus;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountAlreadyExistsException;
import ir.maktab.finalproject.onlinequizapplication.exception.AccountNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.exception.RoleNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.exception.WrongPasswordException;
import ir.maktab.finalproject.onlinequizapplication.model.Account;
import ir.maktab.finalproject.onlinequizapplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    @RequestMapping(value = "/signIn")
    private static String showSignInPage() {
        return "signin";
    }

    @RequestMapping(value = "/signUp")
    private static String signUp() {
        return "signup";
    }

    @RequestMapping(value = "/managerPanel")
    private static String showManagerPanel() {
        return "manager_panel";
    }

    @RequestMapping (value = "/signUp/signUpCheck", method = RequestMethod.POST)
    public String signUp (@ModelAttribute PersonRegisterDTO personRegisterDTO) throws AccountAlreadyExistsException, RoleNotFoundException {
        PersonRegisterCompletionDTO person = accountService.signUp(personRegisterDTO);

        if (person != null)
            return "redirect:/account/signIn";
        return null;
    }

    @RequestMapping (value = "/signIn/signInCheck", method = RequestMethod.POST)
    public String signIn (@ModelAttribute PersonLoginDTO personLoginDTO, ModelMap model) throws AccountNotFoundException, WrongPasswordException {
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

    @RequestMapping (value = "/managerPanel/confirmAccount", method = RequestMethod.POST)
    public String confirmAccount(@ModelAttribute AccountConfirmationDTO accountConfirmationDto) throws AccountNotFoundException {
        accountService.confirm(accountConfirmationDto.getAccountID());

        return "redirect:/account/managerPanel";
    }

    @RequestMapping (value = "/managerPanel/rejectAccount", method = RequestMethod.POST)
    public String rejectAccount(@ModelAttribute AccountConfirmationDTO accountConfirmationDto) throws AccountNotFoundException {
        accountService.reject(accountConfirmationDto.getAccountID());

        return "redirect:/account/managerPanel/ListUnconfirmedAccounts";
    }

    @RequestMapping(value = "/managerPanel/removeAccount", method = RequestMethod.POST)
    public String removeAccount(@ModelAttribute AccountConfirmationDTO accountConfirmationDTO) throws AccountNotFoundException {
        accountService.remove(accountConfirmationDTO.getAccountID());

        return "redirect:/account/managerPanel/ListUnconfirmedAccounts";
    }

    @RequestMapping (value = "/managerPanel/editAccount", method = RequestMethod.POST)
    public String editAccount(@ModelAttribute AccountEditDTO accountEditDTO) throws AccountNotFoundException {
        accountService.editAccount(accountEditDTO);

        return "redirect:/account/managerPanel/ListUnconfirmedAccounts";
    }
}
