package model;
import java.sql.*;
import java.util.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:database";

    public DatabaseManager() throws SQLException {
        initializeDatabase();
    }

    private void initializeDatabase()  {
        try(Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement()){

            // create users table
            String sql = "CREATE TABLE IF NOT EXISTS users  (" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    firstname TEXT NOT NULL," +
                    "    lastname TEXT NOT NULL," +
                    "    email TEXT NOT NULL UNIQUE," +
                    "    username TEXT NOT NULL UNIQUE," +
                    "    password TEXT NOT NULL," +
                    "    quizScore INTEGER DEFAULT 0," +
                    "    quizDate TEXT DEFAULT (datetime('now', 'localtime'))," +
                    "    createdAt TEXT DEFAULT (datetime('now', 'localtime'))," +
                    "    updatedAt TEXT DEFAULT (datetime('now', 'localtime'))" +
                    ");";
            stmt.execute(sql);

            //create admins table
            sql = "CREATE TABLE IF NOT EXISTS admins (" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    username TEXT NOT NULL," +
                    "    password TEXT NOT NULL," +
                    "    createdAt TEXT DEFAULT (datetime('now', 'localtime'))," +
                    "    updatedAt TEXT DEFAULT (datetime('now', 'localtime'))" +
                    ");";

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
        }
    }


    // ------- Admin operations ----------------
    public String getAdminPassword(String admin){
        try(Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement()) {
            String sql = "SELECT * from admins where username = '" + admin + "';";
            ResultSet res = stmt.executeQuery(sql);
            if(res.next()){
                return res.getString("password");
            } else {
                return null;
            }
        }catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
            return null;
        }
    }

// ------------ get all the users ---------------------
    public List<Map<String, String>> getAllUsers(){
        List<Map<String, String>> users = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement()) {
            String sql = "SELECT * from users;";
            ResultSet res = stmt.executeQuery(sql);

            while(res.next()){
                Map<String, String> user = new HashMap<>();
                user.put("firstname" , res.getString("firstname"));
                user.put("lastname" , res.getString("lastname"));
                user.put("email" , res.getString("email"));
                user.put("username" , res.getString("username"));
                user.put("quizScore" , res.getString("quizScore"));
                user.put("quizDate" , res.getString("quizDate"));
                users.add(user);
            }
        }catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
            return null;
        }
        return users;
    }

    // --------------------- Find a user by id ------------------------
    public Map<String, String> findUser(String identifier, String identifierType){
        Map<String, String> user =  new HashMap<>();
        try(Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement()) {
            String sql = "SELECT * from users where ";
            switch(identifierType.toLowerCase()){
                case "id":
                    sql = sql + "id = '" + identifier + "';";
                    break;
                case "username":
                    sql = sql + "username = '" + identifier + "';";
                    break;
                case "email":
                    sql = sql + "email = '" + identifier + "';";
                    break;
            }
            System.out.println(sql);
            ResultSet res = stmt.executeQuery(sql);

            if(!res.next()){
                return null;
            }
            user.put("firstname" , res.getString("firstname"));
            user.put("lastname" , res.getString("lastname"));
            user.put("email" , res.getString("email"));
            user.put("username" , res.getString("username"));
            user.put("quizScore" , res.getString("quizScore"));
            user.put("quizDate" , res.getString("quizDate"));
            user.put("password" , res.getString("password"));
            return user;
        }catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
            return null;
        }
    }

    // ---------------------------- add a user ---------------------------------
    public boolean addUser(Map<String, String> user){
        try(Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement()) {
            String firstname = user.get("firstname");
            String lastname = user.get("lastname");
            String email = user.get("email");
            String username = user.get("username");
            String password = user.get("password");

            String sql = "INSERT INTO users(firstname, lastname, email, username, password) values('" + firstname + "','" + lastname + "','" + email + "','" + username + "','" + password + "');";
            System.out.println(sql);
            int rowsAffected = stmt.executeUpdate(sql);
            return true;
        }catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
            return false;
        }
    }

    // ------------------------------ update user --------------------------------------------
    public boolean updateUser(Map<String, String> user){
        try(Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement()) {
            String firstname = user.get("firstname");
            String lastname = user.get("lastname");
            String email = user.get("email");
            String username = user.get("username");
            String password = user.get("password");

            String sql = "UPDATE users SET" + firstname + "','" + lastname + "','" + email + "','" + username + "','" + password + "');";
            System.out.println(sql);
            int rowsAffected = stmt.executeUpdate(sql);
            return true;
        }catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
            return false;
        }
    }
}







































































































