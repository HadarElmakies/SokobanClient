package commands;

import common.Level;

public class LevelPrinter implements Printer {

	@Override
	public void print(Level lev) {
		for(int i=0;i<lev.getDynamicObjects().size();i++)
			System.out.println(lev.getDynamicObjects().get(i).toString().replace("[", "").replace(",", "").replace("]", ""));
	}


}
