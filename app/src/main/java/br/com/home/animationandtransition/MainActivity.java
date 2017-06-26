package br.com.home.animationandtransition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeImageTransform;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import br.com.home.animationandtransition.bean.Contato;
import br.com.home.animationandtransition.view.ContatoRecyclerView;

/**
 * Created by Ronan.lima on 26/06/17.
 */

public class MainActivity extends Activity {

    protected RecyclerView recyclerView;
    protected List<Contato> contatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        getWindow().setExitTransition(new ChangeImageTransform());
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.lista_contatos);
        contatos = criaContatos();
        recyclerView.setAdapter(new ContatoRecyclerView(this, contatos, criaListenerDetalhe()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ContatoRecyclerView.DetalhaContato criaListenerDetalhe() {
        return new ContatoRecyclerView.DetalhaContato() {
            @Override
            public void detalha(int position, ImageView imgContato) {
                Intent i = new Intent(getApplicationContext(), DetalheActivity.class);
                i.putExtra("nome", contatos.get(position).getNome());
                i.putExtra("telefone", contatos.get(position).getTel());
                getWindow().setSharedElementExitTransition(new ChangeImageTransform());
                startActivity(i, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, imgContato, "picture").toBundle());
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == requestCode) {
            contatos.get(data.getIntExtra("position", 0)).setNome(data.getStringExtra("nome"));
            contatos.get(data.getIntExtra("position", 0)).setTel(data.getStringExtra("tel"));
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    protected List<Contato> criaContatos() {
        List<Contato> contatos = new ArrayList<>();
        String[] nomes = {"Gustavo Martineli", "Daniel Matos", "David Alvarenga", "Fernando Dias"};
        String[] tels = {"1111", "2222", "3333", "4444"};
        for (int i = 0; i < 4; i++) {
            Contato c = new Contato(nomes[i], tels[i]);
            contatos.add(c);
        }
        return contatos;
    }
}
