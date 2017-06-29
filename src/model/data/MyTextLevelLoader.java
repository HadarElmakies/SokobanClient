package model.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import common.Level;

public class MyTextLevelLoader implements LevelLoader {

	
	@Override
	public Level loadLevel(InputStream in)    {
		InputStreamReader isr=new InputStreamReader(in);
		BufferedReader br=new BufferedReader(isr);
		String newLine;
		ArrayList<String> lines= new ArrayList<String>();
		try {
			while((newLine=br.readLine())!=null){
				lines.add(newLine);
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Level newLevel= new Level(lines);
		return newLevel;

	}
}
