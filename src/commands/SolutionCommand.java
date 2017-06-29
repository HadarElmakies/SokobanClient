package commands;

import model.Model;
import view.View;

public class SolutionCommand extends Command {

	public SolutionCommand(Model model,View view) {
		this.model=model;
		this.view=view;
	}

	@Override
	public void execute() {
		System.out.println("i am in the execute of solution command");
		this.model.setSolutionCommand(this);
		this.model.solve();

	}

}
