package Objets;

/**
 * Classe qui représente et gère une main de dés
 */
public class DiceHand {

    private final int NUMBER_OF_DICE = 5;
    private Die[] dice = new Die[NUMBER_OF_DICE];
    private int faceBrelant = 0;
    private int faceCarre = 0;
    private int face1Paire = 0;
    private int face2Paire = 0;


    public DiceHand() {
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            dice[i] = new Die();
        }
    }

    public void rollDice(){
        for (Die die : dice) {
            die.roll();
        }
    }

    public void reroll(int[] UserChoice){
        for (int indexChoice : UserChoice) {
            dice[indexChoice].roll();
        }
    }

    public int[] getValuesDice() {
        int[] values = new int[NUMBER_OF_DICE];
        for (int index = 0; index < dice.length; index++) {
            values[index] = dice[index].getVisibleSide();
        }
        return values;
    }

    /**
     * Calcule le nombre d'occurrences
     * @return nombre d'occurrences de chaque nombre
     */
    public int[] getNombreOccurrences(){
        int[] occurrences = new int[dice[0].getSIDE_COUNT()];
         for (Die die : dice) {
             occurrences[die.getVisibleSide() - 1]++;
         }
        return occurrences;
    }

    /**
     * Vérification de la combinaison d'une paire.
     * @param occurrences les occurrences de chaque nombre
     * @return true s'il y a une paire, false sinon
     */
    public boolean estUnePaire(int[] occurrences){
        for (int occurrence : occurrences) {
            if (occurrence >= 2) {
                return true;
            }
        }
        return false;

    }

    /**
     * Vérification de la présence d'une combinaison de DEUX paires.
     * @param occurrences les occurrences de chaque nombre
     * @return true s'il y a deux pairs, false sinon
     */
    public boolean estDeuxPaires(int[] occurrences){
        int paires = 0;
        for (int face = 0; face < occurrences.length ;face++) {
            if (occurrences[face] >= 2) {
                paires++;
                if (paires == 1)
                    face1Paire = face;
                else if (paires == 2) {
                    face2Paire = face;
                }
            }
        }
        return paires == 2;
    }

    /**
     * Vérification de la présence d'une combinaison « Brelan »
     * @param occurrences les occurrences de chaque nombre
     * @return true s'il y a un Brelan, false sinon
     */
    public boolean estBrelan(int[] occurrences) {
        for (int face = 0; face < occurrences.length; face++) {
            if (occurrences[face] >= 3) {
                faceBrelant = face;
                return true;
            }
        }
        return false;
    }

    /**
     * Vérification de la présence d'une combinaison « Carré »
     * @param occurrences les occurrences de chaque nombre
     * @return true s'il y a un Carré, false sinon
     */
    public boolean estCarre(int[] occurrences) {
        for (int face = 0; face < occurrences.length; face++) {
            if (occurrences[face] >= 4) {
                faceCarre = face;
                return true;
            }
        }
        return false;
    }

    /**
     * Vérification de la présence d'une combinaison « Full House »
     * @param occurrences les occurrences de chaque nombre
     * @return true s'il y a un Full House, false sinon
     */
    public boolean estFullHouse(int[] occurrences) {
        return estBrelan(occurrences) && estUnePaire(occurrences) && (face1Paire != faceBrelant || face2Paire > 0);
    }

    /**
     * Vérification de la présence d'une combinaison « Yahtzee »
     * @param occurrences les occurrences de chaque nombre
     * @return true s'il y a un Yahtzee, false sinon
     */
    public boolean estYahtzee(int[] occurrences) {
        for (int occurrence : occurrences) {
            if (occurrence == 5) {
                return true;
            }
        }
        return false;
    }


    /**
     * Vérification de la présence d'une combinaison « Petite Suite »
     * @param occurrences les occurrences de chaque nombre
     * @return true s'il y a une Petite Suite, false sinon
     */
    public boolean estPetiteSuite(int[] occurrences) {
        int compteurSuite = 0;
        int compteurSuiteMax = 0;
        for (int occurrence : occurrences) {
            if (occurrence >= 1) {
                compteurSuite ++;
                if(compteurSuiteMax < compteurSuite) {
                    compteurSuiteMax = compteurSuite;
                }
            } else {
                compteurSuite = 0;
            }
        }
        return compteurSuiteMax >= 4;
    }

    /**
     * Vérification de la présence d'une combinaison « Grande Suite »
     * @param occurrences les occurrences de chaque nombre
     * @return true s'il y a une Grande Suite, false sinon
     */
    public boolean estGrandeSuite(int[] occurrences) {
        int compteurSuite = 0;
        int compteurSuiteMax = 0;
        for (int occurrence : occurrences) {
            if (occurrence == 1) {
                compteurSuite ++;
                if(compteurSuiteMax < compteurSuite) {
                    compteurSuiteMax = compteurSuite;
                }
            } else {
                compteurSuite = 0;
            }
        }
        return compteurSuiteMax == 5;
    }

    public int getSumCarre() {
        return faceCarre * 4;
    }

    public int getSumBrelant() {
        return faceBrelant * 3;
    }

}
