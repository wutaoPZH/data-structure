import com.mj.set.ListSet;
import com.mj.set.Set;
import com.mj.set.Set.Visitor;

public class Main {
	
	public static void main(String[] args) {
		test1();
	}
	
	static void test1() {
		testSet(new ListSet<>());
	}
	
	static void testSet(Set set) {
		set.add(4);
		set.add(2);
		set.add(1);
		set.add(3);
		set.add(4);
		set.traversal(new Visitor<Integer>() {
			@Override
			public boolean visit(Integer element) {
				System.out.println(element);
				return false;
			}
		});
	}
}
