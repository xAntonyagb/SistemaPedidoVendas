package br.unipar.trabalhoPrimeiroBimestre.services;

import java.util.ArrayList;

import br.unipar.trabalhoPrimeiroBimestre.exceptions.InvalidComponentsException;
import br.unipar.trabalhoPrimeiroBimestre.exceptions.NullFieldException;
import br.unipar.trabalhoPrimeiroBimestre.model.ItemPedido;
import br.unipar.trabalhoPrimeiroBimestre.model.ItemVenda;
import br.unipar.trabalhoPrimeiroBimestre.repository.ItemVendaRepository;

public class ItemPedidoService {
    public static void validarItemPedido(ItemPedido itemPedido) {

        try {
            ItemVendaService.validarItensVenda(itemPedido.getItemVenda());
        } catch (Exception ex) {
            throw new InvalidComponentsException("Item Venda");
        }

        if (itemPedido.getQuantidade() <= 0)
            throw new NullFieldException();
        if (itemPedido.getPrecoTotal() <= 0)
            throw new NullFieldException();
    }

}
