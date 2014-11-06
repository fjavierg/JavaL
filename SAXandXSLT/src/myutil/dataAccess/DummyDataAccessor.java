package myutil.dataAccess;

public class DummyDataAccessor implements DataAccessor { 

  public String getCustomerName(String _customerId) {
    if (_customerId.equals("234")) return "Jill";
    if (_customerId.equals("111")) return "Jack";
    return "Unknown";
  };

  public String getProductName(String _productId) {
    if (_productId.equals("1231")) return "Doohickey";
    if (_productId.equals("2001")) return "Raccoon";
    if (_productId.equals("1001")) return "Nose Cleaner";
    return "Unknown";    
  };
}