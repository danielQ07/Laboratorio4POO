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
  private String tipoMensaje;
  
  public Mensaje(String pMensaje, String pNumeroDestinatario, String pTipoMensaje){
    mensaje = pMensaje;
    numeroDestinatario = pNumeroDestinatario;
    setFechaHora();
    tipoMensaje = pTipoMensaje;
  }
  
  
  public String getMensaje(){
    return mensaje;
  }
  
  
  public String getDestinatario(){
    return numeroDestinatario;
  }
  
  
  public String getFechaHora(){
    SimpleDateFormat mascara = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
    return mascara.format(fechaHora);
  }
  
  
  private void setFechaHora(){
    Calendar calendario;
    calendario = Calendar.getInstance();
    fechaHora = calendario.getTime();
  }
  
  
  public String getTipoMensaje(){
    return tipoMensaje;
  }
  
  public int getNumMes(){
    SimpleDateFormat mascara = new SimpleDateFormat("MM");
    return Integer.parseInt(mascara.format(fechaHora));
  }
}

