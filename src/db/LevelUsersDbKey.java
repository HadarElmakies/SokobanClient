package db;

import java.io.Serializable;


import javax.persistence.Embeddable;
@Embeddable
public class LevelUsersDbKey implements Serializable {

	private int UserId;
	private String LevelName;


	public LevelUsersDbKey() {
		// TODO Auto-generated constructor stub
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((LevelName == null) ? 0 : LevelName.hashCode());
		result = prime * result + UserId;
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LevelUsersDbKey other = (LevelUsersDbKey) obj;
		if (LevelName == null) {
			if (other.LevelName != null)
				return false;
		} else if (!LevelName.equals(other.LevelName))
			return false;
		if (UserId != other.UserId)
			return false;
		return true;
	}




	public LevelUsersDbKey(int userId, String levelName) {
		super();
		this.UserId = userId;
		this.LevelName = levelName;
	}
	public int getUserId() {
		return UserId;
	}
	public String getLevelName() {
		return LevelName;
	}
	public void setUserId(int userId) {
		this.UserId = userId;
	}
	public void setLevelName(String levelName) {
		this.LevelName = levelName;
	}


}
