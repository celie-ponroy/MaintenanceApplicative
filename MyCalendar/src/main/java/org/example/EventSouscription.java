package org.example;

import java.time.LocalDateTime;

public class EventSouscription extends EventPeriodique{
    //une soucription qui se paye tous les 28 jours
    protected double prix;
    protected String entreprise; //l'entreprise qui a qui on a souscrit
    public EventSouscription(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, double prix, String entreprise) {
        super(title, proprietaire, dateDebut, dureeMinutes, 28);
        if(prix<=0){
            throw new IllegalArgumentException("Le prix doit être positif");
        }
        this.prix = prix;
        type = EventType.SOUSCRIPTION;
        this.entreprise = entreprise;
        if (entreprise==null||entreprise.isEmpty()) {
            this.entreprise = "Inconnue";
        }
    }

    public double getPrix() {
        return prix;
    }

    public String getEntreprise() {
        return entreprise;
    }
}
