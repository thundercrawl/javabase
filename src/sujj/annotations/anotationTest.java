package sujj.annotations;
import java.lang.annotation.*;

import logger.Log;
import sujj.annotations.Author;


interface House {
    @Deprecated
    void open();
 
    void openFrontDoor();
    @Deprecated
    void openBackDoor();
}


@Documented
@interface ClassPreamble {
	   String author();
	   String date();
	   int currentRevision() default 1;
	   String lastModified() default "N/A";
	   String lastModifiedBy() default "N/A";
	   // Note use of array
	   String[] reviewers();
	}

@ClassPreamble (
		   author = "John Doe",
		   date = "3/17/2002",
		   currentRevision = 6,
		   lastModified = "4/12/2004",
		   lastModifiedBy = "Jane Doe",
		   // Note array notation
		   reviewers = {"Alice", "Bob", "Cindy"}
		)

@Author(name = "Zhanghai")
@Configure(type="Service")
public class anotationTest {
	
	public final String  abc = "";
	
	public void runData()
	{
		System.out.println("Run Data test passed!");
	}
	
	public void service()
	{
		System.out.println("call service in "+this.getClass().getName());
		
		while(true)
		{
			try {
				Thread.sleep(3000);
				Log.info("call somethig here");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	

	

}
