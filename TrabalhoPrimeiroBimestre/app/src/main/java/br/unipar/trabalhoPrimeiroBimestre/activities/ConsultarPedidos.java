package br.unipar.trabalhoPrimeiroBimestre.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import br.unipar.trabalhoPrimeiroBimestre.R;
import br.unipar.trabalhoPrimeiroBimestre.exceptions.ObjectNotFoundException;
import br.unipar.trabalhoPrimeiroBimestre.exceptions.ParcelaInválidaException;
import br.unipar.trabalhoPrimeiroBimestre.model.ItemPedido;
import br.unipar.trabalhoPrimeiroBimestre.model.ItemVenda;
import br.unipar.trabalhoPrimeiroBimestre.model.Pedido;
import br.unipar.trabalhoPrimeiroBimestre.repository.ItemVendaRepository;
import br.unipar.trabalhoPrimeiroBimestre.repository.PedidoRepository;
import br.unipar.trabalhoPrimeiroBimestre.services.PedidoService;

public class ConsultarPedidos extends AppCompatActivity {

    private AutoCompleteTextView tvCodProduto;
    private Button btProcurar;
    private TextView tvProdutos;
    private TextView tvEscritaPedidos;
    private ArrayList<Pedido> pedidos = PedidoRepository.getInstance().retornarPedidos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_pedidos);

        tvCodProduto = findViewById(R.id.tvCodProduto);
        btProcurar = findViewById(R.id.btProcurar);
        tvProdutos = findViewById(R.id.tvProdutos);
        tvEscritaPedidos = findViewById(R.id.tvEscritaPedidos);

        carregarPedidos();
        exibirTodosPedidos();

        tvCodProduto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(tvCodProduto.getText().toString().isEmpty()) {
                    exibirTodosPedidos();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirPedido();
            }
        });


    }

    private void exibirPedido() {

        Pedido pesquisa = null;

        try {
            int cod = Integer.parseInt(tvCodProduto.getText().toString());

            if(! PedidoService.validarExistencia(cod)) {
                throw new ObjectNotFoundException();
            }

            for (Pedido pedido :
                    pedidos) {
                if (pedido.getCodigo() == cod) {
                    pesquisa = pedido;
                    break;
                }
            }
            tvEscritaPedidos.setText("Pedido Nº "+ pesquisa.getCodigo());
            tvProdutos.setText(pesquisa.toString());

        } catch (ObjectNotFoundException ex) {
            tvCodProduto.setText("");
            tvCodProduto.setError(ex.getMessage());
            tvCodProduto.requestFocus();
        } catch (Exception ex){
            tvCodProduto.setText("");
            tvCodProduto.setError("Input inválido!");
            tvCodProduto.requestFocus();
        }

    }

    private void exibirTodosPedidos() {
        String conteudo = "";

        for (Pedido pedido:
                pedidos) {
            conteudo += pedido.toString() +"\n\n-------------------------------------------------------------------------------------------------\n\n";
        }

        tvProdutos.setText(conteudo);
        tvEscritaPedidos.setText("Lista de todos os pedidos:");
    }


    private void carregarPedidos() {
        String[] vetPedidos = new String[pedidos.size()];

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            vetPedidos[i] = Integer.toString(pedido.getCodigo());
        }

        ArrayAdapter adapter = new ArrayAdapter(ConsultarPedidos.this, android.R.layout.simple_dropdown_item_1line, vetPedidos);
        tvCodProduto.setAdapter(adapter);
    }
}