package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import common.Level;
import model.Model;
import model.data.LevelSaver;
import model.data.MyObjectLevelSaver;
import model.data.MyTextLevelSaver;
import model.data.MyXMLLevelSaver;


public class SaveFileCommand extends Command {
	//data members
private HashMap<String,LevelSaver> commands;
private String filePath;
private String type;
private Level lev;


//get set methods
public HashMap<String, LevelSaver> getCommands() {
	return commands;
}
public void setCommands(HashMap<String, LevelSaver> commands) {
	this.commands = commands;
}
public String getFilePath() {
	return filePath;
}
public void setFilePath(String filePath) {
	this.filePath = filePath;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public Level getLev() {
	return lev;
}
public void setLev(Level lev) {
	this.lev = lev;
}

//default c'tor
public SaveFileCommand() {
	this.filePath=null;
	this.type=null;
	this.lev=null;
	this.commands= new HashMap<>();
	commands.put("txt", new MyTextLevelSaver(lev));
	commands.put("dat", new MyObjectLevelSaver());
	commands.put("xml", new MyXMLLevelSaver());

}
//c'tor that gets the file path
public SaveFileCommand(String filePath) {
	this.commands= new HashMap<>();
	commands.put("txt", new MyTextLevelSaver(lev));
	commands.put("dat", new MyObjectLevelSaver());
	commands.put("xml", new MyXMLLevelSaver());
	this.filePath=filePath;
	if(this.filePath.contains("txt"))
		this.type="txt";
	if(this.filePath.contains("dat"))
		this.type="dat";
	if(this.filePath.contains("xml"))
		this.type="xml";

}
public SaveFileCommand(Model model){
	this.model=model;
	this.commands= new HashMap<>();
	commands.put("txt", new MyTextLevelSaver());
	commands.put("dat", new MyObjectLevelSaver());
	commands.put("xml", new MyXMLLevelSaver());
	//this.filePath=this.getParams().get(0);
	//if(this.filePath.contains("txt"))
	//	this.type="txt";
	//if(this.filePath.contains("dat"))
		//this.type="dat";
	//if(this.filePath.contains("xml"))
	//	this.type="xml";

}

	@Override
	public void execute() {
		this.model.setSave(this);
		this.model.save();
	}

	public void save(){
		if(this.filePath.contains("txt"))
			this.type="txt";
		if(this.filePath.contains("dat"))
			this.type="dat";
		if(this.filePath.contains("xml"))
			this.type="xml";
		if(commands.containsKey(this.type)){
			File f= new File(this.filePath);
			FileOutputStream fis;
			this.lev=this.model.getLevel();
			try {
				fis = new FileOutputStream(f);
				this.commands.get(this.type).saveLevel(fis,this.lev);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

	}

		}

	}
}
