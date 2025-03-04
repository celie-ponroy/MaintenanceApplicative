package trivia;

import java.util.ArrayList;
import java.util.LinkedList;

// REFACTOR ME
public class Game implements IGame {
   ArrayList players = new ArrayList();
   int[] places = new int[6];
   int[] purses = new int[6];
   boolean[] inPenaltyBox = new boolean[6];

   LinkedList popQuestions = new LinkedList();
   LinkedList scienceQuestions = new LinkedList();
   LinkedList sportsQuestions = new LinkedList();
   LinkedList rockQuestions = new LinkedList();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public Game() {
      for (int i = 0; i < 50; i++) {//on créer 50 questions pour chaque catégorie
         popQuestions.addLast(createQuestion("Pop" , i));
         scienceQuestions.addLast(createQuestion("Science" , i));
         sportsQuestions.addLast(createQuestion("Sports" , i));
         rockQuestions.addLast(createQuestion("Rock",i));
      }
   }

   public String createQuestion(String category,int index) {
      return category+" Question " + index;
   }

   public boolean add(String playerName) {
      places[howManyPlayers()] = 1;
      purses[howManyPlayers()] = 0;
      inPenaltyBox[howManyPlayers()] = false;
      players.add(playerName);

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      System.out.println(players.get(currentPlayer) + " is the current player");
      System.out.println("They have rolled a " + roll);
      if (inPenaltyBox[currentPlayer]) {
         // si le joueur est en prison
         prisonPlay(roll);
      } else {
         //le joueur joue
            classicPlay(roll);
      }
   }

   /**
    * Tour du joueur en prison
    * @param roll lancé de dé pour déterminer si le joueur peut poser une question
    */
   public void prisonPlay(int roll){
      if (roll % 2 != 0) {
         //cas de potentielle sortie de prison
         isGettingOutOfPenaltyBox = true;

         System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
         classicPlay(roll);
      } else {
         //reste en prison
         System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
         isGettingOutOfPenaltyBox = false;
      }
   }

   /**
    * Tour classique du joueur
    * @param roll nombre de cases à avancer
    */
   public void classicPlay(int roll){
      places[currentPlayer] = places[currentPlayer] + roll; //on avance le joueur
      if (places[currentPlayer] > 12) places[currentPlayer] = places[currentPlayer] - 12; //si le joueur dépasse la case 12, on le fait avancer à partir de 0

      System.out.println(players.get(currentPlayer)
              + "'s new location is "
              + places[currentPlayer]);
      System.out.println("The category is " + currentCategory());
      askQuestion();
   }

   private void askQuestion() {
      var category = currentCategory();
      if (category == "Pop")
         System.out.println(popQuestions.removeFirst());
      if (category == "Science")
         System.out.println(scienceQuestions.removeFirst());
      if (category == "Sports")
         System.out.println(sportsQuestions.removeFirst());
      if (category == "Rock")
         System.out.println(rockQuestions.removeFirst());
   }


   private String currentCategory() {
      var place = places[currentPlayer];
      if (place - 1 == 0) return "Pop";
      if (place - 1 == 4) return "Pop";
      if (place - 1 == 8) return "Pop";
      if (place - 1 == 1) return "Science";
      if (place - 1 == 5) return "Science";
      if (place - 1 == 9) return "Science";
      if (place - 1 == 2) return "Sports";
      if (place - 1 == 6) return "Sports";
      if (place - 1 == 10) return "Sports";
      return "Rock";
   }

   public boolean handleCorrectAnswer() {//diplication de code
      if (inPenaltyBox[currentPlayer]) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            purses[currentPlayer]++;
            System.out.println(players.get(currentPlayer)
                               + " now has "
                               + purses[currentPlayer]
                               + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
         } else {
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;
            return true;
         }


      } else {

         System.out.println("Answer was correct!!!!");
         purses[currentPlayer]++;
         System.out.println(players.get(currentPlayer)
                            + " now has "
                            + purses[currentPlayer]
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
      inPenaltyBox[currentPlayer] = true;

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return !(purses[currentPlayer] == 6);
   }
}
