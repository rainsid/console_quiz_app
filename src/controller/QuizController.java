package controller;

import model.QuizModel;
import view.QuizAdminView;

import java.util.ArrayList;

public class QuizController {
    private QuizModel quizModel = new QuizModel();
    private QuizAdminView quizAdminView= new QuizAdminView();

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

    private void addQuestion(){
        String question =  quizAdminView.showAddQuestion();
        ArrayList<String> choices = new ArrayList<>();
        choices = quizAdminView.showAddChoices();
        quizModel.addQuestion();
    }

}
