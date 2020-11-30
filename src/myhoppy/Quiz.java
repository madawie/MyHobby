/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhoppy;


import java.util.Map;

/**
 *
 * @author Atheer Hamad
 */
/*this class acts as the super class with properties quiestion and answers to be inhereted by the subclasses
and abstract methods to be override with the concrete classes and making the super class abstract consequently 
*/

abstract public class Quiz {
    
    private String question;
    //to map the answers with their scores
    private Map options;

    //to get the questions text
    public String getQuestion() {
        return question;
    }

    //to set the question text
    public void setQuestion(String quiestion) {
        this.question = quiestion;
    }

    //to get the options related to a question
    public Map getOptions() {
        return options;
    }

    //to set the options along with their scores
    public void setOptions(Map options) {
        this.options = options;
    }

    //abstract method calculateResult to calculate the score for each quiz type
    public abstract void calculateResult();
    //abstract method setQuiz to read the quiz question and options from a file and display it to the user
    public abstract void setQuiz(String fileName);

  
}
