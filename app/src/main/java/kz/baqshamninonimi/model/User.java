package kz.baqshamninonimi.model;

public class User {
    private static String mLogin;
    private static String mPassword;

    public User() {
    }

    public static String getmLogin() {
        return mLogin;
    }

    public static void setmLogin(String mLogin) {
        User.mLogin = mLogin;
    }

    public static String getmPassword() {
        return mPassword;
    }

    public static void setmPassword(String mPassword) {
        User.mPassword = mPassword;
    }
}
