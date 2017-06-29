package model.data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import common.Level;

public class MyTextLevelSaver implements LevelSaver {
	private Level lev;
	
	public MyTextLevelSaver(Level lev) {
		this.lev= lev;
	}
	
	public MyTextLevelSaver() {
		this.lev= null;
	}

		@Override
		public void saveLevel(OutputStream out, Level lev)      {
			
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
				try {
					bw.write("the destinations are:");
					bw.newLine();
					bw.write(lev.getDestinations().toString());
					bw.newLine();
					bw.write("The positions of the destinations are:");
					bw.newLine();
					for(int i=0;i<lev.getDestinations().size();i++){
						bw.write(lev.getDestinations().get(i).getPos().toString());
						bw.newLine();

					}
					
					bw.write("the boxes are:");
					bw.newLine();
					bw.write(lev.getBoxes().toString());
					bw.newLine();
					bw.write("The positions of the boxes are:");
					bw.newLine();

					for(int i=0;i<lev.getBoxes().size();i++){
						bw.write(lev.getBoxes().get(i).getPos().toString());
						bw.newLine();

					}
					
					bw.write("the elements are:");
					bw.newLine();
					bw.write(lev.getDynamicObjects().toString());
					bw.newLine();
					bw.write("The positions of the elements are:");
					bw.newLine();

					for(int i=0;i<lev.getMaxRow();i++){
						for(int j=0;j<lev.getMaxCol();j++){
						bw.write(lev.getDynamicObjects().get(i).get(j).getPos().toString());
						bw.newLine();
					}
					}
					bw.write("the players are:");
					bw.newLine();
					bw.write(lev.getPlayers().toString());
					bw.newLine();
					bw.write("The positions of the players are:");
					bw.newLine();

					for(int i=0;i<lev.getPlayers().size();i++){
						bw.write(lev.getPlayers().get(i).getPos().toString());
						bw.newLine();

					}
					bw.write("the walls are:");
					bw.newLine();
					bw.write(lev.getWalls().toString());
					bw.newLine();
					bw.write("The positions of the walls are:");
					bw.newLine();

					for(int i=0;i<lev.getWalls().size();i++){
						bw.write(lev.getWalls().get(i).getPos().toString());
						bw.newLine();

					}
					bw.write("the spaces are:");
					bw.newLine();
					bw.write(lev.getSpaces().toString());
					bw.newLine();
					bw.write("The positions of the destinations are:");
					bw.newLine();

					for(int i=0;i<lev.getSpaces().size();i++){
						bw.write(lev.getSpaces().get(i).getPos().toString());
						bw.newLine();

					}
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}

		
	}
