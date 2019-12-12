package logicadenegocios;

public class ChipPrepago{
  private String codigoPais;
  private String dueno;
  private int saldo;
  private double megaBytes;
  private boolean isActivado;
  private String numeroChip;
  private static int cantidadChipPrepago = 0;
  private int indiceLlamadas = 0;
  private int indiceMensajes = 0;
  private int cantidadSalvame = 0;
  private String[] historialLlamadas; //cambiar por objeto
  private String[] historialMensajes; //cambiar por objeto
  private String[] historialNavegaciones; //cambiar por objeto
  
  public ChipPrepago(){
   this.cantidadChipPrepago += 1;
   this.codigoPais = "506";
   this.numeroChip = this.codigoPais + String.valueOf(this.cantidadChipPrepago);
 }
  public ChipPrepago(String pCodigoPais){
    this.cantidadChipPrepago += 1;
    this.codigoPais = pCodigoPais;
    this.numeroChip = this.codigoPais + String.valueOf(this.cantidadChipPrepago);
  }
  
  public String getNumeroChip(){
    return this.numeroChip;
  }
  
    public String getCodigoPais(){
    return this.codigoPais;
  }
  
  public String activar(String pDueno, double pMegaBytes){
    String mensaje="";
    if(this.isActivado==true){
      return (mensaje += "El chip ya ha sido activado");
    }else{
      this.dueno = pDueno;
      this.megaBytes = pMegaBytes;
      this.saldo = 1000;
      this.historialLlamadas = new String[10];
      this.historialMensajes = new String[10];
      this.historialNavegaciones = new String[10];
      this.isActivado = true;
      return (mensaje = "El chip ha sido activado");
    }
  }
  
  public String consultarSaldo(){
    return String.valueOf(this.saldo);
  }
  
  public String consultarHistorial(){
    String historialTxt = "";
    historialTxt += "LLAMADAS \n" + consultarLlamadas();
    historialTxt += "MENSAJES \n" + consultarMensajes();
    historialTxt += "NAVEGACIONES \n" + consultarNavegaciones();
    return historialTxt;
  }
  
  public String recargar(int pMonto){
    String mensaje="";
    if(pMonto > 0){
      return(mensaje += "El monto no puede ser negativo");
    }else if(pMonto == 0){
      return(mensaje += "El monto no puede ser 0");
    }else{
      this.saldo += pMonto;
      return ("El monto actual del chip es de " + String.valueOf(this.saldo));
    }
  }
  
  public String salvame(){
    String mensaje = "";
    if(this.cantidadSalvame < 3){
      if(this.saldo > 0){
        return(mensaje = ("La cuenta aún posee un saldo de " + String.valueOf(this.saldo)));
      }else{
        this.saldo += 100;
        this.cantidadSalvame += 1;
        return("La cuenta ha sido recargada con 100 colones con éxito");
      }
    }else{
      return(mensaje = "La cuenta ya realizó las peticiones máximas");
    }
  }
  
  //clase incompleta, falta meter llamada al historial
  public String llamar(int pMinutos, ChipPrepago pChipDestinatario){
    int precioTotal=0;
    String infoMensaje="";
    if(pChipDestinatario.getCodigoPais() == "506"){
      precioTotal = pMinutos * 30;
      if(this.saldo-precioTotal > 0){
        return(infoMensaje += "No cuenta con saldo suficiente para realizar una llamda nacional, con esta" 
            + "duración");    
      }else{
        this.saldo -= pMinutos;
        return(infoMensaje = "La llamada se h realizadon con éxito");
      }
    }else{
      switch(pChipDestinatario.getCodigoPais()){
        case "1":
          precioTotal = pMinutos * 70;
          break;
        case "508":
          precioTotal = pMinutos * 80;
          break;
        case "610":
          precioTotal = pMinutos * 90;
          break;
        case "201":
          precioTotal = pMinutos * 100;
          break;
        default:
          return (infoMensaje = "País no válido");
      }
      if(this.saldo-precioTotal > 0){
        return(infoMensaje += "No cuenta con saldo suficiente para realizar una llamada internacional, con esta" 
            + "duración");    
      }else{
        this.saldo -= pMinutos;
        return(infoMensaje = "La llamada se h realizadon con éxito su saldo restante es" + String.valueOf(this.saldo));
      }
    }
  }
  
  public String consultarLlamadas(){
    String llamadasTxt = "";
    if(this.historialLlamadas.length > 0){
      byte contadorLlamadas = 0;
      while(contadorLlamadas > 10){
        //llamar métodos para sacar info
        llamadasTxt += "Número de llamada " + String.valueOf(contadorLlamadas+1) + "\n";
        llamadasTxt += "Tipo de llamada " + String.valueOf(this.historialLlamadas) + "\n";
        llamadasTxt += "Duración de llamada " + String.valueOf(this.historialLlamadas) + "\n";
        llamadasTxt += "Destinatario " + String.valueOf(this.historialLlamadas) + "\n";
        llamadasTxt += "Fecha y Hora " + String.valueOf(this.historialLlamadas) + "\n\n";
        contadorLlamadas += 1;
      }
      return llamadasTxt;
    }else{
      return (llamadasTxt += "No posee llamadas en el historial");
    }
  }
  
  public String consultarMensajes(){
    String mensajesTxt = "";
    if(this.historialMensajes.length > 0){
      byte contadorMensajes = 0;
      while(contadorMensajes > 10){
        //llamar métodos para sacar info
        mensajesTxt += "Número de mensaje " + String.valueOf(contadorMensajes+1) + "\n";
        mensajesTxt += "Tipo de mensaje " + String.valueOf(this.historialLlamadas) + "\n";
        mensajesTxt += "Mensaje" + String.valueOf(this.historialLlamadas) + "\n";
        mensajesTxt += "Destinatario " + String.valueOf(this.historialLlamadas) + "\n";
        mensajesTxt += "Fecha y Hora " + String.valueOf(this.historialLlamadas) + "\n\n";
        contadorMensajes += 1;
      }
      return mensajesTxt;
    }else{
      return (mensajesTxt += "No posee mensajes en el historial");
    }
  }
  
  public String consultarNavegaciones(){
    String navegacionesTxt = "";
    if(this.historialNavegaciones.length > 0){
      byte contadorNavegaciones = 0;
      while(contadorNavegaciones > 10){
        //llamar métodos para sacar info
        navegacionesTxt += "Número de navegación " + String.valueOf(contadorNavegaciones+1) + "\n";
        navegacionesTxt += "KiloBytes " + String.valueOf(this.historialLlamadas) + "\n";
        navegacionesTxt += "Fecha y Hora " + String.valueOf(this.historialLlamadas) + "\n\n";
        contadorNavegaciones += 1;
      }
      return navegacionesTxt;
    }else{
      return (navegacionesTxt += "No posee mensajes en el historial");
    }
  }
}
