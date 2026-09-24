package Objets;

public class Player {

    private String nom;
    private Scorecard scorecard;

    public Player(String nom) {
        this.nom = nom;
        scorecard = new Scorecard();
    }

    public String getNom() {
        return nom;
    }

    public Scorecard getScorecard() {
        return scorecard;
    }
}
