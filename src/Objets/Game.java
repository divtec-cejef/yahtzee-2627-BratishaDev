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

        for (int round = 0; round < ROUNDS_LIMIT; round++) {
            consoleIO.displayNumberRounds(round);
            this.round.playRound();
            //consoleIO.displayOccurrences(diceHand.getNombreOccurrences());
            consoleIO.displayScore(diceHand, player.getScorecard());
            selectedCategory = consoleIO.categoryChoice(player.getScorecard().getAvailableCategories());
            player.getScorecard().saveScore(selectedCategory, diceHand);
            consoleIO.displayPlayerScore(player.getScorecard().getTotalScore(), round, ROUNDS_LIMIT);
            diceHand.resetFace2Paire();
        }
    }
}
