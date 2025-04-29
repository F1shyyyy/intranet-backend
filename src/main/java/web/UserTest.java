package web;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import shared.User;

import core.UserService;
import shared.Users;

public class UserTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            String pozadavek = scanner.nextLine();
            if (pozadavek.equals( "log in")){
                // přihlášení, když už je přihlášen taky nemůže nastat
                try {
                    Users theService = (Users)Naming.lookup("rmi://localhost:1099/users");

                    System.out.print("Uživatelské jméno: ");
                    String username = scanner.nextLine();
                    System.out.print("Heslo: ");
                    String password = scanner.nextLine();

                    User result = theService.logIn(username, password.toCharArray());
                    if (result == null){
                        System.out.println("Uživatelské jméno nebo heslo se neshoduje");
                    } else {
                        Session.logInUser(result);
                        System.out.println(String.format("Uživateli %s byl jste přihlášen", result.getUsername()));
                    }
                    
                    
                } catch (MalformedURLException | RemoteException | NotBoundException e){
                    e.printStackTrace();
                }
            } else if (pozadavek.equals("log out")){
                if (Session.isLoggedIn()){
                    Session.logOutUser();
                    System.out.println("Byl jste úspěšně přihlášen");
                } else { // nemůže nastat v produkci
                    System.out.println("Nejste přihlášen, aby jste se mohl přihlásit");
                }
                
            }
            
        } while (true);
        
        
    }
}
