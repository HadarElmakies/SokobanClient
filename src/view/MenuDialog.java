package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.Optional;

import db.DbManagerLevel;
import db.DbManagerLevelUsers;
import db.DbManagerUser;
import db.LevelDb;
import db.LevelUsersDb;
import db.UserDb;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class MenuDialog {


DbManagerLevelUsers lu;
	DbManagerLevel levelManager;
	DbManagerUser userManager;
	LevelUsersDb levelsUsers;
	int steps;
	Time time;
	UserDb user;
	ScoreBoard scoreBoard;
	String levelName;
	LevelDb level;






public DbManagerLevelUsers getLu() {
		return lu;
	}





	public LevelDb getLevel() {
		return level;
	}





	public void setLu(DbManagerLevelUsers lu) {
		this.lu = lu;
	}





	public void setLevel(LevelDb level) {
		this.level = level;
	}





public UserDb getUser() {
		return user;
	}





	public ScoreBoard getScoreBoard() {
		return scoreBoard;
	}





	public String getLevelName() {
		return levelName;
	}





	public void setUser(UserDb user) {
		this.user = user;
	}





	public void setScoreBoard(ScoreBoard scoreBoard) {
		this.scoreBoard = scoreBoard;
	}





	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}





@FXML
void cancle(){
	Platform.exit();
}





	public Time getTime() {
		return time;
	}



	public void setTime(Time time) {
		this.time = time;
	}



	public DbManagerLevel getLevelManager() {
		return levelManager;
	}



	public DbManagerUser getUserManager() {
		return userManager;
	}



	public int getSteps() {
		return steps;
	}



	public void setLevelManager(DbManagerLevel levelManager) {
		this.levelManager = levelManager;
	}



	public void setUserManager(DbManagerUser userManager) {
		this.userManager = userManager;
	}

	private void confirmartionDialog() {
		Alert alert= new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setContentText("Do you want to update your name? ");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get()==ButtonType.OK)
			updateDialog();



	}
	public void updateDialog(){
		String name;
		Dialog<Pair<String, String>> dialog = new Dialog<Pair<String, String>>();
		dialog.setTitle("Updating.. ");

		try {
			dialog.setGraphic(new ImageView(new Image(new FileInputStream("./resources/update.gif"))));
			ButtonType okButton = new ButtonType("update", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 150, 10, 10));
			TextField firstName = new TextField();
			firstName.setPromptText("first name:");
			TextField lastName = new TextField();
			lastName.setPromptText("last name:");
			grid.add(new Label("first name"), 0, 0);
			grid.add(firstName, 1, 0);
			grid.add(new Label("last name"), 0, 1);
			grid.add(lastName, 1, 1);

			dialog.getDialogPane().setContent(grid);



			dialog.setResultConverter(dialogButton->{
				if(dialogButton==okButton){



					userManager.updateFirstName(this.user.getUserId(), firstName.getText());
					userManager.updateLastName(this.user.getUserId(), lastName.getText());
					return new Pair<>(firstName.getText(),lastName.getText());



				}
				return null;
			});
		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Optional<Pair<String, String>> result = dialog.showAndWait();

		// if(result.ifPresent())
		// System.out.println("your name: "+result.get());
		// levelDb.setName(result.get());





	}



	public void setSteps(int steps) {
		this.steps = steps;
	}
@FXML
public void signIn(){
	String name;
	Dialog<Pair<String, String>> dialog = new Dialog<Pair<String, String>>();
	dialog.setTitle("registration ");
	dialog.setHeaderText("join");
	try {
		dialog.setGraphic(new ImageView(new Image(new FileInputStream("./resources/reg.png"))));
		ButtonType okButton = new ButtonType("ok", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		TextField firstName = new TextField();
		firstName.setPromptText("first name:");
		TextField lastName = new TextField();
		lastName.setPromptText("last name:");
		grid.add(new Label("first name"), 0, 0);
		grid.add(firstName, 1, 0);
		grid.add(new Label("last name"), 0, 1);
		grid.add(lastName, 1, 1);

		dialog.getDialogPane().setContent(grid);
		//String homo = countingSteps.getText().substring(7);

		//System.out.println(homo);
		//String stu= countingSteps.getText()+7;
		//System.out.println(stu);
		//int steps= Integer.parseInt(homo);


		//int steps= Integer.valueOf(countingSteps.getText());
		//System.out.println(steps);
		//java.sql.Time.valueOf(timerGame.getText());
		//int min2=(int)(this.min);
		//int sec2=(int)(this.sec);

		//Time time=new Time(0, min2, sec2);
		//System.out.println(time);
		//java.sql.Time timeValue = new java.sql.Time(formatter.parse(fajr_prayertime).getTime());
		System.out.println(levelName);

		levelsUsers= new LevelUsersDb(user.getUserId(),levelName);
		dialog.setResultConverter(dialogButton->{
			if(dialogButton==okButton){

				this.user = new UserDb(firstName.getText(), lastName.getText());
				userManager.addUser(user);

				level.setLevelName(levelName);
				System.out.println(levelName);





				//levelsUsers.setSteps(steps);
				//levelsUsers.setTime(time);
				userManager.theLevelUsers(level, user,steps,time);
				//userManager.addLevelUser(levelsUsers);
				//userManager.addLevel(level);

				//userManager.updateTime(user.getUserId(), time);


				//userManager.updateSteps(user.getUserId(), steps);




				confirmartionDialog();

				//confirmartionDialog();
				//managerUser.printUsers();
				return new Pair<>(firstName.getText(),lastName.getText());



			}
			return null;
		});
	}

	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Optional<Pair<String, String>> result = dialog.showAndWait();


}

@FXML
void scoreBoard(){

	try {
		Stage window= new Stage();
		FXMLLoader loader= new FXMLLoader();
		loader.setLocation(getClass().getResource("ScoreBoard.fxml"));

		Pane layout;
		layout = loader.load();
		Scene scene = new Scene(layout);
		 scoreBoard = loader.getController();
		 scoreBoard.setLevelName(levelName);
		 scoreBoard.setLu(new LevelUsersDb(user.getUserId(), level.getLevelName()));


		 //menu.setSteps(steps);


		window.setScene(scene);
		window.show();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}




}

	public MenuDialog() {
		levelManager = new DbManagerLevel();
		userManager= new DbManagerUser();
		lu= new DbManagerLevelUsers();
		user= new UserDb();
		level= new LevelDb();

		this.scoreBoard= new ScoreBoard();

	}




}
