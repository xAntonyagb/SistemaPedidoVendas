package br.unipar.trabalhoPrimeiroBimestre.services;

import java.util.ArrayList;

import br.unipar.trabalhoPrimeiroBimestre.exceptions.InvalidComponentsException;
import br.unipar.trabalhoPrimeiroBimestre.exceptions.NullFieldException;
import br.unipar.trabalhoPrimeiroBimestre.model.CondicaoPagamentoEnum;
import br.unipar.trabalhoPrimeiroBimestre.model.ItemPedido;
import br.unipar.trabalhoPrimeiroBimestre.model.ItemVenda;
import br.unipar.trabalhoPrimeiroBimestre.model.Pedido;
import br.unipar.trabalhoPrimeiroBimestre.repository.ClienteRepository;
import br.unipar.trabalhoPrimeiroBimestre.repository.ItemVendaRepository;
import br.unipar.trabalhoPrimeiroBimestre.repository.PedidoRepository;

public class PedidoService {

    public static PedidoRepository repository = PedidoRepository.getInstance();

    public static void validarPedido(Pedido pedido) {

        try {
            ClienteService.validarCliente(pedido.getCliente());
        } catch (Exception ex) {
            throw new InvalidComponentsException("Cliente");
        }

        try {
            for (ItemPedido item :
                 pedido.getListaItens()) {
                ItemPedidoService.validarItemPedido(item);
            }
        } catch (Exception ex) {
            throw new InvalidComponentsException("Item Pedido");
        }

        if (pedido.getCondicaoPagamento() == null)
            throw new NullFieldException();

        if(pedido.getCondicaoPagamento() == CondicaoPagamentoEnum.PARCELADO &&
                pedido.getQtdparcelas() <= 0
        ) {
            throw new NullFieldException();
        }

        if (pedido.getValorTotal() <= 0)
            throw new NullFieldException();

        if( pedido.getListaItens().size() <= 0) {
            throw new NullFieldException();
        }
    }

    public static void salvarPedido(Pedido pedido) {

        try {
            validarPedido(pedido);
            pedido.setCodigo(repository.getCodigo());
            repository.salvarPedido(pedido);
        } catch (Exception ex) {
            throw new RuntimeException("Por favor preencha todos os campos.");
        }
    }

    public static String retornarPedidos() {

        String output = "";
        for (Pedido pedido:
                repository.retornarPedidos()) {
            output += pedido.toString();
        }

        return output;
    }

    public static boolean validarExistencia(int codigo) {
        for (Pedido pedido :
                repository.retornarPedidos()) {
            if(pedido.getCodigo() == codigo)
                return true;
        }
        return false;
    }

}
