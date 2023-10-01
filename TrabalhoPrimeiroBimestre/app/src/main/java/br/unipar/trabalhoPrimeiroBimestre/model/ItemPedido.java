package br.unipar.trabalhoPrimeiroBimestre.model;

public class ItemPedido {

    private ItemVenda itemVenda;
    private int quantidade;
    private double precoTotal;

    public ItemPedido() {
    }

    public ItemPedido(ItemVenda itemVenda, int quantidade) {
        this.itemVenda = itemVenda;
        this.quantidade = quantidade;
        calcularPrecoTotal();
    }

    private void calcularPrecoTotal(){
        this.precoTotal = itemVenda.getValorUnitario() * quantidade;
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    @Override
    public String toString() {
        return "Item: ("+ itemVenda.getCodigo() +") " + " Desc: " + itemVenda.getDescricao() + "  -  R$ " + itemVenda.getValorUnitario() + "    |    Qnt: " + quantidade + "  -  Total: " + precoTotal + "\n\n";
    }
}
