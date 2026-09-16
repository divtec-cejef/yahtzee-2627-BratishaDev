package Objets;

public enum Category {

        PAIRE ("Paire"),
        DEUX_PAIRS ("Deux paires"),
        BRELAN ("Brelan"),
        CARRE ("Carré"),
        FULL_HOUSE ("Full House"),
        PETITE_SUITE ("Petite suite"),
        GRANDE_SUITE ("Grande suite"),
        YAHTZEE ("Yahtzee");

        private final String name;

        Category(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
}
