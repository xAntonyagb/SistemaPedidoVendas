package br.unipar.trabalhoPrimeiroBimestre.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.unipar.trabalhoPrimeiroBimestre.R;
import br.unipar.trabalhoPrimeiroBimestre.model.Cliente;
import br.unipar.trabalhoPrimeiroBimestre.services.ClienteService;
import br.unipar.trabalhoPrimeiroBimestre.services.ItemVendaService;

public class CadastrarClienteActivity extends AppCompatActivity {

    private EditText etNomeCliente;
    private EditText etCpfCliente;
    private Button btSalvar;
    private TextView tvClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        etNomeCliente = findViewById(R.id.etNomeCliente);
        etCpfCliente = findViewById(R.id.etCpfCliente);
        btSalvar = findViewById(R.id.btSalvar);
        tvClientes = findViewById(R.id.tvClientes);

        atualizarLista();

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarCliente();
                atualizarLista();
            }
        });

    }

    private void atualizarLista() {
        tvClientes.setText(ClienteService.retornarClientes());
    }

    private void cadastrarCliente() {
        String nome = etNomeCliente.getText().toString();
        String cpf = etCpfCliente.getText().toString();

        Cliente cliente = new Cliente(nome, cpf);

        try {
            ClienteService.salvarCliente(cliente);

            Toast.makeText(CadastrarClienteActivity.this, "Cliente cadastrado!", Toast.LENGTH_SHORT).show();
            this.finish();
        } catch (Exception ex) {
            Toast.makeText(CadastrarClienteActivity.this, "Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            etNomeCliente.setText("");
            etCpfCliente.setText("");
        }

    }
}