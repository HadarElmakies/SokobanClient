package view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.omg.CORBA.UserException;

import com.sun.glass.ui.Application;

import db.DbManager;
import db.DbManagerLevel;
import db.DbManagerUser;
import db.LevelDb;
import db.LevelUsersDb;
import db.UserDb;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.data.LevelSaver;

public class ScoreBoard extends DbManager implements Initializable {

	public LevelUsersDb getLu() {
		return lu;
	}

	public void setLu(LevelUsersDb lu) {
		this.lu = lu;
	}

	LevelUsersDb lu;
	ScorePlayer s;
	DbManagerLevel levelManager;
	DbManagerUser userManager;
	int steps1;
	Time time1;
	UserDb user;
	String levelName;

	public DbManagerLevel getLevelManager() {
		return levelManager;
	}

	public DbManagerUser getUserManager() {
		return userManager;
	}

	public int getSteps1() {
		return steps1;
	}

	public Time getTime1() {
		return time1;
	}

	public UserDb getUser() {
		return user;
	}

	public String getLevelName() {
		return levelName;
	}

	public Connection getCon() {
		return con;
	}

	public PreparedStatement getPst() {
		return pst;
	}

	public ResultSet getRs() {
		return rs;
	}

	public ObservableList<UserList> getData() {
		return data;
	}

	public TableView<UserList> getPlayersTable() {
		return playersTable;
	}

	public TableColumn<?, ?> getFirstNameCol() {
		return firstNameCol;
	}

	public TableColumn<?, ?> getLastNameCol() {
		return lastNameCol;
	}

	public TableColumn<?, ?> getPlayerNumber() {
		return playerNumber;
	}

	public TableColumn<?, ?> getTime() {
		return time;
	}

	public TableColumn<?, ?> getSteps() {
		return steps;
	}

	public void setLevelManager(DbManagerLevel levelManager) {
		this.levelManager = levelManager;
	}

	public void setUserManager(DbManagerUser userManager) {
		this.userManager = userManager;
	}

	public void setSteps1(int steps1) {
		this.steps1 = steps1;
	}

	public void setTime1(Time time1) {
		this.time1 = time1;
	}

	public void setUser(UserDb user) {
		this.user = user;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public void setPst(PreparedStatement pst) {
		this.pst = pst;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public void setData(ObservableList<UserList> data) {
		this.data = data;
	}

	public void setPlayersTable(TableView<UserList> playersTable) {
		this.playersTable = playersTable;
	}

	private Connection con = null;
	private PreparedStatement pst = null;

	private ResultSet rs = null;
	private ObservableList<UserList> data;
	private ObservableList<UserList> filterData;
	private ObservableList<String> choicer = FXCollections.observableArrayList("Time", "Steps");

	@FXML
	private TableView<UserList> playersTable;
	@FXML
	private TableColumn<UserList, String> firstNameCol;
	@FXML
	private TableColumn<UserList, String> lastNameCol;

ScorePlayer player;
	@FXML
	private TableColumn<UserList, String> playerNumber;
	@FXML
	private TableColumn<UserList, String> levelNameCol;
	@FXML
	private TableColumn<UserList, String> time;
	@FXML
	private TableColumn<UserList, String> steps;
	@FXML
	private TextField filter;
	@FXML
	private TextField idField;
	@FXML
	private ComboBox<String> choise;

	public void present() {

	}

	@FXML
	public void handle(ActionEvent event) {
		choise.setOnAction((motek) -> {
			String selectedOption = choise.getSelectionModel().getSelectedItem();
			System.out.println("ComboBox Action (selected: " + selectedOption.toString() + ")");
			if (selectedOption.toString().equals("Steps")) {
				data.sort(new Comparator<UserList>() {

					@Override
					public int compare(UserList o1, UserList o2) {
						int steps1 = Integer.parseInt(o1.getSteps());
						int steps2 = Integer.parseInt(o2.getSteps());
						return -(steps1 - steps2);
					}
				});
				playersTable.setItems(data);
			}

		});

	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choise.setItems(choicer);
		setCellTable();
		loadDataFromDataBase();
		// tableSortOrder();
		playersTable.setItems(filterData);


		filter.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				updateFilterData();

			}

		});

