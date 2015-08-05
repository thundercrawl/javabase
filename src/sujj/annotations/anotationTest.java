package sujj.annotations;
import java.lang.annotation.*;
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

@Author(name = "Sujj")

public class anotationTest {
	
	public final String  abc = "";
	
	public void runData()
	{
		System.out.println("Run Data test passed!");
	}

	/**
	 * @param args
	 */
	

	

}
