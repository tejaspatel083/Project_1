package com.example.project_1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotPasswordFragment extends Fragment {

    private EditText forgotpwd;
    private Button submitbtn;
    private NavController navController;
    private FirebaseAuth firebaseAuth;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Forgot Password?");
        forgotpwd = view.findViewById(R.id.ForgotEmail);
        submitbtn = view.findViewById(R.id.ForgotSubmitBtn);


        navController = Navigation.findNavController(getActivity(),R.id.Host_Fragment);

        firebaseAuth = FirebaseAuth.getInstance();

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = forgotpwd.getText().toString().trim();


                if(email.length() == 0)
                {
                    Toast toast = Toast.makeText(getActivity(),"Please Enter Your Email",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                    toast.show();
                }
                else
                {


                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {

                                Toast toast = Toast.makeText(getActivity(),"Password Reset Email sent",Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                                toast.show();
                                navController.navigate(R.id.action_forgotPasswordFragment_to_loginFragment);

                            }
                            else
                            {
                                Toast toast = Toast.makeText(getActivity(),"Enter Registered Email", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL,0,0);
                                toast.show();

                            }
                        }
                    });

                }

            }
        });

    }
}
