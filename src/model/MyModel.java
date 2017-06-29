package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import commands.Command;
import commands.ExitCommand;
import commands.HintCommand;
import commands.LoadFileCommand;
import commands.MoveDownCommand;
import commands.MoveLeftCommand;
import commands.MoveRightCommand;
import commands.MoveUpCommand;
import commands.SaveFileCommand;
import commands.SolutionCommand;
import common.Level;
import javafx.scene.image.Image;
import model.policy.MySokobanPolicy;

public class MyModel extends Observable implements Model   {
	//data members
	private Level level;
	private Level startLevel;
	private Command command;
	private HintCommand hint;
	private LoadFileCommand load= new LoadFileCommand();
	private SaveFileCommand save;
	private MoveUpCommand up = new MoveUpCommand(this);
	private MoveDownCommand down= new MoveDownCommand(this);
	private MoveLeftCommand left= new MoveLeftCommand(this);
	private MoveRightCommand right= new MoveRightCommand(this);
	private String filePath;
	private MySokobanPolicy msp= new MySokobanPolicy(this);
	boolean changed=false;
	LinkedList<String> params= new LinkedList<String>();
	private BlockingQueue<String> commandsQueue=new ArrayBlockingQueue<String>(30);
	Socket myServer;
	boolean stop;
	public int counter=0;
	private ArrayList<String> startBoard=new ArrayList<String>();
	SolutionCommand sol;




