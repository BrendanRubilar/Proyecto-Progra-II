package PanelCentral;

public class InvalidPositionException extends Exception{

    public InvalidPositionException(String mensajeDeError){
        super(mensajeDeError);
    }
}
