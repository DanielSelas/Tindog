package com.danielsela.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danielsela.myapplication.Interfaces.OnChatButtonClickListener;
import com.danielsela.myapplication.Interfaces.PersonCallback;
import com.danielsela.myapplication.Models.Person;
import com.danielsela.myapplication.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.danielsela.myapplication.Utiles.ImageLoader;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonHolder> {
    private Context context;
    private ArrayList<Person> peopleList;
    private PersonCallback personCallback;
    private OnChatButtonClickListener onChatButtonClickListener;

    public PersonAdapter setPersonCallback(PersonCallback personCallback) {
        this.personCallback = personCallback;
        return this;
    }

    public PersonAdapter(Context context, ArrayList<Person> people) {
        this.context = context;
        this.peopleList = people;
    }

    public void setOnChatButtonClickListener(OnChatButtonClickListener onChatButtonClickListener) {
        this.onChatButtonClickListener = onChatButtonClickListener;
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.PersonHolder holder, int position) {
        Person person = getItem(position);
        ImageLoader.getInstance().load(person.getPersonPoster(), holder.person_IMG_poster);
        holder.person_LBL_name.setText(person.getName());
        holder.person_LBL_location.setText(person.getLocation());
        holder.person_LBL_Availability.setText(String.valueOf(person.getDaysOfAvailability()));
        holder.person_LBL_price.setText(String.valueOf(person.getPrice()));
        if (person.isFavorite())
            holder.favorite_IMG.setImageResource(R.drawable.heart);
        else
            holder.favorite_IMG.setImageResource(R.drawable.empty_heart);
       holder.person_approve_btn.setOnClickListener(v->{
               onChatButtonClickListener.onChatButtonClick(position);
       });

    }




    @Override
    public int getItemCount() {
        return peopleList == null ? 0 : peopleList.size();
    }

    public Person getItem(int pos) {
        return peopleList.get(pos);
    }

    public class PersonHolder extends RecyclerView.ViewHolder {
        private final MaterialTextView person_LBL_name;
        private final MaterialTextView person_LBL_location;
        private final MaterialTextView person_LBL_Availability;
        private final MaterialTextView person_LBL_price;
        private final ShapeableImageView favorite_IMG;
        private final ShapeableImageView person_IMG_poster;
        private ImageButton person_approve_btn;


        public PersonHolder(@NonNull View itemView) {
            super(itemView);
            person_LBL_name = itemView.findViewById(R.id.textview_name);
            person_LBL_location = itemView.findViewById(R.id.textview_location);
            person_LBL_Availability = itemView.findViewById(R.id.textview_Availability);
            person_LBL_price = itemView.findViewById(R.id.textview_price);
            favorite_IMG = itemView.findViewById(R.id.favorite_IMG);
            person_IMG_poster = itemView.findViewById(R.id.person_IMG_poster);
            person_approve_btn = itemView.findViewById(R.id.person_approve_btn);

            favorite_IMG.setOnClickListener(v -> {
                if (personCallback != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        personCallback.favoriteButtonClicked(getItem(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });
        }
    }
}





