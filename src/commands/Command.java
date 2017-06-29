package commands;

import java.util.LinkedList;

import model.Model;
import view.View;

public abstract class Command implements general.Command  {

	//data members
	private LinkedList<String> params;
protected Model model;
protected View view;

	public Model getModel() {
	return model;
}
public View getView() {
	return view;
}
public void setModel(Model model) {
	this.model = model;
}
public void setView(View view) {
	this.view = view;
}
	public LinkedList<String> getParams() {
		return params;
	}
	public void setParams(LinkedList<String> params) {
		this.params = params;
	}


}
