package model.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import common.Level;


//object from given file
public class MyObjectLevelLoader implements LevelLoader {

	@Override
	public Level loadLevel(InputStream in) {
	
		Level lev= null;
		 try {
			
			ObjectInputStream ois = new ObjectInputStream(in);
			lev = (Level) ois.readObject();
			
			 ois.close();
			 
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		 return lev;
		
	}
	
}

