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

public class Mensaje{

  private String mensaje;
  private String numeroDestinatario;
  private Date fechaHora;
  
  public Mensaje(String pMensaje, String pNumeroDestinatario){
    mensaje = pMensaje;
    numeroDestinatario = pNumeroDestinatario;
    setFechaHora();
  }

  
  public String getDestinatario(){
    return numeroDestinatario;
  }
  
  
  public String getMensaje(){
    return mensaje;
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
}

