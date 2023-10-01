package br.unipar.trabalhoPrimeiroBimestre.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.unipar.trabalhoPrimeiroBimestre.R;
import br.unipar.trabalhoPrimeiroBimestre.model.ItemVenda;
import br.unipar.trabalhoPrimeiroBimestre.services.ItemVendaService;

public class CadastrarItemVendaActivity extends AppCompatActivity {

    private EditText etDescItem;
    private EditText etValorUnitario;
    private TextView tvItens;
    private Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_item_venda);

        etDescItem = findViewById(R.id.etDescItem);
        etValorUnitario = findViewById(R.id.etValorUnitario);
        tvItens = findViewById(R.id.tvItens);
        btCadastrar = findViewById(R.id.btCadastrar);

        atualizarLista();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarItem();
                atualizarLista();
            }
        });
    }
    private void atualizarLista() {
        tvItens.setText(ItemVendaService.retornarItensVenda());
    }

    private void cadastrarItem() {
        String desc = etDescItem.getText().toString();

        double valor = 0;

        try {
            valor = Double.parseDouble(etValorUnitario.getText().toString());
        } catch (Exception ex) {
            etValorUnitario.setText("");
            etValorUnitario.setError("É necessário que o input seja um número");
        }

        ItemVenda item = new ItemVenda(desc, valor);

        try {
            ItemVendaService.salvarItemVenda(item);

            Toast.makeText(CadastrarItemVendaActivity.this, "Item cadastrado!", Toast.LENGTH_SHORT).show();
            this.finish();
        } catch (Exception ex) {
            Toast.makeText(CadastrarItemVendaActivity.this, "Erro: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            etValorUnitario.setText("");
            etDescItem.setText("");
        }

    }
}