//import jdk.swing.interop.SwingInterOpUtils;

import java.util.Objects;
import java.util.Scanner;

public class YahtzeeProcedural {

    static final int MAX_NOMBRE = 6;
    static final int MAX_DES = 5;
    static final int LIMITE_LANCEMENT = 2;
    static final int SCORE_UNE_PAIRE = 5;
    static final int SCORE_DEUX_PAIRE = 10;
    static final int SCORE_FULL_HOUSE = 25;
    static final int SCORE_PETITE_SUITE = 30;
    static final int SCORE_GRANDE_SUITE = 40;
    static final int SCORE_YAHTZEE = 50;

    static int lancement(int maxNombre) {
        return (int)(Math.random() * maxNombre + 1);
    }

    static void affichageDes(int[] des) {
        for (int index = 0; index < des.length; index++) {
            System.out.println("Des numero " + (index + 1) + ": " + des[index]);
        }
    }

    static void affichageOccurrences(int[] occurrances) {
        for (int index = 0; index < occurrances.length; index++) {
            System.out.println("Occurrences de la face " + (index + 1) + ": " + occurrances[index]);
        }
    }

    static void affichageScore(int[] occurrances){
        String alignementDroite = "%2s\n";
        String alignementGauche = "%-15s";
        System.out.printf(alignementGauche,"Une paire: ");
        if(unePaire(occurrances)){
            System.out.printf(alignementDroite,calculScore(occurrances));
        } else {
            System.out.printf(alignementDroite,"0");
        }
        System.out.printf(alignementGauche,"Deux paires: ");
        if(deuxPaires(occurrances)){
            System.out.printf(alignementDroite,calculScore(occurrances));
        } else {
            System.out.printf(alignementDroite,"0");
        }
        System.out.printf(alignementGauche,"Brelan: ");
        if(estBrelan(occurrances)){
            System.out.printf(alignementDroite,calculScore(occurrances));
        } else {
            System.out.printf(alignementDroite,"0");
        }
        System.out.printf(alignementGauche,"Carré: ");
        if(estCarre(occurrances)){
            System.out.printf(alignementDroite,calculScore(occurrances));
        } else {
            System.out.printf(alignementDroite,"0");
        }
        System.out.printf(alignementGauche,"Full House: ");
        if(estFullHouse(occurrances)){
            System.out.printf(alignementDroite,calculScore(occurrances));
        } else {
            System.out.printf(alignementDroite,"0");
        }
        System.out.printf(alignementGauche,"Petite Suite: ");
        if(estPetiteSuite(occurrances)){
            System.out.printf(alignementDroite,calculScore(occurrances));
        } else {
            System.out.printf(alignementDroite,"0");
        }
        System.out.printf(alignementGauche,"Grande Suite: ");
        if(estGrandeSuite(occurrances)){
            System.out.printf(alignementDroite,calculScore(occurrances));
        } else {
            System.out.printf(alignementDroite,"0");
        }
        System.out.printf(alignementGauche,"Yahtzee: ");
        if(estYahtzee(occurrances)){
            System.out.printf(alignementDroite,calculScore(occurrances));
        } else {
            System.out.printf(alignementDroite,"0");
        }
    }

