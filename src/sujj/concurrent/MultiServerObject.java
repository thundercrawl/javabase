package sujj.concurrent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class crossSiteObject implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -474424721678736475L;
	//private  static final long serialVersionUID=14443L;
	public void set(){};
	//public long ids;
	public String toString(){ return "crossSiteObject";}
}

public class MultiServerObject implements Serializable {
	static public void stream1() throws ClassNotFoundException, IOException
	{
		//file stream
		// TODO Auto-generated method stub
				Object ob =  new crossSiteObject();
				logger.Log.info("Obj version : " + new crossSiteObject().hashCode());
				//logger.Log.info("Obj version : " + new crossSiteObject().hashCode());
				logger.Log.info("Last obj version: " + ob.hashCode());
				
				
				logger.Log.info("Class version: " + crossSiteObject.class.hashCode());
				//logger.Log.info("Class version: " + crossSiteObject.class.hashCode());
				
				//ObjectOutputStream st  = new ObjectOutputStream(new FileOutputStream("c:\\cross.obj")) ;
				//st.writeObject(ob);
		ObjectInputStream it = new ObjectInputStream(new FileInputStream("c:\\cross.obj"));
		Object ob1 = it.readObject();
		logger.Log.info("Deserialized object is : " + ob1.toString());
	
	}
	static public void stream2() throws IOException
	{
		//byte stream
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bs);
		//bs.write("hello".getBytes());
		os.writeObject(new crossSiteObject());
		bs.writeTo(System.out);
		
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
	
		stream2();
	}

}
