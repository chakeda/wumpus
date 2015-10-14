package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Grid;

public class GridTest {
	
	// change!

	// test Wumpus and Blood code
	@Test
	public void testGridWumpusBloodAndGoop() {
		
		// test no wrap, fall in pit, test toString methods
		Grid grid = new Grid();
		grid.setWumpus(5, 5);
		assertEquals(grid.getTileAt(5, 5), "W");
		assertEquals(grid.getTileAt(5+1, 5), "B");
		assertEquals(grid.getTileAt(5+2, 5), "B");
		assertEquals(grid.getTileAt(5, 5+1), "B");
		assertEquals(grid.getTileAt(5, 5+2), "B");
		assertEquals(grid.getTileAt(5-1, 5), "B");
		assertEquals(grid.getTileAt(5-2, 5), "B");
		assertEquals(grid.getTileAt(5, 5-1), "B");
		assertEquals(grid.getTileAt(5, 5-2), "B");
		assertEquals(grid.getTileAt(5+1, 5+1), "B");
		assertEquals(grid.getTileAt(5+1, 5-1), "B");
		assertEquals(grid.getTileAt(5-1, 5+1), "B");
		assertEquals(grid.getTileAt(5-1, 5+1), "B");
		grid.setPit(5, 8);
		assertEquals(grid.getTileAt(5,7), "G");
		grid.setHunter(5,9);
		grid.moveHunter("left");
		assertEquals(grid.checkDeath(5, 8), "You fell in a pit and died");
		String mapAsString = grid.gridToString();
		String mapAsFog = grid.fogToString();
		
		// test wrap at NW corner, move all directions, and get eaten
		grid = new Grid();
		grid.setWumpus(0, 0);
		assertEquals(grid.getTileAt(0, 0), "W");
		assertEquals(grid.getTileAt(9, 0), "B");
		assertEquals(grid.getTileAt(0, 1), "B");
		assertEquals(grid.getTileAt(0, 9), "B");
		assertEquals(grid.getTileAt(1, 0), "B");
		assertEquals(grid.getTileAt(8, 0), "B");
		assertEquals(grid.getTileAt(0, 8), "B");
		assertEquals(grid.getTileAt(0, 2), "B");
		assertEquals(grid.getTileAt(2, 0), "B");
		assertEquals(grid.getTileAt(1, 1), "B");
		assertEquals(grid.getTileAt(9, 1), "B");
		assertEquals(grid.getTileAt(1, 9), "B");
		assertEquals(grid.getTileAt(9, 9), "B");
		grid.setPit(9,1);
		assertEquals(grid.getTileAt(9,0), "G");
		grid.setHunter(3,0);
		grid.moveHunter("up");
		assertEquals(grid.getHint(), "You feel a chill going up your spine");
		grid.moveHunter("down");
		grid.moveHunter("left");
		grid.moveHunter("right");
		grid.moveHunter("up");
		assertEquals(grid.checkDeath(1, 0), "");
		grid.moveHunter("up");
		assertEquals(grid.checkDeath(0, 0), "You got eaten by the Wumpus");
		grid.setFog();

		// test wrap at (1,1), get all sorts of hints, shoot arrows everywhere
		grid = new Grid();
		grid.setWumpus(1, 1);
		assertEquals(grid.getTileAt(1, 1), "W");
		assertEquals(grid.getTileAt(2, 1), "B");
		assertEquals(grid.getTileAt(1, 2), "B");
		assertEquals(grid.getTileAt(0, 1), "B");
		assertEquals(grid.getTileAt(1, 0), "B");
		assertEquals(grid.getTileAt(2, 2), "B");
		assertEquals(grid.getTileAt(0, 0), "B");
		assertEquals(grid.getTileAt(2, 0), "B");
		assertEquals(grid.getTileAt(0, 2), "B");
		assertEquals(grid.getTileAt(3, 1), "B");
		assertEquals(grid.getTileAt(1, 3), "B");
		assertEquals(grid.getTileAt(9, 1), "B");
		assertEquals(grid.getTileAt(1, 9), "B");
		grid.setPit(5,5);
		grid.setPit(2,3);
		grid.setPit(9,0);
		assertEquals(grid.getTileAt(2, 2), "G");
		assertEquals(grid.getTileAt(1, 3), "G");
		assertEquals(grid.getTileAt(0, 0), "G");
		assertEquals(grid.getTileAt(9, 1), "G");
		grid.setHunter(9, 7);
		grid.moveHunter("right");
		assertEquals(grid.getHint(), "You smell something foul");
		for (int i=0; i<8; i++){
			grid.moveHunter("up");
		}
		assertEquals(grid.getHint(), "You feel a chill going up your spine");
		for (int i=0; i<4; i++){
			grid.moveHunter("left");
		}
		assertEquals(grid.getHint(), "You feel a chill going up your spine as you smell somthing foul");
		assertEquals(grid.fireArrow("left"), "You have slayed the Wumpus!");
		grid.moveHunter("left");
		grid.moveHunter("left");
		grid.moveHunter("down");
		grid.moveHunter("left");
		assertEquals(grid.fireArrow("down"), "You have slayed the Wumpus!");
		assertEquals(grid.fireArrow("right"), "");
		grid.moveHunter("left");
		assertEquals(grid.fireArrow("up"), "");

		// test wumpus placement like a mother
		grid = new Grid();
		grid.setWumpus(0, 9);
		assertEquals(grid.getTileAt(0, 9), "W");
		assertEquals(grid.getTileAt(0, 8), "B");
		assertEquals(grid.getTileAt(0, 7), "B");
		assertEquals(grid.getTileAt(1, 8), "B");
		assertEquals(grid.getTileAt(1, 9), "B");
		assertEquals(grid.getTileAt(2, 9), "B");
		assertEquals(grid.getTileAt(0, 0), "B");
		assertEquals(grid.getTileAt(1, 0), "B");
		assertEquals(grid.getTileAt(0, 1), "B");
		assertEquals(grid.getTileAt(9, 0), "B");
		assertEquals(grid.getTileAt(9, 9), "B");
		assertEquals(grid.getTileAt(9, 8), "B");
		assertEquals(grid.getTileAt(8, 9), "B");
		grid.setPit(1,7);
		assertEquals(grid.getTileAt(0, 7), "G");
		assertEquals(grid.getTileAt(1, 8), "G");
		
		// test it even more 
		grid = new Grid();
		grid.setWumpus(9, 0);
		assertEquals(grid.getTileAt(9, 0), "W");
		assertEquals(grid.getTileAt(0, 0), "B");
		assertEquals(grid.getTileAt(0, 1), "B");
		assertEquals(grid.getTileAt(1, 0), "B");
		assertEquals(grid.getTileAt(9, 1), "B");
		assertEquals(grid.getTileAt(9, 2), "B");
		assertEquals(grid.getTileAt(8, 1), "B");
		assertEquals(grid.getTileAt(8, 0), "B");
		assertEquals(grid.getTileAt(7, 0), "B");
		assertEquals(grid.getTileAt(9, 9), "B");
		assertEquals(grid.getTileAt(9, 8), "B");
		assertEquals(grid.getTileAt(8, 9), "B");
		assertEquals(grid.getTileAt(0, 9), "B");
		grid.setPit(7,1);
		assertEquals(grid.getTileAt(7, 0), "G");
		assertEquals(grid.getTileAt(8, 1), "G");

		// test it even more!!!
		grid = new Grid();
		grid.setWumpus(9, 9);
		assertEquals(grid.getTileAt(9, 9), "W");
		assertEquals(grid.getTileAt(9, 8), "B");
		assertEquals(grid.getTileAt(8, 9), "B");
		assertEquals(grid.getTileAt(9, 7), "B");
		assertEquals(grid.getTileAt(7, 9), "B");
		assertEquals(grid.getTileAt(8, 8), "B");
		assertEquals(grid.getTileAt(0, 0), "B");
		assertEquals(grid.getTileAt(9, 0), "B");
		assertEquals(grid.getTileAt(8, 0), "B");
		assertEquals(grid.getTileAt(9, 1), "B");
		assertEquals(grid.getTileAt(0, 9), "B");
		assertEquals(grid.getTileAt(0, 8), "B");
		assertEquals(grid.getTileAt(1, 9), "B");
		
		// test Pit wraps
		grid = new Grid();
		grid.setPit(0,0);
		assertEquals(grid.getTileAt(0, 0), "P");
		assertEquals(grid.getTileAt(9, 0), "S");
		assertEquals(grid.getTileAt(0, 9), "S");
		assertEquals(grid.getTileAt(1, 0), "S");
		assertEquals(grid.getTileAt(0, 1), "S");
		
		grid = new Grid();
		grid.setPit(9,0);
		assertEquals(grid.getTileAt(9, 0), "P");
		assertEquals(grid.getTileAt(0, 0), "S");
		assertEquals(grid.getTileAt(8, 0), "S");
		assertEquals(grid.getTileAt(9, 1), "S");
		assertEquals(grid.getTileAt(9, 9), "S");
		
		grid = new Grid();
		grid.setPit(0,9);
		assertEquals(grid.getTileAt(0, 9), "P");
		assertEquals(grid.getTileAt(0, 8), "S");
		assertEquals(grid.getTileAt(1, 9), "S");
		assertEquals(grid.getTileAt(0, 0), "S");
		assertEquals(grid.getTileAt(9, 9), "S");
		
		grid = new Grid();
		grid.setPit(9,9);
		assertEquals(grid.getTileAt(9, 9), "P");
		assertEquals(grid.getTileAt(9, 8), "S");
		assertEquals(grid.getTileAt(8, 9), "S");
		assertEquals(grid.getTileAt(0, 9), "S");
		assertEquals(grid.getTileAt(9, 0), "S");

	}

}
