package datasource;

import shared.User;

import java.sql.*;
import java.util.*;

import org.mindrot.jbcrypt.BCrypt;

public class UsersDataSourceImpl implements UsersDataSource {
    // singleton
    private static UsersDataSource instance;
    public static UsersDataSource getInstance() {
        if (instance == null){
            instance = new  UsersDataSourceImpl();
        }
        return instance;
    }

    private String url, username, password;

    private UsersDataSourceImpl() {
        this.url = "jdbc:mysql://localhost/sis";
        this.username = "root";
        this.password = "99.Drapek.99";

    }

    @Override
    public List<User> getAll() throws DataSourceException{
        String sql = "SELECT ID, username, heslo, lastLogin FROM uzivatel";
        try(
                Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
                Statement stmt = connection.createStatement();
                ResultSet results = stmt.executeQuery(sql);
        ){
           List<User> users = new ArrayList<>();
           while (results.next()) {
               User user = new User()
                       .setId(results.getLong("ID"))
                       .setUsername(results.getString("username"));
               if (results.getTimestamp("lastLogin") != null){
                   user.setLastLogin(results.getTimestamp("lastLogin").toLocalDateTime());
               }
               users.add(user);
           }
           return users;
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    

    @Override
    public boolean saveUser(User user) throws DataSourceException {
        String sql = "";

        Map<Integer, Object> params = new HashMap<>();

        if (user.getId() != null){
            sql = "UPDATE uzivatel SET username = ?, lastLogin = ?";
            int index = 0;
            params.put(++index,user.getUsername());
            params.put(++index, Timestamp.valueOf(user.getLastLogin()));
            if (user.getHeslo() != null) {
                sql += ", heslo = ?";
                params.put(++index, user.getHeslo());
            }
            sql += " WHERE ID = ?";
            params.put(++index, user.getId());
        } else{
            sql = "INSERT INTO uzivatel (username, heslo) VALUES (?,?)";
            int index = 0;
            params.put(++index,user.getUsername());
            params.put(++index, user.getHeslo());
        }
        try (
                Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
                PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ) {
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                stmt.setObject(entry.getKey(), entry.getValue());
            }
            boolean valid = stmt.executeUpdate() > 0;
            if (user.getId() == null && valid){
                try(ResultSet result = stmt.getGeneratedKeys();){
                    if (result.next()){
                        user.setId(result.getLong(1));
                    }
                }
            }
            return valid;
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public User logIn(String username, char[] password) throws DataSourceException {
        try {
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            try (PreparedStatement stmt = connection.prepareStatement("SELECT ID, username, heslo, lastLogin FROM uzivatel WHERE username = ?")){
                stmt.setString(1, username);
                try (ResultSet result = stmt.executeQuery()){
                    if (result.next() && BCrypt.checkpw(String.valueOf(password), result.getString(3))){
                        return new User().setId(result.getLong(1)).setUsername(result.getString(2)).setHeslo(result.getString(3)).setLastLogin(result.getTimestamp(4).toLocalDateTime());
                    }
                }
            }
        } catch (SQLException exc){
            exc.printStackTrace();
        }

        return null;
    }
}
