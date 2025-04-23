package core;

import datasource.DataSourceException;
import datasource.UsersDataSource;
import datasource.UsersDataSourceImpl;
import shared.User;
import shared.Users;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserService extends UnicastRemoteObject implements Users {
    private Map<Long, User> users;

    public UserService() throws RemoteException, DataSourceException {
        super();
        UsersDataSource source = UsersDataSourceImpl.getInstance();
        this.users = source.getAll().stream().collect(Collectors.toMap(User::getId, u->u));
    }

    @Override
    public List<User> getUsers() throws RemoteException {
        return this.users.values().stream().toList();
    }

    @Override
    public List<User> getUsers(Predicate<User> condition) throws RemoteException {
        return this.users.values().stream().filter(condition).toList();
    }

    @Override
    public User getUserByID(Long id) throws RemoteException {
        return this.users.get(id);
    }

    @Override
    public boolean saveUser(User user) throws RemoteException {
        return false;
    }
}
