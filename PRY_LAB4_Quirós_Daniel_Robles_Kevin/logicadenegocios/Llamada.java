/**
 * Write a description of class Llamada here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
package logicadenegocios;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Llamada{

  private int minutos;
  private String numeroDestinatario;
  private Date fechaHora;
  
  public Llamada(int pMinutos, String pNumeroDestinatario){
    minutos = pMinutos;
    numeroDestinatario = pNumeroDestinatario;
    setFechaHora();
  }
  
  
  public String getFechaHora(){
    SimpleDateFormat mascara = new SimpleDateFormat("dd/MM/yy");
    return mascara.format(fechaHora);
  }
  
  
  private void setFechaHora(){
    Calendar calendario;
    calendario = Calendar.getInstance();
    fechaHora = calendario.getTime();
  }
  
  
  public String getDestinatario(){
    return numeroDestinatario;
  }
  
  
  public int getMinutos(){
    return minutos;
  }
}
