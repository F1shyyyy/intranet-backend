package datasource;

import shared.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersDataSource {
    List<User> getAll() throws DataSourceException;
    boolean saveUser(User user) throws DataSourceException;
}