	public void moveLeft() {
		try {
			this.commandsQueue.put("Move Left");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.commands.add("Move Left");
		/*
		try {
			//this.msp.setPlayer(this.level.getPlayers().get(0));
			//this.msp.moveLeft(this.level);
			this.commands.add("Move Left");
			this.setChanged();
			this.level.getPlayers().get(0).setPlayerImage(new Image(new FileInputStream("./resources/spongleft.jpg")));
			params.add("display");
			this.notifyObservers(params);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/

	}

	public void moveRight(){
		try {
			this.commandsQueue.put("Move Right");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//this.commands.add("Move Right");
		/*

		try {
			this.msp.setPlayer(this.level.getPlayers().get(0));

			this.msp.moveRight(this.level);
			this.setChanged();
			this.level.getPlayers().get(0).setPlayerImage(new Image(new FileInputStream("./resources/Spongebob right.jpg")));
			params.add("display");
			this.notifyObservers(params);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

*/
	}

	public void moveUp(){
		try {
			this.commandsQueue.put("Move Up");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//this.commands.add("Move Up");
		System.out.println("the function move up");
		System.out.println("the size of the commands after move up "+this.commandsQueue.size());


		/*

		try {
			this.msp.setPlayer(this.level.getPlayers().get(0));
			this.msp.moveUp(this.level);
			this.setChanged();
			this.level.getPlayers().get(0).setPlayerImage(new Image(new FileInputStream("./resources/SpongeBob.png")));
			params.add("display");
			this.notifyObservers(params);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/

	}

	public void moveDown()
	{
		try {
			this.commandsQueue.put("Move Down");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//this.commands.add("Move Down");
/*
		try {
			this.msp.setPlayer(this.level.getPlayers().get(0));
			this.msp.moveDown(this.level);
			this.setChanged();
			this.level.getPlayers().get(0).setPlayerImage(new Image(new FileInputStream("./resources/spongbobdown.jpg")));

			params.add("display");
			this.notifyObservers(params);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


*/

	}
	public void load()
	{
		this.load.setFilePath(this.load.getParams().removeFirst());
		this.level=this.load.load();
		this.startLevel=level;
		this.startBoard=this.level.getelementsString();
		this.setChanged();
		params.add("display");
		this.notifyObservers(params);
		try {
			this.commandsQueue.put("load");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//this.commands.add("load");


	}

	public void save(){

		this.save.setFilePath(this.save.getParams().removeFirst());
		this.save.save();
		this.setChanged();
		params.add("display");

		this.notifyObservers(params); //לבדוק מה לעשות במצב כזה

	}

	public MySokobanPolicy getMsp() {
		return msp;
	}


	public void setMsp(MySokobanPolicy msp) {
		this.msp = msp;
	}


	public boolean isChanged() {
		return changed;
	}


	public void setChanged(boolean changed) {
		this.changed = changed;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}



	public MyModel(String filePath)  {
		this.stop=false;
		this.filePath=filePath;
		this.load= new LoadFileCommand(this);
		try{
		this.myServer=new Socket("127.0.0.1", 1234);

			System.out.println("connected to server");
			System.out.println("enter your command");

			InputStream inFromClient=this.myServer.getInputStream();
			OutputStream outToClient=this.myServer.getOutputStream();
			BufferedReader userInput=new BufferedReader(new InputStreamReader(inFromClient));
			PrintWriter serverOutput= new PrintWriter(outToClient);

			Thread fromClient=aSyncReadInput(userInput,"Exit");
			Thread toClient=aSyncSendOutput(serverOutput);
			fromClient.join();
			toClient.join();
			userInput.close();
			serverOutput.close();
			this.myServer.close();
		} catch(UnknownHostException e) {
			System.out.println(e);
		}
		catch(IOException e) {
			System.out.println(e);
		}
		catch(InterruptedException e) {
			System.out.println(e);
		}

	}

	public MyModel() throws InterruptedException {
		this.stop=false;
		this.load= new LoadFileCommand(this);
		try{
		this.myServer=new Socket("127.0.0.1", 5555);

			System.out.println("connected to server");
			System.out.println("enter your command");

			InputStream inFromClient=this.myServer.getInputStream();
			OutputStream outToClient=this.myServer.getOutputStream();
			BufferedReader userInput=new BufferedReader(new InputStreamReader(inFromClient));
			PrintWriter serverOutput= new PrintWriter(outToClient);

			Thread fromClient=aSyncReadInput(userInput,"Exit");
			Thread toClient=aSyncSendOutput(serverOutput);
			//fromClient.join();
			//toClient.join();
			//userInput.close();
			//serverOutput.close();
			//this.myServer.close();
		} catch(UnknownHostException e) {
			System.out.println(e);
			//userInput.close();
			//serverOutput.close();
			//this.myServer.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}

	}

	private Thread aSyncSendOutput(PrintWriter out){
		Thread t= new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					sendOutPut(out);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		t.start();
		return t;
	}

	private void sendOutPut (PrintWriter out) throws InterruptedException{
		String str=null;
		while(!this.stop){
		//	if(!this.commands.isEmpty()){
				//System.out.println("commands bigger then zero");
			System.out.println("i am in the sendoutput of my model");
			str=commandsQueue.poll(10,TimeUnit.SECONDS);
			//this.commands.pop();
			System.out.println("from client to server"+" "+str);
if(str!=null){
			if(str.equals("load")){
				out.println(str);
				out.println(this.level.getLevelName());
				out.println(this.load.getFilePath());
				String newLine;
				for (int i = 0; i < this.level.getelementsString().size(); i++) {
					newLine=this.level.getelementsString().get(i);
					out.println(newLine);

				}
				out.println("done");
				out.flush();
			}
			if(str.equals("Move Up")){
				System.out.println("the model sent move up");
				out.println(str);
				out.flush();

			}
			if(str.equals("Move Down")){
				out.println(str);
				out.flush();
			}
			if(str.equals("Move Left")){
				out.println(str);
				out.flush();
			}
			if(str.equals("Move Right")){
				out.println(str);
				out.flush();
			}
			if(str.equals("Solution")){
				out.println(str);
				out.flush();
			}

		//}
		}
str=null;
		}
	}
	private Thread aSyncReadInput(BufferedReader in,String exitStr){
		Thread t= new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					readInput(in,exitStr);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		t.start();
		return t;
	}

	private void readInput(BufferedReader in,String exitStr) throws InterruptedException{
		String line=null;
		boolean flag=false;

		try {

			while(!flag){
				line=in.readLine();
				if(line!=null){
				System.out.println("from server to client:"+" "+line);

				if (line.equals(exitStr)){
						flag=true;
						params.add(line);
						//commands.put("bye");
						stop();
				}
				if(line.equals("succeed")){
					String newLine;
					ArrayList<String> lines=new ArrayList<String>();
					for(int i=0;i<this.level.getelementsString().size();i++){
						newLine=in.readLine();
						lines.add(newLine);
					}
					this.getPolicy().setCounter(counter);

					++counter;

					this.getPolicy().setCounter(counter);
					Level level=new Level(lines);
					this.setLevel(level);
					this.getLevel().setelementsString(startBoard);

					params.add("display");
					setChanged();
					notifyObservers(params);
				}
				if(line.equals("Solution")){
					this.level=this.startLevel;
					String newLine;
					newLine=in.readLine();
					for(int i=0;i<newLine.length();i++){
						String newCommand=getCommandFromSolution(newLine.charAt(i));
						Thread.sleep(500);
						if(newCommand!=null)
							this.commandsQueue.put(newCommand);

					}



				}

				/*
				else
					params.add(line);
					setChanged();
				notifyObservers(params);
				*/
			}line=null;
				}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	private void readInputsAndSend(BufferedReader in, PrintWriter out,String exitStr){
		try{
			String line;
			boolean flag=false;
			while(!flag){
				if(this.commands!=null)
				{
				line=this.commands.get(0);
				System.out.println(line);
				if(line.equals(exitStr))
					flag=true;
				if(line.equals("save")){
					this.params.removeFirst();
					String line2=this.params.get(0);
					this.params.removeFirst();
					this.save.save();
					//params.add("display");


				}

				if(line.equals("load")){
					String newLine;
					for(int i=0;i<this.level.getelementsString().size();i++){
						newLine=this.level.getelementsString().get(index)
					}
				}



				out.println(line);
				out.flush();
				}
			}
			/*
			while(!(line=in.readLine()).equals(exitStr)){
				out.println(line);
				out.flush();
			}*/
	/*
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private Thread aSyncReadInputsAndSend(BufferedReader in, PrintWriter out,String exitStr){
		Thread t=new Thread(new Runnable() {
			public void run() {
				readInputsAndSend(in, out, exitStr);

			}
		});
		t.start();
		return t;
	}


	*/

	public String getCommandFromSolution(char c){
		String command;
		if(c=='u'||c=='U'){
			command="Move Up";
			return command;
		}
		else if(c=='d'||c=='D'){
			command="Move Down";
			return command;
		}
		else if(c=='l'||c=='L'){
			command="Move Left";
			return command;
		}
		else if(c=='r'||c=='R'){
			command="Move Right";
			return command;
		}
		return null;

	}
	public MyModel(MySokobanPolicy policy){
		this.msp=policy;
		}


	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
		//notifyObservers();
	}

	public LoadFileCommand getLoad() {
		return load;
	}

	public void setLoad(LoadFileCommand load) {
		this.load = load;
		//notifyObservers();

	}

	public SaveFileCommand getSave() {
		return save;
	}

	public void setSave(SaveFileCommand save) {
		this.save = save;
		//notifyObservers();

	}

	public MoveUpCommand getUp() {
		return up;
	}

	public void setUp(MoveUpCommand up) {
		this.up = up;

	}

	public MoveDownCommand getDown() {
		return down;
	}

	public void setDown(MoveDownCommand down) {
		this.down = down;

	}

	public MoveLeftCommand getLeft() {
		return left;
	}

	public void setLeft(MoveLeftCommand left) {
		this.left = left;

	}

	public MoveRightCommand getRight() {
		return right;
	}

	public void setRight(MoveRightCommand right) {
		this.right = right;

	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;

	}

	public MyModel(Command command,Level level) {
		this.command=command;
		this.level=level;

	}






	@Override
	public Level getcCurrentLevel() {
		return this.level;

		}


	@Override
	public MySokobanPolicy getPolicy() {
		return this.msp;
	}


	@Override
	public void setPolicy(MySokobanPolicy policy) {
		this.msp=policy;
	}

	@Override
	public void exit() {
		this.setChanged();
		params.add("Exit");

		this.notifyObservers(params);

	}

	@Override
	public int getSteps() {
		return this.counter;
	}

	private void stop(){
		this.stop=true;

	}

	@Override
	public HintCommand getHint() {
		return this.hint;
	}

	@Override
	public void setHintCommand(HintCommand hint) {
		this.hint=hint;

	}

	@Override
	public SolutionCommand getSolution() {
		return sol;
	}

	@Override
	public void setSolutionCommand(SolutionCommand sol) {
		this.sol=sol;

	}

	@Override
	public void hint() {
		// TODO Auto-generated method stub

	}

	@Override
	public void solve() {
		System.out.println("in the solve method in my model");
		try {
			this.commandsQueue.put("Solution");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}








}






