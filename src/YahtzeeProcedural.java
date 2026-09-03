//import jdk.swing.interop.SwingInterOpUtils;

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

    static int faceBrelant = 0;
    static int faceCarre = 0;
    static int face1Paire = 0;
    static int face2Paire = 0;

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

    static void affichageScore(int[] scores, String[] nomCombinaison){
        String alignementDroite = "%2s\n";
        String alignementGauche = "%-15s";
        for (int index = 0; index < scores.length; index++) {
            System.out.printf(alignementGauche, nomCombinaison[index]);
            System.out.printf(alignementDroite, scores[index]);
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

    static boolean unePaire(int[] occurrences){
        for (int occurrence : occurrences) {
            if (occurrence >= 2) {
                return true;
            }
        }
        return false;

    }

    static boolean deuxPaires(int[] occurrences){
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

    static boolean estBrelan(int[] occurrences) {
        for (int face = 0; face < occurrences.length; face++) {
            if (occurrences[face] >= 3) {
                faceBrelant = face;
                return true;
            }
        }
        return false;
    }

    static boolean estCarre(int[] occurrences) {
        for (int face = 0; face < occurrences.length; face++) {
            if (occurrences[face] >= 4) {
                faceCarre = face;
                return true;
            }
        }
        return false;
    }

    static boolean estFullHouse(int[] occurrences) {
        return estBrelan(occurrences) && unePaire(occurrences) && (face1Paire != faceBrelant || face2Paire > 0);
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
            if (occurrence >= 1) {
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

    static int[] calculScore(int[] occurrences) {
        int[] scores = new int[8];
        if (unePaire(occurrences)) {
            scores[0] = SCORE_UNE_PAIRE;
        } else {
            scores[0] = 0;
        }
        if (deuxPaires(occurrences)) {
            scores[1] = SCORE_DEUX_PAIRE;
        } else {
            scores[1] = 0;
        }
        if (estBrelan(occurrences)) {
            scores[2] = (faceBrelant + 1) * 3;
        } else {
            scores[2] = 0;
        }
        if (estCarre(occurrences)) {
            scores[3] = (faceCarre + 1) * 4;
        } else {
            scores[3] = 0;
        }
        if (estFullHouse(occurrences)) {
            scores[4] = SCORE_FULL_HOUSE;
        } else {
            scores[4] = 0;
        }
        if (estPetiteSuite(occurrences)) {
            scores[5] = SCORE_PETITE_SUITE;
        } else {
            scores[5] = 0;
        }
        if (estGrandeSuite(occurrences)) {
            scores[6] = SCORE_GRANDE_SUITE;
        } else {
            scores[6] = 0;
        }  if (estYahtzee(occurrences)) {
            scores[7] = SCORE_YAHTZEE;
        } else {
            scores[7] = 0;
        }
        return scores;
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
        String[] combinaisons = {"Une paire", "Deux paires", "Brelan", "Carré", "Full House", "Petite suite", "Grande suite", "Yahtzee"};
        affichageOccurrences(occurrences);
        affichageScore(calculScore(occurrences), combinaisons);
    }
}

