package datasource;

import shared.User;

import java.util.List;

public interface UsersDataSource {
    List<User> getAll() throws DataSourceException;
    boolean saveUser(User user) throws DataSourceException;
    User logIn(String username, char[] password) throws DataSourceException;
}
