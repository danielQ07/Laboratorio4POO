package logicadenegocios;

import java.util.Date;
import logicadenegocios.Llamada;
import logicadenegocios.Mensaje;
import logicadenegocios.Navegacion;
import java.util.Calendar;

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
  private int indiceNavegaciones = 0;
  private int cantidadSalvame = 0;
  private Llamada[] historialLlamadas; 
  private Mensaje[] historialMensajes; 
  private Navegacion[] historialNavegaciones; 
  
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
  
  
  public String activar(String pDueno, double pMegaBytes){
    if(this.isActivado==true){
      return "El chip ya ha sido activado";
    }else{
      this.dueno = pDueno;
      this.megaBytes = pMegaBytes;
      this.saldo = 1000;
      this.historialLlamadas = new Llamada[10];
      this.historialMensajes = new Mensaje[10];
      this.historialNavegaciones = new Navegacion[10];
      this.isActivado = true;
      return "El chip ha sido activado";
    }
  }
  
  
  public String llamar(int pMinutos, ChipPrepago pChipDestinatario){
    if(this.isActivado == false){
      return "Chip no ha sido activado";
    }else if(pChipDestinatario.isActivado == false){
      return "Chip de destino no ha sido activado";
    }else{
      int precioTotal=0;
      int precioLlamada = getPrecioLlamada(pChipDestinatario.getCodigoPais());
      precioTotal = pMinutos * precioLlamada;
      if(validarSaldo(precioTotal) == false){
        return "No cuenta con saldo suficiente para realizar una llamada, con esta duración";
      }else{
        this.saldo -= precioTotal;
        Llamada nuevaLlamadaEmisor = new Llamada(pMinutos,pChipDestinatario.getNumeroChip(),"Enviada");
        Llamada nuevaLlamadReceptor = new Llamada(pMinutos,pChipDestinatario.getNumeroChip(),"Recibida");
        //Llamada en emisor
        this.validarIndiceLlamadas();
        this.historialLlamadas[this.indiceLlamadas] = nuevaLlamadaEmisor;
        this.indiceLlamadas+=1;
        //llamada en receptor
        pChipDestinatario.validarIndiceLlamadas();
        pChipDestinatario.historialLlamadas[pChipDestinatario.indiceLlamadas] = nuevaLlamadReceptor;
        pChipDestinatario.indiceLlamadas+=1;
        return "La llamada se ha realizadon con éxito su saldo restante es " 
            + String.valueOf(this.saldo);
      }
    } 
  }
  
  
  public String enviarSms(String pMensaje, ChipPrepago pChipDestinatario){
    if(this.isActivado == false){
      return "Chip no ha sido activado";
    }else if(pChipDestinatario.isActivado == false){
      return "Chip de destino no ha sido activado";
    }
    if(validarTamanoMensaje(pMensaje)){
      int costoMensaje = getPrecioMensaje(pChipDestinatario.getCodigoPais());
      if(validarSaldo(costoMensaje) ==  false){
        return "No posee suficiente saldo para enviar el mensaje";
      }else{
        Mensaje nuevoMensajeEmisor = new Mensaje(pMensaje, pChipDestinatario.getNumeroChip(), "Enviado");
        Mensaje nuevoMensajeReceptor = new Mensaje(pMensaje, pChipDestinatario.getNumeroChip(), "Recibido");
        this.saldo -= costoMensaje;
        this.validarIndiceMensajes();
        //Agrega el mensaje al historial del emisor
        this.historialMensajes[this.indiceMensajes] = nuevoMensajeEmisor;
        this.indiceMensajes += 1;
        //Agrega el mensaje al historial del receptor
        pChipDestinatario.validarIndiceMensajes();
        pChipDestinatario.historialMensajes[pChipDestinatario.indiceMensajes] = nuevoMensajeReceptor;
        pChipDestinatario.indiceMensajes += 1;
        return "Se ha enviado el mensaje con éxito";
      }
    }else{
      return "El tamaño del mensaje debe ser menor a 128 caracteres";
    }
  }
  
  
  public String navegar(String pUrl){
    String infoTxt="";
    Navegacion nuevaNavegacion = new Navegacion(pUrl);
    if(validarKiloBytes(nuevaNavegacion.getKiloBytes())){
      double conversion = nuevaNavegacion.getKiloBytes() / 1000.0;
      this.megaBytes -= conversion;
      validarIndiceNavegaciones();
      this.historialNavegaciones[this.indiceNavegaciones] = nuevaNavegacion;
      this.indiceNavegaciones += 1;
      return "Se ha realizado la búsqueda con éxito, sus megabytes disponibles son "+ this.getMegaBytesTxt();
    }else{
      return "No posee megabytes suficientes para navegar";
    }    
  }
  
  
  public String transferir(int pMonto, ChipPrepago pChipDestinatario){
    if(this.isActivado == false){
      return "Chip no ha sido activado";
    }else if(pChipDestinatario.isActivado == false){
      return "Chip de destino no ha sido activado";
    }else if(validarSaldo(pMonto+5) == false){
      return "No posee con el saldo suficiente";
    }else{
      this.saldo -= (pMonto+5);
      pChipDestinatario.setSaldo(Integer.parseInt(pChipDestinatario.consultarSaldo())+pMonto);
      return "Se ha transferido " + String.valueOf(pMonto) +", su salgo actual es "
          + this.consultarSaldo();
    }
  }
  
  
  public String recargar(int pMonto){
    if(this.isActivado == false){
      return "Chip no ha sido activado";
    }else if(pMonto < 0){
      return "El monto no puede ser negativo";
    }else if(pMonto == 0){
      return "El monto no puede ser 0";
    }else{
      this.saldo += pMonto;
      return "El monto actual del chip es de " + String.valueOf(this.saldo);
    }
  }
  
  
  public String salvame(){
    if(this.isActivado == false){
      return "Chip no ha sido activado";
    }
    if(this.cantidadSalvame < 3){
      if(this.saldo > 0){
        return "La cuenta aún posee un saldo de " + String.valueOf(this.saldo);
      }else{
        this.saldo += 100;
        this.cantidadSalvame += 1;
        return "La cuenta ha sido recargada con 100 colones con éxito";
      }
    }else{
      return "La cuenta ya realizó las peticiones máximas";
    }
  }
  
  
  public String consultarHistorial(){
    String historialTxt = "";
    historialTxt += "LLAMADAS \n" + consultarLlamadas();
    historialTxt += "\nMENSAJES \n" + consultarMensajes();
    historialTxt += "\nNAVEGACIONES \n" + consultarNavegaciones();
    return historialTxt;
  }
  
  
  public String consultarLlamadas(){
    String llamadasTxt = "";
    if(this.historialLlamadas[0] != null){
      int contadorLlamadas = 0;
      while(contadorLlamadas < 10 && this.historialLlamadas[contadorLlamadas] != null){
        llamadasTxt += "Número de llamada:\t" + String.valueOf(contadorLlamadas+1) + "\n";
        llamadasTxt += "Tipo de llamada:\t" + this.historialLlamadas[contadorLlamadas].getTipoLlamada() + "\n";
        llamadasTxt += "Duración de llamada:\t" + this.historialLlamadas[contadorLlamadas].getMinutos() + "\n";
        llamadasTxt += "Destinatario:\t" + this.historialLlamadas[contadorLlamadas].getDestinatario() + "\n";
        llamadasTxt += "Fecha y Hora:\t" + this.historialLlamadas[contadorLlamadas].getFechaHora() + "\n\n";
        contadorLlamadas += 1;
      }
      return llamadasTxt;
    }else{
      return "No posee llamadas en el historial";
    }
  }
  
  
  public String consultarMensajes(){
    String mensajesTxt = "";
    if(this.historialMensajes[0] != null){
      int contadorMensajes = 0;
      while(contadorMensajes < 10 && this.historialMensajes[contadorMensajes] != null){
        mensajesTxt += "Número de mensaje:\t" + String.valueOf(contadorMensajes+1) + "\n";
        mensajesTxt += "Tipo de mensaje:\t" + this.historialMensajes[contadorMensajes].getTipoMensaje() + "\n";
        mensajesTxt += "Mensaje:\t" + this.historialMensajes[contadorMensajes].getMensaje() + "\n";
        mensajesTxt += "Destinatario:\t" + this.historialMensajes[contadorMensajes].getDestinatario() + "\n";
        mensajesTxt += "Fecha y Hora:\t" + this.historialMensajes[contadorMensajes].getFechaHora() + "\n\n";
        contadorMensajes += 1;
      }
      return mensajesTxt;
    }else{
      return "No posee mensajes en el historial";
    }
  }
  
  
  public String consultarNavegaciones(){
    String navegacionesTxt = "";
    if(this.historialNavegaciones[0] != null){
      int contadorNavegaciones = 0;
      while(contadorNavegaciones < 10 && this.historialNavegaciones[contadorNavegaciones] != null){
        navegacionesTxt += "Número de navegación:\t" + String.valueOf(contadorNavegaciones+1) + "\n";
        navegacionesTxt += "KiloBytes:\t" + this.historialNavegaciones[contadorNavegaciones].getKiloBytes() + "\n";
        navegacionesTxt += "Fecha y Hora:\t" + this.historialMensajes[contadorNavegaciones].getFechaHora() + "\n\n";
        contadorNavegaciones += 1;
      }
      return navegacionesTxt;
    }else{
      return "No posee mensajes en el historial";
    }
  }
  
  
  public String consultarSaldo(){
    return String.valueOf(this.saldo);
  } 
  
  
  public String verActividadesMesActual(){
    int mesActual = Calendar.getInstance().get(Calendar.MONTH)+1;
    return verLlamadasMes(mesActual)+verMensajesMes(mesActual);
  }
  
  
  public String verActividadesNumMes(int pMes){
    if(pMes > 0 && pMes < 13){
      return verLlamadasMes(pMes)+verMensajesMes(pMes);
    }else{
      return "Mes no válido";
    }
  }
  
  
  public String verMensajesRecibidos(){
    String mensajesTxt = "";
    if(this.historialMensajes[0] != null){
      int contadorMensajes = 0;
      while(contadorMensajes < 10 && this.historialMensajes[contadorMensajes] != null){
        if(this.historialMensajes[contadorMensajes].getTipoMensaje() == "Recibido"){
          mensajesTxt += "Mensaje:\t" + this.historialMensajes[contadorMensajes].getMensaje() + "\n";
          mensajesTxt += "Fecha y Hora:\t" + this.historialMensajes[contadorMensajes].getFechaHora() + "\n\n";
        } 
        contadorMensajes += 1;
      }
      if(mensajesTxt.equals("")){
        return "No cuenta con mensajes recibidos";
      }else{
        return "Los mensajes son los siguientes: \n" + mensajesTxt;
      }
    }else{
      return "No posee mensajes en el historial";
    }
  }
  
  
  public static int verCantidadChipsPregago(){
    return cantidadChipPrepago;
  }
  
  
  public String toString(){
    String texto = "\n";
    texto+= "Número:\t"+this.numeroChip+"\n";
    texto+= "Dueño:\t"+this.dueno+"\n";
    texto+= "Saldo disponible:\t"+this.saldo+"\n";
    texto+= "MegaBytes disponible:\t"+this.megaBytes+"\n";
    texto+= "Está activado:\t"+this.isActivado+"\n";
    texto+= consultarHistorial();
    return texto;
  }


  public String getNumeroChip(){
    return this.numeroChip;
  }
  
  
  public String getCodigoPais(){
    return this.codigoPais;
  }
  
  private String getMegaBytesTxt(){
    return String.valueOf(this.megaBytes);
  }
  

  private void setSaldo(int pSaldo){
    this.saldo = pSaldo;
  }
  
  
    private boolean validarSaldo(int pSaldo){
    if(this.saldo-pSaldo < 0){
     return false;
    }else{
      return true;
    }
  }
  
  
  private boolean validarTamanoMensaje(String pMensaje){
    if(pMensaje.length() > 128){
      return false;
    }else{
      return true;
    }
  }
  
  
  private boolean validarKiloBytes(double pKiloBytes){
    double conversionMegaBytes = pKiloBytes/1000.0;
    if(this.megaBytes-conversionMegaBytes < 0){
     return false;
    }else{
      return true;
    }
  }  
  
  
  private void validarIndiceMensajes(){
    if(this.indiceMensajes+1 == 10){
      this.indiceMensajes = 0;
    }
  }
  
  
  private void validarIndiceLlamadas(){
    if(this.indiceLlamadas+1 == 10){
      this.indiceLlamadas = 0;
    }
  }
  
  
  private void validarIndiceNavegaciones(){
    if(this.indiceNavegaciones+1 == 10){
      this.indiceNavegaciones = 0;
    }
  }
  
  
  private String verLlamadasMes(int pMes){
    String infoTxt = "Llamadas:\n";
    int contadorLlamadas = 0;
    while(contadorLlamadas < 10 && this.historialLlamadas[contadorLlamadas] != null){
      if(this.historialLlamadas[contadorLlamadas].getNumMes() == pMes){
          infoTxt += "Número de llamada " + String.valueOf(contadorLlamadas+1) + "\n";
          infoTxt += "Tipo de llamada " + this.historialLlamadas[contadorLlamadas].getTipoLlamada() + "\n";
          infoTxt += "Duración de llamada " + this.historialLlamadas[contadorLlamadas].getMinutos() + "\n";
          infoTxt += "Destinatario " + this.historialLlamadas[contadorLlamadas].getDestinatario() + "\n";
          infoTxt += "Fecha y Hora " + this.historialLlamadas[contadorLlamadas].getFechaHora() + "\n\n";
        }
      contadorLlamadas += 1;
    }
    return infoTxt;
  }
  
  
  private String verMensajesMes(int pMes){
    String infoTxt = "Mensajes:\n";
    int contadorMensajes = 0;
    while(contadorMensajes < 10 && this.historialMensajes[contadorMensajes] != null){
      if(this.historialMensajes[contadorMensajes].getNumMes() == pMes){
        infoTxt += "Número de mensaje " + String.valueOf(contadorMensajes+1) + "\n";
        infoTxt += "Tipo de mensaje " + this.historialMensajes[contadorMensajes].getTipoMensaje() + "\n";
        infoTxt += "Mensaje" + this.historialMensajes[contadorMensajes].getMensaje() + "\n";
        infoTxt += "Destinatario " + this.historialMensajes[contadorMensajes].getDestinatario() + "\n";
        infoTxt += "Fecha y Hora " + this.historialMensajes[contadorMensajes].getFechaHora() + "\n\n";
      }
      contadorMensajes += 1;
    }
    return infoTxt;
  }
  

  private int getPrecioMensaje(String pCodigoPais){
    switch(pCodigoPais){
        case "506":
          return 20;
        case "1":
          return 30;
        case "508":
          return 40;
        case "610":
          return 50;
        case "201":
          return 60;
        default:
          return 0;
      }
  }
  
  
  private int getPrecioLlamada(String pCodigoPais){
    switch(pCodigoPais){
        case "506":
          return 30;
        case "1":
          return 70;
        case "508":
          return 80;
        case "610":
          return 90;
        case "201":
          return 100;
        default:
          return 0;
      }
  }
  
}