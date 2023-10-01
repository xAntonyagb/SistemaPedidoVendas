package br.unipar.trabalhoPrimeiroBimestre.model;

public class ItemVenda {
    private int codigo;
    private String descricao;
    private double valorUnitario;

    public ItemVenda() {
    }

    public ItemVenda(String descricao, double valorUnitario) {
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public String toString() {
        return  "Cod " + codigo +  " - " + descricao + " | R$ " + valorUnitario + "\n";
    }
}
