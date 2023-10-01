package br.unipar.trabalhoPrimeiroBimestre.services;

import java.util.ArrayList;

import br.unipar.trabalhoPrimeiroBimestre.exceptions.NullFieldException;
import br.unipar.trabalhoPrimeiroBimestre.model.Cliente;
import br.unipar.trabalhoPrimeiroBimestre.model.Pedido;
import br.unipar.trabalhoPrimeiroBimestre.repository.ClienteRepository;

public class ClienteService {

    public static ClienteRepository repository = ClienteRepository.getInstance();

    public static void validarCliente(Cliente cliente) {

        if (cliente.getCpf().isEmpty())
            throw new NullFieldException();
        if (cliente.getNome().isEmpty())
            throw new NullFieldException();
    }

    public static void salvarCliente(Cliente cliente) {
        try {
            validarCliente(cliente);
            repository.salvarCliente(cliente);
        } catch (Exception ex) {
            throw new RuntimeException("Por favor preencha todos os campos.");
        }
    }
    public static String retornarClientes() {

        String output = "";
        for (Cliente cliente:
                repository.retornarClientes()) {
            output += cliente.toString();
        }

        return output;
    }

}
