package myutil.command;


public class Example2bCommandFactory extends CommandFactory {
  private Command nullCommand = new NullCommand();

  public Example2bCommandFactory() {
    CustomerIdCommand myCustomerIdCommand = new CustomerIdCommand();
    commands.put("/ORDER_INFO/CUSTOMER/ID", myCustomerIdCommand);
    FilterCommand myFilterCommand = new FilterCommand();
    commands.put("/ORDER_INFO/CUSTOMER/SERVICE_ORDERS/ORDER/TIMESTAMP", myFilterCommand);
    ProductIdCommand myProductIdCommand = new ProductIdCommand();
    commands.put("/ORDER_INFO/CUSTOMER/SERVICE_ORDERS/ORDER/PRODUCT_ID", myProductIdCommand);

    PriceCollectorCommand myPriceCollectorCommand = new PriceCollectorCommand(myProductIdCommand);
    commands.put("/ORDER_INFO/CUSTOMER/SERVICE_ORDERS/ORDER/PRICE", myPriceCollectorCommand);
    commands.put("/ORDER_INFO", new PriceSummaryPrintingCommand(myPriceCollectorCommand));
  }
 
  public Command getCommand(String _elementName) {
    Command myCommand = (Command)commands.get(_elementName);
    if (myCommand == null) {
      myCommand = nullCommand;
    }
    return myCommand;
  };

}