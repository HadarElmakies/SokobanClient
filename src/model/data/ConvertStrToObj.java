package model.data;

import java.util.ArrayList;

import common.Element;

public class ConvertStrToObj implements Converter<Element> {

	@Override
	public ArrayList<ArrayList<Element>> convert(ArrayList<String> arr) {
		ArrayList<ArrayList<Element>> matrix= new ArrayList<ArrayList<Element>>(); 
		for(String element :arr)
		{
			matrix.add(new ArrayList<Element>());
		}
		
		return matrix;
	}


	

}
