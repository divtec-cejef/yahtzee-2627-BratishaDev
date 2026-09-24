package Objets;
import java.util.Map;
import java.util.EnumMap;
import java.util.List;
import java.util.ArrayList;

public class Scorecard {

    private Map<Category, ScoreEntry> entries = new EnumMap<>(Category.class);

    public void saveScore(Category category, DiceHand diceHand){
        ScoreEntry entry = new ScoreEntry(category,diceHand);
        entries.put(category, entry);
    }

    public int getTotalScore(){
        int totalScore = 0;
        for (ScoreEntry entry : entries.values()){
            totalScore += entry.getScore();
        }
        return totalScore;
    }

    public List<Category> getAvailableCategories () {
        List<Category> available = new ArrayList<>();
        for (Category category : Category.values()){
            if (!entries.containsKey(category)) {
                available.add(category);
            }
        }
        return available;
    }
}
