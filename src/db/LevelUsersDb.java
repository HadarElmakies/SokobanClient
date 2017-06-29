package db;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="Level_Users")
public class LevelUsersDb {
@EmbeddedId
private LevelUsersDbKey key;
private int Steps;
private Time time;


public LevelUsersDb(int UserId,String levelName){
	super();
	this.key= new LevelUsersDbKey(UserId, levelName);

}

public LevelUsersDb() {
	this.key= new LevelUsersDbKey();
}

public int getSteps() {
	return Steps;
}

public Time getTime() {
	return time;
}

public void setSteps(int steps) {
	this.Steps = steps;
}

public void setTime(Time time) {
	this.time = time;
}

public int getUserId(){
	return key.getUserId();
}

public String levelName(){
	return key.getLevelName();
}




}
