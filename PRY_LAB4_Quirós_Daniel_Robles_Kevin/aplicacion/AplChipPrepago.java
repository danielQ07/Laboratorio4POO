/**
 * Write a description of class AplChipPrepago here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
package aplicacion;
import logicadenegocios.ChipPrepago;

public class AplChipPrepago{
  public static void main(String[] args){

    ChipPrepago chip1 = new ChipPrepago();
    ChipPrepago chip2 = new ChipPrepago("1");
    
    
    System.out.println(chip1.activar("Kevin",100.0));
    System.out.println(chip2.activar("Daniel",150.0));
    
    System.out.println(chip1.recargar(1300));
    System.out.println(chip2.recargar(1600));
    
    System.out.println(chip1.consultarSaldo());
    System.out.println(chip2.consultarSaldo());
    
    System.out.println(chip1.enviarSms("Hola Daniel",chip2));
    System.out.println(chip1.consultarSaldo());
    System.out.println(chip2.consultarHistorial());
    System.out.println(1);
    System.out.println(chip2.consultarMensajes());
    System.out.println(2);
    System.out.println(chip1.verActividadesMesActual());
    System.out.println(3);
    
  }
}
