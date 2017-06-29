package common;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;

public abstract class Element implements Serializable {
	//position of the element
Position pos = new Position();
//paints the character
public abstract void draw(GraphicsContext gc,int i,int j,double cellWidth,double cellHeight);

public Position getPos() {
	return pos;
}

public void setPos(Position pos) {
	this.pos = pos;
}



}
