package org.example.event;

//titre des évenements
public record TitreEvent(String valeur) {
    public TitreEvent {
        if (valeur == null || valeur.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être vide.");
        }
    }

    @Override
    public String toString() {
        return  valeur;
    }

}

