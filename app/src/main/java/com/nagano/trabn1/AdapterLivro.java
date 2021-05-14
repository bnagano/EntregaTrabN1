package com.nagano.trabn1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterLivro extends BaseAdapter {

    private List<Livro> livroList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterLivro(Context context, List<Livro> listaLivros){
        this.livroList = listaLivros;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return livroList.size();
    }

    @Override
    public Object getItem(int i) {
        return livroList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return livroList.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ItemSuporte item;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.layout_lista, null);

            item = new ItemSuporte();
            item.tvNome = convertView.findViewById(R.id.tvListaNome);
            item.tvNome2 = convertView.findViewById(R.id.tvListaNome2);
            item.tvAno = convertView.findViewById(R.id.tvListaAno);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag(item);
        }else{
            item = (ItemSuporte) convertView.getTag();
        }

        Livro livro = livroList.get(i);
        item.tvNome.setText(livro.nome);
        item.tvNome2.setText(livro.autor);
        item.tvAno.setText(livro.getAno());

        if (i%2==0){
            item.layout.setBackgroundColor(Color.rgb(230, 230, 230));
        }else{
            item.layout.setBackgroundColor(Color.WHITE);
        }
        return convertView;
    }

    private class ItemSuporte{
        TextView tvNome, tvNome2, tvAno;
        LinearLayout layout;
    }
}
