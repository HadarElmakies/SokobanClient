package model.data;

import java.io.InputStream;

import common.Level;

public interface LevelLoader {
	
	public Level loadLevel(InputStream in);

}

/** 
 * A. The separation between the data maker to the data representer 
 * was made by the level class responsibility is to handle the level
 * properties and the level loader interface responsibility is to handle 
 * the loading of the level from a file.
 * 
 * B. This separation allows any kind of level loader
 * to implement the level loader interface and by that maintain 
 * the open-close principle.
 * 
 * C. Input stream is an abstract class, a superclass of all
 * classes representing an input stream of bytes so it maintain the 
 * Liskov substitution principle.
 * 
 * D. We used the input stream class because it is generic 
 * and we don't want to use a specific type, such as String.
 * As a result the string file name is not generic enough.

 */

