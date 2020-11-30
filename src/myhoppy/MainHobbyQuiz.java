/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhoppy;



import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Atheer Hamad
 */
public class MainHobbyQuiz extends Quiz{
    private Map<String,Integer> scores;

   //to get the scores saved based on the user's choice
    public Map<String, Integer> getScores() {
        return scores;
    }

    //to set the scores based on the user's choice
    public void setScores(Map<String, Integer> scores) {
        this.scores = scores;
    }

    //method setQuiz is to set up a quiz question and options for the all hobby quiz 
    // @Override to write the method with different internal implementation
    //@param fileName to take the filename passed from the main method
    @Override
    public  void setQuiz(String fileName){
        
        //declare and initialize a score counter for each of the hobbies to be incremented later
        int drawing_score = 0;
        int writing_score = 0;
        int reading_score = 0;
        int playing_sport_score = 0;
        int cooking_score = 0;
        int learning_languages_score = 0;
        int photography_score = 0;
        int graphics_score = 0;
        int sewing_score = 0;
        int growing_plants_score = 0;
        
        //to temparory save the options entered by the user along with their scores
         HashMap<String,Integer> userOption=new HashMap<String,Integer>();
         
        //try and catch block to catch any exception happens during reading the file  
        try{
            
            //define a scannner to read the quiz file
            Scanner sc = new Scanner(new File(fileName));
            
            // Check that the file is not empty
            File f = new File(fileName);
            assert f.length() >0 : "empty file";
            
            //to hold the question text value
            String question = "";
            
            //looping over the file until having no more lines to be read
            while (sc.hasNextLine()) {
            
                //to organize the options saved inside the quiz file as string for the hobby 
                //represented by the option and the option text it self
                //make use of linked hash map as it preserve the order of input
                LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();

                //check if it is the beginning of a new question 
                if(sc.nextLine().equals("nextQ")){
                    
                   question = sc.nextLine();
                   //use the super class method to save the question value
                   setQuestion(question);
                   //printing out the question to the user
                   System.out.println(question);
                   //to set up the number of options for each question to be equal to 10 and inrement it each time we read an option
                   int numOfOptions = 0;
                   
                    //looping out over the options for each question
                   while(numOfOptions<10){ 
                        //to save the score in terms of the hobby name represented by the option
                        String score=sc.nextLine();
                        //to save the option text 
                        String option=sc.nextLine();
                        //to save option with it's score
                        options.put(score, option);

                        numOfOptions++;
                   }
                    
                   //use the super class method to save the individual question options
                   setOptions(options);
                   
                   //printing out the option to the user
                   for (String op : options.keySet()){ 
                        System.out.println(options.get(op));  
                   }
                   
                   //to take the user choice input as an integer
                   // use try and catch to handle non integer values
                   Scanner scanner = new Scanner(System.in);
                   
                   try{
                   int user_input = scanner.nextInt();
                   
                   // Check if the user's input is valid
                   assert (user_input >= 1 && user_input <= 10) :"Invalid input" ; 
                   
                   //check which hobby the user option alignnwith and increment the score of that hobby
                   //for the simplicity, we saved the options in the file in a fixed order as represented below 
                   switch(user_input){
                        case 1 : 
                           userOption.put("drawing",++drawing_score);
                            break;
                        case 2 : 
                           userOption.put("writing",++writing_score);
                            break;
                        case 3 :
                           userOption.put("reading", ++reading_score);
                            break;
                        case 4 :
                           userOption.put("playing_sport",++playing_sport_score);
                            break;
                        case 5 :
                            userOption.put("cooking",++cooking_score);
                            break;
                        case 6 :
                            userOption.put("learning_languages",++learning_languages_score);
                            break;
                        case 7 :
                            userOption.put("photography",++photography_score);
                            break;
                        case 8 :
                            userOption.put("graphics",++graphics_score);
                            break;
                        case 9 :
                            userOption.put("sewing",++sewing_score);
                            break;
                        case 10 :
                            userOption.put("growing_plants",++growing_plants_score);
                            break;
                    }
                   }
                   catch(InputMismatchException exception){
                       System.out.println("Only integer values 1 and 2 are correct inputs");
                       System.exit(0);
                   }
                }

            }
            
            //set the scores value after incrementing the hobbies score 
            setScores(userOption);
            
            //close reading the file process
            sc.close();
        
        }
        //to catch and print any error may happen during reading the file
        catch (IOException e) {
            e.printStackTrace();
        }
      
    }
    
    //this method is designed to order the scores hashmap in descending order based on the value,the score of the hobby
    public  Map<String, Integer> sortByValue(Map<String, Integer> map) {
        //converting the hashmap to a list to apply the collection methods on it
        List list = new LinkedList(map.entrySet());
        
        //use collection sort method and override it to order with descending order
        Collections.sort(list, new Comparator() {
            //compare each two enteries values and return the largest
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                 .compareTo(((Map.Entry) (o2)).getValue());
             }
        });

        //to store the new ordered map
        Map result = new LinkedHashMap();
        //iterate over the ordered list and store the values in hashmap
        for (Iterator it = list.iterator(); it.hasNext();) {
           Map.Entry entry = (Map.Entry)it.next();
           result.put(entry.getKey(), entry.getValue());
        }
        
        //return the sorted hashmap
        return result;
    }
    
    //calculateResult method takes produces the final score of the qall hobbies uiz
    // @Override to write the method with different internal implementation
    @Override
    public void calculateResult(){
        
        //to hold the value after calling sortByValue method on the scores attribute
        Map<String,Integer> sortedScores=sortByValue(getScores());
      
        //looping over the sorted map to return the first entry ,the largest hobby in score
        for (Map.Entry mapElement : sortedScores.entrySet()) { 
            
            //to save the hobby name of the largest score
            String key = (String)mapElement.getKey(); 
  
            //to save the score 
            int value = (int)mapElement.getValue(); 
            
            //printing out the result to the user in percentage format
            System.out.println("You appeared to be more interested in "+key + " and your score is  " + (value/10)*100); 
            break;
        }
 
     }

}
    