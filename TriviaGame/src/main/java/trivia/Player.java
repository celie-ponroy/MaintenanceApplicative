package trivia;

public class Player {
    private String name;
    private int place;
    private int purse;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
        this.place = 1;
        this.purse = 0;
        this.inPenaltyBox = false;
    }
    public void move(int roll) {
        place += roll;
        if (place > 12) {
            place = place - 12;
        }
    }
    public int getPlace() {
        return place;
    }
    public void goToPenaltyBox() {
        inPenaltyBox = true;
    }
    public void getOutPenalityBox() {
        inPenaltyBox = false;
    }
    public void addCoin(int coin) {
        purse = purse + coin;
    }
    public int getPurse() {
        return purse;
    }
    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
