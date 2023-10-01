package br.unipar.trabalhoPrimeiroBimestre.services;

import java.util.ArrayList;

import br.unipar.trabalhoPrimeiroBimestre.exceptions.NullFieldException;
import br.unipar.trabalhoPrimeiroBimestre.exceptions.ObjectNotFoundException;
import br.unipar.trabalhoPrimeiroBimestre.model.ItemVenda;
import br.unipar.trabalhoPrimeiroBimestre.repository.ClienteRepository;
import br.unipar.trabalhoPrimeiroBimestre.repository.ItemVendaRepository;

public class ItemVendaService {

    public static ItemVendaRepository repository = ItemVendaRepository.getInstance();

    public static void validarItensVenda(ItemVenda itemVenda) throws NullFieldException {

        if (itemVenda.getDescricao().isEmpty())
            throw new NullFieldException();
        if (itemVenda.getValorUnitario() <= 0)
            throw new NullFieldException();
    }

    public static void salvarItemVenda(ItemVenda itemVenda) {
        validarItensVenda(itemVenda);
        itemVenda.setCodigo(repository.getCodigo());
        repository.salvarItemVenda(itemVenda);
    }

    public static String retornarItensVenda() {

        String output = "";
        for (ItemVenda item:
             repository.retornarItensVenda()) {
            output += item.toString();
        }

        return output;
    }

    public static ItemVenda findItemVenda(int codigo) throws ObjectNotFoundException {
        for (ItemVenda item:
             repository.retornarItensVenda()) {
            if(item.getCodigo() == codigo)
                return item;
        }
        throw new ObjectNotFoundException();
    }

}
