package org.example.event;

import java.util.Objects;

public record DureeEvent(int minutes) {
    public DureeEvent {
        if (minutes <= 0) {
            throw new IllegalArgumentException("La durée doit être positive.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DureeEvent that = (DureeEvent) o;
        return minutes == that.minutes;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(minutes);
    }
}
