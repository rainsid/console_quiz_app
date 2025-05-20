package model;

import Entities.Question;

import java.lang.classfile.CodeBuilder.CatchBuilder;
import java.sql.*;
import java.util.ArrayList;

public class QuizModel {
  private static final String DB_URL = "jdbc:sqlite:database";
  private static final String QUESTIONS_TABLE = "questions";
  private static final int NUMBER_OF_OPTIONS = 4;
  private Question question = new Question();

  public QuizModel() {
    createQuestionsTableIfNotExists();
  }

  private void createQuestionsTableIfNotExists() {
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {
      String sql = "CREATE TABLE IF NOT EXISTS " + QUESTIONS_TABLE + " (" +
          "question_id INTEGER PRIMARY KEY AUTOINCREMENT," +
          "question_text TEXT NOT NULL," +
          "option_a TEXT NOT NULL," +
          "option_b TEXT NOT NULL," +
          "option_c TEXT NOT NULL," +
          "option_d TEXT NOT NULL," +
          "correct_answer TEXT NOT NULL" +
          ")";
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      System.err.println("Error creating questions table: " + e.getMessage());
    }
  }

  // --------------------------- add user ----------------------------------
  public int addQuestion(Question question) {
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {

      String question_text = question.getQuestion();
      String option_a = question.getOptionA();
      String option_b = question.getOptionB();
      String option_c = question.getOptionC();
      String option_d = question.getOptionD();
      String correctAnswer = question.getCorrectAnswer();

      String sql = "INSERT INTO questions(question_text, option_a, option_b, option_c, option_d, correct_answer) values('"
          + question_text + "','" + option_a + "','" + option_b + "','" + option_c + "','" + option_d + "','"
          + correctAnswer + "');";

      int rowsAffected = stmt.executeUpdate(sql);
      return rowsAffected;
    } catch (SQLException e) {
      System.out.println("error: " + e.getMessage());
    }
    return 0;
  }

  // ---------------------------------- retrieve all questions
  // -------------------------
  public ArrayList<Question> retrieveAllQuestions() {
    ArrayList<Question> allQuestions = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {

      String sql = "SELECT * FROM questions;";
      ResultSet res = stmt.executeQuery(sql);

      while (res.next()) {
        Question question = new Question();
        ArrayList<String> options = new ArrayList<>();
        options.add(res.getString("option_a"));
        options.add(res.getString("option_b"));
        options.add(res.getString("option_c"));
        options.add(res.getString("option_d"));

        question.setQuestion(res.getString("question_text"));
        question.setQuestionID(res.getInt("question_id"));
        question.setChoices(options);
        question.setCorrectAnswer(res.getString("correct_answer"));

        allQuestions.add(question);
      }

    } catch (SQLException e) {
      System.out.println("error: " + e.getMessage());
      return null;
    }
    return allQuestions;
  }

  public Question searchQuestion(int id) {
    Question question = new Question();
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {

      String sql = "SELECT * FROM questions where question_id = '" + id + "';";
      ResultSet res = stmt.executeQuery(sql);

      if (!res.next()) {
        return null;
      }

      ArrayList<String> options = new ArrayList<>();
      options.add(res.getString("option_a"));
      options.add(res.getString("option_b"));
      options.add(res.getString("option_c"));
      options.add(res.getString("option_d"));

      question.setQuestionID(res.getInt("question_id"));
      question.setQuestion(res.getString("question_text"));
      question.setChoices(options);
      question.setCorrectAnswer(res.getString("correct_answer"));

    } catch (SQLException e) {
      System.out.println("error: " + e.getMessage());
    }
    return question;
  }

  // ---------------------------- delete a question ---------------------------
  public boolean deleteQuestion(int id) {
    try (Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement()) {

      String sql = "DELETE FROM questions where question_id = '" + id + "';";
      int rowsAffected = stmt.executeUpdate(sql);

    } catch (SQLException e) {
      System.out.println("error: " + e.getMessage());
      return false;
    }

    return true;
  }
}
