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
                        System.out.println(String.format("Uživateli %s byl jste přihlášen", result.getUsername()));
                    }
                    
                    
                } catch (MalformedURLException | RemoteException | NotBoundException e){
                    e.printStackTrace();
                }
            } else {
                System.out.println("ahaaa");
            }
            
        } while (true);
        
        
    }
}
