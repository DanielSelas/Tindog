package com.danielsela.myapplication.ui.ChatFragment;

import static com.danielsela.myapplication.Utiles.Const.CHAT_NODE;
import static com.danielsela.myapplication.Utiles.Const.USER_EMAIL;
import static com.danielsela.myapplication.Utiles.Const.USER_MESSAGE;
import static com.danielsela.myapplication.Utiles.Const.USER_NAME;
import static com.danielsela.myapplication.Utiles.Const.USER_NODE;
import static com.danielsela.myapplication.Utiles.Const.USER_PICTURE;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danielsela.myapplication.Adapters.ChatAdapter;
import com.danielsela.myapplication.Adapters.MessageAdapter;
import com.danielsela.myapplication.Adapters.PersonAdapter;
import com.danielsela.myapplication.Interfaces.OnChatButtonClickListener;
import com.danielsela.myapplication.Models.Chat;
import com.danielsela.myapplication.R;
import com.danielsela.myapplication.Utiles.ImageLoader;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ChattingFragment extends Fragment {

    private TextInputEditText message;
    private EditText messageBox;
    private MaterialButton sendButton;
    private MaterialTextView  username;
    private ShapeableImageView user_profile_picture;
    private ArrayList<Chat> messageList;
    private MessageAdapter messageAdapter;
    private RecyclerView messageRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chat, container, false);
//        setUserValues();

        messageRecycler = view.findViewById(R.id.message_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        messageRecycler.setLayoutManager(linearLayoutManager);
        messageBox = view.findViewById(R.id.message_box);
        sendButton = view.findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        return view;
    }


    private void loadChatData() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(CHAT_NODE).child(USER_MESSAGE);
        databaseReference.get().addOnSuccessListener(dataSnapshot -> {
            messageList = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Chat chat = snapshot.getValue(Chat.class);
                messageList.add(chat);
            }
            setAdapter(messageList);
        });
    }



    private void setAdapter( ArrayList<Chat> messageList){
        messageAdapter =new MessageAdapter(requireContext(), messageList);
        messageRecycler.setAdapter(messageAdapter);
    }


    private void setUserValues() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(CHAT_NODE).child(USER_NAME);
        databaseReference.get().addOnSuccessListener(dataSnapshot -> username.setText(dataSnapshot.getValue(String.class)));
        ImageLoader.getInstance().load(USER_PICTURE, user_profile_picture);


    }

    private void sendMessage() {
        String message = messageBox.getText().toString().trim();
        if (!TextUtils.isEmpty(message)) {
            DatabaseReference messagesRef = FirebaseDatabase.getInstance().getReference().child("messages");
            String messageId = messagesRef.push().getKey();

            Message newMessage = new Message();

            messagesRef.child(messageId).setValue(newMessage)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(requireContext(), "Message sent", Toast.LENGTH_SHORT).show();
                        messageBox.setText("");
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(requireContext(), "Failed to send message", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(requireContext(), "Please enter a message", Toast.LENGTH_SHORT).show();
        }
    }


}
