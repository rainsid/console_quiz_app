package controller;

import model.DatabaseManager;
import view.AppView;
import view.QuizAdminView;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppController {
    private AppView view;
    private DatabaseManager dbManager;
    private QuizAdminView quizAdminView;
    private QuizController quizController;

    public AppController(AppView view, QuizController quizController, DatabaseManager dbManager){
        this.view = view;
        this.quizController= quizController;
        this.dbManager = dbManager;
    }

    public void start(){
        while(true){
            int choice = view.showMainMenu();
            switch(choice){
                case 1:
                    handleAdminLogin();
                    break;
                default:
                    System.exit(0);
            }
        }
    }

    // --------- handle admin login ---------------
    private void handleAdminLogin(){
        String username = view.enterAdminUsername();
        String password = view.getAdminPassword();
        if(password.equals(dbManager.getAdminPassword(username))){
            adminMenu();
        } else {
            view.showInvalidUsernameEmail();
        }

    }

    // -------------- admin menu ----------------
    private void adminMenu(){
        while(true){
            int choice = view.showAdminMenu();
            if(choice == 3) break;
            switch(choice){
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
    public void manageUsers(){
        int choice = view.showManageUserMenu();
        switch(choice){
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
    // --------- view single user -----------
    private Map<String, String> viewUser(){
        int choice =  view.showSearchBy();
        String identifierType = "";
        String identifier = "";

        switch (choice){
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
        if(user == null){
            view.showNoUserFound();
            return null;
        }

        view.showUser(user);
        return user;
    }

    // -------------------------- Update user -----------------------------------------
    private void updateUser(){
        Map<String, String> user = viewUser();
        if(user != null){
            user = view.showUpdateUser(user);
            boolean isUpdated = dbManager.updateUser(user);
            if(isUpdated){
                view.showSuccessMessage("Success! User has been updated!");
            } else {
                view.showFailMessage("Update failed!");
            }
        }
    }

    private void deleteUser(){
        Map<String, String> user = viewUser();
        boolean isDeleted = false;

        if(user != null){
            if(view.showDeleteUser(user)){
               isDeleted = dbManager.deleteUser(user.get("id"));
            }
            if(isDeleted){
                view.showSuccessMessage("Success! User has been deleted!");
            } else {
                view.showFailMessage("Deletion failed!");
            }
        }

    }

    // ------------------------ view users ---------------------------
    private void viewUsers(){
         view.showAllUsers(dbManager.getAllUsers());
    }

    private void addUser(){
        Map<String, String> user = view.showAddUser();
        boolean isAdded = dbManager.addUser(user);
        if (isAdded) {
            view.showUserAdded();
        } else {
            view.showUserNotAdded();
        }
    }

    // -------------------- manage quiz ------------------------------------
    public void manageQuiz(){
        quizController.start();
    }
    // helper methods

}
