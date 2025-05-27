package controller;

import java.util.Scanner;

import Entities.Question;
import model.QuizModel;
import view.QuizAdminView;

import java.util.ArrayList;

public class QuizController {
  private Scanner sc = new Scanner(System.in);
  private QuizModel quizModel = new QuizModel();
  private QuizAdminView quizAdminView = new QuizAdminView();
  private Question question = new Question();

  public void start() {
    while (true) {
      int choice = quizAdminView.showQuizAdminMenu();
      if (choice == 5)
        break;
      switch (choice) {
        case 1:
          addQuestion();
          break;
        case 2:
          updateAQuestion();
          break;
        case 3:
          retrieveAllQuestions();
          break;
        case 4:
          deleteQuestion();
          break;
        default:
      }
    }
  }

  // ---------------------------- add question ----------------------------------
  private void addQuestion() {
    String qstn = quizAdminView.showAddQuestion();
    ArrayList<String> options = quizAdminView.showAddOptions();
    String correctAnswer = quizAdminView.showAddCorrectAnswer(options);
    question.setQuestion(qstn);
    question.setChoices(options);
    question.setCorrectAnswer(correctAnswer);

    if (quizModel.addQuestion(question) > 0) {
      quizAdminView.showSuccess("Question successfully added!");
      sc.nextLine();
    } else {
      quizAdminView.showError("Adding question failed");
      sc.nextLine();
    }
  }

  // --------------------------- retrieve all questions
  // -----------------------------
  public void retrieveAllQuestions() {
    ArrayList<Question> allQuestions = quizModel.retrieveAllQuestions();
    quizAdminView.showAllQuestions(allQuestions);
  }

  // --------------------------- update question -----------------
  public void updateAQuestion() {
    int id = quizAdminView.showSearchQuestion();
    Question question = quizModel.searchQuestion(id);
    if (question == null) {
      quizAdminView.showError("No question found with ID " + id);
      sc.nextLine();
    } else {
      quizAdminView.showQuestion(question);
      Question q = quizAdminView.showUpdateQuestion(question);
      if (quizModel.updateQuestion(q)) {
        quizAdminView.showSuccess("Question successfully updated!");
      } else {
        quizAdminView.showError("Question update failed");
      }
    }
  }

  // ----------------- delete a question --------------------
  public void deleteQuestion() {
    int idToDelete = quizAdminView.showSearchQuestion();
    Question q = quizModel.searchQuestion(idToDelete);
    if (q == null) {
      quizAdminView.showError("No question found with ID " + idToDelete);
      sc.nextLine();
      return;
    }
    if (quizAdminView.showDeleteQuestion(q)) {
      if (quizModel.deleteQuestion(idToDelete)) {
        quizAdminView.showSuccess("Question deleted");
        sc.nextLine();
      } else {
        quizAdminView.showError("Question deletion failed");
        sc.nextLine();
      }
    }
  }

}
