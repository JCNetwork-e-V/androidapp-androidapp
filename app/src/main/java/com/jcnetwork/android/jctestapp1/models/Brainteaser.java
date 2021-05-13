package com.jcnetwork.android.jctestapp1.models;

public class Brainteaser {

    // Variables
    private String btQuestion;
    private String btSolution;

    /**
     * Constructor
     * @param question or task of the brainteaser
     * @param solution of how to get the answer or simply the short answer of the brainteaser
     */
    public Brainteaser(String question, String solution) {
        btQuestion = question;
        btSolution = solution;
    }

    // Getter Methods
    public String getTeaser() {
        return btQuestion;
    }

    public String getResolution() {
        return btSolution;
    }


}
