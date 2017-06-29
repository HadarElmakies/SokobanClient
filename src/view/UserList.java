package view;



public class UserList {

	private String firstNameCol ;
	private String lastNameCol ;
	private String time ;
	private String steps ;
	private String levelNameCol;
	private String PlayerNumber;




	public String getLevelNameCol() {
		return levelNameCol;
	}
public UserList() {
	// TODO Auto-generated constructor stub
}

	public void setLevelNameCol(String levelNameCol) {
		this.levelNameCol = levelNameCol;
	}

	public UserList(String firstNameCol, String lastNameCol){
		this.firstNameCol=firstNameCol;
		this.lastNameCol=lastNameCol;
	}


	public UserList(String firstNameCol, String lastNameCol,String time, String steps,String levelNameCol,String PlayerNumber) {
		super();
		this.firstNameCol = firstNameCol;
		this.lastNameCol = lastNameCol;
		this.time = time;
		this.steps = steps;
		this.levelNameCol=levelNameCol;
		this.PlayerNumber=PlayerNumber;


	}


	public String getFirstNameCol() {
		return firstNameCol;
	}
	public String getLastNameCol() {
		return lastNameCol;
	}

	public String getTime() {
		return time;
	}
	public String getSteps() {
		return steps;
	}
	public void setFirstNameCol(String firstNameCol) {
		this.firstNameCol = firstNameCol;
	}
	public void setLastNameCol(String lastNameCol) {
		this.lastNameCol = lastNameCol;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}


	public String getLevelName() {
		return levelNameCol;
	}


	public String getPlayerNumber() {
		return PlayerNumber;
	}


	public void setLevelName(String levelName) {
		levelNameCol = levelName;
	}


	public void setPlayerNumber(String playerNumber) {
		PlayerNumber = playerNumber;
	}





}
