package org.example;

import java.time.LocalDateTime;

public class EventSouscription extends EventPeriodique{
    //une soucription qui se paye tous les 28 jours
    protected final double prix;
    protected final String entreprise; //l'entreprise qui a qui on a souscrit
    public EventSouscription(String title, Utilisateur proprietaire, LocalDateTime dateDebut, int dureeMinutes, double prix, String entreprise) {
        super(title, proprietaire, dateDebut, dureeMinutes, 28);
        if (prix <= 0) {
            throw new IllegalArgumentException("Le prix doit être positif");
        }
        this.prix = prix;
        type = EventType.SOUSCRIPTION;
        if (entreprise == null || entreprise.isEmpty()) {
            this.entreprise = "Inconnue";
        } else{
            this.entreprise = entreprise;
        }

    }

    public double getPrix() {
        return prix;
    }

    public String getEntreprise() {
        return entreprise;
    }

    @Override
    public String description() {
        return "Souscription chez : " + entreprise + " de " + prix+" (id: "+ eventId+ ")";
    }
}
