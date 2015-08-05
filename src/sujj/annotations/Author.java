package sujj.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;




@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level，here can limit the class annotation access level,very important
public @interface Author {

	String name() default "-1";

}
