package model.data;

import java.util.ArrayList;

public interface Converter<T> {
	
	ArrayList<ArrayList<T>>convert (ArrayList<String> arr);

}
