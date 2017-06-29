package view;

import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import db.DbManager;
import db.LevelDb;
import db.LevelUsersDb;
import db.UserDb;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ScorePlayer extends DbManager implements Initializable{

	UserList user;
	private Connection con = null;
	private PreparedStatement pst = null;
	String firstName;
	String lastName;

	private ResultSet rs = null;
	private ObservableList<UserList> data;
	private ObservableList<UserList> filterData;
	private ObservableList<String> choicer = FXCollections.observableArrayList("Time", "Steps","Lexicography");

	@FXML
	private TableView<UserList> playersTable;
	@FXML
	private TableColumn<UserList, String> firstNameCol;
	@FXML
	private TableColumn<UserList, String> lastNameCol;

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

	@FXML
	private TextField userFiled;

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

	public ObservableList<UserList> getFilterData() {
		return filterData;
	}

	public ObservableList<String> getChoicer() {
		return choicer;
	}

	public TableView<UserList> getPlayersTable() {
		return playersTable;
	}

	public TableColumn<UserList, String> getFirstNameCol() {
		return firstNameCol;
	}

	public TableColumn<UserList, String> getLastNameCol() {
		return lastNameCol;
	}

	public TableColumn<UserList, String> getPlayerNumber() {
		return playerNumber;
	}

	public TableColumn<UserList, String> getLevelNameCol() {
		return levelNameCol;
	}

	public TableColumn<UserList, String> getTime() {
		return time;
	}

	public TableColumn<UserList, String> getSteps() {
		return steps;
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

	public TextField getUserFiled() {
		return userFiled;
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

	public void setFilterData(ObservableList<UserList> filterData) {
		this.filterData = filterData;
	}

	public void setChoicer(ObservableList<String> choicer) {
		this.choicer = choicer;
	}

	public void setPlayersTable(TableView<UserList> playersTable) {
		this.playersTable = playersTable;
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

	public void setLevelNameCol(TableColumn<UserList, String> levelNameCol) {
		this.levelNameCol = levelNameCol;
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

	public void setUserFiled(TextField userFiled) {
		this.userFiled = userFiled;
	}

	public ScorePlayer() {
		user=new UserList(this.firstName,this.lastName);
		data=FXCollections.observableArrayList();


		System.out.println("**** score player *******");
		System.out.println(this.firstName);
		System.out.println(this.lastName);


		System.out.println("**** score player *******");
	}

 public ScorePlayer(UserList user){
	 this.user=user;
 }


	public String getFirstName() {
		return firstName;

	}




	public String getLastName() {
		return lastName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public UserList getUser() {
		return user;
	}




	public void setUser(UserList user) {
		this.user = user;
	}




	@FXML
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choise.setItems(choicer);
		data=FXCollections.observableArrayList();
		setCellTable();

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
			else if (selectedOption.toString().equals("Lexicography")) {

				data.sort(new Comparator<UserList>() {
					@Override
					public int compare(UserList o1, UserList o2) {
						return o1.getLevelName().compareTo(o2.getLevelName());
					}

				});
				playersTable.setItems(data);
				select();
				filter.requestFocus();
			}

		});


	}

	public void handle(ObservableList<UserList>data){
		playersTable.setItems(data);
		choise.setItems(choicer);
		//data=FXCollections.observableArrayList();
		setCellTable();
		System.out.println("the info from handle");
		System.out.println(data.get(0).getFirstNameCol());

		// tableSortOrder();
		playersTable.setItems(filterData);


		filter.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				updateFilterData(data);
				playersTable.setItems(data);

				System.out.println("the info from changed handle");
				System.out.println(data.get(0).getFirstNameCol());

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
						System.out.println("the info from steps comperator");
						System.out.println(data.get(0).getFirstNameCol());
						return (steps1 - steps2);

					}

				});
				playersTable.setItems(data);
				select(data);
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
							System.out.println("the info from time comperator");
							System.out.println(data.get(0).getFirstNameCol());
							return (time1 - time2);
						} else
							return -1;
					}

				});
				playersTable.setItems(data);
				System.out.println("the info from table platesd");
				System.out.println(data.get(0).getFirstNameCol());
				select(data);

			}
			else if (selectedOption.toString().equals("Lexicography")) {

				data.sort(new Comparator<UserList>() {
					@Override
					public int compare(UserList o1, UserList o2) {
						return o1.getLevelName().compareTo(o2.getLevelName());
					}

				});
				playersTable.setItems(data);
				select(data);
				filter.requestFocus();
			}

		});




	}

	public void GetUser(String user) {
		this.firstName=user;
		userFiled.setText(user);


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
	private void select(ObservableList<UserList>data) {
		playersTable.setItems(filterData);
		System.out.println("the info from select");
		System.out.println(data.get(0).getFirstNameCol());
		filter.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				updateFilterData(data);

			}

		});
		playersTable.setItems(data);

	}

	private void setCellTable() {
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstNameCol"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastNameCol"));

		time.setCellValueFactory(new PropertyValueFactory<>("time"));
		steps.setCellValueFactory(new PropertyValueFactory<>("steps"));
		levelNameCol.setCellValueFactory(new PropertyValueFactory<>("levelNameCol"));
		//playerNumber.setCellValueFactory(new PropertyValueFactory<>("playerNumber"));



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
	private void updateFilterData(ObservableList<UserList>data) {
		filterData.clear();
		System.out.println("the info from updtee filterdata");
		System.out.println(data.get(0).getFirstNameCol());
		for (UserList u : data) {
			if (matchesFilter(u)) {
				filterData.add(u);
			}
		}

		// tableSortOrder();
		playersTable.setItems(data);

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

	public void loadDataFromDataBase() {
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
						UserList newUser= new UserList("" + list.get(i).getFirstName(), "" + list.get(i).getLastName(),
								"" + list2.get(j).getTime(), "" + list2.get(j).getSteps(),""+levelNameCut(list3.get(k).getLevelName()),"");
						data.add(newUser);
						System.out.println("***********************aidi fjjshfjsdjfdsjfjsdf**************");
						System.out.println(firstName);
						System.out.println(user.getFirstNameCol());
						if(!(isTheUser(this.user.getFirstNameCol(), this.user.getLastNameCol(), list.get(i).getFirstName(),list.get(i).getLastName()))){
							data.remove(newUser);

					}
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

	public void loadDataFromDataBase(String firstName,String lastName) {
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
						UserList newUser= new UserList("" + list.get(i).getFirstName(), "" + list.get(i).getLastName(),
								"" + list2.get(j).getTime(), "" + list2.get(j).getSteps(),""+levelNameCut(list3.get(k).getLevelName()),"");
						data.add(newUser);
						System.out.println("***********************aidi fjjshfjsdjfdsjfjsdf**************");
						System.out.println(firstName);
						System.out.println(user.getFirstNameCol());
						if(!(isTheUser(this.user.getFirstNameCol(), this.user.getLastNameCol(), list.get(i).getFirstName(),list.get(i).getLastName()))){
							data.remove(newUser);

					}
				}
				}
			}
			}
			this.data=data;
			//handle(data);
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



private boolean isTheUser(String fl,String ln,String firstName,String lastName){
	System.out.println(fl);
	System.out.println(ln);
	System.out.println(firstName);
	System.out.println(lastName);
	if(fl.equals(firstName)&&ln.equals(lastName)){

		return true;
	}
	return false;
}

	public String levelNameCut(String levelName){
		//String[] str= levelName.split(".txt");
		//String newName=str[0];
		//return newName;
		String newString= levelName.substring(0,levelName.length()-4);
		return newString;

	}




}
