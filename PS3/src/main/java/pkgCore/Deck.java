package pkgCore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.*;

import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.DeckException;

public class Deck {

	private ArrayList<Card> cardsInDeck = new ArrayList();

	public Deck() {
		for (eSuit eSuit : eSuit.values()) {
			for (eRank eRank : eRank.values()) {
				cardsInDeck.add(new Card(eSuit, eRank));
			}
		}
		Collections.shuffle(cardsInDeck);
	}

	public Card Draw() {
		try {
			if (cardsInDeck.size()==0)
				throw new DeckException(this);
			
			return cardsInDeck.remove(0);
		}
		catch (DeckException e) {
			System.out.println("DeckException: There are no more cards in the deck.");
			return null;
		}
	}
	
	public Card Draw(eSuit eSuit) {
		Card card = cardsInDeck.stream()
				.filter(c->c.geteSuit() == eSuit)
				.findFirst()
				.orElse(null);
		cardsInDeck.remove(card);
		
		if (card == null)
			System.out.println("There are no more of this suit.");
		
		return card;
	}

	public Card Draw(eRank eRank) {
		Card card = cardsInDeck.stream()
				.filter(c->c.geteRank() == eRank)
				.findFirst()
				.orElse(null);
		cardsInDeck.remove(card);
		
		if (card == null)
			System.out.println("There are no more of this rank.");
			
		return card;
	}
	
	public int deckSuitCount(eSuit eSuit) {
		int remaining = (int) cardsInDeck.stream()
				.filter(c->c.geteSuit() == eSuit)
				.count();
		
		return remaining;
	}
	
	public int deckRankCount(eRank eRank) {
		int remaining = (int) cardsInDeck.stream()
				.filter(c->c.geteRank() == eRank)
				.count();
		
		return remaining;
	}
	
	public int deckCardCount(Card Card) {
		int remaining = (int) cardsInDeck.stream()
				.filter(c->c == Card)
				.count();
		
		if (remaining > 1) // in case, more than 1 deck are used
			remaining = 1;
		
		return remaining;
	}
	
}
