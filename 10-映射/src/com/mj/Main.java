package com.mj;

import com.mj.map.Map;
import com.mj.map.Map.Visitor;
import com.mj.map.TreeMap;

public class Main {
	



	public static void main(String[] args) {
		
		Map<String, Integer> map = new TreeMap<>();
		map.put("吴涛涛", 1);
		map.put("郭敏敏", 2);
		map.put("吴涛涛", 3);
		System.out.println(map.containsKey("124"));
		System.out.println(map.containsKey("吴涛涛"));
		System.out.println(map.get("吴涛涛"));
		
		map.traversal(new Visitor<String, Integer>() {
			
			@Override
			public boolean visit(String key, Integer value) {
				System.out.println(key+"_"+value);
				return false;
			}
		});
		
	}
	

}
