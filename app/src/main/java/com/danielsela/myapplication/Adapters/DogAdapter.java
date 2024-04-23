package com.danielsela.myapplication.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.danielsela.myapplication.Interfaces.DogCallback;
import com.danielsela.myapplication.Interfaces.OnChatButtonClickListener;
import com.danielsela.myapplication.Models.Dog;
import com.danielsela.myapplication.R;
import com.danielsela.myapplication.Utiles.ImageLoader;
import com.danielsela.myapplication.ui.PlaydateFragment.PlaydateFragment;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogHolder> {
    private Context context;
    private ArrayList<Dog> dogList;
    private DogCallback dogCallback;
    private OnChatButtonClickListener onChatButtonClickListener;


    public DogAdapter setDogCallback(DogCallback dogCallback) {
        this.dogCallback = dogCallback;
        return this;
    }

    public DogAdapter(Context context, ArrayList<Dog> Dogs) {
        this.context = context;
        this.dogList = Dogs;
    }


    @NonNull
    @Override
    public DogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item, parent, false);
        return new DogHolder(view);
    }

    public void setOnChatButtonClickListener(OnChatButtonClickListener onChatButtonClickListener) {
        this.onChatButtonClickListener = onChatButtonClickListener;
    }
    public class DogHolder extends RecyclerView.ViewHolder {

        private ShapeableImageView favorite_IMG;
        private MaterialTextView dog_LBL_name;
        private MaterialTextView dog_LBL_location;
        private MaterialTextView dog_LBL_playStyle;
        private MaterialTextView dog_LBL_bread;
        private MaterialTextView dog_LBL_energy;
        private ShapeableImageView dog_IMG_poster;
        private ImageButton dog_approve_btn;

        public DogHolder(@NonNull View itemView) {
            super(itemView);
            favorite_IMG = itemView.findViewById(R.id.favorite_IMG);
            dog_IMG_poster = itemView.findViewById(R.id.dog_IMG_poster);
            dog_LBL_name = itemView.findViewById(R.id.textview_dogsName);
            dog_LBL_location = itemView.findViewById(R.id.textview_location);
            dog_LBL_playStyle = itemView.findViewById(R.id.textview_playstyle);
            dog_LBL_bread = itemView.findViewById(R.id.textview_bread);
            dog_LBL_energy = itemView.findViewById(R.id.textview_energy);
            dog_approve_btn = itemView.findViewById(R.id.dog_approve_btn);

            favorite_IMG.setOnClickListener(v -> {
                if (dogCallback != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        dogCallback.favoriteButtonClickedDog(getItem(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });

        }
    }


    @Override
    public void onBindViewHolder(@NonNull DogHolder holder, int position) {
        Dog dog = getItem(position);
        ImageLoader.getInstance().load(dog.getDogPoster(), holder.dog_IMG_poster);
        holder.dog_LBL_name.setText(dog.getName());
        holder.dog_LBL_location.setText(dog.getLocation());
        holder.dog_LBL_playStyle.setText(dog.getPlayStyle());
        holder.dog_LBL_bread.setText(dog.getBread());
        holder.dog_LBL_energy.setText(String.valueOf(dog.getEnergy()));

        if (dog.isFavorite())
            holder.favorite_IMG.setImageResource(R.drawable.heart);
        else
            holder.favorite_IMG.setImageResource(R.drawable.empty_heart);
        holder.dog_approve_btn.setOnClickListener(v -> {
            onChatButtonClickListener.onChatButtonClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return dogList == null ? 0 : dogList.size();
    }

    public Dog getItem(int pos) {
        return dogList.get(pos);
    }


}
