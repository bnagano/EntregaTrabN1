package com.nagano.trabn1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etNome2;
    private Spinner spAno;
    private Button btnSalvar;
    private String acao;
    private Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etNome2 = findViewById(R.id.etNome2);
        spAno = findViewById(R.id.spAno);
        btnSalvar = findViewById(R.id.btnSalvar);

        acao = getIntent().getStringExtra(acao);
        if (acao.equals("editar")) {
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    private void carregarFormulario() {
        int idLivro = getIntent().getIntExtra("idLivro", 0);
        if (idLivro != 0) {
            livro = LivroDAO.getLivroById(this, idLivro);
            etNome.setText(livro.nome);

            String[] arrayAno = getResources().getStringArray(R.array.arrayAno);
            for (int i = 1; i < arrayAno.length; i++) {
                if (Integer.valueOf(arrayAno[i]) == livro.getAno()) {
                    spAno.setSelection(i);
                }
            }

        }
    }

    private void salvar() {
        if (spAno.getSelectedItemPosition() == 0 || etNome.getText().toString().isEmpty()) {

            Toast.makeText(this, "Todos campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();

        } else {
            if (acao.equals("novo")) {
                livro = new Livro();
            }

            livro.nome = etNome.getText().toString();
            livro.autor = etNome2.getText().toString();
            livro.setAno(Integer.valueOf(spAno.getSelectedItem().toString()));

            if (acao.equals("editar")){
                LivroDAO.editar(livro, this);
                finish();
            }else{
                LivroDAO.inserir(livro, this);
                etNome.setText("");
                etNome2.setText("");
                spAno.setSelection(0);
            }
        }
    }
}