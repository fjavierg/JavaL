package es.javiergomez.tutorial.java.annotations;
import java.lang.annotation.Documented;

/*This annotation is documented so it willappear in Javadoc
 * Check it by Project->Generate Javadoc*/

@Documented
public @interface Annotation {int id(); String synopsis() default "[none]"; String engineer() default "[none]";}; 


