package br.unipar.trabalhoPrimeiroBimestre.exceptions;

public class ParcelaInválidaException extends RuntimeException{
    public ParcelaInválidaException() {
        super("A quantia de parcelas informadas é inválida! \n(Min 2 - Max 12)");
    }
}
