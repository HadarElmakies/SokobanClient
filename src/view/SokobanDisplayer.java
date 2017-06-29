package view;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import common.Element;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class SokobanDisplayer extends Canvas {
	ArrayList<ArrayList<Element>> sokoboanData;
	int counter = 0;

	private StringProperty wallFileName;
	int cCol, cRow;

	public SokobanDisplayer() {
		wallFileName = new SimpleStringProperty();
		cCol = 0;
		cRow = 2;
	}

	public void setCharacterPosition(int row, int col) {
		cCol = col;
		cRow = row;
		redraw();
	}

	public int getcCol() {
		return cCol;
	}

	public int getcRow() {
		return cRow;
	}

	public String getWallFileName() {
		return wallFileName.get();
	}

	public void setWallFileName(String wallFileName) {
		this.wallFileName.set(wallFileName);
	}

	public void startRedraw() {

		double w=getWidth();
		double h= getHeight();
		Image startMenue= null;
		GraphicsContext gc = this.getGraphicsContext2D();
		try{
			gc.clearRect(0,0 , w, h);
			startMenue=new Image(new FileInputStream("./resources/enter.png"));
			gc.drawImage(startMenue, 0, 0,w,h);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}

	}

	public ArrayList<ArrayList<Element>> getSokoboanData() {
		return sokoboanData;
	}

	public void setSokoboanData(ArrayList<ArrayList<Element>> sokoboanData) {
		if (this.sokoboanData != sokoboanData)
			counter++;
		this.sokoboanData = sokoboanData;
		redraw();
	}

	public void redraw() {
		if (sokoboanData == null)
			return;

		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(javafx.scene.paint.Color.WHITESMOKE);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());

		int rows = sokoboanData.size();
		int cols = sokoboanData.get(0).size();
		double cellWidth = getWidth() / cols;
		double cellHeight = getHeight() / rows;

		// gc.clearRect(0, 0, cellWidth, cellHeight);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (sokoboanData.get(i).get(j) != null)
					sokoboanData.get(i).get(j).draw(gc, i, j, cellWidth, cellHeight);
			}
		}
	}

}
