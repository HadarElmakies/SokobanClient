package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Levels")
public class LevelDb {

	@Id
	private String LevelName;

	@Column(name="LevelNumber")
	private int LevelNumber;

	public LevelDb() {
		// TODO Auto-generated constructor stub
	}

	public String getLevelName() {
		return LevelName;
	}

	public int getLevelNumber() {
		return LevelNumber;
	}

	public void setLevelName(String levelName) {
		LevelName = levelName;
	}

	public void setLevelNumber(int levelNumber) {
		LevelNumber = levelNumber;
	}

	@Override
	public String toString() {
		return "LevelDb [LevelName=" + LevelName + ", LevelNumber=" + LevelNumber + "]";
	}

	public LevelDb(int levelNumber) {
		super();

		LevelNumber = levelNumber;
	}




}
