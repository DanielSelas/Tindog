package com.danielsela.myapplication.ui.LoginFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.danielsela.myapplication.R;
import com.danielsela.myapplication.Utiles.Alerter;
import com.danielsela.myapplication.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    private FirebaseAuth fbAuth;
    private EditText email_ED, password_ED;
    private ImageButton login_btm;
    private FragmentLoginBinding binding;

    public LoginFragment(){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        fbAuth = FirebaseAuth.getInstance();
        this.email_ED= this.binding.loginLBLEmail;
        this.password_ED = this.binding.loginLBLPassword;
        this.login_btm=this.binding.loginBTNMain;
        login_btm.setOnClickListener(e->login());
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Sign out the user from Firebase Authentication
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public void onStop() {
        super.onStop();
        // Sign out the user from Firebase Authentication
        FirebaseAuth.getInstance().signOut();
    }

    private void login() {
        String email = this.email_ED.getText().toString().trim();
        String password = this.password_ED.getText().toString().trim();

        if(email.isEmpty()||password.isEmpty()){
            Alerter.getInstance().toast("Fill all fields",200);
        }else{
            fbAuth.signInWithEmailAndPassword(email,password).
                    addOnSuccessListener(authResult -> {
                        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_content);
                        navController.navigate(R.id.nav_profile);
                    }).addOnFailureListener(e -> Alerter.getInstance().toast("Incorrect info",500));
        }
    }
}
