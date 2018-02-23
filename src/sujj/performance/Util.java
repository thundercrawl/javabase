package sujj.performance;

public class Util {

	public static void cal1()
	{
		double t1 = -1.0;
	    double t2 = 1.0;
	    double step = 1e-6;

	    double z = 0.0;
	    for(double t=t1; t<=t2; t += step) {
	        double y = Math.tanh(t);
	        z += y;
	    }
	}
}
