package sujj.mathematics;

public class Prandom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<10000;i++)
		{	
			for(int j=0;j<5;j++)
				System.out.print("\t"+((int)(Math.random()*2)));
			System.out.println();
		}
	}

}
