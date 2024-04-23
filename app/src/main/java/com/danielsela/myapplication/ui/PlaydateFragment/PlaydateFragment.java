package com.danielsela.myapplication.ui.PlaydateFragment;

import static com.danielsela.myapplication.Utiles.Const.DOGS_NODE;
import static com.danielsela.myapplication.Utiles.Const.PERSONS_NODE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danielsela.myapplication.Adapters.DogAdapter;
import com.danielsela.myapplication.Interfaces.DogCallback;
import com.danielsela.myapplication.Interfaces.OnChatButtonClickListener;
import com.danielsela.myapplication.Models.Dog;
import com.danielsela.myapplication.R;
import com.danielsela.myapplication.databinding.FragmentPlaydateBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PlaydateFragment extends Fragment implements DogCallback, OnChatButtonClickListener {

    private FragmentPlaydateBinding binding;
    private  ArrayList<Dog> dogsList;
    private DogAdapter dogAdapter;
    private RecyclerView dogRecycler;
    private final DogCallback dogCallback = this;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playdate, container, false);
        dogRecycler = view.findViewById(R.id.main_LST_dog);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        dogRecycler.setLayoutManager(linearLayoutManager);

        loadDog();

        return view;
    }

    private void loadDog() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(DOGS_NODE);
        databaseReference.get().addOnSuccessListener(dataSnapshot -> {
            dogsList = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Dog dog = snapshot.getValue(Dog.class);
                dogsList.add(dog);
            }
            setAdapter(dogsList);
        });
    }

    private void setAdapter(ArrayList<Dog> dogsList) {
        dogAdapter =new DogAdapter(requireContext(), dogsList);
        dogAdapter.setDogCallback(dogCallback);
        dogAdapter.setOnChatButtonClickListener(new OnChatButtonClickListener() {
            @Override
            public void onChatButtonClick(int pos) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_content);
                navController.navigate(R.id.nav_one_chat);
            }
        });
        dogRecycler.setAdapter(dogAdapter);
    }

    @Override
    public void favoriteButtonClickedDog(Dog dog, int pos) {
        dog.setFavorite(!dog.isFavorite());
        dogAdapter.notifyItemChanged(pos);
    }

    @Override
    public void onChatButtonClick(int pos) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_playdate);
        navController.navigate(R.id.nav_one_chat);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}