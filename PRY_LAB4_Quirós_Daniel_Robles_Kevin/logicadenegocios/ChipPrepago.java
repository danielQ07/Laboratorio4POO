package logicadenegocios;

import logicadenegocios.Llamada;
import logicadenegocios.Mensaje;
import logicadenegocios.Navegacion;

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
  private Llamada[] historialLlamadas; //cambiar por objeto
  private Mensaje[] historialMensajes; //cambiar por objeto
  private Navegacion[] historialNavegaciones; //cambiar por objeto
  
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
      this.historialLlamadas = new Llamada[10];
      this.historialMensajes = new Mensaje[10];
      this.historialNavegaciones = new Navegacion[10];
      this.isActivado = true;
      return (mensaje = "El chip ha sido activado");
    }
  }
  
  public String consultarSaldo(){
    return String.valueOf(this.saldo);
  } 
  
  private void setSaldo(int pSaldo){
    this.saldo = pSaldo;
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
      if(this.saldo-precioTotal < 0){
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
        return(infoMensaje = "La llamada se h realizadon con éxito su saldo restante es" 
            + String.valueOf(this.saldo));
      }
    }
  }
  
  public String consultarLlamadas(){
    String llamadasTxt = "";
    if(this.historialLlamadas.length > 0){
      byte contadorLlamadas = 0;
      while(contadorLlamadas > 10){
        //llamar métodos para sacar info de cada registro
        llamadasTxt += "Número de llamada " + String.valueOf(contadorLlamadas+1) + "\n";
        llamadasTxt += "Tipo de llamada " + this.historialLlamadas[contadorLlamadas].getTipoLlamada() + "\n";
        llamadasTxt += "Duración de llamada " + this.historialLlamadas[contadorLlamadas].getMinutos() + "\n";
        llamadasTxt += "Destinatario " + this.historialLlamadas[contadorLlamadas].getDestinatario() + "\n";
        llamadasTxt += "Fecha y Hora " + this.historialLlamadas[contadorLlamadas].getFechaHora() + "\n\n";
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
        mensajesTxt += "Tipo de mensaje " + this.historialMensajes[contadorMensajes].getTipoMensaje() + "\n";
        mensajesTxt += "Mensaje" + this.historialMensajes[contadorMensajes].getMensaje() + "\n";
        mensajesTxt += "Destinatario " + this.historialMensajes[contadorMensajes].getDestinatario() + "\n";
        mensajesTxt += "Fecha y Hora " + this.historialMensajes[contadorMensajes].getFechaHora() + "\n\n";
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
        navegacionesTxt += "KiloBytes " + this.historialNavegaciones[contadorNavegaciones].getKiloBytes() + "\n";
        navegacionesTxt += "Fecha y Hora " + this.historialMensajes[contadorNavegaciones].getFechaHora() + "\n\n";
        contadorNavegaciones += 1;
      }
      return navegacionesTxt;
    }else{
      return (navegacionesTxt += "No posee mensajes en el historial");
    }
  }
  
  public String tranferir(int pMonto, ChipPrepago pChipDestinatario){
    String infoTxt="";
    if(this.saldo-(pMonto+5) < 0){
      return (infoTxt = "No posee con el saldo suficiente");
    }else{
      this.saldo -= (pMonto+5);
      pChipDestinatario.setSaldo(Integer.parseInt(pChipDestinatario.consultarSaldo())+pMonto);
      return (infoTxt = "Se ha transferido" + String.valueOf(pMonto) +", su salgo actual es "
          + this.consultarSaldo());
    }
  }
  
  public String enviarSms(String pMensaje, ChipPrepago pChipDestinatario){
    String infoTxt="";
    if(validarTamanoMensaje(pMensaje)){
      int costoMensaje=0;
      switch(pChipDestinatario.getCodigoPais()){
        case "506":
          costoMensaje = 20;
          break;
        case "1":
          costoMensaje = 30;
          break;
        case "508":
          costoMensaje = 40;
          break;
        case "610":
          costoMensaje = 50;
          break;
        case "201":
          costoMensaje = 60;
          break;
        default:
          return (infoTxt = "País no válido");
      }
      if(this.saldo-costoMensaje < 0){
        return ( infoTxt= "No posee suficiente saldo para enviar el mensaje");
      }else{
        Mensaje nuevoMensajeEmisor = new Mensaje(pMensaje, pChipDestinatario.getNumeroChip(), "Enviado");
        Mensaje nuevoMensajeReceptor = new Mensaje(pMensaje, pChipDestinatario.getNumeroChip(), "Recibido");
        this.saldo -= costoMensaje;
        //Agrega el mensaje al historial del emisor
        if(this.indiceMensajes+1 == 10){
          this.indiceMensajes = 0;
          this.historialMensajes[this.indiceMensajes] = nuevoMensajeEmisor;
          this.indiceMensajes += 1;
        }else{
          this.historialMensajes[this.indiceMensajes] = nuevoMensajeEmisor;
          this.indiceMensajes += 1;
        }
        //Agrega el mensaje al historial del receptor
        if(this.indiceMensajes+1 == 10){
          this.indiceMensajes = 0;
          this.historialMensajes[this.indiceMensajes] = nuevoMensajeReceptor;
          this.indiceMensajes += 1;
        }else{
          this.historialMensajes[this.indiceMensajes] = nuevoMensajeReceptor;
          this.indiceMensajes += 1;
        }
        return (infoTxt = "Se ha enviado el mensaje con éxito");
      }
    }else{
      return (infoTxt = "El tamaño del mensaje debe ser menor a 128 caracteres");
    }
  }
  
  private boolean validarTamanoMensaje(String pMensaje){
    if(pMensaje.length() > 128){
      return false;
    }else{
      return true;
    }
  }
}
