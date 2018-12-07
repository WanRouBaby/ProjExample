package aop;

public class DemoAOP {

	public String method1(String name) {
		long time = System.currentTimeMillis();
		return name + " : " + time;
	}
}
