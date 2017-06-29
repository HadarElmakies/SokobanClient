package model.data;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import common.Level;

public class MyXMLLevelSaver implements LevelSaver {
	Level lev;
	
	public MyXMLLevelSaver() {
		this.lev= null;
	}
	
	public MyXMLLevelSaver(Level lev) {
		this.lev= lev;
	}


	@Override
	public void saveLevel(OutputStream out,Level lev) {
		BufferedOutputStream bos= new BufferedOutputStream(out);
		XMLEncoder encoderi= new XMLEncoder(bos);
	
		encoderi.writeObject(lev.getDynamicObjects());
		encoderi.close();
		
	}

}
