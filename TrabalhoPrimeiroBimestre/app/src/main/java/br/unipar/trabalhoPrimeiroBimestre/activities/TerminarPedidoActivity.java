package br.unipar.trabalhoPrimeiroBimestre.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.unipar.trabalhoPrimeiroBimestre.R;
import br.unipar.trabalhoPrimeiroBimestre.exceptions.NullFieldException;
import br.unipar.trabalhoPrimeiroBimestre.exceptions.ParcelaInválidaException;
import br.unipar.trabalhoPrimeiroBimestre.model.Cliente;
import br.unipar.trabalhoPrimeiroBimestre.model.CondicaoPagamentoEnum;
import br.unipar.trabalhoPrimeiroBimestre.model.Pedido;
import br.unipar.trabalhoPrimeiroBimestre.repository.ClienteRepository;
import br.unipar.trabalhoPrimeiroBimestre.repository.PedidoCacheRepository;
import br.unipar.trabalhoPrimeiroBimestre.services.PedidoService;

public class TerminarPedidoActivity extends AppCompatActivity {


    private RadioGroup rgPagamento;
    private RadioButton rbVista;
    private RadioButton rbParcelado;
    private Button btConcluirPedido;
    private TextView tvValorTotal;
    private Spinner spCliente;
    private TextView tvParcelas;
    private LinearLayout llParcelas;
    private EditText edQtdParcelas;
    private TextView tvTotalPagamento;

    private int qtdParcelas;
    private ArrayList<Cliente> listaClientes;
    private Pedido pedido = PedidoCacheRepository.getInstance().getPedido();

    private int posCliente = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminar_pedido);

        //Atribuindo variaveis
        rgPagamento = findViewById(R.id.rgPagamento);
        rbVista = findViewById(R.id.rbVista);
        rbParcelado = findViewById(R.id.rbParcelado);
        spCliente = findViewById(R.id.spCliente);
        btConcluirPedido = findViewById(R.id.btConcluirPedido);
        tvValorTotal = findViewById(R.id.tvValorTotal);
        tvParcelas = findViewById(R.id.tvParcelas);
        llParcelas = findViewById(R.id.llParcelas);
        edQtdParcelas = findViewById(R.id.edQtdParcelas);
        tvTotalPagamento = findViewById(R.id.tvTotalPagamento);

        //Carregando conteudo do spinner e valor total
        carregaClientes();
        tvValorTotal.setText(Double.toString(pedido.getValorTotal()));

        //Seleção de conteúdo do spinner
        spCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int posicao, long l) {
                if (posicao > 0) {
                    posCliente = posicao;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Quando clicar no bt de concluir
        btConcluirPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    finalizarPedido();
                } catch (Exception ex) {
                    Toast.makeText(TerminarPedidoActivity.this, "Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


        //Interação com os radio buttons
        rgPagamento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == rbVista.getId()) {
                    pedido.setCondicaoPagamento(CondicaoPagamentoEnum.VISTA);
                    llParcelas.setVisibility(View.GONE);
                    tvParcelas.setVisibility(View.GONE);

                } else {
                    pedido.setCondicaoPagamento(CondicaoPagamentoEnum.PARCELADO);
                    llParcelas.setVisibility(View.VISIBLE);
                    tvParcelas.setVisibility(View.VISIBLE);
                }

                //Exibir valor após seleção de radio group
                Double totalPagamento = pedido.getValorTotalPagamento();
                tvTotalPagamento.setText("Total: " + totalPagamento);
                tvTotalPagamento.setVisibility(View.VISIBLE);
            }
        });

        //Campo de qtd de parcelas
        edQtdParcelas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    qtdParcelas = Integer.parseInt(edQtdParcelas.getText().toString());

                    if (qtdParcelas <= 1 || qtdParcelas > 12)
                        throw new ParcelaInválidaException();

                    pedido.setQtdparcelas(qtdParcelas);
                    popularViewParcelas();

                } catch (ParcelaInválidaException ex) {

                    edQtdParcelas.setError(ex.getMessage());
                    tvParcelas.setText("");
                    edQtdParcelas.requestFocus();

                }  catch (Exception ex){
                    edQtdParcelas.setError("Valor inválido");
                    tvParcelas.setText("");
                    edQtdParcelas.requestFocus();
                }
            }
        });




    }

    private void popularViewParcelas() {
        String conteudo = "";
        double valorParcela = pedido.getValorParcela();

        for (int j = 0; j < qtdParcelas; j++) {
            conteudo += (j+1) + "x - R$ " + String.format("%.2f",valorParcela) + "\n";
        }

        tvParcelas.setText(conteudo);
    }

    private void finalizarPedido() {

        if(posCliente <= 0)
            throw new NullFieldException();

        pedido.setCliente(listaClientes.get(posCliente-1));
        PedidoService.salvarPedido(pedido);

        Toast.makeText(TerminarPedidoActivity.this, "Pedido Cadastrado!", Toast.LENGTH_SHORT).show();
        this.finish();
    }


    private void carregaClientes() {

        listaClientes = ClienteRepository.getInstance().retornarClientes();
        String [] vetClientes = new String[listaClientes.size() + 1];
        vetClientes[0] = "Selecione o cliente";

        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            vetClientes[i+1] = cliente.toString();
        }


        ArrayAdapter adapter = new ArrayAdapter(
                TerminarPedidoActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                vetClientes);

        spCliente.setAdapter(adapter);
    }
}