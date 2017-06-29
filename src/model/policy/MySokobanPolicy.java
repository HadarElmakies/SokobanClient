package model.policy;



import common.Box;
import common.Destination;
import common.Level;
import common.Player;
import common.Position;
import common.Space;
import common.Wall;
import model.Model;
import model.data.Move;

public class MySokobanPolicy implements Move {
	private Player player;
	private Wall wall= new Wall();
	private Destination des= new Destination();
	private Box box= new Box();
	private Space space= new Space();
	private boolean changed;
	int counter=0;
	private boolean succeedU=false;
	private boolean succeedD=false;
	private boolean succeedR=false;
	private boolean succeedL=false;
	private Model model;






	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public boolean isSucceedU() {
		if(this.succeedU==true){
			this.succeedU=false;
			return true;
		}
		return false;
	}

	public boolean isSucceedD() {
		if(this.succeedD==true){
			this.succeedD=false;
			return true;
		}
		return false;
	}

	public boolean isSucceedR() {
		if(this.succeedR==true){
			this.succeedR=false;
			return true;
		}
		return false;
	}

	public boolean isSucceedL() {
		if(this.succeedL==true){
			this.succeedL=false;
			return true;
		}
		return false;
	}

	public void setSucceedU(boolean succeedU) {
		this.succeedU = succeedU;
	}

	public void setSucceedD(boolean succeedD) {
		this.succeedD = succeedD;
	}

	public void setSucceedR(boolean succeedR) {
		this.succeedR = succeedR;
	}

	public void setSucceedL(boolean succeedL) {
		this.succeedL = succeedL;
	}


