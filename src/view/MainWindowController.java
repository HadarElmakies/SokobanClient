package view;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

//import org.hibernate.dialect.pagination.FirstLimitHandler;

import commands.Command;
import commands.ExitCommand;
import commands.Printer;
import common.Level;
import db.DbManagerLevel;
import db.LevelDb;
import db.UserDb;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainWindowController extends java.util.Observable implements Initializable, View {
	// data members
	boolean stop = false;
	private Command ExitCommand = new ExitCommand();
	private double opacity;
	private KeyXMLLoader keyLoader;
	LevelDb levelDb;
	DbManagerLevel managerLevel;
	UserDb user;
	private Timer timer;
	private long min;
	private long sec;
	private StringProperty timeCounting;
	//DbManagerUser managerUser;
	String ssound;
	Media sound;
	int userId;
	MenuDialog menu;
	MediaPlayer mediaPlayer;

	@FXML
	private Text countingSteps;

	@FXML
	private Label label;

	@FXML
	SokobanDisplayer sokobanDisplayer;

	@FXML
	private Text timerGame;

	@FXML
	private Text clock;

	public MainWindowController() {

		this.sokobanDisplayer = new SokobanDisplayer();
		this.timer = new Timer();
		this.timeCounting = new SimpleStringProperty();
		this.ssound = "SPONGEBOB TRAP REMIX! KRUSTY KRAB Vine Remix.mp3";
		this.sound = new Media(new File(ssound).toURI().toString());
		this.mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setAutoPlay(true);
		this.keyLoader = startKey("./resources/key.xml");
		mediaPlayer.play();
		managerLevel = new DbManagerLevel();
		this.menu = new MenuDialog();
		levelDb= new LevelDb();

	}

	public void start() {
		sokobanDisplayer.startRedraw();
		// managerLevel.updateName(3, "Nir");
		// managerLevel.addLevel(new LevelDb(2,"level1.txt"));
		// managerLevel.addLevel(new LevelDb(3,"level2.txt"));
		// managerLevel.deleteLevel(1);

		/*
		 * String command ="Load"; LinkedList<String>params= new
		 * LinkedList<String>(); params.add(command);
		 *
		 * params.add("level1.txt");
		 *
		 * this.setChanged(); this.notifyObservers(params);
		 * managerLevel.addLevel(levelDb); startTimer(0, 0);
		 *
		 *
		 */

		// DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		// Calendar cali = Calendar.getInstance();
		// cali.getTime();
		// String time = timeFormat.format(cali.getTimeInMillis());
		// System.out.println(timeFormat.format(cali.getTimeInMillis()));
		// timer.setText(time);
		// }

	}

	public void openFile() {

		FileChooser fc = new FileChooser();
		fc.setTitle("open sokoboan file");
		fc.setInitialDirectory(new File("./resources"));// מחפשים את הקובץ
														// בתיקייה
		fc.setSelectedExtensionFilter(new ExtensionFilter("XML files", "*.xml")); // איזה
																					// סוגי
																					// קובץ
																					// להראות
		File chosen = fc.showOpenDialog(null);
		if (chosen != null) {
			System.out.println(chosen.getName());
			LinkedList<String> params = new LinkedList<String>();
			String str = chosen.getName();
			params.add("Load");
			params.add(str);
			levelDb.setLevelName(str);
			 int value = Integer.parseInt(str.replaceAll("[^0-9]", ""));
			levelDb.setLevelNumber(value);

		//	managerUser.addLevel(levelDb);


			mediaPlayer.stop();
			mediaPlayer.play();
			this.setChanged();
			this.notifyObservers(params);
			startTimer(0, 0);

		}

	}

	public void startPlaying(){


			LinkedList<String> params = new LinkedList<String>();
			String str = "level1.txt";
			params.add("Load");
			params.add(str);
			levelDb.setLevelName(str);
			 int value = Integer.parseInt(str.replaceAll("[^0-9]", ""));
			levelDb.setLevelNumber(value);

		//	managerUser.addLevel(levelDb);


			mediaPlayer.stop();
			mediaPlayer.play();
			this.setChanged();
			this.notifyObservers(params);
			startTimer(0, 0);

		}



	public void saveFile() {
		FileChooser fc = new FileChooser();
		fc.setTitle("save sokoboan file");
		fc.setInitialDirectory(new File("./resources"));
		fc.setSelectedExtensionFilter(new ExtensionFilter("XML files", "*.xml"));
		File chosen = fc.showSaveDialog(null);
		if (chosen != null) {
			System.out.println(chosen.getName());
			LinkedList<String> params = new LinkedList<String>();
			String str = chosen.getName();
			params.add("Save");
			params.add(str);
			this.setChanged();
			this.notifyObservers(params);
		}

	}

	public void stopMusic() {

		mediaPlayer.pause();

	}

	@Override
	public boolean isFlag() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFlag(boolean flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public void display(Level level) {
		System.out.println("display");
		if (level != null) {
			sokobanDisplayer.setSokoboanData(level.getDynamicObjects());
			if (level.Win()) {
				finishLevel();
			}

		}

	}

	private void startTimer(long mini, long seco) {
		this.timer = new Timer();
		this.min = mini;
		this.sec = seco;
		this.timerGame.textProperty().bind(this.timeCounting);
		this.timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				sec++;
				if (sec > 59) {
					min++;
					sec = 0;
				}
				if (min < 10) {
					if (sec < 10)
						timeCounting.set("0" + min + ":0" + sec);
					else
						timeCounting.set("0" + min + ":" + sec);
				} else
					timeCounting.set("" + min + ":" + sec);

			}
		}, 0, 1000);

	}

	public void stopTimer() {
		if (this.timer != null)
			this.timer.cancel();

	}

	public void continTimer() {
		startTimer(min, sec);

	}

	public void continSong() {
		this.mediaPlayer.play();

	}

	@Override
	public void exit() {
		stop = true;
		LinkedList<String> params = new LinkedList<String>();

		params.add("Exit");

		this.setChanged();
		this.notifyObservers(params);
		Platform.exit();
		System.exit(0);

	}

	@Override
	public void setP(Printer p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		sokobanDisplayer.setSokoboanData(sokobanDisplayer.getSokoboanData());
		sokobanDisplayer.requestFocus();

		sokobanDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> sokobanDisplayer.requestFocus());
		sokobanDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.UP) {
					LinkedList<String> params = new LinkedList<String>();
					params.add("Move Up");
					setChanged();

					notifyObservers(params);

					event.consume();
					sokobanDisplayer.requestFocus();
				}
				if (event.getCode() == KeyCode.DOWN) {

					LinkedList<String> params = new LinkedList<String>();
					params.add("Move Down");
					setChanged();

					notifyObservers(params);

					event.consume();
					sokobanDisplayer.requestFocus();
				}
				if (event.getCode() == KeyCode.RIGHT) {

					LinkedList<String> params = new LinkedList<String>();
					params.add("Move Right");
					setChanged();
					notifyObservers(params);

					event.consume();
					sokobanDisplayer.requestFocus();
				}
				if (event.getCode() == KeyCode.LEFT) {

					LinkedList<String> params = new LinkedList<String>();
					params.add("Move Left");
					setChanged();
					notifyObservers(params);

					event.consume();
					sokobanDisplayer.requestFocus();
				}

			}
		});

	}


	public void solveSokoban()
	{
		LinkedList<String> params = new LinkedList<String>();
		params.add("Solution");
		setChanged();
		notifyObservers(params);


	}

	// *********************************************
	public void finishLevel() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("finish level");
				alert.setHeaderText("you won!!!");
				try {
					ImageView imageView = new ImageView(new Image(new FileInputStream("./resources/win.jpg")));
					imageView.setFitWidth(400);
					imageView.setFitHeight(400);
					alert.setGraphic(imageView);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				alert.setContentText(countingSteps.getText() + "\n Time: " + timerGame.getText());
				alert.show();
				//managerUser.deleteUser(3);
				//managerUser.updateLastName(1, "Adi");

				//startMenu();


				try {
					String sSteps = countingSteps.getText().substring(7);

					int steps= Integer.parseInt(sSteps);


					int min2=(int)(min);
					int sec2=(int)(sec);

					Time time=new Time(0, min2, sec2);

					Stage window= new Stage();
					FXMLLoader loader= new FXMLLoader();
					loader.setLocation(getClass().getResource("Menu.fxml"));

					Pane layout;
					layout = loader.load();


					Scene scene = new Scene(layout);
					 menu = loader.getController();
					 menu.setSteps(steps);
					 menu.setTime(time);
					 menu.setLevelName(levelDb.getLevelName());
					 menu.setLevel(levelDb);
					 menu.setSteps(steps);


					window.setScene(scene);
					window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}
		});
		stopTimer();
		stopMusic();

	}


	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	private KeyXMLLoader startKey(String path) {
		XMLDecoder deco;
		KeyXMLLoader key = null;
		try {
			deco = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File(path))));
			key = (KeyXMLLoader) deco.readObject();
			deco.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;

	}

	@Override
	public void bindSteps(StringProperty steps) {
		this.countingSteps.textProperty().bind(steps);
	}

}
