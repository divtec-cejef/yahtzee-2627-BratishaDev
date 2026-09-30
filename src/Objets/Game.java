package Objets;

public class Game {
    private Player player = new Player("Andrey");
    private ConsoleIO consoleIO;
    private DiceHand diceHand = new DiceHand();
    private final int ROUNDS_LIMIT = 5;
    private Round round;

    Game(ConsoleIO consoleIO){
        this.consoleIO = consoleIO;
        this.round = new Round(consoleIO, diceHand);
    }

    public void startGame(){
        round.playRound();
        consoleIO.displayOccurrences(diceHand.getNombreOccurrences());
        consoleIO.displayScore(diceHand, player.getScorecard());
    };
}
