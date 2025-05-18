package controller;

import Entities.Question;
import model.QuizModel;
import view.QuizAdminView;

import java.util.ArrayList;

public class QuizController {
    private QuizModel quizModel = new QuizModel();
    private QuizAdminView quizAdminView= new QuizAdminView();
    private Question question =  new Question();

    public void start(){
        while(true){
            int choice = quizAdminView.showQuizAdminMenu();
            if(choice == 5) break;
            switch(choice){
                case 1:
                    addQuestion();
                    break;
                default:
            }
        }
    }

    // ---------------------------- add question ----------------------------------
    private void addQuestion(){
        String qstn =  quizAdminView.showAddQuestion();
        ArrayList<String> options = quizAdminView.showAddOptions();
        String correctAnswer = quizAdminView.showAddCorrectAnswer(options);
        question.setQuestion(qstn);
        question.setChoices(options);
        question.setCorrectAnswer(correctAnswer);

        if(quizModel.addQuestion(question) > 0) {
            quizAdminView.showMessage("Question successfully added!");
        } else {
            quizAdminView.showMessage("Adding question failed");
        }
    }
}
