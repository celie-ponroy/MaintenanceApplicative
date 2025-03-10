package trivia;

public enum Categories {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private String name;

    Categories(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
