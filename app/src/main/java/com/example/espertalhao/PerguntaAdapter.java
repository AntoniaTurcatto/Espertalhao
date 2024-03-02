package com.example.espertalhao;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PerguntaAdapter extends RecyclerView.Adapter<PerguntaAdapter.MyViewHolder>{

    private List<Pergunta> listaPerguntas;
    private PerguntaOnClickListener perguntaOnClickListener;
    android.content.res.Resources res;

    public PerguntaAdapter(List<Pergunta> listaPerguntas, PerguntaOnClickListener perguntaOnClickListener){
        this.listaPerguntas = listaPerguntas;
        this.perguntaOnClickListener = perguntaOnClickListener;
    }

    @Override
    public PerguntaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PerguntaAdapter.MyViewHolder holder, final int position){
        Pergunta pergunta = listaPerguntas.get(position);
        //tipo Conteudo
        //imagem tipo conteudo
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

        if(perguntaOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    perguntaOnClickListener.onClickPergunta(holder.itemView, position);
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
}
