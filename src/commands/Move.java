package commands;

import common.Level;
import common.Player;
import model.Model;
import model.policy.MySokobanPolicy;

public abstract class Move extends Command {
	protected Level lev;
	protected Model model;
	protected Player player;
	protected MySokobanPolicy msp;



	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public MySokobanPolicy getMsp() {
		return msp;
	}
	public void setMsp(MySokobanPolicy msp) {
		this.msp = msp;
	}


	public Level getLev() {
		return lev;
	}



	public void setLev(Level lev) {
		this.lev = lev;
	}

}
