package br.unipar.trabalhoPrimeiroBimestre.repository;

import java.util.ArrayList;

import br.unipar.trabalhoPrimeiroBimestre.model.Pedido;

public class PedidoRepository {

    private static PedidoRepository instancia;
    private ArrayList<Pedido> listaPedidos;
    private int codigo;

    public static PedidoRepository getInstance () {
        return (instancia == null) ? new PedidoRepository() : instancia;
    }

    private PedidoRepository() {
        this.listaPedidos = new ArrayList<>();
        this.codigo = 1;
        instancia = this;
    }

    public void salvarPedido(Pedido pedido){
        codigo++;
        listaPedidos.add(pedido);
    }

    public ArrayList<Pedido> retornarPedidos() {
        return listaPedidos;
    }

    public int getCodigo() { return 200 + codigo; }

}
