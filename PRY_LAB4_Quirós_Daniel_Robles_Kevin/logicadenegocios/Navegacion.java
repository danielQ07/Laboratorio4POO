/**
 * Clase para crear objetos tipo Navegacion
 *
 * @author Kevin Robles, Daniel Quiros
 * @version 1.0
 */
package logicadenegocios;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.lang.Math;

public class Navegacion{

  private String url;
  private double kiloBytes;
  private Date fechaHora;
  
  /**
   *  Metodo constructor de objetos tipo Navegacion
   * 
   * @param pUrl direccion web de la pagina navegada
   * 
   */
  public Navegacion(String pUrl){
    url = pUrl;
    double pKiloBytes = (1.0 + Math.random()*(8.0 - 1.0)); 
    kiloBytes = pKiloBytes;//Numero kylobites es numero aleatorio de 1 a 8 
    setFechaHora();
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
  
  
  public String getUrl(){
    return url;
  }
  
  
  public double getKiloBytes(){
    return kiloBytes;
  }
  
  
  public int getNumMes(){
    SimpleDateFormat mascara = new SimpleDateFormat("MM");
    return Integer.parseInt(mascara.format(fechaHora));
  }
}

