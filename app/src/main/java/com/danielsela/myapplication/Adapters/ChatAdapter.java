package com.danielsela.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danielsela.myapplication.Interfaces.OnChatButtonClickListener;
import com.danielsela.myapplication.Models.Chat;
import com.danielsela.myapplication.Models.Person;
import com.danielsela.myapplication.R;
import com.danielsela.myapplication.Utiles.ImageLoader;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder>{


    private Context context;
    private ArrayList<Chat> chatList;
    private OnChatButtonClickListener onChatButtonClickListener;



    public ChatAdapter(Context context, ArrayList<Chat> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ChatHolder(view);
    }
    public void setOnChatButtonClickListener(OnChatButtonClickListener onChatButtonClickListener) {
        this.onChatButtonClickListener = onChatButtonClickListener;
    }
    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatHolder holder, int position) {
        Chat chat =  getItem(position);
        ImageLoader.getInstance().load(chat.getPicture(), holder.chat_person_poster);
        holder.chat_person_CARD_name.setText(chat.getName());
        holder.chat_open_CARD_btn.setOnClickListener(v -> {
            onChatButtonClickListener.onChatButtonClick(position);
        });

    }

    private Chat getItem(int position) {
        return chatList.get(position);
    }

    @Override
    public int getItemCount() {
        return chatList == null ? 0 : chatList.size();
    }

    public class ChatHolder extends RecyclerView.ViewHolder {
        private final ShapeableImageView chat_person_poster;
        private final MaterialTextView chat_person_CARD_name;
        private final ImageButton chat_open_CARD_btn;

        public ChatHolder(@NonNull View itemView) {
            super(itemView);
            chat_person_poster = itemView.findViewById(R.id.chat_person_poster);
            chat_person_CARD_name= itemView.findViewById(R.id.chat_person_CARD_name);
            chat_open_CARD_btn = itemView.findViewById(R.id.chat_open_CARD_btn);
        }
    }
}
