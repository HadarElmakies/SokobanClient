package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Space extends Element implements Serializable {

	public Space(){
		this.pos.setX(0);
		this.pos.setY(0);
	}

public Space(Position pos) {
		this.setPos(pos);
		

	}
@Override
public String toString()
{
	return " ";
}

@Override
public void draw(GraphicsContext gc, int i, int j, double cellWidth, double cellHeight) {
	


}
}
