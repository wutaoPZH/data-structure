package com.panzh;

import java.awt.MenuComponent;

import com.panzh.Times.Task;
import com.panzh.file.FileInfo;
import com.panzh.file.Files;
import com.panzh.model.Key;
import com.panzh.model.SubKey1;
import com.panzh.model.SubKey2;
import com.panzh.tree.HashMap;
import com.panzh.tree.Map;
import com.panzh.tree.Map.Visitor;

/**
 * 因为红黑树太难写了，我们就直接用AVL树来代替红黑树进行一个哈希表的完成。
 * @author 涛宝宝
 *
 */
public class main {
	
	static void test1Map(Map<String, Integer> map, String[] words) {
		Times.test(map.getClass().getName(), new Task() {
			@Override
			public void execute() {
				for (String word : words) {
					map.put(word, 1);
				}
				System.out.println(map.size()); // 17188
				
				for (String word : words) {
					map.remove(word);
				}
				Asserts.test(map.size() == 0);
			}
		});
	}
	
	static void test2Map(java.util.HashMap<String, Integer> map, String[] words) {
		Times.test(map.getClass().getName(), new Task() {
			@Override
			public void execute() {
				for (String word : words) {
					map.put(word, 1);
				}
				System.out.println(map.size()); // 17188
				
				for (String word : words) {
					map.remove(word);
				}
				Asserts.test(map.size() == 0);
			}
		});
	}
	
	
	static void test2(HashMap<Object, Integer> map) {
		for (int i = 1; i <= 20; i++) {
			map.put(new Key(i), i);
		}
		for (int i = 5; i <= 7; i++) {
			map.put(new Key(i), i + 5);
		}
		Asserts.test(map.size() == 20);
		Asserts.test(map.get(new Key(4)) == 4);
		Asserts.test(map.get(new Key(5)) == 10);
		Asserts.test(map.get(new Key(6)) == 11);
		Asserts.test(map.get(new Key(7)) == 12);
		Asserts.test(map.get(new Key(8)) == 8);
	}
	
	static void test3(HashMap<Object, Integer> map) {
		map.put(null, 1); // 1
		map.put(new Object(), 2); // 2
		map.put("jack", 3); // 3
		map.put(10, 4); // 4
		map.put(new Object(), 5); // 5
		map.put("jack", 6);
		map.put(10, 7);
		map.put(null, 8);
		map.put(10, null);
		Asserts.test(map.size() == 5);
		Asserts.test(map.get(null) == 8);
		Asserts.test(map.get("jack") == 6);
		Asserts.test(map.get(10) == null);
		Asserts.test(map.get(new Object()) == null);
		Asserts.test(map.containsKey(10));
		Asserts.test(map.containsKey(null));
		Asserts.test(map.containsValue(null));
		Asserts.test(map.containsValue(1) == false);
	}
	
	static void test4(HashMap<Object, Integer> map) {
		map.put("jack", 1);
		map.put("rose", 2);
		map.put("jim", 3);
		map.put("jake", 4);		
		map.remove("jack");
		map.remove("jim");//2
		for (int i = 1; i <= 10; i++) {
			map.put("test" + i, i);
			map.put(new Key(i), i);
		}//22
//		

		for (int i = 5; i <= 7; i++) {
			Asserts.test(map.remove(new Key(i)) == i);
		}//19
			
		
		for (int i = 1; i <= 3; i++) {
			map.put(new Key(i), i + 5);
		}//19
//		
//		System.out.println("新put完元素size"+map.size());
//		map.traversal(new Visitor<Object, Integer>() {
//			public boolean visit(Object key, Integer value) {
//				System.out.println(key + "_" + value);
//				return false;
//			}
//		});
//		
		System.out.println(map.size());
		Asserts.test(map.size() == 19);
		
		System.out.println(map.get(new Key(1)));
		System.out.println(map.get(new Key(2)));
		Asserts.test(map.get(new Key(1)) == 6);
		Asserts.test(map.get(new Key(2)) == 7);
		Asserts.test(map.get(new Key(3)) == 8);
		Asserts.test(map.get(new Key(4)) == 4);
		Asserts.test(map.get(new Key(5)) == null);
		Asserts.test(map.get(new Key(6)) == null);
		Asserts.test(map.get(new Key(7)) == null);
		Asserts.test(map.get(new Key(8)) == 8);
//		map.traversal(new Visitor<Object, Integer>() {
//			public boolean visit(Object key, Integer value) {
//				System.out.println(key + "_" + value);
//				return false;
//			}
//		});
	}
	
	static void test5(HashMap<Object, Integer> map) {
		for (int i = 1; i <= 20; i++) {
			map.put(new SubKey1(i), i);
		}
		map.put(new SubKey2(1), 5);
		Asserts.test(map.get(new SubKey1(1)) == 5);
		Asserts.test(map.get(new SubKey2(1)) == 5);
		Asserts.test(map.size() == 20);
	}
	
	
	
	public static void main(String[] args) {
		
//		test5(new HashMap<>());
		
//		HashMap<String, Integer> map = new HashMap<>();
//		map.put("111", 1);
//		map.put("121", 2);
//		map.put("131", 3);
//		map.remove("111");
//		System.out.println(map.size());
//		
//		map.traversal(new Visitor<String, Integer>() {
//			@Override
//			public boolean visit(String key, Integer value) {
//				System.out.println(key+"_"+value);
//				return false;
//			}
//		});
		String filepath = "C:\\Users\\涛宝宝\\Desktop\\垃圾文件夹\\java\\util\\concurrent";
		FileInfo fileInfo = Files.read(filepath, null);
		String[] words = fileInfo.words();

		System.out.println("总行数：" + fileInfo.getLines());
		System.out.println("单词总数：" + words.length);
		System.out.println("-------------------------------------");

//		test1Map(new TreeMap<>(), words);
//		测试1还是过不到，好难受
		test1Map(new HashMap<>(), words);
//		test2Map(new java.util.HashMap<>(), words);
//		test1Map(new LinkedHashMap<>(), words);
	}
	
	

}
