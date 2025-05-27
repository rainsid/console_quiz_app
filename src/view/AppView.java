package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import helper.ConsoleUtil;
import helper.Banner;

public class AppView {
  private Scanner sc = new Scanner(System.in);

  public int showMainMenu() {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println("1. Admin Login 👩‍💼💻🔑 ");
    System.out.println("2. User Login 👤💻");
    System.out.println("3. Exit 👋😊");
    return getIntInput("Enter the number of your choice");
  }

  // --------- admin methods ----------------
  public String enterAdminUsername() {
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.ADMIN_LOGIN);
    return getStringInput("Enter admin username: ");
  }

  public String getAdminPassword() {
    return getStringInput("Enter admin password: ");
  }

  // -------------- admin menu ------------------------
  public int showAdminMenu() {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.ADMIN_MENU);
    System.out.println("1. Manage Trainee");
    System.out.println("2. Manage Quiz");
    System.out.println("3. Go back to Main Menu");
    return getIntInput("Enter choice: ");
  }

  // ----------- User management ----------------
  public int showManageUserMenu() {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.MANAGE_USER);
    System.out.println("1. Add Trainee");
    System.out.println("2. View All Trainees");
    System.out.println("3. View a Trainee");
    System.out.println("4. Update Trainee");
    System.out.println("5. Delete Trainee");
    System.out.println("6. Go back to Admin Menu");
    return getIntInput("Enter number of your choice");
  }

  // ------------- input username and last name user -----------------
  public void inputUserInfo() {
    String firstname = getStringInput("Enter first name: ");
    String lastname = getStringInput("Enter last name: ");
  }

  // ------------------- add a user ----------------------------
  public Map<String, String> showAddUser() {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.ADD_TRAINEE);
    Map<String, String> user = new HashMap<>();
    System.out.print("First Name: ");
    user.put("firstname", sc.nextLine());
    System.out.print("Last Name: ");
    user.put("lastname", sc.nextLine());
    System.out.print("Email: ");
    user.put("email", sc.nextLine());
    System.out.print("Username: ");
    user.put("username", sc.nextLine());
    System.out.print("Password: ");
    user.put("password", sc.nextLine());

    return user;
  }

  // ------------------- update a user ----------------------------
  public Map<String, String> showUpdateUser(Map<String, String> user) {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.UPDATE_TRAINEE);
    Map<String, String> returnUser = new HashMap<>();
    System.out.println("\nEnter updated trainee details (leave blank to keep current):");
    System.out.print("First Name (" + user.get("firstname") + "): ");
    String firstname = sc.nextLine();
    System.out.print("Last Name (" + user.get("lastname") + "): ");
    String lastname = sc.nextLine();
    System.out.print("Email (" + user.get("email") + "): ");
    String email = sc.nextLine();
    System.out.print("Username(" + user.get("username") + "): ");
    String username = sc.nextLine();
    System.out.print("Password(" + user.get("password") + "): ");
    String password = sc.nextLine();

    returnUser.put("firstname", firstname.isEmpty() ? user.get("firstname") : firstname);
    returnUser.put("lastname", lastname.isEmpty() ? user.get("lastname") : lastname);
    returnUser.put("email", email.isEmpty() ? user.get("email") : email);
    returnUser.put("oldUsername", user.get("username"));
    returnUser.put("username", username.isEmpty() ? user.get("username") : username);
    returnUser.put("password", password.isEmpty() ? user.get("password") : password);

    return returnUser;
  }

  public boolean showDeleteUser(Map<String, String> user, int numberOfQuestions) {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.DELETE_TRAINEE);
    String yesNo = getStringInput("Are you sure you want to delete this trainee?\n(type 'yes' or 'no'): ");

    return yesNo.equals("yes");
  }

  // ------------- show all users -----------------
  public void showAllUsers(List<Map<String, String>> users, int numberOfQuestions) {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.SHOW_ALL_TRAINEES);
    for (Map<String, String> user : users) {
      System.out.println("Trainee ID: " + user.get("id"));
      System.out.println("First Name: " + user.get("firstname"));
      System.out.println("Last Name: " + user.get("lastname"));
      System.out.println("Email: " + user.get("email"));
      System.out.println("Username: " + user.get("username"));
      String score = (user.get("quizScore").equals("-1")) ? "N/A" : user.get("quizScore") + "/" + numberOfQuestions;
      System.out.println("Score: " + score);
      String quizDate = (user.get("quizDate") == null) ? "N/A" : user.get("quizDate");
      System.out.println("Quiz Date Taken: " + quizDate);
      System.out.println("--------------------------------------------------------------");
    }
    pressToContinue();
  }

  // ----------- search a single user ------------------------------
  public int showSearchBy() {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.SEARCH_BY);
    System.out.println("\nSearch Trainee By:");
    System.out.println("1. ID");
    System.out.println("2. Username");
    System.out.println("3. Email");
    System.out.println("4. Exit");
    return getIntInput("Enter choice: ");
  }

  // ----------------- search user by id --------------------------
  public int inputUserID() {
    System.out.print("Enter trainee id: ");
    return sc.nextInt();
  }

  // ----------------- search user by username--------------------------
  public String inputUsername() {
    System.out.print("Enter trainee: ");
    return sc.nextLine();
  }

  // ----------------- search user by email --------------------------
  public String inputUserEmail() {
    System.out.print("Enter email: ");
    return sc.nextLine();
  }

  public int inputUserChoice() {
    return getIntInput("Enter choice: ");
  }

  // ----------------- NO user found --------------------------
  public void showNoUserFound() {
    System.out.println("No trainee found");
  }

  // ----------------- Invalid Username/Email --------------------------
  public void showInvalidUsernameEmail() {
    System.out.println("Invalid email or password");
  }

  // ----------------- user found --------------------------
  public void showUser(Map<String, String> user, int numberOfQuestions) {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.TRAINEE_INFO);
    System.out.println("\n--------------------------------------------------------------");
    System.out.println("Trainee ID: " + user.get("id"));
    System.out.println("First Name: " + user.get("firstname"));
    System.out.println("Last Name: " + user.get("lastname"));
    System.out.println("Email: " + user.get("email"));
    System.out.println("Username: " + user.get("username"));
    String score = (user.get("quizScore").equals("-1")) ? "N/A" : user.get("quizScore") + "/" + numberOfQuestions;
    System.out.println("Score: " + score);
    String quizDate = (user.get("quizDate") == null) ? "N/A" : user.get("quizDate");
    System.out.println("Quiz Date Taken: " + quizDate);
    System.out.println("Date Account Registered: " + user.get("createdAt"));
    System.out.println("Date Account Updated : " + user.get("updatedAt"));
    System.out.println("--------------------------------------------------------------");

    pressToContinue();
    sc.nextLine();
  }

  // ----------- helper methods ----------------
  private String getStringInput(String prompt) {
    System.out.print(prompt);
    return sc.nextLine();
  }

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

  private void pressToContinue() {
    System.out.print("\nPress 'enter' to continue");
    sc.nextLine();
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

  public void showMessage(String message) {
    System.out.println("\n" + message);
  }
}
