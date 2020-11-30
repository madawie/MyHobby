/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhoppy;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * @author sanasultan
 */

/*this class manipulate the individual hobby quiz by allowing
the user to attempt one of 5 quizez for five distinct hobbies
*/
public class IndividualHobbyEvaluationQuiz extends Quiz{
    private String hobbyName;
    private ArrayList enteredChoices;
    
    //class constructor initiates the hobby name
    public IndividualHobbyEvaluationQuiz(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public ArrayList getEnteredChoices() {
        return enteredChoices;
    }

    public void setEnteredChoices(ArrayList enteredChoices) {
        this.enteredChoices = enteredChoices;
    }

   
    //method setQuiz is to set up a quiz question and options for the individual hobby quiz 
    // @Override to write the method with different internal implementation
    //@param fileName to take the filename passed from the main method
    @Override
    public void setQuiz(String fileName){
        
        // Check that the file is not empty
        File f = new File(fileName);
        assert f.length() >0 : "empty file";
        
        //adding a unified set of options for all questions using a LinkedHashMap as it preserve the order of the input
        LinkedHashMap<String, Integer> options = new LinkedHashMap<>();
        
        //assigning score for each option to be calculated later
        options.put("Never",0);
        options.put("Very Rarely",1);
        options.put("Rarely",2);
        options.put("Occasionally",3);
        options.put("Very Frequently",4);
        options.put("Always",5);
     
        //to save the choices entered by the user
        ArrayList<Integer> enteredChoices=new ArrayList<Integer>();

        //try and catch block to catch any exception happens during reading the file  
	try{
            
            //creating a new file object to read from a file
            File hobbyQuestionsOptionsFile=new File(fileName);      
            Scanner scanner = new Scanner(hobbyQuestionsOptionsFile);
            
            //to choose the question related to the initiated hobby name
            String hobbyName=scanner.nextLine();
            
            //to store the question text retrieved from the file
            String question;
            
            System.out.println("Quiz started\n\n\nNote the following \nEnter a choice , 0 for the first choice, 1"
                                 + " for the second choice, 2 for the third choice, 3 for the "
                                 + "forht choice, 4 for the fifth,and 5 for the sixth choice\n\n\n\n"); 
            
            //to loop over the file content
            while (scanner.hasNextLine()) {
              
               //to retrieved the question related to the hobby only 
                if(hobbyName.equals(this.hobbyName)){
                    
                    question=scanner.nextLine();
                    //use the super class method to store the question 
                    setQuestion(question);
                    //use the super class methods to store the options 
                    setOptions(options);
                    
                    //to display the questions and options to the user
                    System.out.println(getQuestion());
                    for (String name : options.keySet()){  
                        System.out.println(name); 
                    }
                    
                    
                    Scanner sc= new Scanner(System.in); 
                    
                    //take a choice input from the user
                    // use try and catch to handle non integer values
                    try{
                    int choice= sc.nextInt();
                    
                    // Make sure that the user entered a valid value
                    assert (choice>=0 && choice<=5) : "Invalid input";
                    
                    //store the entered choice in the enteredChoices list
                    enteredChoices.add(choice);
                    }
                    catch(InputMismatchException exception){
                       System.out.println("Only integer values 1 and 2 are correct inputs");
                       System.exit(0);
                   }
                }
                
                //to check the next hobby name value 
                hobbyName=scanner.nextLine();
            }
            setEnteredChoices(enteredChoices);
           
            //to close reading file process
            scanner.close();
            }catch (IOException e) {
		e.printStackTrace();
            }
        //return the user answers to be analyzed later
        //return enteredChoices;
         
    }
    
    
    //calculateResult method takes produces the final score of the individual hobby quiz
    // @Override to write the method with different internal implementation
    @Override
    public void calculateResult(){
        //to initialize the score 
        int initScore=0;
        ArrayList<Integer> userChoices=enteredChoices;
        
        //to loop over the user choices and sum up the scores corresponding to the options defined above
        //we made the user entered choices to be equal to the option scores for the simplicity of the calculation
        for(int i=0;i<userChoices.size();i++){
            
            initScore+=userChoices.get(i); 
        }
        
        //printing the user score in percentage format
        System.out.println("You are engaged in "+hobbyName+" by "+(initScore/25.0)*100);
        
    }  
}