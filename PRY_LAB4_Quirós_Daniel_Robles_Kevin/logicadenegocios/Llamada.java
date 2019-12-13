/**
 * Clase para crear objetos tipo Llamada
 *
 * @author Kevin Robles, Daniel Quiros
 * @version 1.0
 */
package logicadenegocios;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Llamada{

  private int minutos;
  private String numeroDestinatario;
  private Date fechaHora;
  private String tipoLlamada;
  
  /**
   *  Metodo constructor de objetos tipo Llamada
   * 
   * @param pMinutos numero de minutos en llamada,pNumeroDestinario numero de chip de destinario,
   *     pTipoLlamada si es enviada o recibida
   */
  public Llamada(int pMinutos, String pNumeroDestinatario, String pTipoLlamada){
    minutos = pMinutos;
    numeroDestinatario = pNumeroDestinatario;
    setFechaHora();
    tipoLlamada = pTipoLlamada;
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
  
  
  public String getDestinatario(){
    return numeroDestinatario;
  }
  
  
  public int getMinutos(){
    return minutos;
  }
  
  
  public String getTipoLlamada(){
    return tipoLlamada;
  }
  
  
  public int getNumMes(){
    SimpleDateFormat mascara = new SimpleDateFormat("MM");
    return Integer.parseInt(mascara.format(fechaHora));
  }
}