	public boolean isChanged() {
		return changed;
	}
	public int getCounter() {
		return counter;
	}
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Wall getWall() {
		return wall;
	}
	public void setWall(Wall wall) {
		this.wall = wall;
	}
	public Destination getDes() {
		return des;
	}
	public void setDes(Destination des) {
		this.des = des;
	}
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}
	public Space getSpace() {
		return space;
	}
	public void setSpace(Space space) {
		this.space = space;
	}
	public MySokobanPolicy(){
		this.player=null;

	}
	public MySokobanPolicy(Player player){
		this.player= new Player();
		this.player=player;

	}
	public MySokobanPolicy(Model model){
		this.model=model;
	}


	@Override
	public void moveUp(Level lev) {

		boolean flag=false;
		this.changed=false;
		if (this.player.getPos().getX()==0 && this.changed==false)
			return;
		if((lev.getDynamicObjects().get(this.player.getPos().getX()-1).get(this.player.getPos().getY())).getClass()==wall.getClass()&& this.changed==false)
			return;
		if((lev.getDynamicObjects().get(this.player.getPos().getX()-1).get(this.player.getPos().getY())).getClass()==des.getClass()&& this.changed==false){
			Position newPos= new Position(this.player.getPos().getX(),this.player.getPos().getY());
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())=='O'&&flag==false){
				Destination desti= new Destination();
				desti.setPos(newPos);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),desti);
				flag=true;

			}

			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())==' '&&flag==false){
			Space space = new Space();
			space.setPos(newPos);
			lev.getSpaces().add(space);
			lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),space);
			flag=true;
			}
			if(flag==false){
				Space space = new Space();
				space.setPos(newPos);
				lev.getSpaces().add(space);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),space);
			}
			Player player = new Player(new Position(this.player.getPos().getX()-1,this.player.getPos().getY()));
			lev.getPlayers().set(0, player);
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()).setPos(lev.getDynamicObjects().get(this.player.getPos().getX()-1).get(this.player.getPos().getY()).getPos());
			lev.getDynamicObjects().get(this.player.getPos().getX()-1).set(this.player.getPos().getY(),player);
			this.player=player;
			this.changed=true;
			this.succeedU=true;
			System.out.println("sokoban moved up");
			counter++;

		}
		if(this.player.getPos().getY()>1&& this.changed==false)
		{
		if(((lev.getDynamicObjects().get(this.player.getPos().getX()-1).get(this.player.getPos().getY()).getClass())==box.getClass())&& (lev.getDynamicObjects().get(this.player.getPos().getX()-2).get(this.player.getPos().getY()).getClass()==box.getClass())&& this.changed==false)
			return;
		}
		if(((lev.getDynamicObjects().get(this.player.getPos().getX()-1).get(this.player.getPos().getY()).getClass())==box.getClass())&& (lev.getDynamicObjects().get(this.player.getPos().getX()-2).get(this.player.getPos().getY()).getClass()==wall.getClass())&& this.changed==false)
			return;
		if(lev.getDynamicObjects().get(this.player.getPos().getX()-1).get(this.player.getPos().getY()).getClass()==space.getClass()){
			Position newPos= new Position(this.player.getPos().getX(),this.player.getPos().getY());
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())=='O'){
				Destination desti= new Destination();
				desti.setPos(newPos);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),desti);
			}
			else{
			Position newPoseSpace= new Position(this.player.getPos().getX(),this.player.getPos().getY());
			Space space= new Space(newPoseSpace);
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()).setPos(newPoseSpace);
			lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),space);

			}
			Position newPosePlayer= new Position(this.player.getPos().getX()-1,this.player.getPos().getY());
			lev.getDynamicObjects().get(this.player.getPos().getX()-1).get(this.player.getPos().getY()).setPos(newPosePlayer);
			Player player= new Player(newPosePlayer);
			lev.getDynamicObjects().get(this.player.getPos().getX()-1).set(this.player.getPos().getY(),player);
			lev.getPlayers().set(0, player);
			this.player=player;
			this.changed=true;
			this.succeedU=true;
			System.out.println("sokoban moved up");
			counter++;

		}


		if(lev.getDynamicObjects().get(this.player.getPos().getX()-1).get(this.player.getPos().getY()).getClass()==box.getClass()&& this.changed==false){
			Position newPoseSpace = new Position(this.player.getPos().getX(),this.player.getPos().getY());
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())=='O'&&flag==false){
				Destination desti= new Destination();
				desti.setPos(newPoseSpace);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),desti);
			}
			else{
				Space space=new Space(newPoseSpace);
				lev.getSpaces().add(space);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(), space);

			}
			Position newPoseBox= new Position(this.player.getPos().getX()-2,this.player.getPos().getY());
			Position newPosePlayer=new Position(this.player.getPos().getX()-1,this.player.getPos().getY());
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()).setPos(newPoseSpace);
			lev.getDynamicObjects().get(this.player.getPos().getX()-1).get(this.player.getPos().getY()).setPos(newPosePlayer);
			lev.getDynamicObjects().get(this.player.getPos().getX()-2).get(this.player.getPos().getY()).setPos(newPoseBox);

			lev.getDynamicObjects().get(this.player.getPos().getX()-2).set(this.player.getPos().getY(),lev.getDynamicObjects().get(this.player.getPos().getX()-1).get(this.player.getPos().getY()));
			Player player= new Player(newPosePlayer);
			lev.getDynamicObjects().get(this.player.getPos().getX()-1).set(this.player.getPos().getY(), player);

			lev.getPlayers().set(0, player);
			this.player=player;
			this.changed=true;
			this.succeedU=true;
			System.out.println("sokoban moved up");
			counter++;
		}

		}


	@Override
	public void moveDown(Level lev) {
		this.changed=false;
		boolean flag=false;
		if (this.player.getPos().getX()==lev.getMaxRow()&& this.changed==false) //אני נמצאת בשורה התחתונה אין לי לאן לרדת
			return;
		if((lev.getDynamicObjects().get(this.player.getPos().getX()+1).get(this.player.getPos().getY())).getClass()==wall.getClass()&& this.changed==false) //אם יש מתחתיי קיר
			return;
		if((lev.getDynamicObjects().get(this.player.getPos().getX()+1).get(this.player.getPos().getY())).getClass()==des.getClass()&& this.changed==false){//אם יש מתחתיי יעד
			Position newPos= new Position(this.player.getPos().getX(),this.player.getPos().getY());
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())=='O'&&flag==false){
				Destination desti= new Destination();
				desti.setPos(newPos);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),desti);
				flag=true;
			}
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())==' '&&flag==false){
			Space space = new Space();
			space.setPos(newPos);
			lev.getSpaces().add(space);
			lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),space);
			flag=true;
			}
			if(flag==false){
				Space space = new Space();
				space.setPos(newPos);
				lev.getSpaces().add(space);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),space);
			}
			Player player = new Player(new Position(this.player.getPos().getX()+1,this.player.getPos().getY()));
			lev.getPlayers().set(0, player);
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()).setPos(lev.getDynamicObjects().get(this.player.getPos().getX()+1).get(this.player.getPos().getY()).getPos());
			lev.getDynamicObjects().get(this.player.getPos().getX()+1).set(this.player.getPos().getY(),player);
			this.player=player;
			this.changed=true;
			this.succeedD=true;
			System.out.println("sokoban moved down");
			counter++;

		}
		if(this.player.getPos().getX()>1&& this.changed==false)
		{
		if(((lev.getDynamicObjects().get(this.player.getPos().getX()+1).get(this.player.getPos().getY()).getClass())==box.getClass())&& (lev.getDynamicObjects().get(this.player.getPos().getX()+2).get(this.player.getPos().getY()).getClass()==box.getClass())&& this.changed==false)
			return;
		}
		if(((lev.getDynamicObjects().get(this.player.getPos().getX()+1).get(this.player.getPos().getY()).getClass())==box.getClass())&& (lev.getDynamicObjects().get(this.player.getPos().getX()+2).get(this.player.getPos().getY()).getClass()==wall.getClass())&& this.changed==false)
			return;
		if(lev.getDynamicObjects().get(this.player.getPos().getX()+1).get(this.player.getPos().getY()).getClass()==space.getClass()&& this.changed==false){
			Position newPoseSpace= new Position(this.player.getPos().getX(),this.player.getPos().getY());
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())=='O'){
				Destination desti= new Destination();
				desti.setPos(newPoseSpace);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),desti);

			}
			else{
				Space space= new Space(newPoseSpace);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),space);
				lev.getSpaces().add(space);
			}
			Position newPosePlayer= new Position(this.player.getPos().getX()+1,this.player.getPos().getY());
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()).setPos(newPoseSpace);
			lev.getDynamicObjects().get(this.player.getPos().getX()+1).get(this.player.getPos().getY()).setPos(newPosePlayer);
			Player player= new Player(newPosePlayer);
			lev.getDynamicObjects().get(this.player.getPos().getX()+1).set(this.player.getPos().getY(), player);

			lev.getPlayers().set(0, player);
			this.player=player;
			this.changed=true;
			this.succeedD=true;

			System.out.println("sokoban moved down");
			counter++;

		}


		if(lev.getDynamicObjects().get(this.player.getPos().getX()+1).get(this.player.getPos().getY()).getClass()==box.getClass()&& this.changed==false){
			Position newPoseSpace = new Position(this.player.getPos().getX(),this.player.getPos().getY());
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())=='O'&&flag==false){
				Destination desti= new Destination();
				desti.setPos(newPoseSpace);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),desti);
			}
			else
			{
				Space space=new Space(newPoseSpace);
				lev.getSpaces().add(space);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(), space);

			}
			Position newPoseBox= new Position(this.player.getPos().getX()+2,this.player.getPos().getY());
			Position newPosePlayer=new Position(this.player.getPos().getX()+1,this.player.getPos().getY());
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()).setPos(newPoseSpace);
			lev.getDynamicObjects().get(this.player.getPos().getX()+1).get(this.player.getPos().getY()).setPos(newPosePlayer);
			lev.getDynamicObjects().get(this.player.getPos().getX()+2).get(this.player.getPos().getY()).setPos(newPoseBox);

			lev.getDynamicObjects().get(this.player.getPos().getX()+2).set(this.player.getPos().getY(),lev.getDynamicObjects().get(this.player.getPos().getX()+1).get(this.player.getPos().getY()));
			Player player= new Player(newPosePlayer);
			lev.getDynamicObjects().get(this.player.getPos().getX()+1).set(this.player.getPos().getY(), player);
			lev.getPlayers().set(0, player);
			this.player=player;
			this.changed=true;
			this.succeedD=true;

			System.out.println("sokoban moved down");
			counter++;


		}

	}

	@Override
	public void moveRight(Level lev) {
		this.changed=false;
		if (this.player.getPos().getY()==lev.getMaxCol()&& this.changed==false) //אני נמצאת בשורה הימנית ביותר אין לי לאן להתקדם ימינה
			return;
		if((lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+1)).getClass()==wall.getClass()&& this.changed==false) //אם יש מימיני קיר
			return;
		boolean flag=false;
		if((lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+1)).getClass()==des.getClass()&& this.changed==false){//אם יש מימיני יעד
			Position newPos= new Position(this.player.getPos().getX(),this.player.getPos().getY());
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())=='O'){
				Destination desti= new Destination();
				desti.setPos(newPos);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),desti);
				flag=true;

			}
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())==' '&& flag==false){
			Space space = new Space();
			space.setPos(newPos);
			lev.getSpaces().add(space);
			lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),space);
			flag=true;
			}
			if(flag==false)
			{
				Space space = new Space();
				space.setPos(newPos);
				lev.getSpaces().add(space);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),space);

			}

			Player player = new Player(new Position(this.player.getPos().getX(),this.player.getPos().getY()+1));
			lev.getPlayers().set(0, player);
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+1).setPos(lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()).getPos());
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()).setPos(lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+1).getPos());
			lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY()+1,player);
			this.player=player;
			this.changed=true;
			this.succeedR=true;
			System.out.println("sokoban moved Right");
			counter++;
		}
		if(this.player.getPos().getY()>1&& this.changed==false)
		{
		if(((lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+1).getClass())==box.getClass())&& (lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+2).getClass()==box.getClass())&& this.changed==false)
			return;
		}
		if(((lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+1).getClass())==box.getClass())&& (lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+2).getClass()==wall.getClass())&& this.changed==false)
			return;
		if(lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+1).getClass()==space.getClass()&& this.changed==false){
			Position newPoseSpace= new Position(this.player.getPos().getX(),this.player.getPos().getY());
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())=='O'){
				Destination desti= new Destination();
				desti.setPos(newPoseSpace);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),desti);

			}
			else
			{
				Space space= new Space(newPoseSpace);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),space);


			}
			Position newPosePlayer= new Position(this.player.getPos().getX(),this.player.getPos().getY()+1);
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()).setPos(newPoseSpace);
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+1).setPos(newPosePlayer);
			Player player= new Player(newPosePlayer);
			lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY()+1, player);
			lev.getPlayers().set(0, player);
			this.player=player;
			this.changed=true;
			this.succeedR=true;

			System.out.println("sokoban moved right");
			counter++;

		}


		if(lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+1).getClass()==box.getClass()&& this.changed==false){
			Position newPoseSpace = new Position(this.player.getPos().getX(),this.player.getPos().getY());
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())=='O'&&flag==false){
				Destination desti= new Destination();
				desti.setPos(newPoseSpace);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),desti);
			}
			else
			{
				Space space=new Space(newPoseSpace);
				lev.getSpaces().add(space);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(), space);

			}
			Position newPoseBox= new Position(this.player.getPos().getX(),this.player.getPos().getY()+2);
			Position newPosePlayer=new Position(this.player.getPos().getX(),this.player.getPos().getY()+1);

			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()).setPos(newPoseSpace);
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+1).setPos(newPosePlayer);
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+2).setPos(newPoseBox);

			lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY()+2,lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()+1));
			Player player= new Player(newPosePlayer);
			lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY()+1, player);
			lev.getPlayers().set(0, player);

			this.player=player;
			this.changed=true;
			this.succeedR=true;

			System.out.println("sokoban moved right");
			counter++;
		}
	}


	@Override
	public void moveLeft(Level lev) {
		this.changed=false;
		if (this.player.getPos().getY()==0&& this.changed==false) //אני נמצאת בשורה הראשונה אין לי לאן לעלות
			return;
		if((lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()-1)).getClass()==wall.getClass()&& this.changed==false) //אם יש משמאלי קיר
			return;
		boolean flag=false;
		if((lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()-1)).getClass()==des.getClass()&& this.changed==false){//אם יש  משמאלי יעד
			Position newPos= new Position(this.player.getPos().getX(),this.player.getPos().getY());
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())=='O'&&flag==false){
				Destination desti= new Destination();
				desti.setPos(newPos);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),desti);
				flag=true;

			}
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())==' '&&flag==false){
			Space space = new Space();
			space.setPos(newPos);
			lev.getSpaces().add(space);
			lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),space);
			flag=true;
			}
			if(flag==false)
			{
				Space space = new Space();
				space.setPos(newPos);
				lev.getSpaces().add(space);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),space);
			}
			Player player = new Player(new Position(this.player.getPos().getX(),this.player.getPos().getY()-1));
			lev.getPlayers().set(0, player);
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()).setPos(lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()-1).getPos());
			lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY()-1,player);
			this.changed=true;
		}
		if(this.player.getPos().getY()>1&& this.changed==false)
		{
		if(((lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()-1).getClass())==box.getClass())&& (lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()-2).getClass()==box.getClass())&& this.changed==false)
			return;
		}
		if(((lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()-1).getClass())==box.getClass())&& (lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()-2).getClass()==wall.getClass())&& this.changed==false)
			return;
		if(lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()-1).getClass()==space.getClass()&& this.changed==false){
			Position newPoseSpace= new Position(this.player.getPos().getX(),this.player.getPos().getY());
			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())=='O'){
				Destination desti= new Destination();
				desti.setPos(newPoseSpace);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),desti);
			}
			else{
				Space space= new Space(newPoseSpace);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),space);


			}
			Position newPosePlayer= new Position(this.player.getPos().getX(),this.player.getPos().getY()-1);
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()).setPos(newPoseSpace);
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()-1).setPos(newPosePlayer);
			Player player= new Player(newPosePlayer);
			lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY()-1, player);
			lev.getPlayers().set(0, player);
			this.player=player;
			this.changed=true;
			this.succeedL=true;
			System.out.println("sokoban moved left");
			counter++;
		}


		if(lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()-1).getClass()==box.getClass()&& this.changed==false){
			Position newPoseSpace = new Position(this.player.getPos().getX(),this.player.getPos().getY());

			if(lev.getElementsString().get(this.player.getPos().getX()).charAt(this.player.getPos().getY())=='O'&&flag==false){
				Destination desti= new Destination();
				desti.setPos(newPoseSpace);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(),desti);
			}
			else{
				Space space=new Space(newPoseSpace);
				lev.getSpaces().add(space);
				lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY(), space);

			}
			Position newPoseBox= new Position(this.player.getPos().getX(),this.player.getPos().getY()-2);
			Position newPosePlayer=new Position(this.player.getPos().getX(),this.player.getPos().getY()-1);

			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()).setPos(newPoseSpace);
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()-1).setPos(newPosePlayer);
			lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()-2).setPos(newPoseBox);

			lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY()-2,lev.getDynamicObjects().get(this.player.getPos().getX()).get(this.player.getPos().getY()-1));
			Player player= new Player(newPosePlayer);
			lev.getDynamicObjects().get(this.player.getPos().getX()).set(this.player.getPos().getY()-1, player);

			lev.getPlayers().set(0, player);
			this.player=player;
			this.changed=true;
			this.succeedL=true;

			System.out.println("sokoban moved left");
			counter++;


		}


	}


}
