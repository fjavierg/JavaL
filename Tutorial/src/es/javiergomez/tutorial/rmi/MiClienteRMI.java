package es.javiergomez.tutorial.rmi;


public class MiClienteRMI
{
  public static void main(String[] args)
  {
    try
    {
      MiInterfazRemoto mir = (MiInterfazRemoto)java.rmi.Naming.lookup("//" + args[0] + ":" + args[1] + "/PruebaRMI");

      // Imprimimos miMetodo1() tantas veces como devuelva miMetodo2()
      for (int i=1;i<=mir.miMetodo2();i++) mir.miMetodo1();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }