package Objets;

public enum Category {

    PAIRE ("Paire") { @Override public int Score(DiceHand hand){ return hand.estUnePaire() ? 5 : 0; } },
    DEUX_PAIRS ("Deux paires"){ @Override public int Score(DiceHand hand){ return hand.estDeuxPaires() ? 10: 0;}},
    BRELAN ("Brelan") { @Override public int Score(DiceHand hand){ return hand.estBrelan() ? hand.getSumBrelan() : 0;}},
    CARRE ("Carré") { @Override public int Score(DiceHand hand){ return hand.estCarre() ? hand.getSumCarre(): 0;}},
    FULL_HOUSE ("Full House") { @Override public int Score(DiceHand hand){ return hand.estFullHouse() ? 25 : 0;}},
    PETITE_SUITE ("Petite suite") { @Override public int Score(DiceHand hand){ return hand.estPetiteSuite() ? 30 : 0;}},
    GRANDE_SUITE ("Grande suite") { @Override public int Score(DiceHand hand){ return hand.estGrandeSuite() ? 40 : 0;}},
    YAHTZEE ("Yahtzee") { @Override public int Score(DiceHand hand){ return hand.estYahtzee() ? 50 : 0;}};

    public abstract int Score(DiceHand hand);
    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
