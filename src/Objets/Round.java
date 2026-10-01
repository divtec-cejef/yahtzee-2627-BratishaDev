package Objets;

public class Round {
    private ConsoleIO consoleIO;
    private DiceHand diceHand;
    private final int ROLLS_LIMIT = 2;


    public Round(ConsoleIO consoleIO, DiceHand diceHand) {
        this.consoleIO = consoleIO;
        this.diceHand = diceHand;
    }

    public void playRound() {
        // Premier lancement de dés
        diceHand.rollDice();
        consoleIO.displayNumberRolls(1);
        consoleIO.displayDice(diceHand.getValuesDice());

        // Relancement optionnel de dés
        for (int reroll = 0; reroll < ROLLS_LIMIT; reroll++) {
            int[] userChoice = consoleIO.requestReroll();
            if (userChoice.length != 0) {
                diceHand.reroll(userChoice);
            } else {
                break;
            }
            consoleIO.displayNumberRolls(reroll + 2);
            consoleIO.displayDice(diceHand.getValuesDice());
        }


    }
}
