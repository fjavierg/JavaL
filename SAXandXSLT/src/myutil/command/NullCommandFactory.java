package myutil.command;


public class NullCommandFactory extends CommandFactory {
  private Command nullCommand = new NullCommand();

  public Command getCommand(String _elementName) {
    return nullCommand;
  };

}