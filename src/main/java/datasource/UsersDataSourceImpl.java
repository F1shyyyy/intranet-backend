package datasource;

import shared.User;

import java.sql.*;
import java.util.*;

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
        this.password = "";

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
}
