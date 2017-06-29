package model.data;

import java.io.OutputStream;

import common.Level;

public interface LevelSaver {
	
	void saveLevel(OutputStream out,Level lev);
	

}
