package br.unipar.trabalhoPrimeiroBimestre.model;

public enum CondicaoPagamentoEnum {
    VISTA("A vista", 0.95),
    PARCELADO("Parcelado", 1.05);

    String descricao;
    double valorPorcentual;
    CondicaoPagamentoEnum(String descricao, double valorPorcentual) {
        this.descricao = descricao;
        this.valorPorcentual = valorPorcentual;
    }

    public static double calcularTotal (double valor, CondicaoPagamentoEnum pagamento) {

        if(pagamento == CondicaoPagamentoEnum.VISTA)
            return valor * CondicaoPagamentoEnum.VISTA.valorPorcentual;
        else
            return valor * CondicaoPagamentoEnum.PARCELADO.valorPorcentual;
    }

}
