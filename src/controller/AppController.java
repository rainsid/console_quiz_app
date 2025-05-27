package controller;

import helper.Banner;
import helper.ConsoleUtil;
import model.DatabaseManager;
import model.QuizModel;
import view.AppView;
import view.QuizAdminView;
import view.UserView;

import java.util.Map;
import java.util.Scanner;

public class AppController {
  private AppView view;
  private QuizAdminView quizAdminView;
  private UserView userView;
  private UserController userController;
  private QuizController quizController;
  private DatabaseManager dbManager;
  private Scanner consumeLine = new Scanner(System.in);

  public AppController(AppView view, QuizController quizController, UserController userController,
      DatabaseManager dbManager) {
    this.view = view;
    this.quizController = quizController;
    this.dbManager = dbManager;
    this.userController = userController;
  }

  public void start() {
    while (true) {
      int choice = view.showMainMenu();
      switch (choice) {
        case 1:
          handleAdminLogin();
          break;
        case 2:
          handleUserLogin();
          break;
        case 3:
          System.out.println("Byeeee!");
          System.exit(0);
      }
    }
  }

  // --------- handle admin login ---------------
  private void handleAdminLogin() {
    ConsoleUtil.clearConsole();
    String username = view.enterAdminUsername();
    if (username == null || username.trim().isEmpty()) {
      view.showError("Username cannot be empty");
      return;
    }
    String password = view.getAdminPassword();
    if (password == null || password.trim().isEmpty()) {
      view.showError("Password cannot be empty");
      return;
    }
    String storedPassword = dbManager.getAdminPassword(username);

    if (password.equals(storedPassword)) {

      view.showSuccess("Admin login successful");
      adminMenu();
    } else {
      view.showError("Invalid Username or Email");
    }
  }

  // --------- handle user login ---------------
  private void handleUserLogin() {
    userController.handleUserLogin();
  }

  // -------------- admin menu ----------------
  private void adminMenu() {
    while (true) {
      int choice = view.showAdminMenu();
      if (choice == 3)
        break;
      switch (choice) {
        case 1:
          manageUsers();
          break;
        case 2:
          manageQuiz();
          break;
        default:
          System.out.println("Invalid input");
      }
    }
  }

  // --------- manage users ---------------
  public void manageUsers() {
    while (true) {
      int choice = view.showManageUserMenu();
      if (choice == 6)
        break;
      switch (choice) {
        case 1:
          addUser();
          break;
        case 2:
          viewUsers();
          break;
        case 3:
          viewUser();
          break;
        case 4:
          updateUser();
          break;
        case 5:
          deleteUser();
          break;
      }
    }
  }

  // --------- view single user -----------
  private Map<String, String> viewUser() {
    int choice = view.showSearchBy();
    String identifierType = "";
    String identifier = "";

    switch (choice) {
      case 1: {
        int id = view.inputUserID();
        identifier = Integer.toString(id);
        identifierType = "id";
        break;
      }
      case 2: {
        identifier = view.inputUsername();
        identifierType = "username";
        break;
      }
      case 3: {
        identifier = view.inputUserEmail();
        identifierType = "email";
        break;
      }
    }
    Map<String, String> user = dbManager.findUser(identifier, identifierType);
    if (user == null) {
      view.showError("Trainee not found");
      consumeLine.nextLine();
      return null;
    }

    QuizModel quizModel = new QuizModel();
    int numberOfQuestions = quizModel.retrieveAllQuestions().size();
    view.showUser(user, numberOfQuestions);
    return user;
  }

  // -------------------------- Update user
  // -----------------------------------------
  private void updateUser() {
    Map<String, String> user = viewUser();
    if (user != null) {
      user = view.showUpdateUser(user);
      boolean isUpdated = dbManager.updateUser(user);
      if (isUpdated) {
        view.showSuccess("Success! User has been updated!");
      } else {
        view.showError("Update failed!");
      }
    }
  }

  private void deleteUser() {
    Map<String, String> user = viewUser();
    boolean isDeleted = false;

    QuizModel quizModel = new QuizModel();
    int numberOfQuestions = quizModel.retrieveAllQuestions().size();

    if (user != null ) {
      if (view.showDeleteUser(user, numberOfQuestions)) {
        isDeleted = dbManager.deleteUser(user.get("id"));
      } else {
        view.showMessage("Trainee deletion cancelled.");
      }
      if (isDeleted) {
        view.showSuccess("Success! User has been deleted!");
      } else {
        view.showError("Deletion failed!");
      }
    }

  }

  // ------------------------ view users ---------------------------
  private void viewUsers() {
    QuizModel quizModel = new QuizModel();
    int numberOfQuestions = quizModel.retrieveAllQuestions().size();
    view.showAllUsers(dbManager.getAllUsers(), numberOfQuestions);
  }

  private void addUser() {
    Map<String, String> user = view.showAddUser();
    if (user.get("firstname").isEmpty() || user.get("lastname").isEmpty() ||
        user.get("email").isEmpty() || user.get("username").isEmpty() || user.get("password").isEmpty()) {
      view.showError("Please fill in all the fields");
      return;
    }
    boolean isAdded = dbManager.addUser(user);
    if (isAdded) {
      view.showSuccess("Success! User added");
    } else {
      view.showError("Eeeengk! User not added");
    }
  }

  // -------------------- manage quiz ------------------------------------
  private void manageQuiz() {
    quizController.start();
  }
}
