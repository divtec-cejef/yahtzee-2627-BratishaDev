import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class YahtzeeProcedural {

    static short lancement(short maxNombre) {
        return (short)(Math.random() * maxNombre + 1);
    }

    static void affichageDes(short[] des) {
        for (int index = 0; index < des.length; index++) {
            System.out.println("Des numero " + (index + 1) + ": " + des[index]);
        }
    }

    static short[] demandeRelancer(short maxDes) {
        System.out.println("Indiquez les dés que vous souhaitez relancer (ou « rien » pour arrêter)");
        Scanner scanner = new Scanner(System.in);
        String choixUtilisateur = scanner.nextLine();
        short[] tableauChoixUtilisateurShort = new short[maxDes];
        if (!choixUtilisateur.isEmpty()) {
            String[] tableauChoixUtilisateurString;
            tableauChoixUtilisateurString = choixUtilisateur.split(" ");
            for(short index = 0; index < tableauChoixUtilisateurString.length; index++){
                tableauChoixUtilisateurShort[index] = Short.parseShort(tableauChoixUtilisateurString[index]);
            }
        } else {
            tableauChoixUtilisateurShort = new short[0];
        }
        return tableauChoixUtilisateurShort;
    }

    static void relancemet(short[] des, short[] choixUtilisateur){
        for(short indexDes = 0; indexDes < des.length; indexDes++){
            for (Short indexChoix : choixUtilisateur) {
                if (indexDes + 1 == indexChoix) {
                    des[indexChoix - 1] = lancement((short)des.length);
                }
            }
        }
    }

    public static void main(String[] args) {
        final short MAX_NOMBRE = 6;
        final short MAX_DES = 5;
        final short LIMITE_LANCEMENT = 2;

        short[] des = new short[MAX_DES];

        for (int index = 0; index < des.length; index++) {
            des[index] = lancement(MAX_NOMBRE);
        }
        affichageDes(des);

        boolean choixVide= false;
        for (short lancements = 0; lancements < LIMITE_LANCEMENT && !choixVide; lancements++){
            short[] choixUtilisateur = demandeRelancer(MAX_DES);
            if (choixUtilisateur.length != 0){
                relancemet(des, choixUtilisateur);
            } else {
                choixVide= true;
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

