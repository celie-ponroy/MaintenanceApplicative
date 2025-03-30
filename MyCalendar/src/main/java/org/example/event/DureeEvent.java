package org.example.event;

public record DureeEvent(int minutes) {
    public DureeEvent {
        if (minutes <= 0) {
            throw new IllegalArgumentException("La durée doit être positive.");
        }
    }
}
