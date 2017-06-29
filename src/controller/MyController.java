package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import commands.Command;
import commands.DisplayLevelCommand;
import commands.ExitCommand;
import commands.HintCommand;
import commands.LevelPrinter;
import commands.LoadFileCommand;
import commands.MoveDownCommand;
import commands.MoveLeftCommand;
import commands.MoveRightCommand;
import commands.MoveUpCommand;
import commands.Printer;
import commands.SaveFileCommand;
import commands.SolutionCommand;
import common.Level;

import general.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import view.MainWindowController;
import view.View;

public class MyController implements Observer  {
	//data members
	private View view;
	private Model model;
	private HashMap<String, Command>commands;
	private Controller controller;
	//private ClientHandler clientHandler;
	//private MyServer server;
	private StringProperty countSteps;

//c'tor
	public MyController() {
		commands=new HashMap<String,Command>();
		commands.put("Load", new LoadFileCommand(model));
		commands.put("load", new LoadFileCommand(model));
		commands.put("Save", new SaveFileCommand(model));
		commands.put("save", new SaveFileCommand(model));
		commands.put("Move Up",new MoveUpCommand(model));
		commands.put("move up",new MoveUpCommand(model));
		commands.put("Move Down",new MoveDownCommand(model));
		commands.put("move down",new MoveDownCommand(model));
		commands.put("Move Left",new MoveLeftCommand(model));
		commands.put("move left",new MoveLeftCommand(model));
		commands.put("Move Right",new MoveRightCommand(model));
		commands.put("move right",new MoveRightCommand(model));
		/*
		commands.put("hint", new HintCommand(model));
		commands.put("Hint", new HintCommand(model));

*/
	}


//get set methods

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	/*
	//c'tor with server
	public MyController(View view,Model model,ClientHandler ch,int port) {
		this.view=view;
		//this.clientHandler=ch;
		this.model=model;
		//this.server= new MyServer(port,this.clientHandler);
		//this.server.start();
		this.countSteps= new SimpleStringProperty();
		this.controller =new Controller();
		this.view.bindSteps(this.countSteps);
		commands=new HashMap<String,Command>();
		commands.put("Load", new LoadFileCommand(model));
		commands.put("load", new LoadFileCommand(model));
		commands.put("Save", new SaveFileCommand(model));
		commands.put("save", new SaveFileCommand(model));
		commands.put("Move Up",new MoveUpCommand(model,this.countSteps));
		commands.put("move up",new MoveUpCommand(model,this.countSteps));
		commands.put("Move Down",new MoveDownCommand(model,this.countSteps));
		commands.put("move down",new MoveDownCommand(model,this.countSteps));
		commands.put("Move Left",new MoveLeftCommand(model,this.countSteps));
		commands.put("move left",new MoveLeftCommand(model,this.countSteps));
		commands.put("Move Right",new MoveRightCommand(model,this.countSteps));
		commands.put("move right",new MoveRightCommand(model,this.countSteps));
		commands.put("display",new DisplayLevelCommand(model,view));
		commands.put("Display",new DisplayLevelCommand(model,view));
		commands.put("Exit", new ExitCommand(view,server));
		commands.put("exit", new ExitCommand(view,server));
		this.view.setP(new LevelPrinter());


		this.controller.start();

	}

	*/
//c'tor without a server
public MyController(View view,Model model) {
	this.view=view;
	this.model=model;
this.countSteps= new SimpleStringProperty();
	this.controller =new Controller();
	this.view.bindSteps(this.countSteps);
	commands=new HashMap<String,Command>();
	commands.put("Load", new LoadFileCommand(model));
	commands.put("load", new LoadFileCommand(model));
	commands.put("Save", new SaveFileCommand(model));
	commands.put("save", new SaveFileCommand(model));
	commands.put("Move Up",new MoveUpCommand(model,this.countSteps));
	commands.put("move up",new MoveUpCommand(model,this.countSteps));
	commands.put("Move Down",new MoveDownCommand(model,this.countSteps));
	commands.put("move down",new MoveDownCommand(model,this.countSteps));
	commands.put("Move Left",new MoveLeftCommand(model,this.countSteps));
	commands.put("move left",new MoveLeftCommand(model,this.countSteps));
	commands.put("Move Right",new MoveRightCommand(model,this.countSteps));
	commands.put("move right",new MoveRightCommand(model,this.countSteps));
	commands.put("display",new DisplayLevelCommand(model,view));
	commands.put("Display",new DisplayLevelCommand(model,view));
	commands.put("hint", new HintCommand(model,view));
	commands.put("Hint", new HintCommand(model,view));
	commands.put("Solution", new SolutionCommand(model, view));
	commands.put("solution", new SolutionCommand(model, view));



	commands.put("Exit", new ExitCommand(view));
	commands.put("exit", new ExitCommand(view));
	this.view.bindSteps(this.countSteps);
	this.view.setP(new LevelPrinter());
	this.controller.start();

}


	@Override
	public void update(Observable o, Object arg) {
		LinkedList<String> params=(LinkedList<String>) arg;
		String commandKey= params.removeFirst();
		Command c=commands.get(commandKey);
		if(c==null){
			System.out.println("Error finding "+ commandKey);
			return;
		}
		c.setParams(params);

		controller.insertCommand(c);





		}



//get set methods
	public HashMap<String, Command> getCommands() {
		return commands;
	}




	public Controller getController() {
		return controller;
	}



/*
	public ClientHandler getClientHandler() {
		return clientHandler;
	}




	public MyServer getServer() {
		return server;
	}


*/

	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
	}




	public void setController(Controller controller) {
		this.controller = controller;
	}


/*

	public void setClientHandler(ClientHandler clientHandler) {
		this.clientHandler = clientHandler;
	}




	public void setServer(MyServer server) {
		this.server = server;
	}

*/








}
