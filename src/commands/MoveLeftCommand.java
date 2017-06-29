package commands;

import common.Level;
import common.Player;
import javafx.beans.property.StringProperty;
import model.Model;
import model.policy.MySokobanPolicy;

public class MoveLeftCommand extends Move {
	StringProperty countSteps;
	int counter=0;

	public MoveLeftCommand(Model model){
		this.model=model;
	}




	public MoveLeftCommand(Model model, StringProperty countSteps) {
		this.model=model;
		this.countSteps=countSteps;
	}




	@Override
	public void execute() {
		this.setLev(this.model.getLevel());
		this.setMsp(this.model.getPolicy());
		this.setPlayer(this.model.getLevel().getPlayers().get(0));
		this.model.moveLeft();
		counter=this.model.getSteps();


		this.countSteps.set("steps: "+counter);


	}

}
