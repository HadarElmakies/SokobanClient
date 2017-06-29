package view;

import commands.Printer;
import common.Level;
import javafx.beans.property.StringProperty;
import model.Model;
import model.policy.MySokobanPolicy;

public interface View {

	public void start();
	public void stop();
	public boolean isFlag();
	public void setFlag(boolean flag);
	public void display(Level level);

	public void exit();
	public void setP(Printer p);
	public void bindSteps(StringProperty steps);
}
