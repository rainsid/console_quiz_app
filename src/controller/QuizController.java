package controller;

import Entities.Question;
import model.QuizModel;
import view.QuizAdminView;

import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;

public class QuizController {
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
      quizAdminView.showMessage("Question successfully added!");
    } else {
      quizAdminView.showMessage("Adding question failed");
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
      quizAdminView.showMessage("No question found with ID " + id);
    } else {
      quizAdminView.showQuestion(question);
    }
  }

  // ----------------- delete a question --------------------
  public void deleteQuestion() {
    System.out.println("Delete a Question");
    int idToDelete = quizAdminView.showSearchQuestion();
    Question q = quizModel.searchQuestion(idToDelete);
    if (q == null) {
      quizAdminView.showMessage("No question with that ");
      return;
    }
    if (quizAdminView.showDeleteQuestion(q)) {
      if (quizModel.deleteQuestion(idToDelete)) {
        System.out.println("Deleted");
      } else {
        System.out.println("deletion failed");
      }
    }
  }

}
