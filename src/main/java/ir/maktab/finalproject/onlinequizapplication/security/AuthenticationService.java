package ir.maktab.finalproject.onlinequizapplication.security;

import ir.maktab.finalproject.onlinequizapplication.dto.UserLoginDTO;


public final class AuthenticationService {
    private static UserLoginDTO userLoginDTO;


    public static UserLoginDTO getLoginUser() {
        return userLoginDTO;
    }

    public static void setLoginUser(UserLoginDTO userLogin) {
        userLoginDTO = userLogin;
    }

    public static void logoutUser() {
        userLoginDTO = null;
    }
}
