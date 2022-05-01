package com.jcnetwork.android.app1.models;

public class Brainteaser {

    // Variables
    private String btTitel;
    private String btQuestion;
    private String btSolution;

    /**
     * Constructor
     * @param title thema of the brainteaser
     * @param question or task of the brainteaser
     * @param solution of how to get the answer or simply the short answer of the brainteaser
     */
    public Brainteaser(String title, String question, String solution) {
        btQuestion = question;
        btSolution = solution;
        btTitel = title;
    }

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
    public String getTitel() {
        return btTitel;
    }

    public String getTeaser() {
        return btQuestion;
    }

    public String getResolution() {
        return btSolution;
    }


}
