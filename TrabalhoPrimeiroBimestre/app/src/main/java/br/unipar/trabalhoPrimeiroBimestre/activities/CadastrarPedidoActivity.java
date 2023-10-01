package br.unipar.trabalhoPrimeiroBimestre.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.unipar.trabalhoPrimeiroBimestre.R;
import br.unipar.trabalhoPrimeiroBimestre.exceptions.NullFieldException;
import br.unipar.trabalhoPrimeiroBimestre.model.Cliente;
import br.unipar.trabalhoPrimeiroBimestre.model.ItemPedido;
import br.unipar.trabalhoPrimeiroBimestre.model.ItemVenda;
import br.unipar.trabalhoPrimeiroBimestre.model.Pedido;
import br.unipar.trabalhoPrimeiroBimestre.repository.ClienteRepository;
import br.unipar.trabalhoPrimeiroBimestre.repository.ItemVendaRepository;
import br.unipar.trabalhoPrimeiroBimestre.repository.PedidoCacheRepository;
import br.unipar.trabalhoPrimeiroBimestre.services.ItemPedidoService;
import br.unipar.trabalhoPrimeiroBimestre.services.ItemVendaService;
import br.unipar.trabalhoPrimeiroBimestre.services.PedidoService;

public class CadastrarPedidoActivity extends AppCompatActivity {

    private AutoCompleteTextView tvCodProduto;
    private EditText edQuantidade;
    private TextView tvItens;
    private Button btAdicionarItem;
    private Button btProximo;
    private LinearLayout llResumoTotais;
    private TextView tvTotalItens;
    private TextView tvValorTotal;
    private ArrayList<ItemVenda> listaProdutos;
    private Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pedido);


        tvCodProduto = findViewById(R.id.tvCodProduto);
        edQuantidade = findViewById(R.id.edQuantidade);
        tvItens = findViewById(R.id.tvItens);
        btProximo = findViewById(R.id.btProximo);
        btAdicionarItem = findViewById(R.id.btAdicionarItem);
        llResumoTotais = findViewById(R.id.llResumoTotais);
        tvValorTotal = findViewById(R.id.tvValorTotal);
        tvTotalItens = findViewById(R.id.tvTotalItens);

        PedidoCacheRepository.limparInstancia();
        carregarProdutos();

        btAdicionarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    adicionarItem();
                } catch (Exception ex) {
                    Toast.makeText(CadastrarPedidoActivity.this, "Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });


        btProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizarPedido();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            PedidoService.validarPedido(pedido);
            this.finish();
        } catch (Exception ex) { }
    }



    private void finalizarPedido() {
       this.pedido = PedidoCacheRepository.getInstance().getPedido();

        if(pedido.getListaItens().size() != 0) {
            Intent intent = new Intent(CadastrarPedidoActivity.this,
                    TerminarPedidoActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(CadastrarPedidoActivity.this, "Erro: Adicione pelo menos um item.", Toast.LENGTH_LONG).show();
        }

    }

    private void adicionarItem() {
        this.pedido = PedidoCacheRepository.getInstance().getPedido();

        int qtd = 0;
        try {
            qtd = Integer.parseInt(edQuantidade.getText().toString());
        } catch (Exception ex) {
            edQuantidade.setError("Input inválido");
            edQuantidade.requestFocus();
            return;
        }


        int codProduto = 0;
        try{
            codProduto = Integer.parseInt(tvCodProduto.getText().toString());
        } catch (Exception ex) {
            tvCodProduto.setError("Input inválido");
            tvCodProduto.requestFocus();
            return;
        }

        ItemVenda produto = ItemVendaService.findItemVenda(codProduto);

        ItemPedido item = new ItemPedido(produto, qtd);
        ItemPedidoService.validarItemPedido(item);

        pedido.addListaItens(item);

        tvItens.setText(item + tvItens.getText().toString());
        atualizarTotais();
        tvCodProduto.getText().clear();
        edQuantidade.getText().clear();
    }

    private void atualizarTotais() {

        if (llResumoTotais.getVisibility() == View.GONE) {
            llResumoTotais.setVisibility(View.VISIBLE);
        }

        tvTotalItens.setText(Integer.toString(pedido.getListaItens().size()));
        tvValorTotal.setText(Double.toString(pedido.getValorTotal()));
    }


    private void carregarProdutos() {
        listaProdutos = ItemVendaRepository.getInstance().retornarItensVenda();
        String[] vetProdutos = new String[listaProdutos.size()];

        for (int i = 0; i < listaProdutos.size(); i++) {
            ItemVenda produto = listaProdutos.get(i);
            vetProdutos[i] = Integer.toString(produto.getCodigo());
        }

        ArrayAdapter adapter = new ArrayAdapter(CadastrarPedidoActivity.this, android.R.layout.simple_dropdown_item_1line, vetProdutos);
        tvCodProduto.setAdapter(adapter);
    }
}