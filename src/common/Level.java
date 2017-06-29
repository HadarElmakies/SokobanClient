package common;

import java.io.Serializable;
import java.util.ArrayList;



import model.data.ConvertStrToObj;

@SuppressWarnings("serial")
public class Level  implements Serializable {
	//data members
 ArrayList<Element>elements=new ArrayList<Element>();
 ArrayList<Box> boxes= new ArrayList<Box>();
 ArrayList<Wall> walls= new ArrayList<Wall>();
ArrayList<Destination> destinations = new ArrayList<Destination>();
ArrayList<Player>sokobans = new ArrayList<Player>();
ArrayList<Space>spaces = new ArrayList<Space>();
ArrayList<String>elementsString;
ArrayList<ArrayList<Element>> dynamicObjects=null;
int maxCol;
int maxRow;
int LevelId;
String LevelName;



// get set methods
public ArrayList<Element> getElements() {
	return elements;
}
public void setElements(ArrayList<Element> elements) {
	this.elements = elements;
}
public ArrayList<String> getElementsString() {
	return elementsString;
}
public void setElementsString(ArrayList<String> elementsString) {
	this.elementsString = elementsString;
}
public ArrayList<ArrayList<Element>> getDynamicObjects() {
	return dynamicObjects;
}
public void setDynamicObjects(ArrayList<ArrayList<Element>> dynamicObjects) {
	this.dynamicObjects = dynamicObjects;
}
public ArrayList<Space> getSpaces() {
	return spaces;
}
public void setSpaces(ArrayList<Space> spaces) {
	this.spaces = spaces;
}
public ArrayList<String> getelementsString() {
	return elementsString;
}
public void setelementsString(ArrayList<String> elementsString) {
	this.elementsString = elementsString;
}

public ArrayList<Element> getCharacters() {
	return elements;
}
public void setCharacters(ArrayList<Element> characters) {
	this.elements = characters;
}

public ArrayList<Box> getBoxes() {
	return boxes;
}

public void setBoxes(ArrayList<Box> boxes) {
	this.boxes = boxes;
}

public ArrayList<Wall> getWalls() {
	return walls;
}


public void setWalls(ArrayList<Wall> walls) {
	this.walls = walls;
}


public ArrayList<Destination> getDestinations() {
	return destinations;
}


public int getMaxCol() {
	return maxCol;
}
public void setMaxCol(int maxCol) {
	this.maxCol = maxCol;
}
public int getMaxRow() {
	return maxRow;
}
public void setMaxRow(int maxRow) {
	this.maxRow = maxRow;
}
public void setDestinations(ArrayList<Destination> destinations) {
	this.destinations = destinations;
}


public ArrayList<Player> getPlayers() {
	return sokobans;
}

public void setPlayers(ArrayList<Player> players) {
	this.sokobans = players;
}

public int getBoxNumber(){
	return this.getBoxes().size();
}
public int getDestNumber(){
	return this.getDestinations().size();
}
public int getSpaceNumber(){
	return this.getSpaces().size();
}
public int getPlayerNumber(){
	return this.getPlayers().size();
}
public int getWallNumber(){
	return this.getWalls().size();
}
//checks how much boxes on the destinations
public int boxOnDest (){
	System.out.println(elementsString.toString());
	System.out.println(dynamicObjects.toString());
	int counter=0;
	for(int i=0;i<elementsString.size();i++){
		int j=0;
		String temp=elementsString.get(i);
		for (char tav: temp.toCharArray()){
			if((tav=='O') &&(dynamicObjects.get(i).get(j).getClass()==getBoxes().get(0).getClass()))
					counter++;
			j++;
		}

	}
	return counter;
}


public ArrayList<Player> getSokobans() {
	return sokobans;
}
public int getLevelId() {
	return LevelId;
}
public String getLevelName() {
	return LevelName;
}
public void setSokobans(ArrayList<Player> sokobans) {
	this.sokobans = sokobans;
}
public void setLevelId(int levelId) {
	LevelId = levelId;
}
public void setLevelName(String levelName) {
	LevelName = levelName;
}
//checks if the player own
public boolean Win(){
	if(boxOnDest()==boxes.size())
		return true;
	return false;
	/*
	System.out.println("Im herr");
	int numOfBoxes= boxes.size();
	int counter=0;
	for(int i=0;i<boxes.size();i++){
		for(int j=0;j<destinations.size();j++){
		if(boxes.get(i).getPos()==destinations.get(j).getPos()){
			counter++;
			System.out.println(counter);
		}
		System.out.println(boxes.get(i).getPos());
		System.out.println(destinations.get(j).getPos());
		}
	}
	if(numOfBoxes==counter){
		return true;
	}
	return false;
*/
}

//default c'tor
public Level()
{
this.elements=new ArrayList<Element>();
this.boxes= new ArrayList<Box>();
this.walls=new ArrayList<Wall>();
this.destinations =new ArrayList<Destination>();
this.spaces=new ArrayList<Space>();
}
//c'tor that gets string and makes them objects
public Level (ArrayList<String> c){
elementsString = new ArrayList<String>();
this.elementsString=c;
setelementsString(c);
Position pos= new Position();
this.sokobans=new ArrayList<Player>();
this.walls=new ArrayList<Wall>();
this.destinations=new ArrayList<Destination>();
this.boxes=new ArrayList<Box>();
this.spaces=new ArrayList<Space>();
int col=0;
dynamicObjects=new ConvertStrToObj().convert(c);

for(int i=0;i<c.size();i++){
	String temp=c.get(i);
	pos.setX(i);
	if(this.maxRow<=i)
		this.maxRow=i;
	for (char tav: temp.toCharArray())
	{
		if(tav==' '){
			Space space= new Space(new Position(i,col));
			dynamicObjects.get(i).add(space);
			elements.add(space);
			spaces.add(space);
			setSpaces(spaces);
			}
		if(tav=='#'){
			Wall wall= new Wall(new Position(i,col));
			dynamicObjects.get(i).add(wall);
			elements.add(wall);
			walls.add(wall);
			setWalls(walls);

		}
		if(tav=='O'){
			Destination des= new Destination(new Position(i,col));
			dynamicObjects.get(i).add(des);
			elements.add(des);
			destinations.add(des);
			setDestinations(destinations);

		}
		if(tav=='A'){
			Player player= new Player(new Position(i,col));
			dynamicObjects.get(i).add(player);
			elements.add(player);
			sokobans.add(player);
			System.out.println("new player!!");
			setPlayers(sokobans);
		}
		if(tav=='@'){
			Box box= new Box(new Position(i,col));
			dynamicObjects.get(i).add(box);
			elements.add(box);
			boxes.add(box);
			setBoxes(boxes);
		}

	col++;
	if(col>= this.maxCol)
	{
		this.maxCol=col;

	}
	pos.setY(col);

}
	col=0;
}
for(int i=0;i<=maxRow;i++)
	for(int j=dynamicObjects.get(i).size();j<=maxCol;j++)
	{
		if (dynamicObjects.get(i).size()<maxCol)
			dynamicObjects.get(i).add(j, new Space(new Position(i,j)));
	}
}
}
