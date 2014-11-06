package myutil.dataAccess;

public class DummyDataAccessorFactory extends DataAccessorFactory {

  public DataAccessor getDataAccessor() {
    if (myAccessor == null) {
      myAccessor = new DummyDataAccessor();
    }
    return myAccessor;
  };  

}