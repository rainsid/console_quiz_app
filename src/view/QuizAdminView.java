package view;

import Entities.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class QuizAdminView {
  private Scanner sc = new Scanner(System.in);

  public int showQuizAdminMenu() {
    System.out.println();
    System.out.println("Main Menu: ");
    System.out.println("1. Add question");
    System.out.println("2. Update question");
    System.out.println("3. Retrieve all questions");
    System.out.println("4. Delete question");
    System.out.println("5. Exit");
    return getIntInput("Enter choice: ");
  }

  // ----------------------- Add question -------------------------
  public String showAddQuestion() {
    System.out.print("Enter question: ");
    return sc.nextLine().trim();
  }

  // ----------------------- Add choices to the question -------------------------
  public ArrayList<String> showAddOptions() {
    ArrayList<String> options = new ArrayList<>();
    char letter = 'A';
    for (int i = 0; i < 4; i++) {
      System.out.print("Enter choice " + letter + " : ");
      letter++;
      options.add(sc.nextLine().trim().toLowerCase());
    }

    return options;
  }

  // ---------------------- add correct answer ---------------------
  public String showAddCorrectAnswer(ArrayList<String> options) {
    String correctAnswer;
    do {
      System.out.print("Enter correct answer: ");
      correctAnswer = sc.nextLine().trim().toLowerCase();
    } while (!options.contains(correctAnswer.toLowerCase()));

    return correctAnswer;
  }

  // ---------------------- show all questions ----------------------------
  public void showAllQuestions(ArrayList<Question> allQuestions) {
    int questionNumber = 0;
    for (Question q : allQuestions) {
      System.out.println("---------------------------------------------------------------");
      System.out.println("Question #: " + ++questionNumber);
      System.out.println("Question database ID: " + q.getQuestionID());
      System.out.println(q.getQuestion());
      System.out.println("A. " + q.getOptionA());
      System.out.println("B. " + q.getOptionB());
      System.out.println("C. " + q.getOptionC());
      System.out.println("D. " + q.getOptionD());
      System.out.println("Correct Answer: " + q.getCorrectAnswer());
      System.out.println("---------------------------------------------------------------");
    }
    System.out.println();
  }

  // -------------------------------- search a question -----------------------
  public int showSearchQuestion() {
    System.out.print("Enter question id: ");
    return sc.nextInt();
  }

  // -------------------------------- show Question ------------------------------
  public void showQuestion(Question q) {
    System.out.println("---------------------------------------------------------------");
    System.out.println("Question database ID: " + q.getQuestionID());
    System.out.println(q.getQuestion());
    System.out.println("A. " + q.getOptionA());
    System.out.println("B. " + q.getOptionB());
    System.out.println("C. " + q.getOptionC());
    System.out.println("D. " + q.getOptionD());
    System.out.println("Correct Answer: " + q.getCorrectAnswer());
    System.out.println("---------------------------------------------------------------");
  }

  // -------------------------------- show delete question
  // --------------------------
  public boolean showDeleteQuestion(Question q) {
    showQuestion(q);
    char yesNo = getYesNoInput("Are you sure you want to delete this question?(y/n)");
    return (yesNo == 'y' || yesNo == 'Y') ? true : false;
  }

  public void showMessage(String message) {
    System.out.println(message);
  }

  // ------------------ helper get Yes/No input -------------------------
  private char getYesNoInput(String prompt) {
    System.out.print(prompt);
    char input = sc.next().charAt(0);

    return input;
  }

  // ------------------ helper -------------------------
  private int getIntInput(String prompt) {
    System.out.print(prompt);
    int input = sc.nextInt();
    sc.nextLine();

    return input;
  }
}
