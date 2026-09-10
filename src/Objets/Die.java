package Objets;

/**
 * Classe qui représente un dé à 6 faces.
 */
public class Die {

    private final int SIDE_COUNT = 6;
    private int visibleSide = 1;

    /**
     * Constructeur par défaut.
     */
    Die() {

    }

    /**
     * Lance le dé.
     * @return le résultat obtenu.
     */
    public int roll() {
        visibleSide = (int)(Math.random() * SIDE_COUNT + 1);
        return visibleSide;
    }

    /**
     * @return la face visible exposée par ce dé.
     */
    public int getVisibleSide() {
        return visibleSide;
    }

    /**
     * Modifie la face visible de ce dé.
     * @param visibleSide Nouvelle face visible.
     */
    public void setVisibleSide(int visibleSide) {
        if (visibleSide >= 1 && visibleSide <= SIDE_COUNT) {
            this.visibleSide = visibleSide;
        }
    }

    public int getSIDE_COUNT() {
        return SIDE_COUNT;
    }
}
