package Objets;

import java.util.List;
import java.util.Scanner;

public class ConsoleIO {

    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String YELLOW = "\u001B[33m";
    private final String BLUE = "\u001B[34m";
    private final String PURPLE = "\u001B[35m";
    private final String CYAN = "\u001B[36m";
    private final String GRAY = "\033[38;5;245m";
    /**
     * Affiche les dés et les chiffres qui sont sortis
     * @param diceHand dés à afficher
     */
    public void displayDice(int[] diceHand) {
        for (int index = 0; index < diceHand.length; index++) {
            System.out.print(GRAY + " " + (index + 1) + "  " + RESET);
        }
        System.out.println();
        for (int faceDie : diceHand) {
            System.out.print(CYAN + "[" + faceDie + "] " + RESET);
        }

        System.out.println();
    }

    /**
     * Affiche des occurrences
     * @param occurrances occurrences à afficher
     */
    public void displayOccurrences(int[] occurrances) {
        for (int index = 0; index < occurrances.length; index++) {
            System.out.println("Occurrences de la face " + (index + 1) + ": " + occurrances[index]);
        }
        System.out.println();
    }

    /**
     * Affiche les scores des combinaisons
     * @param diceHand dés de tour
     * @param scorecard la feuille de score complète
     */
    public void displayScore(DiceHand diceHand, Scorecard scorecard){
        String alignementDroite = "%2s\n";
        String alignementGauche = "%-17s";
        String color;
        for (Category category : Category.values()) {
            color = scorecard.isAvailable(category) ? GREEN : GRAY;
            String texteFormatte = String.format(alignementGauche, (category.ordinal() + 1) + ") " + category.getName());
            System.out.printf(alignementGauche, color + texteFormatte + RESET);
            System.out.printf(alignementDroite, category.Score(diceHand));
        }
        System.out.println();
    }

    /**
     * Demande à l'utilisateur relancer les dés
     * @return les indices de dés à relancer
     */
    public int[] requestReroll() {
        System.out.println(YELLOW + "Indiquez les dés que vous souhaitez relancer (ou enter pour arrêter):" + RESET);
        Scanner scanner = new Scanner(System.in);
        String choixUtilisateur = scanner.nextLine();
        String[] ChoixUtilisateur = choixUtilisateur.split(" ");
        int[] indiceDes = new int[ChoixUtilisateur.length];
        if (!choixUtilisateur.isEmpty()) {
            for(int index = 0; index < ChoixUtilisateur.length; index++){
                indiceDes[index] = Integer.parseInt(ChoixUtilisateur[index]) - 1;
            }
        } else {
            return new int[0];
        }
        return indiceDes;
    }

    /**
     * Demande à l'utilisateur de choisir une des combinaisons disponibles pour prendre le score de cette combinaison
     * @return choix de combinaison
     */
    public Category categoryChoice(List<Category> availableCategories) {
        Scanner scanner = new Scanner(System.in);
        Category selectedCategory;
        boolean valide = false;
        do {
            System.out.println(YELLOW + "Choisissez le numéro correspondant à l'une des combinaisons disponibles: " + RESET);
            selectedCategory = Category.values()[(Integer.parseInt(scanner.nextLine())) - 1];;
            if (availableCategories.contains(selectedCategory)){
                valide = true;
            }
        } while (!valide);
        return selectedCategory;
    }

    /**
     * Affiche le nombre de lancements
     * @param rolls le nombre de lancements
     */
    public void displayNumberRolls(int rolls) {
        System.out.println(BLUE + "Lancement: " + rolls + RESET);
    }

    /**
     * Affiche le nombre de manches
     * @param rounds le nombre de manches
     */
    public void displayNumberRounds(int rounds) {
        System.out.println(BLUE + "Manche numero: " + (rounds + 1) + RESET);
    }

    /**
     * Affiche le score d'un joueur
     * @param playerScore le score d'un joueur
     */
    public void displayPlayerScore(int playerScore, int round, int roundsLimit) {
        if (round < roundsLimit - 1){
            System.out.println(PURPLE + "Votre score: " + playerScore + "\n" + RESET);
        } else {
            System.out.println(PURPLE + "Votre score final: " + playerScore + "\n" + RESET);
        }

    }

}
