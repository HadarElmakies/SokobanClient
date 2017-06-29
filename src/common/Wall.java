package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall extends Element implements Serializable {
//data members
	private Image wallImage;

	//default c'tor
	public Wall(){
		this.pos.setX(0);
		this.pos.setY(0);
		try {
			wallImage= new Image(new FileInputStream("./resources/seabbbbbb.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//c'tor with position
public Wall(Position pos) {
	this.setPos(pos);
	try {
		wallImage= new Image(new FileInputStream("./resources/sea2.jpg"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	}
@Override
public String toString()
{
	return "#";
}
@Override
public void draw(GraphicsContext gc, int i, int j, double cellWidth, double cellHeight) {
	gc.drawImage(wallImage, j*cellWidth, i*cellHeight,cellWidth,cellHeight);
}

}
