package Objets;

public class YahtzeeOOApp {

    ConsoleIO consoleIO = new ConsoleIO();
    Game game = new Game(consoleIO);

    public static void main(String[] args) {
        ConsoleIO consoleIO = new ConsoleIO();
        Game game = new Game(consoleIO);
        game.startGame();
    }
}
