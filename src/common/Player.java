package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Element implements Serializable  {
	//data members
	private Wall wall= new Wall();
	private Destination des= new Destination();
	private Box box= new Box();
	private Space space= new Space();
	private Image playerImage;


//get set methods
	public Image getPlayerImage() {
		return playerImage;
	}
	public void setPlayerImage(Image playerImage) {
		this.playerImage = playerImage;
	}
	public Wall getWall() {
		return wall;
	}
	public void setWall(Wall wall) {
		this.wall = wall;
	}
	public Destination getDes() {
		return des;
	}
	public void setDes(Destination des) {
		this.des = des;
	}
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}
	public Space getSpace() {
		return space;
	}
	public void setSpace(Space space) {
		this.space = space;
	}

	//default c'tor
	public Player() {
		this.pos.setX(0);
		this.pos.setY(0);
		try {
			playerImage= new Image(new FileInputStream("./resources/SpongeBob.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//c'tor with position
	public Player (Position pos){
		this.setPos(pos);
		try {
			playerImage= new Image(new FileInputStream("./resources/SpongeBob.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	@Override
	public String toString()
	{
		return "A";
	}
@Override
public void draw(GraphicsContext gc, int i, int j, double cellWidth, double cellHeight) {
	gc.drawImage(playerImage, j*cellWidth, i*cellHeight,0.95*cellWidth,0.95*cellHeight);
}
}
