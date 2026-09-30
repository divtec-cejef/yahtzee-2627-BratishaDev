package Objets;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleIO {

    /**
     * Affiche les dés et les chiffres qui sont sortis
     * @param diceHand dés à afficher
     */
    public void displayDice(int[] diceHand) {
        for (int index = 0; index < diceHand.length; index++) {
            System.out.println("Des numero " + (index + 1) + ": " + diceHand[index]);
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
     * @param availableCategories categories disponibles
     */
    public void displayScore(DiceHand diceHand, List<Category> availableCategories){
        String alignementDroite = "%2s\n";
        String alignementGauche = "%-17s";
        for (Category category : availableCategories) {
            System.out.printf(alignementGauche, (category.ordinal() + 1) + ") " + category.getName());
            System.out.printf(alignementDroite, category.Score(diceHand));
        }
        System.out.println();
    }

    /**
     * Demande à l'utilisateur relancer les dés
     * @return les indices de dés à relancer
     */
    public int[] requestReroll() {
        System.out.println("Indiquez les dés que vous souhaitez relancer (ou enter pour arrêter)");
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

    public void displayNumberRolls(int rolls) {
        System.out.println("Lancement: " + rolls + 1);
    }

    public void displayNumberRounds(int rounds) {
        System.out.println("Manche numero: " + (rounds + 1));
    }

    public void displayPlayerScore(int playerScore) {
        System.out.println("Votre score: " + playerScore + "\n");
    }

}
