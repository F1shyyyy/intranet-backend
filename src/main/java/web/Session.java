package web;

public class Session {
    private static shared.User loggedInUser = null;

    public static shared.User getLoggedInUser() {
        return loggedInUser;
    }

    public static void logInUser(shared.User theUser){
        loggedInUser = theUser;
    }

    public static void logOutUser(){
        loggedInUser = null;
    }

    public static boolean isLoggedIn(){
        return loggedInUser != null ? true : false;
    }
}
