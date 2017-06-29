package commands;

import common.Level;
import model.Model;
import view.View;

public class DisplayLevelCommand extends Command {

	private  Printer p;

	public DisplayLevelCommand(View v) {
		this.view=v;


}
	public DisplayLevelCommand(Model model,View view) {
		this.model=model;
		this.view= view;
	}





	@Override
	public void execute() {

		this.view.display(this.model.getLevel());

	}







}
