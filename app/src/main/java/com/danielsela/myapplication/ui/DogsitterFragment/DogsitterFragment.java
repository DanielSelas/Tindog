package com.danielsela.myapplication.ui.DogsitterFragment;

import static com.danielsela.myapplication.Utiles.Const.PERSONS_NODE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danielsela.myapplication.Adapters.PersonAdapter;
import com.danielsela.myapplication.Interfaces.OnChatButtonClickListener;
import com.danielsela.myapplication.Interfaces.PersonCallback;
import com.danielsela.myapplication.Models.Person;

import com.danielsela.myapplication.R;
import com.danielsela.myapplication.databinding.FragmentDogsitterBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DogsitterFragment extends Fragment implements PersonCallback {

    private FragmentDogsitterBinding binding;
    private PersonAdapter personAdapter;
    private RecyclerView personRecycler;
    private ArrayList<Person> personList;
    private PersonCallback personCallback = this::favoriteButtonClicked;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dogsitter, container, false);
        personRecycler = view.findViewById(R.id.main_LST_person);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        personRecycler.setLayoutManager(linearLayoutManager);

        loadPersonData();

        return view;
    }



    private void loadPersonData() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(PERSONS_NODE);
        databaseReference.get().addOnSuccessListener(dataSnapshot -> {
            personList = new ArrayList<>();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Person person = snapshot.getValue(Person.class);
                personList.add(person);
            }
            setAdapter(personList);
        });
    }


    private void setAdapter( ArrayList<Person> personList){
        personAdapter =new PersonAdapter(requireContext(), personList);
        personAdapter.setPersonCallback(personCallback);
        personAdapter.setOnChatButtonClickListener(new OnChatButtonClickListener() {
            @Override
            public void onChatButtonClick(int pos) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_content);
                navController.navigate(R.id.nav_one_chat);
            }
        });
        personRecycler.setAdapter(personAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void favoriteButtonClicked(Person person, int pos) {
        person.setFavorite(!person.isFavorite());
        personAdapter.notifyItemChanged(pos);
    }

}
