package corePack;

import java.util.*;

public class valueComparator implements Comparator<String>{
	
	Map<String, Integer> map;
	
	public valueComparator(Map<String, Integer> base) {
		// TODO Auto-generated constructor stub
		map = base;
	}

	public int compare(String a, String b) {
		// TODO Auto-generated method stub
		if(map.get(a)<=map.get(b)){
			return 1;
		}
		else{
			return -1;
		}
	
	}

}