		choise.setOnAction((motek) -> {
			this.filter.requestFocus();
			String selectedOption = choise.getSelectionModel().getSelectedItem();
			System.out.println("ComboBox Action (selected: " + selectedOption.toString() + ")");
			if (selectedOption.toString().equals("Steps")) {

				data.sort(new Comparator<UserList>() {
					@Override
					public int compare(UserList o1, UserList o2) {
						int steps1 = Integer.parseInt(o1.getSteps());
						int steps2 = Integer.parseInt(o2.getSteps());

						return (steps1 - steps2);
					}

				});
				playersTable.setItems(data);
				select();
				filter.requestFocus();

			} else if (selectedOption.toString().equals("Time")) {
				data.sort(new Comparator<UserList>() {

					@Override
					public int compare(UserList o1, UserList o2) {
						if (!o1.getTime().toString().equals("null") && !o2.getTime().toString().equals("null")) {
							String[] hourmin = o1.getTime().split(":");
							String[] hourmin2 = o2.getTime().split(":");
							System.out.println(hourmin[0].toString());
							int hour = Integer.parseInt(hourmin[0]);
							int mins = Integer.parseInt(hourmin[1]);
							int sec = Integer.parseInt(hourmin[2]);

							int time1 = hour * 3600 + mins * 60 + sec;
							System.out.println(time1);

							int hour2 = Integer.parseInt(hourmin2[0]);
							int mins2 = Integer.parseInt(hourmin2[1]);
							int sec2 = Integer.parseInt(hourmin2[2]);
							int time2 = hour2 * 3600 + mins2 * 60 + sec2;
							System.out.println(time2);
							return (time1 - time2);
						} else
							return -1;
					}

				});
				playersTable.setItems(data);
				select();

			}

		});
		setPlayerLevelsFromTable();


		/*
		 * filter=new TextField(); FilteredList<UserList> filterData = new
		 * FilteredList<>(data,p->true);
		 * filter.textProperty().addListener((observable,oldValue,newValue)->
		 * {filterData.setPredicate(user->{ if(newValue==null ||
		 * newValue.isEmpty()){return true;} String lowerCaseFilter=
		 * newValue.toLowerCase();
		 * if(user.getFirstNameCol().toLowerCase().contains(lowerCaseFilter)){
		 * return true;} else
		 * if(user.getLastNameCol().toLowerCase().contains(lowerCaseFilter)){
		 * return true; } return false; }); });
		 *
		 *
		 * SortedList<UserList> sortedData = new SortedList<>(filterData);
		 * sortedData.comparatorProperty().bind(playersTable.comparatorProperty(
		 * )); playersTable.setItems(sortedData);
		 */
	}
@FXML
	private void setPlayerLevelsFromTable(){
		playersTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				UserList ul= playersTable.getItems().get(playersTable.getSelectionModel().getSelectedIndex());

		load(ul);



			}
		});
	}
