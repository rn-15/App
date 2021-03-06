package br.com.hlnengenharia.app.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.hlnengenharia.app.R;
import br.com.hlnengenharia.app.dao.CarroDAO;
import br.com.hlnengenharia.app.dao.PerguntaDAO;
import br.com.hlnengenharia.app.dao.pCarroDAO;
import br.com.hlnengenharia.app.helper.CarroHelper;
import br.com.hlnengenharia.app.helper.PerguntaCarroHelper;
import br.com.hlnengenharia.app.helper.PerguntaHelper;
import br.com.hlnengenharia.app.model.Carro;
import br.com.hlnengenharia.app.model.Inspecao;
import br.com.hlnengenharia.app.model.Pergunta;
import br.com.hlnengenharia.app.model.PerguntaCarro;

public class CadPerguntaActivity extends AppCompatActivity {

    Button salvar;
    EditText pergunta;
    TextView codigo, setor, nome;
    private PerguntaCarroHelper helper;
    private CarroHelper helperC;
    private Inspecao inspecao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_pergunta);

        helper = new PerguntaCarroHelper(CadPerguntaActivity.this);

        if (inspecao!=null) {
            Intent intent = getIntent();
            inspecao = (Inspecao) intent.getSerializableExtra("inspecao");
            codigo = findViewById(R.id.codigo_insp);
            setor = findViewById(R.id.setor_insp);
            nome = findViewById(R.id.nome_insp);
            codigo.setText(inspecao.getId().toString());
            setor.setText(inspecao.getIdSetor().toString());
            nome.setText(inspecao.getNome());
        }

        salvar = findViewById(R.id.salvar_pergunta);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    pCarroDAO dao = new pCarroDAO(CadPerguntaActivity.this);
                    PerguntaCarro perguntaCarro = helper.pegaPergunta();
                    dao.insere(perguntaCarro);
                    dao.close();
                    Toast.makeText(CadPerguntaActivity.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();


                finish();
            }
        });

    }
}
