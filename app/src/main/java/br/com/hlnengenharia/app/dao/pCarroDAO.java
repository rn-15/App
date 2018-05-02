package br.com.hlnengenharia.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.hlnengenharia.app.model.PerguntaCarro;
import br.com.hlnengenharia.app.model.RespostaCarro;

public class pCarroDAO implements Closeable {


    private final Context context;
    private HelperDAO dao;

    public pCarroDAO(Context context) {
        this.dao = new HelperDAO(context);
        this.context = context;
    }

    @Override
    public void close() {
        dao.close();
    }

    public void insere(PerguntaCarro perguntaCarro) {
        ContentValues values = new ContentValues();
            values.put("cpergunta", perguntaCarro.getPergunta());
        dao.getWritableDatabase().insert("CarroPergunta",null, values);
    }

    public List<PerguntaCarro> buscaPerguntaCarro() {
        List<PerguntaCarro> perguntas = new ArrayList<>();
        Cursor c = dao.getReadableDatabase().rawQuery("SELECT * FROM CarroPergunta",null);
            while (c.moveToNext()){
                PerguntaCarro p = new PerguntaCarro();
                    p.setId(c.getLong(c.getColumnIndex("cpergunta_id")));
                    p.setPergunta(c.getString(c.getColumnIndex("cpergunta")));
                 perguntas.add(p);
            }c.close();
            return perguntas;
    }

    public void inserir(RespostaCarro resposta) {
        ContentValues values = new ContentValues();
            values.put("idCarro", resposta.getIdCarro());
            values.put("idPergunta", resposta.getIdPergunta());
            values.put("cresposta_desc", resposta.getResposta());
        dao.getWritableDatabase().insert("CarroResposta", null, values);
    }

    public List<RespostaCarro> buscaResposta() {
        List<RespostaCarro> respostas = new ArrayList<>();
        Cursor c = dao.getReadableDatabase().rawQuery("SELECT * FROM CarroResposta",null);
        while (c.moveToNext()){
            respostas.add(criaResposta(c));
        }c.close();
        return respostas;
    }

    @NonNull
    private RespostaCarro criaResposta(Cursor c) {
        RespostaCarro r = new RespostaCarro();
        r.setId(c.getLong(c.getColumnIndex("cresposta_id")));
        r.setIdCarro(c.getLong(c.getColumnIndex("idCarro")));
        r.setIdPergunta(c.getLong(c.getColumnIndex("idPergunta")));
        r.setResposta(c.getString(c.getColumnIndex("cresposta_desc")));
        return r;
    }
}
