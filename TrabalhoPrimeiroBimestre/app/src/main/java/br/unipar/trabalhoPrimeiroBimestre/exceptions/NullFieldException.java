package br.unipar.trabalhoPrimeiroBimestre.exceptions;

public class NullFieldException extends RuntimeException {
    public NullFieldException() {
        super("Todos os campos devem ser preenchidos!");
    }
}
