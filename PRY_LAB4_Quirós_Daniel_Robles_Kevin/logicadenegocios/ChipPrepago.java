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
  private String historialLlamadas;
  private String historialMensajes;
  private String historialNavegaciones;
  
  public ChipPrepago(){
    
  }
}
