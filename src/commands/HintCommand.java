package commands;

import model.Model;
import view.View;

public class HintCommand extends Command {

	public HintCommand(Model model,View view) {
		this.model=model;
		this.view=view;
	}

	@Override
	public void execute() {
		this.model.hint();

	}


}
