package view;

import Entities.Question;
import helper.Banner;
import helper.ConsoleUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class QuizAdminView {
  private Scanner sc = new Scanner(System.in);

  public int showQuizAdminMenu() {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.MANAGE_QUIZ);
    System.out.println("1. Add question");
    System.out.println("2. Update question");
    System.out.println("3. Retrieve all questions");
    System.out.println("4. Delete question");
    System.out.println("5. Go back to Admin Menu");
    return getIntInput("Enter choice: ");
  }

  // ----------------------- Add question -------------------------
  public String showAddQuestion() {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.ADD_QUESTION);
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
      options.add(sc.nextLine().trim());
    }

    return options;
  }

  // ---------------------- add correct answer ---------------------
  public String showAddCorrectAnswer(ArrayList<String> options) {
    String correctAnswer;
    do {
      System.out.print("Enter correct answer: ");
      correctAnswer = sc.nextLine().trim();
    } while (!options.contains(correctAnswer));

    return correctAnswer;
  }

  // ---------------------- show all questions ----------------------------
  public void showAllQuestions(ArrayList<Question> allQuestions) {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.UPDATE_QUESTION);

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
    pressToContinue();
  }

  // -------------------------------- search a question -----------------------
  public int showSearchQuestion() {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.SEARCH_QUESTION);
    System.out.print("Enter question id: ");
    return sc.nextInt();
  }

  // -------------------------------- show Question ------------------------------
  public void showQuestion(Question q) {
    ConsoleUtil.clearConsole();
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

  // -------------------------------- show update question
  // --------------------------
  public Question showUpdateQuestion(Question q) {
    sc.nextLine();
    ConsoleUtil.clearConsole();
    System.out.println(Banner.MAIN_BANNER);
    System.out.println(Banner.UPDATE_QUESTION);
    System.out.println("\nEnter updated question details (leave blank to keep current):");
    System.out.print("Enter question: (" + q.getQuestion() + "): ");
    String question = sc.nextLine();
    System.out.print("Enter Option A: (" + q.getOptionA() + "): ");
    String optionA = sc.nextLine();
    System.out.print("Enter Option B: (" + q.getOptionB() + "): ");
    String optionB = sc.nextLine();
    System.out.print("Enter Option C: (" + q.getOptionC() + "): ");
    String optionC = sc.nextLine();
    System.out.print("Enter Option D: (" + q.getOptionD() + "): ");
    String optionD = sc.nextLine();
    System.out.print("Enter Correct Answer: (" + q.getCorrectAnswer() + "): ");
    String correctAnswer = sc.nextLine();
    ArrayList<String> options = new ArrayList<>();

    if (optionA.isEmpty()) {
      options.add(q.getOptionA());
    } else {
      options.add(optionA);
    }

    if (optionB.isEmpty()) {
      options.add(q.getOptionB());
    } else {
      options.add(optionB);
    }

    if (optionC.isEmpty()) {
      options.add(q.getOptionC());
    } else {
      options.add(optionC);
    }

    if (optionD.isEmpty()) {
      options.add(q.getOptionD());
    } else {
      options.add(optionD);
    }

    q.setChoices(options);
    q.setQuestion(question.isEmpty() ? q.getQuestion() : question);
    q.setCorrectAnswer(correctAnswer.isEmpty() ? q.getCorrectAnswer() : correctAnswer);
    q.setCorrectAnswer(correctAnswer.isEmpty() ? q.getCorrectAnswer() : correctAnswer);

    return q;
  }

  // -------------------------------- show delete question
  // --------------------------
  public boolean showDeleteQuestion(Question q) {
    ConsoleUtil.clearConsole();
    System.out.println(Banner.DELETE_QUESTION);
    showQuestion(q);
    char yesNo = getYesNoInput("Are you sure you want to delete this question?(y/n): ");
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

  // ----------------------- Success and Fail message ------------------------
  public void showError(String errorMessage) {
    System.out.println("\n⚠️" + errorMessage);
    pressToContinue();
  }

  public void showSuccess(String successMessage) {
    System.out.println("\n🎉" + successMessage);
    pressToContinue();
  }

  private void pressToContinue() {
    System.out.print("\nPress 'enter' to continue");
    sc.nextLine();
  }
}
