package com.danielsela.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danielsela.myapplication.Models.Chat;
import com.danielsela.myapplication.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {
    private Context context;
    private ArrayList<Chat> messageList;

    public MessageAdapter(Context context, ArrayList<Chat> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        Chat chat = getItem(position);
        holder.one_line_message.setText((CharSequence) chat.getMessages());

    }

    private Chat getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public int getItemCount() {
        return messageList == null ? 0 : messageList.size();
    }

    public class MessageHolder extends RecyclerView.ViewHolder {
        private MaterialTextView one_line_message;
        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            one_line_message=itemView.findViewById(R.id.one_line_message);
        }
    }
}
