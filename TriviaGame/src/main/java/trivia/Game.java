package trivia;

import java.util.HashMap;
import java.util.LinkedList;

public class Game implements IGame {
   LinkedList<Player> players = new LinkedList<>();
   HashMap<String, LinkedList> questions = new HashMap<String, LinkedList>();
   Player currentPlayer ;
   boolean askedQuestion = false;

   public Game() {
      for (var category : Categories.values())
         questions.put(category.toString(), new LinkedList());

       //on créer 50 questions pour chaque catégorie
       for (int i = 0; i < 50; i++)
           for (var category : questions.keySet())
               questions.get(category.toString()).addLast(createQuestion(category, i));
   }

   /**
    * Crée une question
    * @param category catégorie de la question
    * @param index index de la question
    * @return
    */
   public String createQuestion(String category,int index) {
      return category+" Question " + index;
   }

   /**
    * Ajoute un joueur
    * @param playerName nom du joueur
    * @return
    */
   public boolean add(String playerName) {
      players.add(new Player(playerName));

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      if(players.size()==1) currentPlayer=players.peekFirst();
      return true;
   }

   /**
    * Nombre de joueurs
    * @return
    */
   public int howManyPlayers() {
      return players.size();
   }

   /**
    * Joue je tour du joueur
    * @param roll nombre de cases à avancer
    */
   public void roll(int roll) {
      System.out.println(currentPlayer + " is the current player");
      System.out.println("They have rolled a " + roll);
       // si le joueur est en prison
       if (currentPlayer.isInPenaltyBox()){
          prisonPlay(roll);
       } else {
          classicPlay(roll);
       }
   }

   /**
    * Tour du joueur en prison
    * @param roll lancé de dé pour déterminer si le joueur peut poser une question
    */
   public void prisonPlay(int roll){
      boolean isGettingOutOfPenaltyBox = roll % 2 != 0;
      System.out.println(currentPlayer.toString() +
              ( isGettingOutOfPenaltyBox ? " is" : " is not") +
              " getting out of the penalty box");

      if (isGettingOutOfPenaltyBox) {
         //cas de potentielle sortie de prison
         currentPlayer.getOutPenalityBox();
         classicPlay(roll);
      } else {
         //reste en prison
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

   /**
    * Pose une question
    */
   private void askQuestion() {
      askedQuestion = true;
      var category = currentCategory();
      System.out.println(questions.get(category).removeFirst());
   }

   /**
    * Récupère la catégorie de la case du player courant
    * @return la catégorie
    */
   private String currentCategory() {
      var place = currentPlayer.getPlace();
      int categoryIndex = (place-1) % Categories.values().length;
      return Categories.values()[categoryIndex].toString();
   }

   /**
    * Gère une bonne réponse
    * @return
    */
   public boolean handleCorrectAnswer() {
      if (currentPlayer.isInPenaltyBox()) {
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

   /**
    * Gère une mauvaise réponse
    * @return
    */
   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer + " was sent to the penalty box");
      currentPlayer.goToPenaltyBox();

     finTour();
     return true;
   }

   /**
    * gère la fin du tour
    */
   public void finTour() {
        var nextPlayer = players.pollFirst();
        players.addLast(nextPlayer);
        currentPlayer = players.peekFirst();
   }

   /**
    * Passe le tour
    * @return
    */
   public boolean nextPlayer() {
      finTour();
      return true;
   }

   /**
    * Vérifie si le joueur a gagné
    * @return si le joueur a gagné
    */
   private boolean didPlayerWin() {
      return !(currentPlayer.getPurse() == 6);
   }

   /**
    * Vérifie si une question a été posée (pas de questions sur la case de penalité)
    * @return
    */
   public boolean questionAsked() {
      return askedQuestion;
   }
}
