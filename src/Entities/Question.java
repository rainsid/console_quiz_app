package Entities;

import java.util.ArrayList;

public class Question {
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public String getOptionA(){
        return choices.get(0);
    }

    public String getOptionB(){
        return choices.get(1);
    }

    public String getOptionC(){
        return choices.get(2);
    }

    public String getOptionD(){
        return choices.get(3);
    }
    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    private String question;
    private ArrayList<String> choices;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    private String correctAnswer;

}
