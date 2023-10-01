package br.unipar.trabalhoPrimeiroBimestre.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.unipar.trabalhoPrimeiroBimestre.R;

public class MainActivity extends AppCompatActivity {

    private Button btConsultarPedidos;
    private Button btCadastrarPedido;
    private Button btCadastrarItemVenda;
    private Button btCadastrarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btConsultarPedidos = findViewById(R.id.btConsultarPedidos);
        btCadastrarPedido = findViewById(R.id.btCadastrarPedido);
        btCadastrarItemVenda = findViewById(R.id.btCadastrarItemVenda);
        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);


        btConsultarPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { abrirActivity(ConsultarPedidos.class); }
        });


        btCadastrarPedido.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) { abrirActivity(CadastrarPedidoActivity.class); }
        });


        btCadastrarItemVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { abrirActivity(CadastrarItemVendaActivity.class); };
        });


        btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) { abrirActivity(CadastrarClienteActivity.class); }
        });
    }

    private void abrirActivity(Class<?> activity) {
        Intent intent = new Intent(MainActivity.this,
                activity);
        startActivity(intent);
    }

}