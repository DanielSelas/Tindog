package com.danielsela.myapplication.ui.ProfileFragment;

import static com.danielsela.myapplication.Utiles.Const.USER_NODE;
import static com.danielsela.myapplication.Utiles.Const.USER_PICTURE;

import android.os.Bundle;
import android.os.UserManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.danielsela.myapplication.Models.User;
import com.danielsela.myapplication.Utiles.ImageLoader;
import com.danielsela.myapplication.databinding.FragmentProfileBinding;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private EditText name, dogName, dogBreed, playStyle, energyLevel;

    private ShapeableImageView user_profile_picture;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initUser();
        setUserFromDataBase();
        final TextView textView = binding.userLBL;
        return root;
    }

    private void initUser() {
        this.name = this.binding.userLBLName;
        this.dogName = this.binding.dogLBLName;
        this.dogBreed = this.binding.dogLBLBread;
        this.playStyle = this.binding.dogLBLPlaystyle;
        this.energyLevel = this.binding.dogLBLEnergy;
        this.user_profile_picture=this.binding.userProfilePicture;

        this.name.setEnabled(false);
        this.dogName.setEnabled(false);
        this.dogBreed.setEnabled(false);
        this.playStyle.setEnabled(false);

    }


    private void setUserFromDataBase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(USER_NODE).child(USER_NODE);
        databaseReference.get().addOnSuccessListener(dataSnapshot -> {
            User user = dataSnapshot.getValue(User.class);
            if (user != null) {
                name.setText(user.getName());
                dogName.setText(user.getDogName());
                dogBreed.setText(user.getDogBreed());
                playStyle.setText(user.getPlayStyle());
                energyLevel.setText(String.valueOf(user.getEnergyLevel()));
                ImageLoader.getInstance().load(USER_PICTURE, user_profile_picture);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}