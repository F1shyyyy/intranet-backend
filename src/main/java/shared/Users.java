package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.function.Predicate;

public interface Users extends Remote {
    List<User> getUsers() throws RemoteException;
    List<User> getUsers(Predicate<User> condition) throws RemoteException;
    User getUserByID(Long id) throws RemoteException;
    boolean saveUser(User user) throws RemoteException;
    User logIn(String username, char[] password) throws RemoteException;
}