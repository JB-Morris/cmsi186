public class PicomonGame {

    public enum Player {
        GYM_LEADER("Gym leader"), TRAINER("Trainer");
        
        private String representation;
        private Player(String representation) {
            this.representation = representation;
        }

        @Override
        public String toString() {
            return representation;
        }
    }

    public class Round {
        public Player winner; // null if tied.
        public PicomonCard gymLeaderCard;
        public PicomonCard trainerCard;
        
        public Round(PicomonCard gymLeaderCard, PicomonCard trainerCard) {
            this.gymLeaderCard = gymLeaderCard;
            this.trainerCard = trainerCard;
        }

        @Override
        public String toString() {
            if (winner == null) {
                return "It's a tie between " + Player.GYM_LEADER + "'s " + gymLeaderCard + " and " +
                        Player.TRAINER + "'s " + trainerCard + "!";
            } else {
                Player loser = winner == Player.GYM_LEADER ? Player.TRAINER : Player.GYM_LEADER;
                PicomonCard winningCard = winner == Player.GYM_LEADER ? gymLeaderCard : trainerCard;
                PicomonCard losingCard = winner == Player.GYM_LEADER ? trainerCard : gymLeaderCard;
                return winner + "'s " + winningCard + " beats " + loser + "'s " + losingCard + "!";
            }
        }
    }

    private PicomonDeck gymLeaderDeck;
    private PicomonDeck trainerDeck;
    private int gymLeaderPosition;
    private int trainerPosition;
    
    public PicomonGame() {
        this(new PicomonDeck(), new PicomonDeck());
    }
    
    public PicomonGame(PicomonDeck gymLeaderDeck, PicomonDeck trainerDeck) {
        if (gymLeaderDeck.getSize() != trainerDeck.getSize()) {
            throw new IllegalArgumentException();
        }

        gymLeaderPosition = 0;
        trainerPosition = 0;
        this.gymLeaderDeck = gymLeaderDeck;
        this.trainerDeck = trainerDeck;
    }
    
    public PicomonDeck getGymLeaderDeck() {
        return gymLeaderDeck;
    }
    
    public PicomonDeck getTrainerDeck() {
        return trainerDeck;
    }

    public boolean isMatchOver() {
        // Implement me!
        return(trainerDeck.getSize() == trainerPosition || gymLeaderDeck.getSize() == gymLeaderPosition);
    }
    
    public Player getLeader() {
        // Implement me!
        if(trainerPosition < gymLeaderPosition){
            return Player.TRAINER;
        }else if(trainerPosition > gymLeaderPosition){
            return Player.GYM_LEADER;
        }else return null;
    }
    
    public Round playRound() {
        // Implement me!
        Round game = new Round(gymLeaderDeck.cardAt(gymLeaderPosition), trainerDeck.cardAt(trainerPosition));
        if(trainerDeck.cardAt(trainerPosition).beats(gymLeaderDeck.cardAt(gymLeaderPosition)) == true && gymLeaderDeck.cardAt(gymLeaderPosition).beats(trainerDeck.cardAt(trainerPosition)) == false){
            game.winner = Player.TRAINER;
            gymLeaderPosition++;
        }else if(trainerDeck.cardAt(trainerPosition).beats(gymLeaderDeck.cardAt(gymLeaderPosition)) == false && gymLeaderDeck.cardAt(gymLeaderPosition).beats(trainerDeck.cardAt(trainerPosition)) == true){
            game.winner = Player.GYM_LEADER;
            trainerPosition++;
        }else{
            game.winner = null;
            trainerPosition++;
            gymLeaderPosition++;
        }
        return game;
    }

    public Round[] playMatch() {
        // Implement me!
        int matchLength;
        int counter = 0;
        if(trainerDeck.getSize() > (gymLeaderDeck.getSize())){
            matchLength = trainerDeck.getSize() * 2;
        }else matchLength = gymLeaderDeck.getSize() * 2;
        Round[] rounds = new Round[matchLength];
        while(!isMatchOver()){
            rounds[counter] = playRound();
            counter++;
        }
        return rounds;
    }

    public static void main(String[] args) {
        // Implement me!
        PicomonGame game;
        PicomonCard[] gameCards;
        PicomonDeck player1Deck;
        PicomonDeck player2Deck;
        if(args.length == 0) {
            game = new PicomonGame();
            gameCards = new PicomonCard[game.getTrainerDeck().getSize()];
        }else if(args.length % 2 != 0) {
            System.out.println("Cannot create a deck based on the supplied arguments.");
            return;
        }else {
            gameCards = new PicomonCard[args.length / 2];
            int counter = 0;
            for(int i = 0; i < args.length; i = i + 2){
                int cardPower;
                PicomonElement cardElement;
                if(args[i].equals("water")){
                    cardElement = PicomonElement.WATER;
                }else if(args[i].equals("fire")){
                    cardElement = PicomonElement.FIRE;
                }else if(args[i].equals("earth")){
                    cardElement = PicomonElement.EARTH;
                }else if(args[i].equals("air")){
                    cardElement = PicomonElement.AIR;
                }else{
                    System.out.println("Invalid element specified");
                    return;
                }
                cardPower = Integer.parseInt(args[i + 1]);
                gameCards[counter] = new PicomonCard(cardElement, cardPower);
                counter++;
            }
            player1Deck = new PicomonDeck(gameCards);
            player2Deck = new PicomonDeck(gameCards);
            game = new PicomonGame(player1Deck, player2Deck);
//            game.gymLeaderDeck.shuffle();


        }
        System.out.println("Trainer's Deck: " + game.getTrainerDeck());
        System.out.println("Gym Leader's Deck: " + game.getGymLeaderDeck());
        Round[] match = game.playMatch();
        for(int i = 0; i < game.playMatch().length; i++){
//            if(match[i].equals(null)){
//                System.out.println("");
//            }else {
                System.out.println(match[i]);
//            }
        }
        System.out.println("And the winner is: " + game.getLeader());
    }

}
