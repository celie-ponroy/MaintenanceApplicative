package org.example.event;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TitreEvent that = (TitreEvent) o;
        return Objects.equals(valeur, that.valeur);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(valeur);
    }
}

