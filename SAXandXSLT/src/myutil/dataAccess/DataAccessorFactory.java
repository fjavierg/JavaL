package myutil.dataAccess;

import java.util.HashMap;

public abstract class DataAccessorFactory {
  
  private static DataAccessorFactory myDataAccessorFactory = null;
  private static String mode = System.getProperty("mode");

  protected DataAccessor myAccessor = null;

  public static DataAccessorFactory getFactory() {
    if (myDataAccessorFactory != null) {
      return myDataAccessorFactory;
    }
    if (mode.equals("debug") || mode.equals("default")) {
      myDataAccessorFactory = new DummyDataAccessorFactory();   
      return myDataAccessorFactory;
    } else {
      return null;
    }    
  }

  public abstract DataAccessor getDataAccessor();
  
}