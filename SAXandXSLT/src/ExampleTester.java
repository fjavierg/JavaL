
import java.io.FileNotFoundException;

public class ExampleTester {
  
  public static void main(String[] argv) {
    if (argv.length < 4) {
      System.out.println("Usage: ExampleTester <Class Name> <Input File Name> <Template File Name> <Output File Name>");
      System.exit(1);
    } 
    String className = argv[0];
    String inputFileName = argv[1];
    String templateFileName = argv[2];
    String outputFileName = argv[3];

    Class myClass = null;
    Example example = null;
    try {
      myClass = Class.forName(className);
      example = (Example)myClass.newInstance();
    } catch (ClassNotFoundException cnfe) {
      System.out.println("Class " + className + " not found. Check the command line argument 0.");
      System.exit(1);
    } catch (InstantiationException ie) {
      System.out.println("Cannot instantiate " + className + ". It may be an abstract class or an interface.");
      System.exit(1);
    } catch (IllegalAccessException iae) {
      System.out.println("Cannot instantiate " + className + ". The constructor may be private.");
      System.exit(1);
    }
    try { 
      example.doTransform(inputFileName, templateFileName, outputFileName);
    } catch (FileNotFoundException fnfe) {
      System.out.println(fnfe.getMessage());
      System.exit(1);
    }
  } 

}