package br.unipar.trabalhoPrimeiroBimestre.exceptions;

public class InvalidComponentsException extends RuntimeException {
    public InvalidComponentsException(String component) {
        super("O componente: " + component + " está incompleto para prosseguir com as etapas!");
    }
}
