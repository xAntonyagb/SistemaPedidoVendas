package br.unipar.trabalhoPrimeiroBimestre.repository;

import java.util.ArrayList;

import br.unipar.trabalhoPrimeiroBimestre.model.Cliente;

public class ClienteRepository {

    private static ClienteRepository instancia;
    private ArrayList<Cliente> listaClientes;

    public static ClienteRepository getInstance () {
        return (instancia == null) ? new ClienteRepository() : instancia;
    }

    private ClienteRepository () {

        listaClientes = new ArrayList<>();
        instancia = this;
    }

    public void salvarCliente(Cliente cliente){
        listaClientes.add(cliente);
    }

    public ArrayList<Cliente> retornarClientes() {
        return listaClientes;
    }

}
