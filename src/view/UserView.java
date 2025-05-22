package view;

import java.util.Scanner;

public class UserView {
  private Scanner sc = new Scanner(System.in);

  // ---------------------- user menu -----------------------------
  public int showUserMenu() {
    System.out.println("1. Take Quiz");
    System.out.println("2. View Result");

    return getIntInput("Enter your choice");
  }

  // --------- user options----------------

  public String enterUserUsername() {
    return getStringInput("Enter username: ");
  }

  public String getUserPassword() {
    return getStringInput("Enter password: ");
  }

  // helper

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
}
