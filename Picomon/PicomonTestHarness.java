public class PicomonTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        test_CardCreation();
        test_CardEquals();
        test_CardBeats();
        test_DeckCreation();
        test_DeckEquals();
        test_DeckOrderedEquals();
        test_DeckShuffle();
        test_Game();

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void test_CardCreation() {
        System.out.println("Testing PicomonCard constructors...");

        // Due to randomization, test conditions are somewhat limited.
        // Further, some defects might not emerge consistently, again
        // due to randomization.
        PicomonCard card = new PicomonCard();
        try {
            displaySuccessIfTrue(null != card.getName());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(null != card.getElement());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(0 < card.getPower());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        card = new PicomonCard(PicomonElement.FIRE, 50);
        try {
            displaySuccessIfTrue(null != card.getName());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(PicomonElement.FIRE == card.getElement());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(50 == card.getPower());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        card = new PicomonCard("Bob", PicomonElement.EARTH, 500);
        try {
            displaySuccessIfTrue("Bob".equals(card.getName()));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(PicomonElement.EARTH == card.getElement());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(500 == card.getPower());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        // We expect this one to throw an exception.
        try {
            card = new PicomonCard(PicomonElement.AIR, -100);
            // If we get here, the constructor failed.
            displaySuccessIfTrue(false);
        } catch(Exception e) {
            displaySuccessIfTrue(true);
        }
    }

    private static void test_CardEquals() {
        System.out.println("Testing PicomonCard equality...");

        PicomonCard card = new PicomonCard("Jane", PicomonElement.WATER, 20);
        PicomonCard otherCard = new PicomonCard("Jill", PicomonElement.WATER, 20);

        try {
            displaySuccessIfTrue(!card.equals(otherCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherCard.equals(card));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        otherCard = new PicomonCard("Jane", PicomonElement.WATER, 20);
        try {
            displaySuccessIfTrue(card.equals(otherCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(otherCard.equals(card));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }

    private static void test_CardBeats() {
        System.out.println("Testing PicomonCard beats method...");

        PicomonCard card = new PicomonCard(PicomonElement.WATER, 20);
        PicomonCard otherCard = new PicomonCard(PicomonElement.WATER, 20);

        try {
            displaySuccessIfTrue(!card.beats(otherCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherCard.beats(card));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        otherCard = new PicomonCard(PicomonElement.WATER, 200);
        try {
            displaySuccessIfTrue(!card.beats(otherCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(otherCard.beats(card));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        otherCard = new PicomonCard(PicomonElement.AIR, 21);
        try {
            displaySuccessIfTrue(!card.beats(otherCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(otherCard.beats(card));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        otherCard = new PicomonCard(PicomonElement.FIRE, 39);
        try {
            displaySuccessIfTrue(card.beats(otherCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherCard.beats(card));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        card = new PicomonCard(PicomonElement.EARTH, 28);
        try {
            displaySuccessIfTrue(card.beats(otherCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherCard.beats(card));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        card = new PicomonCard(PicomonElement.AIR, 118);
        try {
            displaySuccessIfTrue(card.beats(otherCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherCard.beats(card));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        otherCard = new PicomonCard(PicomonElement.WATER, 235);
        try {
            displaySuccessIfTrue(card.beats(otherCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherCard.beats(card));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        otherCard = new PicomonCard(PicomonElement.EARTH, 236);
        try {
            displaySuccessIfTrue(!card.beats(otherCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherCard.beats(card));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        otherCard = new PicomonCard(PicomonElement.FIRE, 40);
        try {
            displaySuccessIfTrue(!card.beats(otherCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(otherCard.beats(card));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        card = new PicomonCard(PicomonElement.EARTH, 10);
        try {
            displaySuccessIfTrue(!card.beats(otherCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherCard.beats(card));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }
    
    private static void test_DeckCreation() {
        System.out.println("Testing PicomonDeck constructors...");

        PicomonDeck deck = new PicomonDeck();
        try {
            displaySuccessIfTrue(10 == deck.getSize());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.valueOf("fire".toUpperCase()), 50),
            new PicomonCard("B", PicomonElement.valueOf("Earth".toUpperCase()), 100),
            new PicomonCard("C", PicomonElement.valueOf("waTER".toUpperCase()), 10)     
        });
        try {
            displaySuccessIfTrue(3 == deck.getSize());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(new PicomonCard("A", PicomonElement.FIRE, 50).equals(deck.cardAt(0)));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(new PicomonCard("B", PicomonElement.EARTH, 100).equals(deck.cardAt(1)));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(new PicomonCard("C", PicomonElement.WATER, 10).equals(deck.cardAt(2)));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        // Bad card.
        try {
            deck = new PicomonDeck(new PicomonCard[] {
                new PicomonCard(PicomonElement.FIRE, 20),
                new PicomonCard(PicomonElement.WATER, -100)
            });
            displaySuccessIfTrue(false);
        } catch(Exception e) {
            displaySuccessIfTrue(true);
        }
    }

    private static void test_DeckEquals() {
        System.out.println("Testing PicomonDeck equality...");

        PicomonDeck deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.WATER, 10)     
        });

        PicomonDeck otherDeck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("C", PicomonElement.WATER, 10),
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100)
        });

        try {
            displaySuccessIfTrue(deck.equals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(otherDeck.equals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("A", PicomonElement.FIRE, 50)     
        });

        otherDeck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("A", PicomonElement.FIRE, 50)     
        });

        try {
            displaySuccessIfTrue(deck.equals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(otherDeck.equals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100)
        });

        otherDeck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("C", PicomonElement.WATER, 150)     
        });

        try {
            displaySuccessIfTrue(!deck.equals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherDeck.equals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.WATER, 150)     
        });

        otherDeck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("D", PicomonElement.WATER, 150)     
        });

        try {
            displaySuccessIfTrue(!deck.equals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherDeck.equals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.WATER, 150)     
        });

        otherDeck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.AIR, 150)     
        });

        try {
            displaySuccessIfTrue(!deck.equals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherDeck.equals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }

    private static void test_DeckOrderedEquals() {
        System.out.println("Testing PicomonDeck ordered equality...");

        PicomonDeck deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.WATER, 10)     
        });

        PicomonDeck otherDeck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("C", PicomonElement.WATER, 10),
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100)
        });

        try {
            displaySuccessIfTrue(!deck.orderedEquals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherDeck.orderedEquals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.AIR, 50)     
        });

        otherDeck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.AIR, 50)     
        });

        try {
            displaySuccessIfTrue(deck.orderedEquals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(otherDeck.orderedEquals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100)
        });

        otherDeck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.WATER, 150)     
        });

        try {
            displaySuccessIfTrue(!deck.orderedEquals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherDeck.orderedEquals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.WATER, 150)     
        });

        otherDeck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("C", PicomonElement.WATER, 150)     
        });

        try {
            displaySuccessIfTrue(!deck.orderedEquals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherDeck.orderedEquals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.WATER, 150)
        });

        otherDeck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.WATER, 150)
        });

        try {
            displaySuccessIfTrue(deck.orderedEquals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(otherDeck.orderedEquals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }

    private static void test_DeckShuffle() {
        System.out.println("Testing PicomonDeck shuffle...");

        PicomonDeck deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.WATER, 10)     
        });

        PicomonDeck otherDeck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.WATER, 10)     
        });

        otherDeck.shuffle();
        try {
            displaySuccessIfTrue(!deck.orderedEquals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherDeck.orderedEquals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        try {
            displaySuccessIfTrue(deck.equals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(otherDeck.equals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.WATER, 10),
            new PicomonCard("D", PicomonElement.AIR, 20)
        });

        otherDeck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.WATER, 10),     
            new PicomonCard("D", PicomonElement.AIR, 20)
        });

        otherDeck.shuffle();
        try {
            displaySuccessIfTrue(!deck.orderedEquals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(!otherDeck.orderedEquals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        try {
            displaySuccessIfTrue(deck.equals(otherDeck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(otherDeck.equals(deck));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }

    private static void test_Game() {
        System.out.println("Testing PicomonGame sequence...");

        PicomonDeck deck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100),
            new PicomonCard("C", PicomonElement.WATER, 10)     
        });

        PicomonDeck otherDeck = new PicomonDeck(new PicomonCard[] {
            new PicomonCard("C", PicomonElement.WATER, 10),
            new PicomonCard("A", PicomonElement.FIRE, 50),
            new PicomonCard("B", PicomonElement.EARTH, 100)
        });

        PicomonGame game = new PicomonGame(deck, otherDeck);

        try {
            displaySuccessIfTrue(!game.isMatchOver());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        PicomonGame.Round round = game.playRound();
        try {
            displaySuccessIfTrue(PicomonGame.Player.GYM_LEADER == round.winner);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(new PicomonCard("A", PicomonElement.FIRE, 50).equals(round.gymLeaderCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(new PicomonCard("C", PicomonElement.WATER, 10).equals(round.trainerCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(!game.isMatchOver());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(PicomonGame.Player.GYM_LEADER == game.getLeader());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        round = game.playRound();
        try {
            displaySuccessIfTrue(null == round.winner);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(new PicomonCard("A", PicomonElement.FIRE, 50).equals(round.gymLeaderCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(new PicomonCard("A", PicomonElement.FIRE, 50).equals(round.trainerCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(!game.isMatchOver());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(PicomonGame.Player.GYM_LEADER == game.getLeader());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        
        round = game.playRound();
        try {
            displaySuccessIfTrue(null == round.winner);
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(new PicomonCard("B", PicomonElement.EARTH, 100).equals(round.gymLeaderCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(new PicomonCard("B", PicomonElement.EARTH, 100).equals(round.trainerCard));
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(game.isMatchOver());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(PicomonGame.Player.GYM_LEADER == game.getLeader());
        } catch(Exception e) {
            displaySuccessIfTrue(false);
        }
    }
}
