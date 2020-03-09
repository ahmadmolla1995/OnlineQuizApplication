package ir.maktab.finalproject.onlinequizapplication.dto;


public class AccountConfirmationDTO {
    private Long accountID;

    public AccountConfirmationDTO(Long accountID) {
        this.accountID = accountID;
    }

    public Long getAccountID() {
        return accountID;
    }
}
