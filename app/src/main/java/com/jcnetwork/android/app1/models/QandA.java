package com.jcnetwork.android.app1.models;

/**
 * An object to hold a question and answer string for FAQ hidden activity
 */

public class QandA {

    // Variables
    private final String mQuestion;
    private final String mAnswer;

    /**
     * Constructor for Q&A Object
     */
    public QandA(String question,
                 String answer) {
        mQuestion = question;
        mAnswer = answer;
    }

    // Getter Methods
    public String getQuestion() {
        return mQuestion;
    }
    public String getAnswer() {
        return mAnswer;
    }

}
