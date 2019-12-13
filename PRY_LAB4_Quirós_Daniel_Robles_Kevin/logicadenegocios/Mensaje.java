/**
 * Clase para crear objetos tipo Mensaje
 *
 * @author Kevin Robles, Daniel Quiros
 * @version 1.0
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
  
  /**
   *  Metodo constructor de objetos tipo Mensaje
   * 
   * @param pMensaje texto del mensaje a enviar, pNumeroDestinario numero de chip del destinario,
   *     pTipoMensaje si es enviado o recibido
   */
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

