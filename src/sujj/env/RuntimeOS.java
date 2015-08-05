package sujj.env;

public class RuntimeOS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ncpu = Runtime.getRuntime().availableProcessors();
		System.out.println(ncpu);
	}

}
