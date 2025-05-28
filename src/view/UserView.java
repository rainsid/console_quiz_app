package view;

import helper.Banner;
import helper.ConsoleUtil;
import Entities.Question;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserView {
  private Scanner sc = new Scanner(System.in);

  // ---------------------- user menu -----------------------------
  public int showUserMenu() {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.TRAINEE_MENU);
    System.out.println("1. Take Quiz");
    System.out.println("2. View Result");
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

  public void showResult()

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
