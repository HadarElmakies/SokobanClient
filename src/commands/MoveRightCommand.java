package commands;

import common.Level;
import common.Player;
import javafx.beans.property.StringProperty;
import model.Model;
import model.policy.MySokobanPolicy;

public class MoveRightCommand extends Move {
StringProperty countSteps;
int counter=0;


	public MoveRightCommand(Model model){
		this.model=model;
	}




	public MoveRightCommand(Model model, StringProperty countSteps) {
		this.model=model;
		this.countSteps=countSteps;
	}




	@Override
	public void execute() {
		this.setLev(this.model.getLevel());
		this.setMsp(this.model.getPolicy());
		this.setPlayer(this.model.getLevel().getPlayers().get(0));
		this.model.moveRight();
		counter=this.model.getSteps();



		this.countSteps.set("steps: "+counter);



	}

}
