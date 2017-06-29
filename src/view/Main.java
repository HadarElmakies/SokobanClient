package view;

import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;

import javax.naming.ldap.ManageReferralControl;
import javax.net.ssl.ManagerFactoryParameters;
import javax.print.DocFlavor.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import controller.MyController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.MyModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		try {
			FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
			BorderPane root= (BorderPane)loader.load();

			MainWindowController view= loader.getController();
			try {
				init(view);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Scene scene = new Scene(root,1300,1000);
			Scene scene = new Scene(root,1300,1000);


			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();




		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
	private void init(MainWindowController view) throws InterruptedException{
		MyModel model= new MyModel();
		MyController controller;

		List<String> args= getParameters().getRaw();
		if(args.size()>0){
			/*
		if(args.get(0).equals("-server")){
			int port=Integer.parseInt(args.get(1));
			System.out.println(port);
			MyClientHandler clientHandler= new MyClientHandler();
			controller = new MyController(view,model,clientHandler,port) ;
			clientHandler.addObserver(controller);
			model.addObserver(controller);
			view.addObserver(controller);
			view.start();
		}
		*/
		}

		else{
			controller= new MyController(view,model);
			model.addObserver(controller);
			view.addObserver(controller);
			view.start();
		}

		}

	public static void main(String[] args) {


		launch(args);



	}
}
