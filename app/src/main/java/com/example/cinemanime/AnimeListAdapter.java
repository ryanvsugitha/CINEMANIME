package com.example.cinemanime;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class AnimeListAdapter extends RecyclerView.Adapter<AnimeListAdapter.ViewHolder> {
    private AnimeAtt[] animeAtts;

    public AnimeListAdapter(AnimeAtt[] animeAtts){
        this.animeAtts = animeAtts;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView animeName;
        public TextView animeDesc;
        public ImageView animeImage;
        public TextView animeScore;
        public LinearLayout linearLayout;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.animeName = itemView.findViewById(R.id.anime_name);
            this.animeImage = itemView.findViewById(R.id.anime_image);
            this.animeScore = itemView.findViewById(R.id.anime_score);
            this.animeDesc = itemView.findViewById(R.id.anime_desc);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

    @NonNull
    @Override
    public AnimeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.anime_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull AnimeListAdapter.ViewHolder holder, int position) {

        String animeName = animeAtts[position].getAnimeName();
        String animeScore = animeAtts[position].getAnimeScore();
        String animeImage = animeAtts[position].getAnimeImg();
        String animeDesc = animeAtts[position].getAnimeDesc();

        holder.animeName.setText(animeName);
        holder.animeScore.setText(animeScore);
        Glide.with(holder.animeImage).load(animeImage).into(holder.animeImage);
        holder.animeDesc.setText(animeDesc);

    }

    @Override
    public int getItemCount() {
        return animeAtts.length;
    }
}
