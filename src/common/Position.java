package common;
import java.io.Serializable;

public class Position implements Serializable {
	
	private int x,y;
	
	public Position() {
		this.x=0;
		this.y=0;
	}
	
	public Position(int x,int y){
		setX(x);
		setY(y);	
	}


	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	 public String toString(){
		 return "("+ this.x +","+this.y+ ")";
	 }
	

}
