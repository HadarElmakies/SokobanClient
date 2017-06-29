package model.data;

import common.Level;

public interface Move {
	
	public void moveUp(Level lev);
	public void moveDown(Level lev);
	public void moveRight(Level lev);
	public void moveLeft(Level lev);
	
}