    static int[] demandeRelancer() {
        System.out.println("Indiquez les dés que vous souhaitez relancer (ou « rien » pour arrêter)");
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

    static void relancemet(int[] des, int[] choixUtilisateur){
        for(int indexDes = 0; indexDes < des.length; indexDes++){
            for (int indexChoix : choixUtilisateur) {
                if (indexDes == indexChoix) {
                    des[indexChoix] = lancement(des.length);
                }
            }
        }
    }

    static int[] nombreOccurrences(int[] des, int maxNombre){
        int[] occurrences = new int[maxNombre];
        for (int index = 0 ; index < des.length; index ++){
            occurrences[des[index] - 1] ++;
        }
        return occurrences;
    }
    /*
        static String combinaison(int[] occurrences){
            boolean unePaire = false;
            boolean troisIdentiques = false;
            int compteur = 0;
            String resultatNull = "";
            for (int index = 0; index < occurrences.length; index++){
                if (occurrences[index] == 5){
                    return "Yahtzee";
                } else if (occurrences[index] == 4){
                    return "Carré";
                } else if (occurrences[index] == 3) {
                    troisIdentiques = true;
                } else if (occurrences[index] == 2 && !unePaire) {
                    unePaire = true;
                } else if (troisIdentiques && unePaire) {
                    return "Full House";
                } else if (unePaire && occurrences[index] == 2) {
                    return "Deux paires";
                } else if (unePaire && index == occurrences.length - 1) {
                    return "Une paire";
                } else if (troisIdentiques) {
                    return "Brelan";
                } else if (compteur == 5){
                    return "Grande suite";
                } else if (compteur == 4){
                    return "Petite suite";
                } else if (occurrences[index] == 1){
                    compteur ++;
                } else {
                    compteur = 0;
                }
            }
            return resultatNull;
        }
    */
    static boolean unePaire(int[] occurrences){
        for (int occurrence : occurrences) {
            if (occurrence == 2) {
                return true;
            }
        }
        return false;
    }

    static boolean deuxPaires(int[] occurrences){
        int paires = 0;
        for (int occurrence : occurrences) {
            if (occurrence == 2) {
                paires++;
            }
        }
        return paires == 2;
    }

    static boolean estBrelan(int[] occurrences) {
        for (int occurrence : occurrences) {
            if (occurrence == 3) {
                return true;
            }
        }
        return false;
    }

    static boolean estCarre(int[] occurrences) {
        for (int occurrence : occurrences) {
            if (occurrence == 4) {
                return true;
            }
        }
        return false;
    }

    static boolean estFullHouse(int[] occurrences) {
        return estBrelan(occurrences) && unePaire(occurrences);
    }

    static boolean estYahtzee(int[] occurrences) {
        for (int occurrence : occurrences) {
            if (occurrence == 5) {
                return true;
            }
        }
        return false;
    }

    static boolean estPetiteSuite(int[] occurrences) {
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
        return compteurSuiteMax == 4;
    }

    static boolean estGrandeSuite(int[] occurrences) {
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

    static int calculScore(int[] occurrences) {
        int score = 0;
        if (estFullHouse(occurrences)) {
            score = SCORE_FULL_HOUSE;
        } else if (estCarre(occurrences)) {
            for (int face = 0; face < occurrences.length; face++) {
                if (occurrences[face] == 4) {
                    score = (face + 1) * 4;
                    break;
                }
            }
        } else if (estBrelan(occurrences)) {
            for (int face = 0; face < occurrences.length; face++) {
                if (occurrences[face] == 3) {
                    score = (face + 1) * 3;
                    break;
                }
            }
        } else if (deuxPaires(occurrences)) {
            score = SCORE_DEUX_PAIRE;
        } else if (unePaire(occurrences)) {
            score = SCORE_UNE_PAIRE;
        } else if (estPetiteSuite(occurrences)) {
            score = SCORE_PETITE_SUITE;
        } else if (estGrandeSuite(occurrences)) {
            score = SCORE_GRANDE_SUITE;
        } else if (estYahtzee(occurrences)) {
            score = SCORE_YAHTZEE;
        }
        return score;
    }

    public static void main(String[] args) {
        int[] des = new int[MAX_DES];

        for (int index = 0; index < des.length; index++) {
            des[index] = lancement(MAX_NOMBRE);
        }
        affichageDes(des);

        for (int lancements = 0; lancements < LIMITE_LANCEMENT; lancements++){
            int[] choixUtilisateur = demandeRelancer();
            if (choixUtilisateur.length != 0){
                relancemet(des, choixUtilisateur);
            } else {
                break;
            }
            affichageDes(des);
        }

        int[] occurrences = nombreOccurrences(des, MAX_NOMBRE);
        affichageOccurrences(occurrences);
        //System.out.println("Score: " + calculScore(occurrences));
        affichageScore(occurrences);

    }
}

