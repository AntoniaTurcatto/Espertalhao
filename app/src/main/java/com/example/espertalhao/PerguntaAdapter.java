package com.example.espertalhao;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.espertalhao.model.Pergunta;

import java.util.List;

public class PerguntaAdapter extends RecyclerView.Adapter<PerguntaAdapter.MyViewHolder>{

    private List<Pergunta> listaPerguntas;
    private PerguntaOnClickListener perguntaOnClickListener;
    private PerguntaOnLongClickListener perguntaOnLongClickListener;
    private Cursor cursorPerguntas;

    public PerguntaAdapter(List<Pergunta> listaPerguntas, PerguntaOnClickListener perguntaOnClickListener, PerguntaOnLongClickListener perguntaOnLongClickListener){
        this.listaPerguntas = listaPerguntas;
        this.perguntaOnClickListener = perguntaOnClickListener;
        this.perguntaOnLongClickListener = perguntaOnLongClickListener;
    }


    @Override
    public PerguntaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PerguntaAdapter.MyViewHolder holder, final int position){
        //int id = cursorPerguntas.getInt(cursorPerguntas.getColumnIndex("_id"));


//        String enunciado = cursorPerguntas.getString(cursorPerguntas.getColumnIndex("enunciado"));
//        String opcao_a = cursorPerguntas.getString(cursorPerguntas.getColumnIndex("opcao_a"));
//        String opcao_b = cursorPerguntas.getString(cursorPerguntas.getColumnIndex("opcao_b"));
//        String opcao_c = cursorPerguntas.getString(cursorPerguntas.getColumnIndex("opcao_c"));
//        String opcao_d = cursorPerguntas.getString(cursorPerguntas.getColumnIndex("opcao_d"));
//        String opcao_e = cursorPerguntas.getString(cursorPerguntas.getColumnIndex("opcao_e"));
//        String opcao_correta = cursorPerguntas.getString(cursorPerguntas.getColumnIndex("opcao_correta"));
//        String conteudo = cursorPerguntas.getString(cursorPerguntas.getColumnIndex("conteudo"));

        //tipo Conteudo
        //imagem tipo conteudo

        Pergunta pergunta = listaPerguntas.get(position);
        switch (pergunta.getConteudo().getTipoConteudo()){
            case 1:
                holder.textViewTipoConteudoVisualizaPerguntas.setText(holder.itemView.getContext().getString(R.string.exactSciences));
                holder.imageViewTipoConteudoVisualizaPerguntas.setImageResource(R.drawable.mathematical_symbol);
                break;
            case 2:
                holder.textViewTipoConteudoVisualizaPerguntas.setText(holder.itemView.getContext().getString(R.string.humanSciences));
                holder.imageViewTipoConteudoVisualizaPerguntas.setImageResource(R.drawable.books_symbol);
                break;
            case 3:
                holder.textViewTipoConteudoVisualizaPerguntas.setText(holder.itemView.getContext().getString(R.string.languages));
                holder.imageViewTipoConteudoVisualizaPerguntas.setImageResource(R.drawable.language_symbol);
                break;
            default:
                holder.textViewTipoConteudoVisualizaPerguntas.setText(holder.itemView.getContext().getString(R.string.error));
                holder.imageViewTipoConteudoVisualizaPerguntas.setImageResource(R.drawable.ic_sentiment_dissatisfied_white_24dp);
                break;
        }
        //conteudo
        holder.textViewConteudoVisualizaPerguntas.setText(pergunta.getConteudo().getNomeConteudo());
        //enunciado
        holder.textViewPerguntaVisualizaPerguntas.setText(pergunta.getEnunciado());

        if(perguntaOnClickListener != null && perguntaOnLongClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    perguntaOnClickListener.onClickPergunta(holder.itemView, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    perguntaOnLongClickListener.onLongClickPergunta(holder.itemView, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount(){
        return listaPerguntas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTipoConteudoVisualizaPerguntas, textViewConteudoVisualizaPerguntas, textViewPerguntaVisualizaPerguntas;
        ImageView imageViewTipoConteudoVisualizaPerguntas;

        public MyViewHolder(View itemView){
            super(itemView);
            textViewTipoConteudoVisualizaPerguntas = itemView.findViewById(R.id.textViewTipoConteudoVisualizaPerguntas);
            textViewConteudoVisualizaPerguntas = itemView.findViewById(R.id.textViewConteudoVisualizaPerguntas);
            textViewPerguntaVisualizaPerguntas = itemView.findViewById(R.id.textViewPerguntaVisualizaPerguntas);

            imageViewTipoConteudoVisualizaPerguntas = itemView.findViewById(R.id.imageViewTipoConteudoVisualizaPerguntas);
        }
    }

    public interface PerguntaOnClickListener{
        public void onClickPergunta(View view, int position);
    }

    public interface PerguntaOnLongClickListener{
        public void onLongClickPergunta(View view, int position);
    }
}
