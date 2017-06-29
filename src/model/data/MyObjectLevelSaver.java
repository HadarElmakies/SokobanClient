package model.data;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import common.Level;

public class MyObjectLevelSaver implements LevelSaver {
	private Level lev;
	public MyObjectLevelSaver() {
		this.lev= null;
	}


	@Override
	public void saveLevel(OutputStream out,Level lev) {
		 ObjectOutputStream oos;
		try {
			BufferedOutputStream bos= new BufferedOutputStream(out);
			
			oos = new ObjectOutputStream(bos);
			oos.writeObject(lev);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
		
	}
}
