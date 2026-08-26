import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class YahtzeeProcedural {

    static int lancement(int maxNombre) {
        return (int)(Math.random() * maxNombre + 1);
    }

    static void affichageDes(int[] des) {
        for (int index = 0; index < des.length; index++) {
            System.out.println("Des numero " + (index + 1) + ": " + des[index]);
        }
    }

    static int[] demandeRelancer(int maxDes) {
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
            int[] choixUtilisateur = demandeRelancer(MAX_DES);
            if (choixUtilisateur.length != 0){
                relancemet(des, choixUtilisateur);
            } else {
                break;
            }
            affichageDes(des);
        }

        /*
        boolean saisieVideEtZero = false;
        for (short lancements = 0; lancements < LIMITE_LANCEMENT && !saisieVideEtZero; lancements++){
            boolean saisieZero = false;
            System.out.println("Indiquez les dés que vous souhaitez relancer (ou « zéro » pour arrêter)");
            List<Short> choixUtilisateur = new ArrayList<>();
            for (short numeroChoix = 0; numeroChoix < MAX_DES && !saisieZero; numeroChoix++){
                Scanner scanner = new Scanner(System.in);
                choixUtilisateur.add(scanner.nextShort());
                if (choixUtilisateur.get(numeroChoix) == 0) {
                    saisieZero = true;
                }
                if (choixUtilisateur.size() == 1 && saisieZero) {
                    saisieVideEtZero = true;
                }
            }
            if (!saisieVideEtZero) {
                for(short indexDes = 0; indexDes < MAX_DES; indexDes++){
                    for (Short indexChoix : choixUtilisateur) {
                        if (indexDes + 1 == indexChoix) {
                            des[indexChoix - 1] = lancement(MAX_NOMBRE);
                        }
                    }
                }
            }
            affichageDes(des);
        }*/
    }
}

