package es.javiergomez.tutorial.rmi;

public interface MiInterfazRemoto extends java.rmi.Remote
{
  public void miMetodo1() throws java.rmi.RemoteException;
  public int miMetodo2() throws java.rmi.RemoteException;
}
