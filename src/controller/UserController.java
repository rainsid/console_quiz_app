package controller;

import Entities.Question;
import model.DatabaseManager;
import model.QuizModel;
import view.UserView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

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
    ArrayList<String> idAndPassword = dbManager.getUserPassword(username);
    if (idAndPassword == null) {
      userView.showError("Username does not exist");
      return;
    }
    if (password.equals(idAndPassword.get(1))) {
      userView.showSuccess("User login successful");
      userMenu(idAndPassword.get(0));
    } else {
      userView.showError("Invalid Username or Email");
    }
  }

  private void userMenu(String id) {
    while (true) {
      int choice = userView.showUserMenu();
      if (choice == 3)
        break;
      switch (choice) {
        case 1:
          takeQuiz(id);
          break;
      }
    }
  }

  private void takeQuiz(String id) {
    ArrayList<Question> questions = quizModel.retrieveAllQuestions();
    if (questions == null || questions.isEmpty()) {
      userView.showError("No questions stored in the database");
      return;
    }
    Map<Integer, String> userAnswers = userView.showQuestions(questions);

    int numberOfItems = questions.size();
    int counter = 0;
    int quizScore = 0;

    StringBuilder userAnswersString = new StringBuilder();
    for (Map.Entry<?, ?> entry : userAnswers.entrySet()) {
      if (questions.get(counter).getCorrectAnswer().equals(entry.getValue())) {
        quizScore++;
      }
      userAnswersString.append(entry.getValue()).append(",");
      counter++;
    }
    if (userAnswersString.length() > 0) {
      userAnswersString.deleteCharAt(userAnswersString.length() - 1);
    }
    if (dbManager.saveQuizData(id, quizScore, String.valueOf(userAnswersString), numberOfItems)) {
      userView.showResult();
    } else {
      userView.showError("Quiz data not saved!");
    }
  }

}
