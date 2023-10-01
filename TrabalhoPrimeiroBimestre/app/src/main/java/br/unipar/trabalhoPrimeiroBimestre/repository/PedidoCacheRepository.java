package br.unipar.trabalhoPrimeiroBimestre.repository;

import br.unipar.trabalhoPrimeiroBimestre.model.Pedido;

public class PedidoCacheRepository {

    private static PedidoCacheRepository instancia;

    private Pedido pedido;

    private PedidoCacheRepository() {
        this.pedido = new Pedido();
        this.instancia = this;
    }

    public static PedidoCacheRepository getInstance () {
        return (instancia == null) ? new PedidoCacheRepository() : instancia;
    }

    public Pedido getPedido() {
        return this.pedido;
    }

    public static void limparInstancia() {
        new PedidoCacheRepository();
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
