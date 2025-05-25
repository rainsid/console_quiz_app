package view;

import Entities.Question;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserView {
  private Scanner sc = new Scanner(System.in);

  // ---------------------- user menu -----------------------------
  public int showUserMenu() {
    System.out.println("1. Take Quiz");
    System.out.println("2. View Result");
    System.out.println("3. Exit User");

    return getIntInput("Enter your choice");
  }

  // --------- enter username and password ----------------
  public String enterUserUsername() {
    return getStringInput("Enter username: ");
  }

  public String getUserPassword() {
    return getStringInput("Enter password: ");
  }

  // ------------------------------- show Questions -----------------
  public Map<Integer, String> showQuestions(ArrayList<Question> questions) {
    Map<Integer, String> userAnswers = new HashMap<>();

    for (Question q : questions) {
      System.out.println(q.getQuestion());
      System.out.println("a. " + q.getOptionA());
      System.out.println("b. " + q.getOptionB());
      System.out.println("c. " + q.getOptionC());
      System.out.println("d. " + q.getOptionD());

      String answer = getStringInput("Enter the letter of your answer (a, b, c, d) :").toLowerCase();
      while (!answer.matches("[abcd]")) {
        System.out.println("\nInvalid input. Please enter a, b, c, or d");
        answer = getStringInput("Enter the letter of your answer (a, b, c, d) :").toLowerCase();
      }
      userAnswers.put(q.getQuestionID(), answer);
    }

    return userAnswers;
  }

  // ----------------- helpers
  private int getIntInput(String prompt) {
    System.out.print(prompt);
    int input = sc.nextInt();
    sc.nextLine();

    return input;
  }

  private String getStringInput(String prompt) {
    System.out.print(prompt);
    return sc.nextLine();
  }

  // ----------------------- Success and Fail message ------------------------
  public void showError(String errorMessage) {
    System.out.println(errorMessage);
    pressToContinue();
  }

  public void showSuccess(String successMessage) {
    System.out.println(successMessage);
    pressToContinue();
  }

  private void pressToContinue() {
    System.out.print("Press 'enter' to continue.");
    sc.nextLine();
  }

  public void takeQuiz() {

  }
}
