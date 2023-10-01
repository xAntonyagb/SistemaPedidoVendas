package br.unipar.trabalhoPrimeiroBimestre.model;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Pedido {
    private Cliente cliente;
    private ArrayList<ItemPedido> listaItens;

    private CondicaoPagamentoEnum condicaoPagamento;
    private double valorTotal;
    private double valorTotalPagamento;
    private int qtdparcelas;
    private double valorParcela;
    private int codigo;

    public Pedido() {
        this.listaItens = new ArrayList<>();
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public CondicaoPagamentoEnum getCondicaoPagamento() {
        return condicaoPagamento;
    }

    public void setCondicaoPagamento(CondicaoPagamentoEnum condicaoPagamento) {
        this.condicaoPagamento = condicaoPagamento;
    }

    public void addListaItens(ItemPedido item) {
        this.listaItens.add(item);
    }

    public ArrayList<ItemPedido> getListaItens() {
        return listaItens;
    }

    public void calcularValorTotal() {
        double total = 0;

        for (ItemPedido item: this.listaItens) {
            total += item.getPrecoTotal();
        }
        this.valorTotal = total;
    }

    public void calcularValorTotalPagamento() {
        this.valorTotalPagamento = Math.round(CondicaoPagamentoEnum.calcularTotal(valorTotal, condicaoPagamento));
    }

    public double getValorTotal() {
        calcularValorTotal();
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorTotalPagamento() {
        calcularValorTotalPagamento();
        return valorTotalPagamento;
    }

    public void setValorTotalPagamento(double valorTotalPagamento) {
        this.valorTotalPagamento = valorTotalPagamento;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getQtdparcelas() {
        return qtdparcelas;
    }

    public void setQtdparcelas(int qtdparcelas) {
        this.qtdparcelas = qtdparcelas;
    }

    public double getValorParcela() {
        calcularValorParcela();
        return valorParcela;
    }

    private void calcularValorParcela() {
        this.valorParcela = valorTotalPagamento / qtdparcelas;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {

        if (condicaoPagamento == CondicaoPagamentoEnum.VISTA)
            return "Cógido: " + codigo + "\n" +
                    "Cliente: " + cliente + "\n" +
                    "Itens: " + "\n" + listaItens + "\n\n" +
                    "Total: " + getValorTotal() + "\n" +
                    "Forma de pagamento: " + condicaoPagamento.descricao + "\n\n" +
                    "Total Final: " + getValorTotalPagamento();

        else
            return "Cógido: " + codigo + "\n" +
                    "Cliente: " + cliente + "\n" +
                    "Itens: " + "\n" + listaItens + "\n\n" +
                    "Total: " + getValorTotal() + "\n" +
                    "Forma de pagamento: " + condicaoPagamento.descricao + "\n\n" +
                    "Quantidade de parcelas: " + qtdparcelas + "\n" +
                    "Valor individual de cada parcela: " + getValorParcela() + "\n" +
                    "Total Final: " + getValorTotalPagamento() + "\n";
    }
}
