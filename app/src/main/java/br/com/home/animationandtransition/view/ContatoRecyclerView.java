package br.com.home.animationandtransition.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import br.com.home.animationandtransition.R;
import br.com.home.animationandtransition.bean.Contato;

/**
 * Created by Ronan.lima on 26/06/17.
 */

public class ContatoRecyclerView extends RecyclerView.Adapter<ContatoAdapter> {
    private Context mcontext;
    private List<Contato> list;
    private DetalhaContato listenerDetalha;

    public ContatoRecyclerView(Context mcontext, List<Contato> list, DetalhaContato listener) {
        this.mcontext = mcontext;
        this.list = list;
        this.listenerDetalha = listener;
    }

    @Override
    public ContatoAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.adapter_lista_contatos, parent, false);
        return new ContatoAdapter(view);
    }

    @Override
    public void onBindViewHolder(final ContatoAdapter holder, final int position) {
        holder.getNomeContato().setText(list.get(position).getNome());
        holder.getTelContato().setText(list.get(position).getTel());

        holder.getImgContato().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenerDetalha.detalha(position, holder.getImgContato());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public interface DetalhaContato extends Serializable {
        void detalha(int position, ImageView imgContato);
    }

}

class ContatoAdapter extends RecyclerView.ViewHolder {

    private ImageView imgContato;
    private TextView nomeContato, telContato;

    public ContatoAdapter(View itemView) {
        super(itemView);
        imgContato = (ImageView) itemView.findViewById(R.id.img_contato);
        nomeContato = (TextView) itemView.findViewById(R.id.nome_contato);
        telContato = (TextView) itemView.findViewById(R.id.tel_contato);
    }

    public ImageView getImgContato() {
        return imgContato;
    }

    public void setImgContato(ImageView imgContato) {
        this.imgContato = imgContato;
    }

    public TextView getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(TextView nomeContato) {
        this.nomeContato = nomeContato;
    }

    public TextView getTelContato() {
        return telContato;
    }

    public void setTelContato(TextView telContato) {
        this.telContato = telContato;
    }
}
