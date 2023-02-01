package cn.netkiller;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public Test(int id) {
		this.id = id;
	}

	public int id;
	public String name;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public static void main(String[] args) throws InterruptedException {
		List<Test> arrayList = new ArrayList<Test>();
		arrayList.add(new Test(1));
		arrayList.add(new Test(2));
		arrayList.add(new Test(3));
		arrayList.add(new Test(4));
		arrayList.add(new Test(5));

		arrayList.forEach(item -> {
			System.out.println(item.getId());
			item.setId(item.getId() + 1);
		});

		arrayList.forEach(item -> {
			System.out.println(item.getId());
		});
	}

}
