package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import ConsoleColor.TerminalColors;

public class AppView {
    private Scanner sc = new Scanner(System.in);
    private TerminalColors tc;

    public int showMainMenu() {
        System.out.println();
        System.out.println(tc.BLUE + tc.WHITE_BG + "Main Menu: " + tc.RESET);
        System.out.println("1. Admin Login👨🏻");
        System.out.println("2. User Registration");
        System.out.println("3. User Login");
        System.out.println("4. Exit");
        return getIntInput("Enter choice: ");
    }

    // --------- admin methods ----------------
    public String enterAdminUsername() {
        return getStringInput("Enter admin username: ");
    }

    public String getAdminPassword() {
        return getStringInput("Enter admin password: ");
    }

    // -------------- admin menu ------------------------
    public int showAdminMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Manage Users");
        System.out.println("2. Mange quiz");
        System.out.println("3. Exit");
        return getIntInput("Enter choice: ");
    }

    // ----------- User management ----------------
    public int showManageUserMenu() {
        System.out.println("\nManage User:");
        System.out.println("1. Add User");
        System.out.println("2. View All Users");
        System.out.println("3. View a User");
        System.out.println("4. Update User");
        System.out.println("5. Delete User");
        System.out.println("6. Exit");
        return getIntInput("Enter choice: ");
    }

    // ------------- input username and last name user -----------------
    public void inputUserInfo() {
        String firstname = getStringInput("Enter first name: ");
        String lastname = getStringInput("Enter last name: ");
    }

    // ------------------- add a user ----------------------------
    public Map<String, String> showAddUser() {
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
    public Map<String, String> showUpdateUser(Map<String, String> user ) {
        Map<String, String> returnUser = new HashMap<>();
        sc.nextLine();
        System.out.println("\nEnter updated user details (leave blank to keep current):");
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

        returnUser.put("firstname", firstname.isEmpty()?user.get("firstname"):firstname);
        returnUser.put("lastname", lastname.isEmpty()?user.get("lastname"):lastname);
        returnUser.put("email", email.isEmpty()?user.get("email"):email);
        returnUser.put("username", username.isEmpty()?user.get("username"):username);
        returnUser.put("password", password.isEmpty()?user.get("password"):password);

        return returnUser;
    }
    // ----------------- show "user not added" ------------------
    public void showUserNotAdded() {
        System.out.println(tc.RED + tc.BLACK_BG + "Error: User not added" + tc.RESET);
    }

    // ----------------- show "user added" ------------------
    public void showUserAdded() {
        System.out.println("Success: User added");
    }

    // ------------- show all users -----------------
    public void showAllUsers(List<Map<String, String>> users) {
        for (Map<String, String> user : users) {
            System.out.println("First Name: " + user.get("firstname"));
            System.out.println("Last Name: " + user.get("lastname"));
            System.out.println("Email: " + user.get("email"));
            System.out.println("Username: " + user.get("username"));
            System.out.println("Score: " + user.get("quizScore"));
            System.out.println("Quiz Date Taken: " + user.get("quizDate"));
            System.out.println("--------------------------------------------------------------");
        }
    }

    // ----------- search a single user ------------------------------
    public int showSearchBy() {
        System.out.println("\nSearch User By:");
        System.out.println("1. ID");
        System.out.println("2. Username");
        System.out.println("3. Email");
        System.out.println("4. Exit");
        return getIntInput("Enter choice: ");
    }

    // ----------------- search user by id --------------------------
    public int inputUserID() {
        System.out.print("Enter user id: ");
        return sc.nextInt();
    }

    // ----------------- search user by username--------------------------
    public String inputUsername() {
        System.out.print("Enter username: ");
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
        System.out.println("No user found");
        pressToContinue();
    }

    // ----------------- Invalid Username/Email --------------------------
    public void showInvalidUsernameEmail() {
        System.out.println(tc.RED + tc.BLACK_BG + "Invalid email or password" + tc.RESET);
        pressToContinue();
    }

    // ----------------- user found --------------------------
    public void showUser(Map<String, String> user) {
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("First Name: " + user.get("firstname"));
        System.out.println("Last Name: " + user.get("lastname"));
        System.out.println("Email: " + user.get("email"));
        System.out.println("Username: " + user.get("username"));
        System.out.println("Score: " + user.get("quizScore"));
        System.out.println("Quiz Date Taken: " + user.get("quizDate"));
        System.out.println("--------------------------------------------------------------");
    }

    // ----------------------- Success and Fail message ------------------------
    public void showSuccessMessage(String message){
        System.out.println(message);
    }

    public void showFailMessage(String message){
        System.out.println(message);
    }

    // ----------- helper methods ----------------
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        int input = sc.nextInt();
        sc.nextLine();

        return input;
    }

    private void pressToContinue() {
        System.out.print("Press 'enter' to continue.");
        sc.nextLine();
    }

}
