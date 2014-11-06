package myutil.command;


public class Example2CommandFactory extends CommandFactory {
  private Command nullCommand = new NullCommand();

  public Example2CommandFactory() {
    CustomerIdCommand myCustomerIdCommand = new CustomerIdCommand();
    commands.put("/ORDER_INFO/CUSTOMER/ID", myCustomerIdCommand);
    FilterCommand myFilterCommand = new FilterCommand();
    commands.put("/ORDER_INFO/CUSTOMER/SERVICE_ORDERS/ORDER/TIMESTAMP", myFilterCommand);
    ProductIdCommand myProductIdCommand = new ProductIdCommand();
    commands.put("/ORDER_INFO/CUSTOMER/SERVICE_ORDERS/ORDER/PRODUCT_ID", myProductIdCommand);
  }
 
  public Command getCommand(String _elementName) {
    Command myCommand = (Command)commands.get(_elementName);
    if (myCommand == null) {
      myCommand = nullCommand;
    }
    return myCommand;
  };

}