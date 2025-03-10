package trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// REFACTOR ME
public class Game implements IGame {
   LinkedList<Player> players = new LinkedList<>();
   HashMap<String, LinkedList> questions = new HashMap<String, LinkedList>();
   Player currentPlayer ;
   boolean isGettingOutOfPenaltyBox;
   boolean askedQuestion = false;

   public Game() {
      for (var category : Categories.values())
         questions.put(category.toString(), new LinkedList());

       //on créer 50 questions pour chaque catégorie
       for (int i = 0; i < 50; i++)
           for (var category : questions.keySet())
               questions.get(category.toString()).addLast(createQuestion(category, i));
   }

   public String createQuestion(String category,int index) {
      return category+" Question " + index;
   }

   public boolean add(String playerName) {
      players.add(new Player(playerName));

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      if(players.size()==1) currentPlayer=players.peekFirst();
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      System.out.println(currentPlayer + " is the current player");
      System.out.println("They have rolled a " + roll);
       // si le joueur est en prison

       if (currentPlayer.isInPenaltyBox()) prisonPlay(roll);
       else classicPlay(roll);
   }

   /**
    * Tour du joueur en prison
    * @param roll lancé de dé pour déterminer si le joueur peut poser une question
    */
   public void prisonPlay(int roll){
      if (roll % 2 != 0) {
         //cas de potentielle sortie de prison
         isGettingOutOfPenaltyBox = true;
         System.out.println(currentPlayer + " is getting out of the penalty box");
         classicPlay(roll);
      } else {
         //reste en prison
         System.out.println(currentPlayer + " is not getting out of the penalty box");
         isGettingOutOfPenaltyBox = false;
         askedQuestion=false;
      }
   }

   /**
    * Tour classique du joueur
    * @param roll nombre de cases à avancer
    */
   public void classicPlay(int roll){
      currentPlayer.move(roll);


      System.out.println(currentPlayer
              + "'s new location is "
              + currentPlayer.getPlace());
      System.out.println("The category is " + currentCategory());
      askQuestion();
   }

   private void askQuestion() {
      askedQuestion = true;
      var category = currentCategory();
      System.out.println(questions.get(category).removeFirst());
   }

   private String currentCategory() {
      var place = currentPlayer.getPlace();
      int categoryIndex = (place-1) % Categories.values().length;
      return Categories.values()[categoryIndex].toString();
   }

   public boolean handleCorrectAnswer() {

      if (currentPlayer.isInPenaltyBox() && !isGettingOutOfPenaltyBox) {
         finTour();
         return true;
      }
      System.out.println("Answer was correct!!!!");
      currentPlayer.addCoin(1);
      System.out.println(currentPlayer
                         + " now has "
                         + currentPlayer.getPurse()
                         + " Gold Coins.");

      boolean winner = didPlayerWin();
      finTour();
      return winner;
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer + " was sent to the penalty box");
      currentPlayer.goToPenaltyBox();

     finTour();
     return true;
   }
   public void finTour() {
        var nextPlayer = players.pollFirst();
        players.addLast(nextPlayer);
        currentPlayer = players.peekFirst();
   }
   public boolean nextPlayer() {
      finTour();
      return true;
   }


   private boolean didPlayerWin() {
      return !(currentPlayer.getPurse() == 6);
   }
   public boolean questionAsked() {
      return askedQuestion;
   }
}
