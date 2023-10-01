package br.unipar.trabalhoPrimeiroBimestre.repository;

import java.util.ArrayList;

import br.unipar.trabalhoPrimeiroBimestre.model.ItemVenda;

public class ItemVendaRepository {

    private static ItemVendaRepository instancia;
    private ArrayList<ItemVenda> listaItensVenda;
    private int codigo;

    public static ItemVendaRepository getInstance () {
        return (instancia == null) ? new ItemVendaRepository() : instancia;
    }

    private ItemVendaRepository() {
        this.listaItensVenda = new ArrayList<>();
        this.codigo = 1;
        this.instancia = this;
    }

    public void salvarItemVenda(ItemVenda itemVenda){
        listaItensVenda.add(itemVenda);
        codigo++;
    }

    public ArrayList<ItemVenda> retornarItensVenda() {
        return listaItensVenda;
    }

    public int getCodigo() { return 100 + codigo; }

}
