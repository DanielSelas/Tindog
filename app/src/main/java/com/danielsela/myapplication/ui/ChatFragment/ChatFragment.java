package com.danielsela.myapplication.ui.ChatFragment;


import static com.danielsela.myapplication.Utiles.Const.CHAT_NODE;
import static com.danielsela.myapplication.Utiles.Const.PERSONS_NODE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danielsela.myapplication.Adapters.ChatAdapter;
import com.danielsela.myapplication.Adapters.PersonAdapter;
import com.danielsela.myapplication.Interfaces.ChatCallback;
import com.danielsela.myapplication.Interfaces.OnChatButtonClickListener;
import com.danielsela.myapplication.Models.Chat;
import com.danielsela.myapplication.Models.Person;
import com.danielsela.myapplication.R;
import com.danielsela.myapplication.Utiles.DataManager;
import com.danielsela.myapplication.databinding.FragmentChatBinding;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;


public class ChatFragment extends Fragment {
    private FragmentChatBinding binding;
    private ChatAdapter chatAdapter;
    private RecyclerView chatRecycler;
    private ArrayList<Chat> chatList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        chatRecycler = view.findViewById(R.id.main_LST_chat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        chatRecycler.setLayoutManager(linearLayoutManager);
        loadChatData();
        return view;
    }

    private void loadChatData() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(CHAT_NODE);
        databaseReference.get().addOnSuccessListener(dataSnapshot -> {
            chatList = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Chat chat = snapshot.getValue(Chat.class);
                chatList.add(chat);
            }
            setAdapter(chatList);
        });
    }



    private void setAdapter( ArrayList<Chat> chatList){
        chatAdapter =new ChatAdapter(requireContext(), chatList);
        chatAdapter.setOnChatButtonClickListener(new OnChatButtonClickListener() {
            @Override
            public void onChatButtonClick(int pos) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_content);
                navController.navigate(R.id.nav_one_chat);
            }
        });
        chatRecycler.setAdapter(chatAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
