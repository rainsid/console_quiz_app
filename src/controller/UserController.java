package controller;

import Entities.Question;
import model.DatabaseManager;
import model.QuizModel;
import view.UserView;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {

  private final UserView userView = new UserView();
  private final DatabaseManager dbManager = new DatabaseManager();
  private final QuizModel quizModel = new QuizModel();
  private Question question;

  public UserController() throws SQLException {
    }

    // --------- handle user login ---------------
  public void handleUserLogin() {
    String username = userView.enterUserUsername();
    if (username == null || username.trim().isEmpty()) {
      userView.showError("Username cannot be empty");
      return;
    }
    String password = userView.getUserPassword();
    if (password == null || password.trim().isEmpty()) {
      userView.showError("Password cannot be empty");
      return;
    }
    String storedPassword = dbManager.getUserPassword(username);
    System.out.println("stored password: " + storedPassword);
    System.out.println("input password: " + password);
    if (password.equals(storedPassword)) {
      userView.showSuccess("User login successful");
      userMenu();
    } else {
      userView.showError("Invalid Username or Email");
    }
  }

  private void userMenu() {
      while(true) {
        int choice = userView.showUserMenu();
        switch(choice){
          case 1:
            takeQuiz();
            break;
        }
      }
  }

  private void takeQuiz(){
      ArrayList<Question> questions = quizModel.retrieveAllQuestions();
      if(questions == null){
        userView.showError("No questions stored in the database");
        return;
      }

  }

}
