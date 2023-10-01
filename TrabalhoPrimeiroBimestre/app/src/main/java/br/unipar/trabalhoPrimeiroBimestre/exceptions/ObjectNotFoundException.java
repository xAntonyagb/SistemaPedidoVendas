package br.unipar.trabalhoPrimeiroBimestre.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException() {
        super("Essa referencia não existe!");
    }
}
