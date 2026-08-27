import java.util.Objects;
import java.util.Scanner;

public class YahtzeeProcedural {

    static final int MAX_NOMBRE = 6;
    static final int MAX_DES = 5;
    static final int LIMITE_LANCEMENT = 2;
    final int SCORE_UNE_PAIRE = 5;
    final int SCORE_DEUX_PAIRE = 10;
    final int SCORE_FULL_HOUSE = 25;
    final int SCORE_PETITE_SUITE = 30;
    final int SCORE_GRANDE_SUITE = 40;
    final int SCORE_YAHTZEE = 50;

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
    static String combinaison(int[] occurrences){
        int paires = 0;
        boolean troisIdentiques = false;
        boolean carre = false;
        boolean Yahtzee = false;
        int compteurSuite = 0;
        int compteurMax = 0;
        String resultat = "";
        for (int index = 0; index < occurrences.length; index++){
            if (occurrences[index] == 2) {
                paires ++;
            } else if (occurrences[index] == 3) {
                troisIdentiques = true;
            } else if (occurrences[index] == 4){
                carre = true;
            } else if (occurrences[index] == 5) {
                Yahtzee = true;
            } else if (occurrences[index] == 1) {
                compteurSuite ++;
                if (compteurMax < compteurSuite){
                    compteurMax = compteurSuite;
                }
            } else {
                compteurSuite = 0;
            }
        }

        if (troisIdentiques && paires == 1) {
            resultat = "Full House";
        } else if (carre) {
            resultat = "Carré";
        } else if (troisIdentiques) {
            resultat = "Brelan";
        } else if (paires == 1) {
            resultat = "Une paire";
        } else if (paires == 2) {
            resultat = "Deux paires";
        } else if (compteurMax == 4) {
            resultat = "Petite suite";
        } else if (compteurMax == 5) {
            resultat = "Grande suite";
        } else if (Yahtzee){
            resultat = "Yahtzee";
        }
        return resultat;
    }

    int calculScore(String combinaison, int[] occurrences) {
        int score = 0;
        if (Objects.equals(combinaison, "Une paire")) {
            score = SCORE_UNE_PAIRE;
        } else if (Objects.equals(combinaison, "Deux paires")) {
            score = SCORE_DEUX_PAIRE;
        } else if (Objects.equals(combinaison, "Brelan")) {
            for (int face = 0; face < occurrences.length; face++) {
                if (occurrences[face] == 3) {
                    score = (face + 1) * 3;
                    break;
                }
            }
        } else if (Objects.equals(combinaison, "Carré")) {
            for (int face = 0; face < occurrences.length; face++) {
                if (occurrences[face] == 4) {
                    score = (face + 1) * 3;
                    break;
                }
            }
        } else if (Objects.equals(combinaison, "Full House")) {
            score = SCORE_FULL_HOUSE;
        } else if (Objects.equals(combinaison, "Petite suite")) {
            score = SCORE_PETITE_SUITE;
        } else if (Objects.equals(combinaison, "Grande suite")) {
            score = SCORE_GRANDE_SUITE;
        } else if (Objects.equals(combinaison, "Yahtzee")) {
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

        affichageOccurrences(nombreOccurrences(des, MAX_NOMBRE));

        System.out.println(combinaison(nombreOccurrences(des, MAX_NOMBRE)));

    }
}

