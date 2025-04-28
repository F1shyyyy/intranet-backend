package core;

import datasource.DataSourceException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class App {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("users", new UserService());
        } catch (DataSourceException|RemoteException e) {
            e.printStackTrace();
        }
    }
}
