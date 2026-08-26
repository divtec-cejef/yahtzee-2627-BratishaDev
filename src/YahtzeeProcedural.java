import java.util.Scanner;

public class YahtzeeProcedural {

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
        String resultatNull = "";
        for (int index = 0; index < occurrences.length; index++){
            if (occurrences[index] == 2) {
                paires ++;
            } else if (occurrences[index] == 3) {
                troisIdentiques = true;
            } else if (occurrences[index] == 4){
                carre = true;
            } else if (occurrences[index] == 5) {
                Yahtzee = true;
            }
        }
        return resultatNull;
    }

    public static void main(String[] args) {
        final int MAX_NOMBRE = 6;
        final int MAX_DES = 5;
        final int LIMITE_LANCEMENT = 2;

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

