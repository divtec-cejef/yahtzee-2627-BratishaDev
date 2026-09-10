import java.util.Scanner;

public class YahtzeeProcedural {

    static final int MAX_NOMBRE = 6;
    static final int MAX_DES = 5;
    static final int LIMITE_LANCEMENT = 2;
    static final int LIMITE_MANCHES = 5;
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

    static boolean[] combinaisonsPrises = new boolean[8];
    static int scoreJoueur = 0;

    /**
     * Lance un dé
     * @param maxNombre nombre maximal à obtenir
     * @return le résultat (nombre) du lancer de dé
     */
    static int lancement(int maxNombre) {
        return (int)(Math.random() * maxNombre + 1);
    }

    /**
     * Affiche les dés et les chiffres qui sont sortis
     * @param des dés à afficher
     */
    static void affichageDes(int[] des) {
        for (int index = 0; index < des.length; index++) {
            System.out.println("Des numero " + (index + 1) + ": " + des[index]);
        }
        System.out.println();
    }

    /**
     * Affiche des occurrences
     * @param occurrances occurrences à afficher
     */
    static void affichageOccurrences(int[] occurrances) {
        for (int index = 0; index < occurrances.length; index++) {
            System.out.println("Occurrences de la face " + (index + 1) + ": " + occurrances[index]);
        }
        System.out.println();
    }

    /**
     * Affiche les scores des combinaisons
     * @param scores scores des combinaisons
     * @param nomCombinaison nom des combinaisons
     */
    static void affichageScore(int[] scores, String[] nomCombinaison){
        String alignementDroite = "%2s\n";
        String alignementGauche = "%-17s";
        for (int index = 0; index < nomCombinaison.length; index++) {
            if(!combinaisonsPrises[index]) {
                System.out.printf(alignementGauche, (index + 1) + ") " + nomCombinaison[index]);
                System.out.printf(alignementDroite, scores[index]);
            }
        }
        System.out.println();
    }

    /**
     * Demande à l'utilisateur relancer les dés
     * @return les indices de dés à relancer
     */
    static int[] demandeRelancer() {
        System.out.println("Indiquez les dés que vous souhaitez relancer (ou rien pour arrêter)");
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
     * Relance les dés donnée
     * @param des les dés
     * @param choixUtilisateur les numéros des dés à relancer
     */
    static void relancemet(int[] des, int[] choixUtilisateur){
        for(int indexDes = 0; indexDes < des.length; indexDes++){
            for (int indexChoix : choixUtilisateur) {
                if (indexDes == indexChoix) {
                    des[indexChoix] = lancement(des.length);
                }
            }
        }
    }

    /**
     * Calcule le nombre d'occurrences
     * @param des les dés
     * @param maxNombre nombre maximal d'occurrences d'un même nombre.
     * @return nombre d'occurrences de chaque nombre
     */
    static int[] nombreOccurrences(int[] des, int maxNombre){
        int[] occurrences = new int[maxNombre];
        for (int index = 0 ; index < des.length; index ++){
            occurrences[des[index] - 1] ++;
        }
        return occurrences;
    }

    /**
     * Vérification de la combinaison d'une paire.
     * @param occurrences les occurrences de chaque nombre
     * @return true s'il y a une paire, false sinon
     */
    static boolean unePaire(int[] occurrences){
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

    /**
     * Vérification de la présence d'une combinaison « Brelan »
     * @param occurrences les occurrences de chaque nombre
     * @return true s'il y a un Brelan, false sinon
     */
    static boolean estBrelan(int[] occurrences) {
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
    static boolean estCarre(int[] occurrences) {
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
    static boolean estFullHouse(int[] occurrences) {
        return estBrelan(occurrences) && unePaire(occurrences) && (face1Paire != faceBrelant || face2Paire > 0);
    }

    /**
     * Vérification de la présence d'une combinaison « Yahtzee »
     * @param occurrences les occurrences de chaque nombre
     * @return true s'il y a un Yahtzee, false sinon
     */
    static boolean estYahtzee(int[] occurrences) {
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
        return compteurSuiteMax >= 4;
    }

    /**
     * Vérification de la présence d'une combinaison « Grande Suite »
     * @param occurrences les occurrences de chaque nombre
     * @return true s'il y a une Grande Suite, false sinon
     */
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

    /**
     * Calcule les scores pour chaque combinaison en fonction de sa présence
     * @param occurrences les occurrences de chaque nombre
     * @return un tableau des scores de chaque combinaison
     */
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

    /**
     * Demande à l'utilisateur de choisir une des combinaisons disponibles pour prendre le score de cette combinaison
     * @return choix de combinaison
     */
    static int choixCombinaison() {
        Scanner scanner = new Scanner(System.in);
        int choixCombinaison;
        boolean valide = false;
        do {
            System.out.println("Choisissez le numéro correspondant à l'une des combinaisons disponibles: ");
            choixCombinaison = Integer.parseInt(scanner.nextLine());
            if (choixCombinaison > 0 && choixCombinaison < 9 && !combinaisonsPrises[choixCombinaison - 1]){
                valide = true;
            }
        } while (!valide);
        combinaisonsPrises[choixCombinaison - 1] = true;
        return choixCombinaison - 1;
    }

    /**
     * Point d'entrée du programme
     * @param args
     */
    public static void main(String[] args) {
        for (int manche = 0; manche < LIMITE_MANCHES; manche++) {
            System.out.println("Manche numero: " + (manche + 1));
            int[] des = new int[MAX_DES];

            // Premier lancement de dés
            for (int index = 0; index < des.length; index++) {
                des[index] = lancement(MAX_NOMBRE);
            }
            System.out.println("Lancement: " + 1);
            affichageDes(des);

            // Replacement de dés optionnel
            for (int lancements = 0; lancements < LIMITE_LANCEMENT; lancements++) {
                int[] choixUtilisateur = demandeRelancer();
                if (choixUtilisateur.length != 0) {
                    relancemet(des, choixUtilisateur);
                } else {
                    break;
                }
                System.out.println("Lancement: " + (lancements + 2));
                affichageDes(des);
            }

            // Affichage des occurrences et des combinaisons disponibles et demande de choisir une des combinaisons,
            // et apres affichage de score de Joueur
            int[] occurrences = nombreOccurrences(des, MAX_NOMBRE);
            String[] combinaisons = {"Une paire", "Deux paires", "Brelan", "Carré", "Full House", "Petite suite", "Grande suite", "Yahtzee"};
            affichageOccurrences(occurrences);
            affichageScore(calculScore(occurrences), combinaisons);
            scoreJoueur = scoreJoueur + calculScore(occurrences)[choixCombinaison()];
            if (manche != LIMITE_MANCHES - 1) {
                System.out.println("Votre score: " + scoreJoueur + "\n");
            } else {
                System.out.println("Votre score final: " + scoreJoueur);
            }
            face2Paire = 0;
        }
    }
}