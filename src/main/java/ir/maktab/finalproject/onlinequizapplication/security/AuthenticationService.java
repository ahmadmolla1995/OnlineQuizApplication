package ir.maktab.finalproject.onlinequizapplication.security;

import ir.maktab.finalproject.onlinequizapplication.dto.PersonSignInCompletionDTO;


public final class AuthenticationService {
    private static PersonSignInCompletionDTO personSignInCompletionDTO;


    public static PersonSignInCompletionDTO getLoginUser() {
        return personSignInCompletionDTO;
    }

    public static void setLoginUser(PersonSignInCompletionDTO person) {
        personSignInCompletionDTO = person;
    }

    public static void logoutUser() {
        personSignInCompletionDTO = null;
    }
}
