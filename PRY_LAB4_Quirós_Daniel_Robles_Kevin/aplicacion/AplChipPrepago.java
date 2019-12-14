/**
 * Pequeña clase tipo main, donde se pueden encontrar las pruebas 
 *
 * @author Kevin Robles, Daniel Quiros
 * @version 1.0
 */
package aplicacion;
import logicadenegocios.ChipPrepago;
import java.lang.Math;

public class AplChipPrepago{
  public static void main(String[] args){
      
    ChipPrepago chip1 = new ChipPrepago();
    ChipPrepago chip2 = new ChipPrepago("201");
    ChipPrepago chip3 = new ChipPrepago();
    
    System.out.println("Activar chips");
    System.out.println(chip1.activar("Kevin",100.0));
    System.out.println(chip2.activar("Daniel",150.0));
    System.out.println(chip1.activar("Prueba",0.0));
    System.out.println();
    
    System.out.println("Recargar");
    System.out.println(chip1.recargar(1300));
    System.out.println();
    
    System.out.println("Llamar");
    System.out.println(chip1.llamar(10,chip2));
    System.out.println(chip2.llamar(5,chip1));
    System.out.println(chip2.llamar(12,chip3));
    System.out.println();
    
    System.out.println("Enviar SMS");
    System.out.println(chip1.enviarSms("Hola Daniel",chip2));
    System.out.println(chip2.enviarSms("Hola Kevin",chip1));
    System.out.println();
    
    System.out.println("Navegar");
    System.out.println(chip1.navegar("fb.com"));
    System.out.println(chip2.navegar("instagram.com"));
    System.out.println();
    
    System.out.println("Transferir");
    System.out.println(chip1.transferir(200,chip2));
    System.out.println(chip2.transferir(10000,chip1));
    System.out.println();
    
    System.out.println("Salvame");
    System.out.println(chip3.activar("Isaac",25.0));
    System.out.println(chip3.llamar(10,chip2));
    System.out.println(chip3.salvame());
    System.out.println(chip1.salvame());
    System.out.println();
    
    System.out.println("Historial");
    System.out.println(chip2.consultarHistorial());
    System.out.println();
    
    System.out.println("Historial Llamadas");
    System.out.println(chip2.consultarLlamadas());
    System.out.println("Historial Mensajes");
    System.out.println(chip1.consultarMensajes());
    
    System.out.println("Consultar saldo");
    System.out.println(chip1.consultarSaldo());
    System.out.println();
    
    System.out.println("Actividades mes actual");
    System.out.println(chip1.verActividadesMesActual());
    
    System.out.println("Actividades numero de mes");
    System.out.println(chip2.verActividadesNumMes(12));
    
    System.out.println("Mensajes recibidos");
    System.out.println(chip1.verMensajesRecibidos());
    
    System.out.println("Actividad salida a un numero especifico");
    System.out.println(chip2.actividadDeSalida("5061"));
    
    System.out.println("Cantidad chips");
    System.out.println(ChipPrepago.verCantidadChipsPregago());
    
    System.out.println("toString");
    System.out.println(chip2.toString());
  }
}
