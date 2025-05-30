package view;

import helper.Banner;
import helper.ConsoleUtil;
import Entities.Question;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UserView {
  private Scanner sc = new Scanner(System.in);

  // ---------------------- user menu -----------------------------
  public int showUserMenu(String username) {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.TRAINEE_MENU);
    System.out.println("Hey there, " + username + " 👋\n");
    System.out.println("1. Take Quiz");
    System.out.println("2. Quiz Attempts");
    System.out.println("3. Exit User");

    return getIntInput("Enter your choice");
  }

  // --------- enter username and password ----------------
  public String enterUserUsername() {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.TRAINEE_LOGIN);
    return getStringInput("Enter username: ");
  }

  public String getUserPassword() {
    return getStringInput("Enter password: ");
  }

  // ------------------------------- show Questions -----------------
  public Map<Integer, String> showQuestions(ArrayList<Question> questions) {
    Map<Integer, String> userAnswers = new HashMap<>();
    int questionNumber = 1;
    int numberOfQuestions = questions.size();

    for (Question q : questions) {
      ConsoleUtil.clearConsole();
      System.out.println(Banner.MAIN_BANNER);
      System.out.println(Banner.QUIZ_TIME);
      System.out.println("\nQuestion: " + questionNumber + "/" + numberOfQuestions + "\n");
      System.out.println(q.getQuestion());
      System.out.println("a. " + q.getOptionA());
      System.out.println("b. " + q.getOptionB());
      System.out.println("c. " + q.getOptionC());
      System.out.println("d. " + q.getOptionD());

      String answer = getStringInput("\nEnter the letter of your answer (a, b, c, d) : ").toLowerCase();
      while (!answer.matches("[abcd]")) {
        System.out.println("\nInvalid input. Please enter a, b, c, or d");
        answer = getStringInput("\nEnter the letter of your answer (a, b, c, d) : ").toLowerCase();
      }

      switch (answer) {
        case "a":
          answer = q.getOptionA();
          break;
        case "b":
          answer = q.getOptionB();
          break;
        case "c":
          answer = q.getOptionC();
          break;
        case "d":
          answer = q.getOptionD();
          break;
      }
      questionNumber++;
      userAnswers.put(q.getQuestionID(), answer);
    }

    return userAnswers;
  }

  public void showQuizDone() {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.QUIZ_TIME);
    System.out.println("\n\n🎉 Your done! Press Enter to view your result");
    sc.nextLine();
  }

  public void showResult(int quizScore, int numberOfItems, StringBuilder userAnswerString,
      ArrayList<Question> questions) {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.QUIZ_RESULT);

    System.out.println("Result: " + quizScore + " of " + numberOfItems + " ("
        + (double) quizScore / (double) numberOfItems * 100.00 + "%)");
    pressToContinue();
  }

  public void showQuizAttempts(ArrayList<Map<String, String>> quiz_attempts) {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.QUIZ_ATTEMPT);
    int attemptCounter = 0;
    for (Map<String, String> attempt : quiz_attempts) {
      System.out.println("-------------------------------------------------------");
      System.out.println("Attempt Number: " + ++attemptCounter);
      System.out.println("Attempt ID: " + attempt.get("attempt_id"));
      System.out.println("Score: " + attempt.get("score") + " of " + attempt.get("total_questions") + " ("
          + Double.parseDouble(attempt.get("score")) / Double.parseDouble(attempt.get("total_questions")) * 100.00
          + "%)");
      System.out.println("Attempt Date: " + attempt.get("attempt_date"));
      System.out.println("-------------------------------------------------------");
      // String[] answers = attempt.get("answers_concatenated").split(",");
      // for (String answer : answers) {
      // System.out.print(answer + " ");
      // }
    }
    pressToContinue();
  }

  // ----------------- helpers

  private int getIntInput(String prompt) {
    int input = -1;
    boolean validInput = false;

    while (!validInput) {
      System.out.print("\n" + prompt + "\n> ");
      if (sc.hasNextInt()) {
        input = sc.nextInt();
        sc.nextLine();
        validInput = true;
      } else {
        showError("Invalid input. Please enter a whole number.");
        sc.nextLine();
      }
    }

    return input;
  }

  public void showTraineeInfo(Map<String, String> trainee) {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.TRAINEE_INFO);
    System.out.println("\n--------------------------------------------------------------");
    System.out.println("Trainee ID: " + trainee.get("id"));
    System.out.println("First Name: " + trainee.get("firstname"));
    System.out.println("Last Name: " + trainee.get("lastname"));
    System.out.println("Email: " + trainee.get("email"));
    System.out.println("Username: " + trainee.get("username"));
    System.out.println("Date Account Registered: " + trainee.get("createdAt"));
    System.out.println("Date Account Updated : " + trainee.get("updatedAt"));
    System.out.println("--------------------------------------------------------------");

  }

  private String getStringInput(String prompt) {
    System.out.print(prompt);
    return sc.nextLine();
  }

  // ----------------------- Success and Fail message ------------------------
  public void showError(String errorMessage) {
    System.out.println("\n⚠️ " + errorMessage);
    pressToContinue();
  }

  public void showSuccess(String successMessage) {
    System.out.println("\n🎉 " + successMessage);
    pressToContinue();
  }

  // ------------- other helpers ------------------
  public void showMessage(String message) {
    System.out.println(message);
    pressToContinue();
  }

  private void pressToContinue() {
    System.out.print("Press 'enter' to continue.");
    sc.nextLine();
  }

}
