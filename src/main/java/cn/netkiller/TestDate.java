package cn.netkiller;

import java.util.Date;

public class TestDate {

	public TestDate() {
		// TODO Auto-generated constructor stub
	}

	public void test(Date date) throws InterruptedException {
		System.out.println(date.getSeconds());
		Thread.sleep(5000);  
		System.out.println(date.getSeconds());
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		Date date = new Date();

		// System.out.println(date.getSeconds());
		// Thread.sleep(10000);
		// System.out.println(date.getSeconds());
		// Thread.sleep(10000);
		// System.out.println(date.getSeconds());
		TestDate td = new TestDate();
		td.test(new Date());

	}

}
