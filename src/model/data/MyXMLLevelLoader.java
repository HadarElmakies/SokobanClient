package model.data;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.InputStream;

import common.Level;

public class MyXMLLevelLoader implements LevelLoader {

	@SuppressWarnings("resource")
	@Override
	
	public Level loadLevel(InputStream in) {
		Level lev= null;
		XMLDecoder decoder = null;
		decoder= new XMLDecoder(new BufferedInputStream(in));
		lev= (Level) decoder.readObject();
		return lev;
	}


}

