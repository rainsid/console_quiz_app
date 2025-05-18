package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class QuizModel {
    private static final String DB_URL = "jdbc:sqlite:database";
    private static final String QUESTIONS_TABLE = "questions";
    private static final int NUMBER_OF_OPTIONS = 4;

    public QuizModel(){
        createQuestionsTableIfNotExists();
    }

    private void createQuestionsTableIfNotExists(){
        try(Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement()){
            String sql = "CREATE TABLE IF NOT EXISTS " + QUESTIONS_TABLE+ " (" +
                    "question_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "question_text TEXT NOT NULL," +
                    "option_a TEXT NOT NULL," +
                    "option_b TEXT NOT NULL," +
                    "option_c TEXT NOT NULL," +
                    "option_d TEXT NOT NULL," +
                    "correct_answer TEXT NOT NULL CHECK(correct_answer IN ('a', 'b', 'c', 'd'))" +
                    ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e){
            System.err.println("Error creating questions table: " + e.getMessage());
        }
    }

    public void addQuestion(){

    }
}