@FXML
	private void load(UserList ul){


	try {
		Stage window= new Stage();
		FXMLLoader loader= new FXMLLoader();
		loader.setLocation(getClass().getResource("ScorePlayer.fxml"));



		Pane layout;
		layout = loader.load();
		Scene scene = new Scene(layout);
		window.setTitle(ul.getFirstNameCol());
		 s = loader.getController();
		 s.setFirstName(ul.getFirstNameCol());
		 s.setLastName(ul.getFirstNameCol());
		 System.out.println("mamamamammamamamamamama");
		 System.out.println(s.getFirstName());
		 System.out.println(s.getLastName());




		 System.out.println("friday i am here");

		 s.setLastName(ul.getLastNameCol());
		 s.setUser(ul);
		 s.loadDataFromDataBase(ul.getFirstNameCol(),ul.getLastNameCol());
		 System.out.println("plaease wooeekrkrkrkrkrkrfjisdsijdfjds");
		 System.out.println(s.getData().get(0).getFirstNameCol());
		// s.handle(s.getData());



		 System.out.println(s.getData().get(0).getFirstNameCol());

		 //menu.setSteps(steps);


		window.setScene(scene);
		window.show();
		 System.out.println("After mamamamamma");
		 System.out.println(s.getFirstName());
		 System.out.println(s.getLastName());

		 s.handle(s.getData());




		 System.out.println("mamamamammamamamamamama");


	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

	private void select() {
		playersTable.setItems(filterData);
		filter.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				updateFilterData();

			}

		});

	}

	private void setCellTable() {
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstNameCol"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastNameCol"));

		time.setCellValueFactory(new PropertyValueFactory<>("time"));
		steps.setCellValueFactory(new PropertyValueFactory<>("steps"));
		levelNameCol.setCellValueFactory(new PropertyValueFactory<>("levelNameCol"));




	}

	public ObservableList<UserList> getFilterData() {
		return filterData;
	}

	public ObservableList<String> getChoicer() {
		return choicer;
	}

	public TableColumn<UserList, String> getLevelColName() {
		return levelNameCol;
	}

	public TextField getFilter() {
		return filter;
	}

	public TextField getIdField() {
		return idField;
	}

	public ComboBox<String> getChoise() {
		return choise;
	}

	public void setFilterData(ObservableList<UserList> filterData) {
		this.filterData = filterData;
	}

	public void setChoicer(ObservableList<String> choicer) {
		this.choicer = choicer;
	}

	public void setFirstNameCol(TableColumn<UserList, String> firstNameCol) {
		this.firstNameCol = firstNameCol;
	}

	public void setLastNameCol(TableColumn<UserList, String> lastNameCol) {
		this.lastNameCol = lastNameCol;
	}

	public void setPlayerNumber(TableColumn<UserList, String> playerNumber) {
		this.playerNumber = playerNumber;
	}

	public void setLevelColName(TableColumn<UserList, String> levelColName) {
		this.levelNameCol = levelColName;
	}

	public void setTime(TableColumn<UserList, String> time) {
		this.time = time;
	}

	public void setSteps(TableColumn<UserList, String> steps) {
		this.steps = steps;
	}

	public void setFilter(TextField filter) {
		this.filter = filter;
	}

	public void setIdField(TextField idField) {
		this.idField = idField;
	}

	public void setChoise(ComboBox<String> choise) {
		this.choise = choise;
	}

	public ScoreBoard() {
		data = FXCollections.observableArrayList();
		filterData = FXCollections.observableArrayList();
		this.filterData.addAll(data);

		this.s=new ScorePlayer(new UserList());
		data.addListener(new ListChangeListener<UserList>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends UserList> c) {
				updateFilterData();

			}
		});
	}

	private void updateFilterData() {
		filterData.clear();
		for (UserList u : data) {
			if (matchesFilter(u)) {
				filterData.add(u);
			}
		}

		// tableSortOrder();

	}

	private void tableSortOrder() {
		ArrayList<TableColumn<UserList, ?>> sortOrder = new ArrayList<>(playersTable.getSortOrder());
		playersTable.getSortOrder().clear();

		// playersTable.getSortOrder().addAll(sortOrder);
		playersTable.getSortOrder().add(firstNameCol);
		playersTable.getSortOrder().add(levelNameCol);
		// playersTable.sort();

	}

	private boolean matchesFilter(UserList u) {
		String filterString = filter.getText();
		if (filterString == null || filterString.isEmpty())
			return true;
		String lowerCaseFilter = filterString.toLowerCase();
		if (u.getFirstNameCol().toLowerCase().indexOf(lowerCaseFilter) != -1)
			return true;
		else if (u.getLastNameCol().toLowerCase().indexOf(lowerCaseFilter) != -1)
			return true;
		else if (u.getLevelNameCol().toLowerCase().indexOf(lowerCaseFilter) != -1)
			return true;

		return false;
	}

	private void loadDataFromDataBase() {
		Session session = this.getFactory().openSession();
		try {
			Query<UserDb> query = session.createQuery("from Users");
			Query<LevelUsersDb> query2 = session.createQuery("from Level_Users");
			Query<LevelDb> query3 = session.createQuery("from Levels");

			List<LevelDb> list3 = query3.list();
			List<LevelUsersDb> list2 = query2.list();
			List<UserDb> list = query.list();

			for (int i = 0; i < list.size(); i++) {
				for(int j=0;j<list2.size();j++){
					for(int k=0;k<list3.size();k++){

					System.out.println(list.get(i).getUserId());
					System.out.println(list2.get(j).getUserId());
					if (list.get(i).getUserId() == list2.get(j).getUserId()&&list2.get(j).levelName().equals(list3.get(k).getLevelName())){
						data.add(new UserList("" + list.get(i).getFirstName(), "" + list.get(i).getLastName(),
								"" + list2.get(j).getTime(), "" + list2.get(j).getSteps(),""+levelNameCut(list3.get(k).getLevelName()),""));
					}
				}
				}
			}

		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		firstNameCol.setSortType(TableColumn.SortType.ASCENDING);
		levelNameCol.setSortType(TableColumn.SortType.ASCENDING);

		data.sort(new Comparator<UserList>() {

			@Override
			public int compare(UserList o1, UserList o2) {
				return o1.getLevelNameCol().compareTo(o2.getLevelNameCol());

			}
		});

		data.sort(new Comparator<UserList>() {

			@Override
			public int compare(UserList o1, UserList o2) {
				return o1.getFirstNameCol().compareTo(o2.getFirstNameCol());
			}
		});
		playersTable.setItems(data);

	}

	public String levelNameCut(String levelName){
		//String[] str= levelName.split(".txt");
		//String newName=str[0];
		//return newName;
		String newString= levelName.substring(0,levelName.length()-4);
		return newString;

	}




}
