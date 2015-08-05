package sujj.concurrent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;



class ReliableWebServer { 

	  static Executor pool =

	    Executors.newFixedThreadPool(7);

	  static void flushBuffer(byte[] buffer,int size)
	  {
		  for(int i=0;i<size;i++)
		  {
			  buffer[i] = '\0';
			  
		  }
	  }
	    public static void main(String[] args) throws IOException {

	    ServerSocket socket = new ServerSocket(80);

	      while (true) {

	      final Socket connection = socket.accept();

	      Runnable r = new Runnable() {

	        public void run() {

	          handleRequest(connection);

	        }

			
			private void handleRequest(Socket connection) {
				// TODO Auto-generated method stub
				
				byte[] buffer = new byte[1024];
				try {
					int number = connection.getInputStream().read(buffer);
					while(number>0)
					{
						
						
						System.out.print(new String(buffer));
						flushBuffer(buffer,1024);
						number = connection.getInputStream().read(buffer);
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

	      };

	      pool.execute(r);

	    }

	  }

	}


public class webConcurrent {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
