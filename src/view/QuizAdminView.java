package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class QuizAdminView {
    private Scanner sc = new Scanner(System.in);

    public int showQuizAdminMenu(){
        System.out.println();
        System.out.println("Main Menu: ");
        System.out.println("1. Add question");
        System.out.println("2. Edit question");
        System.out.println("3. Edit question");
        System.out.println("4. Delete question");
        System.out.println("5. Exit");
        return getIntInput("Enter choice: ");
    }

    //----------------------- Add question -------------------------
    public String showAddQuestion(){
        System.out.print("Enter question: ");
        return sc.nextLine().trim();
    }

    //----------------------- Add choices to the question -------------------------
    public ArrayList<String> showAddOptions(){
        ArrayList<String> options = new ArrayList<>();
        char letter = 'A';
        for(int i=0; i < 4; i++){
            System.out.print("Enter choice " + letter + " : ");
            letter++;
            options.add(sc.nextLine().trim().toLowerCase());
        }

        return options;
    }

    // ---------------------- add correct answer ---------------------
    public String showAddCorrectAnswer(ArrayList<String> options){
        String correctAnswer;
        do {
            System.out.print("Enter correct answer: ");
            correctAnswer = sc.nextLine().trim().toLowerCase();
        } while (!options.contains(correctAnswer.toLowerCase()));

        return correctAnswer;
    }

    public  void showMessage(String message){
        System.out.println(message);
    }

    // ------------------ helper -------------------------
    private int getIntInput(String prompt) {
        System.out.print(prompt);
        int input = sc.nextInt();
        sc.nextLine();

        return input;
    }
}
