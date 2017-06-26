package br.com.home.animationandtransition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeImageTransform;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ronan.lima on 26/06/17.
 */

public class DetalheActivity extends Activity {

    private EditText editNome, editTel;
    private Button btnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setAllowEnterTransitionOverlap(true);
        getWindow().setSharedElementEnterTransition(new ChangeImageTransform());
        setContentView(R.layout.detalhe_activity);
        editNome = (EditText) findViewById(R.id.edit_nome);
        editTel = (EditText) findViewById(R.id.edit_tel);
        btnOk = (Button) findViewById(R.id.bt_salvar);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("nome", editNome.getText().toString());
                i.putExtra("tel", editTel.getText().toString());
                startActivity(i);
                finishAfterTransition();
            }
        });
    }
}
