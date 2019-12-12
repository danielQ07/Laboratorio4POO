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
  private String historialLlamadas; //cambiar por objeto
  private String historialMensajes; //cambiar por objeto
  private String historialNavegaciones; //cambiar por objeto
  
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
    String mensaje="";
    if(this.isActivado==true){
      return (mensaje += "El chip ya ha sido activado");
    }else{
      this.dueno = pDueno;
      this.megaBytes = pMegaBytes;
      this.saldo = 1000;
      this.historialLlamadas = "nueva llamada";
      this.historialMensajes = "nuevo mensaje";
      this.historialNavegaciones = "nueva navegacion";
      this.isActivado = true;
      return (mensaje = "El chip ha sido activado");
    }
  }
  
  public String consultarSaldo(){
    return String.valueOf(this.saldo);
  }
  
  public String consultarHistorial(){
   return "falta meter el tipo de dato y completar método";
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
  
  public String llamar(int pMinutos){
    return "trbajando en esta";
  }
}
