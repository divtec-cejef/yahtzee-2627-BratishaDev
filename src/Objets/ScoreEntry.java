package Objets;

public class ScoreEntry {

    private final Category category;
    private final int score;
    private final int[] diceValuesFinal;

    public ScoreEntry(Category category, DiceHand diceHand){
        this.category = category;
        score = category.Score(diceHand);
        diceValuesFinal = diceHand.getValuesDice();
    }

    public Category getCategory() {
        return category;
    }

    public int getScore() {
        return score;
    }

    public int[] getDiceValuesFinal() {
        return diceValuesFinal;
    }
}
