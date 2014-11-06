package es.javiergomez.tutorial.rmi;

import es.javiergomez.tutorial.rmi.MiInterfazRemoto;

public class MiClaseRemota extends java.rmi.server.UnicastRemoteObject implements MiInterfazRemoto
{
  public MiClaseRemota() throws java.rmi.RemoteException
  {
    // C�digo del constructor
  }

  public void miMetodo1() throws java.rmi.RemoteException
  {
    // Aqu� ponemos el c�digo que queramos
    System.out.println("Estoy en miMetodo1()");
  }

  public int miMetodo2() throws java.rmi.RemoteException
  {
    return 5; // Aqu� ponemos el c�digo que queramos
  }

  public void otroMetodo()
  {
    // Si definimos otro m�todo, �ste no podr�a llamarse
    // remotamente al no ser del interfaz remoto
  }

  public static void main(String[] args)
  {
    try
    {
      MiInterfazRemoto mir = new MiClaseRemota();
      java.rmi.Naming.rebind("//" + java.net.InetAddress.getLocalHost().getHostAddress() +
                              ":" + args[0] + "/PruebaRMI", mir);
      System.out.println("Binded"+ "//" + java.net.InetAddress.getLocalHost().getHostAddress() +
              ":" + args[0] + "/PruebaRMI");
    }
    catch (Exception e)
    {
    	System.out.println("Exception..." + e);
    	e.printStackTrace();
    }
  }
}
