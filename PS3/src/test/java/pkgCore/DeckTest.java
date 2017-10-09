package pkgCore;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.DeckException;

public class DeckTest {

	@Test
	public void TestEmptyDeck() throws DeckException{
		Deck deck = new Deck();
		
		//Draws 51 cards, so there is only 1 left
		for (int i=0; i < 51; i++)
			deck.Draw();
		
		//draws and tests last card
		assertEquals(deck.Draw()==null,false);
		
		//try to draw an empty deck
		assertNull(deck.Draw());
	}
	
	@Test
	public void TestDrawSuit() {
		Deck deck = new Deck();
		
		assertEquals(eSuit.CLUBS, deck.Draw(eSuit.CLUBS).geteSuit());
		assertEquals(eSuit.DIAMONDS == deck.Draw(eSuit.CLUBS).geteSuit(), false);
		
		// loop removes rest of CLUBS for next test
		for(int i=0;i<11;i++)
			deck.Draw(eSuit.CLUBS);
		
		assertEquals(deck.Draw(eSuit.CLUBS),null);
	}
	
	@Test
	public void TestDrawRank() {
		Deck deck = new Deck();
		
		assertEquals(eRank.ACE, deck.Draw(eRank.ACE).geteRank());
		assertEquals(eRank.EIGHT == deck.Draw(eRank.ACE).geteRank(), false);
		
		// loop removes the rest of the ACEs for next test
		for(int i=0;i<2;i++)
			deck.Draw(eRank.ACE);
		
		assertEquals(deck.Draw(eRank.ACE),null);
	}
	
	@Test
	public void TestDeckRankCount() {
		Deck deck = new Deck();
		
		assertEquals(deck.deckRankCount(eRank.EIGHT), 4);
		
		deck.Draw(eRank.EIGHT);
		deck.Draw(eRank.EIGHT);
		
		assertEquals(deck.deckRankCount(eRank.EIGHT), 2);
		
		deck.Draw(eRank.EIGHT);
		deck.Draw(eRank.EIGHT);
		
		assertEquals(deck.deckRankCount(eRank.EIGHT), 0);
		
	}
	
	@Test
	public void TestDeckSuitCount() {
		Deck deck = new Deck();
		
		assertEquals(deck.deckSuitCount(eSuit.CLUBS), 13);
		
		for (int i = 0; i<6; i++)
			deck.Draw(eSuit.CLUBS);
		
		assertEquals(deck.deckSuitCount(eSuit.CLUBS), 7);
		
		for (int i = 0; i<7; i++)
			deck.Draw(eSuit.CLUBS);
		
		assertEquals(deck.deckSuitCount(eSuit.CLUBS), 0);
	}

}
