/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhoppy;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Atheer Hamad
 */
public class MyHoppy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
      
        
            while(true){
            
            try{
                //display an introduction statement 
                System.out.println("\n\n\n\n\n\nWelcome to Hobby app\nThis app helps you find more about your hobby through two types of assessments"
                     + "\n\n1-Individual hobby assessment\n2-All hobbies assessment\n\nplease choose one ,"
                     + " enter 1 for the first choice or 2 for the second choice, or 3 to exit");

                //to take the user input
                Scanner sc= new Scanner(System.in);

                //to choose the quiz type
                int quizTypechoice= sc.nextInt();

                //if the choice is made for the individual quiz
                if(quizTypechoice ==1){

                    System.out.println("you can choice one of the following hobbies to "
                             + "be assessed with\n1-Reading\n2-Photography\n3-Writing\n4-Sports\n5-Cooking ");

                    //to choose which hobby related quiz to be attempted
                    int hobbyQuizChoice= sc.nextInt(); 

                    String hobbyName="";
                    //assigning the hobbyname based on the user choice
                    switch(hobbyQuizChoice){

                        case 1:
                            hobbyName="Reading";
                            break;
                        case 2:
                            hobbyName="Photography";
                            break;
                        case 3:
                            hobbyName="Writing";
                            break;
                        case 4:
                            hobbyName="Sports";
                            break;
                        case 5:
                            hobbyName="Cooking";
                            break;
                        default:
                            System.out.println("invalid input");
                            System.exit(0);

                    }

                    /*to define a concrete class of type IndividualHobbyQuiz from a 
                    more general one of type Quiz to be able to call the concrete methods implementation depending on 
                    IndividualHobbyQuiz. this is an application of polymorphism 
                    */
                    Quiz individualQuiz=new IndividualHobbyEvaluationQuiz(hobbyName);

                    //calling setQuiz method from the IndividualHobbyQuiz class to set up the quiz and passign the individualQuiz file 
                    individualQuiz.setQuiz("individualQuiz.txt");

                    //calling calculateResult method from the IndividualHobbyQuiz class to calculate the final score of the user 
                    individualQuiz.calculateResult();

                }
            //if the user chose to be assessed in all hobbies, general quiz for all hobbies
                else if(quizTypechoice ==2){

                    /*to define a concrete class of type AllHobbiesQuiz from a 
                    more general one of type Quiz to be able to call the concrete methods implementation.
                    this is an application of polymorphism 
                    */
                    Quiz allHobbiesQuiz =new MainHobbyQuiz();

                    //calling setQuiz method from the AllHobbiesQuiz class to set up the quiz and passign the mainquiz file 
                    allHobbiesQuiz.setQuiz("mainQuiz.txt");
                    //calling calculateResult method from the AllHobbiesQuiz class to set up the quiz
                    allHobbiesQuiz.calculateResult();

                }
                //if the user wants to exit
                else if(quizTypechoice==3){
                    System.out.println("See you soon");

                    //to terminate the running program
                    System.exit(0);  
                }
                //if the user enteres invalid choice,other than 1 or 2
                else{
                    System.out.println("you entered wrong answer");

                    //to terminate the running program
                    System.exit(0);
                }

        
   
            }catch(InputMismatchException exception){
            System.out.println("Only integer values 1 ,2, and 3 are correct inputs");
            }
        
        }
      
    }  
        
}
