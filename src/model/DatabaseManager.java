package model;

import java.sql.*;
import java.util.*;

public class DatabaseManager {
  private static final String DB_URL = "jdbc:sqlite:quiz_app.db";

  public DatabaseManager() throws SQLException {
    initializeDatabase();
  }

  private void initializeDatabase() {
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {

      String sql = "CREATE TABLE IF NOT EXISTS users  (" +
          "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
          "    firstname TEXT NOT NULL," +
          "    lastname TEXT NOT NULL," +
          "    email TEXT NOT NULL UNIQUE," +
          "    username TEXT NOT NULL UNIQUE," +
          "    password TEXT NOT NULL," +
          "    quizScore INTEGER DEFAULT -1," +
          "    quizDate TEXT," +
          "    createdAt TEXT DEFAULT (datetime('now', 'localtime'))," +
          "    updatedAt TEXT DEFAULT (datetime('now', 'localtime'))" +
          ");";
      stmt.execute(sql);
      // create admins table
      sql = "CREATE TABLE IF NOT EXISTS admins (" +
          "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
          "    username TEXT NOT NULL UNIQUE," +
          "    password TEXT NOT NULL," +
          "    createdAt TEXT DEFAULT (datetime('now', 'localtime'))," +
          "    updatedAt TEXT DEFAULT (datetime('now', 'localtime'))" +
          ");";
      stmt.execute(sql); // Execute the admins table creation SQL
      sql = "INSERT INTO admins (username, password) VALUES ('admin', 'adminpassword123');";
      stmt.execute(sql);
      // create quiz_attempts table
      sql = "CREATE TABLE IF NOT EXISTS quiz_attempts ( " +
          "attempt_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
          "user_id INTEGER NOT NULL, " +
          "score INTEGER NOT NULL, " +
          "total_questions INTEGER NOT NULL, " +
          "attempt_date TEXT DEFAULT (datetime('now', 'localtime'))," +
          "answers_concatenated TEXT NOT NULL," +
          "FOREIGN KEY (user_id) REFERENCES users(id));";
      stmt.execute(sql); // Execute the quiz_attempts table creation SQL

    } catch (SQLException e) {
      System.out.println("Database initialization error: " + e.getMessage());
    }
  }

  // ------- Admin operations ----------------
  public String getAdminPassword(String admin) {
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {
      String sql = "SELECT * from admins where username = '" + admin + "';";
      ResultSet res = stmt.executeQuery(sql);
      if (res.next()) {
        return res.getString("password");
      } else {
        return null;
      }
    } catch (SQLException e) {
      System.out.println("Database initialization error: " + e.getMessage());
      return null;
    }
  }

  // ------------ get all the users ---------------------
  public List<Map<String, String>> getAllUsers() {
    List<Map<String, String>> users = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {
      String sql = "SELECT * from users;";
      ResultSet res = stmt.executeQuery(sql);

      while (res.next()) {
        Map<String, String> user = new HashMap<>();
        user.put("firstname", res.getString("firstname"));
        user.put("lastname", res.getString("lastname"));
        user.put("email", res.getString("email"));
        user.put("username", res.getString("username"));
        user.put("quizScore", res.getString("quizScore"));
        user.put("quizDate", res.getString("quizDate"));
        users.add(user);
      }
    } catch (SQLException e) {
      System.out.println("Database initialization error: " + e.getMessage());
      return null;
    }
    return users;
  }

