package db;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="Users")

public class UserDb {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int UserId;
	//private List <LevelDb> levels= new ArrayList<LevelDb>();

	//public List<LevelDb> getLevels() {
	//	return levels;
	//}

	//public void setLevels(List<LevelDb> levels) {
	//	this.levels = levels;
	//}
	@Column(name="FirstName")
	private String firstName;

	@Column(name="LastName")
	private String lastName;




//אם לא מגדירים כלום זה יהיה לייזי
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="UserId")
	private List<LevelUsersDb> levels;
	//@JoinTable(name="Level_Users",catalog="SokobanDb",joinColumns={@JoinColumn(name="UserId")},inverseJoinColumns={@JoinColumn(name="LevelName")})
	public int getUserId() {
		return UserId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}




	public List<LevelUsersDb> getLevels() {
		return levels;
	}

	public void setLevels(List<LevelUsersDb> levels) {
		this.levels = levels;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public UserDb(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		levels= new ArrayList<LevelUsersDb>();

	}
public UserDb() {
	levels= new ArrayList<LevelUsersDb>();


}

@Override
public String toString() {
	return "UserDb [UserId=" + UserId + ", firstName=" + firstName + ", lastName=" + lastName + ", levels=" + levels
			+ "]";
}





}
