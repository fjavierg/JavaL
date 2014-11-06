package myutil.command;

import java.util.HashMap;

public abstract class CommandFactory {
  
  private static CommandFactory myCommandFactory = null;

  protected HashMap commands = new HashMap();    

  public static CommandFactory getFactory(String _factoryIdentifier) {
    if (myCommandFactory != null) {
      return myCommandFactory;
    }
    if (_factoryIdentifier.equals("Example2")) {
      myCommandFactory = new Example2CommandFactory(); 
      return myCommandFactory;
    } else if (_factoryIdentifier.equals("Example2b")) {
      myCommandFactory = new Example2bCommandFactory(); 
      return myCommandFactory;
    } else {
      return new NullCommandFactory();
    }    
  }

  public abstract Command getCommand(String _elementName);
  
}