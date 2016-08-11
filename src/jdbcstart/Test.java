package jdbcstart;

public class Test {
	public static void main(String[] args) {
		Integer n = null;
		method(n);
		System.out.println(n);
	}

	public static void method(Integer a) {
		a = new Integer(12);
	}
}
