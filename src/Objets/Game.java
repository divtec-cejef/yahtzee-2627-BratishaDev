package Objets;

public class Game {
    private Player player;
    private ConsoleIO consoleIO;
    private DiceHand diceHand = new DiceHand();
    private final int ROUNDS_LIMIT = 5;
    private Round round;

    Game(ConsoleIO consoleIO){
        player = new Player("Andrey");
        this.consoleIO = consoleIO;
        this.round = new Round(consoleIO, diceHand);
    }

    public void startGame(){
        Category selectedCategory;
        int numberRounds = 0;
        for (int manche = 0; manche < ROUNDS_LIMIT; manche++) {
            consoleIO.displayNumberRounds(numberRounds);
            round.playRound();
            //consoleIO.displayOccurrences(diceHand.getNombreOccurrences());
            consoleIO.displayScore(diceHand, player.getScorecard());
            selectedCategory = consoleIO.categoryChoice(player.getScorecard().getAvailableCategories());
            player.getScorecard().saveScore(selectedCategory, diceHand);
            consoleIO.displayPlayerScore(player.getScorecard().getTotalScore(), numberRounds, ROUNDS_LIMIT);
            diceHand.resetFace2Paire();
            numberRounds++;
        }
    }
}
