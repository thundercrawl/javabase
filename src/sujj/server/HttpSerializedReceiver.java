package sujj.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import sujj.concurrent.MultiServerObject;

class HttpSerializedObject implements Serializable
{
	private String _secreteMSG = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6788871726783153368L;
	
	public String toString()
	{
		if (_secreteMSG == null)
			return "None";
		return _secreteMSG;		
		
	}
	public void setString(String s1)
	{
		_secreteMSG = s1;
	}
	}

class HttpReceiverImpl implements Runnable
{
	public void run()
	{
		// TODO Auto-generated method stub
		try {
			
			ServerSocket sc = new ServerSocket(8080,2000);
			//SocketAddress ad = new SocketAddress();
			logger.Log.info("Listen for the input stream for other server");
			ObjectInputStream is;
			while(!Thread.currentThread().isInterrupted())
			{
				Socket s = sc.accept();
			
			//byte[] buffer = new byte[2048];
			//s.getInputStream().read(buffer);
			//logger.Log.info("get the connections serialized object as : " + buffer);
			try{
				 is = new ObjectInputStream(s.getInputStream());
				logger.Log.info("Server received the client object as : " + is.readObject().toString());
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				logger.Log.info("Found an exceptional data from serialized data receiver !"+s.getInputStream());
				
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}
		
	}
	


class HttpObjectSenderImpl implements Runnable
{
	
	public void run()
	{
		HttpSerializedObject obj = new HttpSerializedObject();
		obj.setString("This PMR is so critical need you finish it soon");
		
		Socket s = new Socket();
		try {
			logger.Log.info("Client will send the object in 3 seconds");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			InetSocketAddress ia = new InetSocketAddress("localhost",8080);
			s.connect(ia, 6000);
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(bs);
			os.writeObject(obj);
			bs.writeTo(s.getOutputStream());
			s.getOutputStream().flush();
			//bs.writeTo(System.out);
			bs.close();
			os.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
public class HttpSerializedReceiver {

	public static void main(String[] args) {
	
		ExecutorService ex = Executors.newFixedThreadPool(50);
		ex.submit(new HttpReceiverImpl());
		for(int i=0;i<10000;i++)
		ex.submit(new HttpObjectSenderImpl());
		
		
	}

}