  // --------------------- Find a user by id ------------------------
  public Map<String, String> findUser(String identifier, String identifierType) {
    Map<String, String> user = new HashMap<>();
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {
      String sql = "SELECT * from users where ";
      switch (identifierType.toLowerCase()) {
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

      ResultSet res = stmt.executeQuery(sql);

      if (!res.next()) {
        return null;
      }

      user.put("id", res.getString("id"));
      user.put("firstname", res.getString("firstname"));
      user.put("lastname", res.getString("lastname"));
      user.put("email", res.getString("email"));
      user.put("username", res.getString("username"));
      user.put("quizScore", res.getString("quizScore"));
      user.put("quizDate", res.getString("quizDate"));
      user.put("password", res.getString("password"));
      return user;
    } catch (SQLException e) {
      System.out.println("Database initialization error: " + e.getMessage());
      return null;
    }
  }

  // ---------------------------- add a user ---------------------------------
  public boolean addUser(Map<String, String> user) {
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {
      String firstname = user.get("firstname");
      String lastname = user.get("lastname");
      String email = user.get("email");
      String username = user.get("username");
      String password = user.get("password");

      String sql = "INSERT INTO users(firstname, lastname, email, username, password) values('" + firstname + "','"
          + lastname + "','" + email + "','" + username + "','" + password + "');";

      int rowsAffected = stmt.executeUpdate(sql);
      return true;
    } catch (SQLException e) {
      System.out.println("Database initialization error: " + e.getMessage());
      return false;
    }
  }

  // ------------------------------ update user
  // --------------------------------------------
  public boolean updateUser(Map<String, String> user) {
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {

      String firstname = user.get("firstname");
      String lastname = user.get("lastname");
      String email = user.get("email");
      String username = user.get("username");
      String password = user.get("password");

      String sql = "UPDATE users SET firstname = '" + firstname + "', lastname = '" + lastname + "', email = '" + email
          + "', username = '" + username + "', password = '" + password + "' where username = '" + username + "';";
      int rowsAffected = stmt.executeUpdate(sql);
      if (rowsAffected > 0)
        return true;
    } catch (SQLException e) {
      System.out.println("Database initialization error: " + e.getMessage());
      return false;
    }
    return false;
  }

  // ------------------------------ delete user
  // --------------------------------------
  public boolean deleteUser(String id) {
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {
      String sql = "DELETE FROM users where id = '" + id + "';";
      int rowsAffected = stmt.executeUpdate(sql);

    } catch (SQLException e) {
      System.out.println("Database initialization error: " + e.getMessage());
      return false;
    }
    return true;
  }

  // ---------------------------------- user management
  // ------------------------------
  public ArrayList<String> getUserPassword(String user) {
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {
      String sql = "SELECT * from users where username = '" + user + "';";
      ArrayList<String> idAndPassword = new ArrayList<>();
      ResultSet res = stmt.executeQuery(sql);
      if (res.next()) {
        idAndPassword.add(res.getString("id"));
        idAndPassword.add(res.getString("password"));
        return idAndPassword;
      } else {
        return null;
      }
    } catch (SQLException e) {
      System.out.println("Database initialization error: " + e.getMessage());
      return null;
    }
  }

  public boolean saveQuizData(String id, int quizScore, String userAnswers, int numberOfItems) {
    String sql = "UPDATE users SET quizScore = ?, quizDate = datetime('now', 'localtime') WHERE id = ?;";
    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, quizScore);
      pstmt.setInt(2, Integer.parseInt(id));

      int rowsAffected = pstmt.executeUpdate();
      boolean isQuizAttemptSaved = saveQuizAttempt(id, quizScore, userAnswers, numberOfItems);
      return (rowsAffected > 0 && isQuizAttemptSaved);
    } catch (SQLException e) {
      System.out.println("Database initialization error: " + e.getMessage());
    }
    return false;
  }

  private boolean saveQuizAttempt(String id, int quizScore, String userAnswers, int totalQuestions) {
    String sql = "INSERT INTO quiz_attempts (user_id, score, total_questions, answers_concatenated) VALUES (?, ?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(DB_URL);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, Integer.parseInt(id));
      pstmt.setInt(2, quizScore);
      pstmt.setInt(3, totalQuestions);
      pstmt.setString(4, userAnswers);

      int rowsAffected = pstmt.executeUpdate();
      return (rowsAffected > 0);
    } catch (SQLException e) {
      System.out.println("Database initialization error: " + e.getMessage());
    }
    return false;
  }
}
