package com.example.project_1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class ProfileFragment extends Fragment {

    private TextView profileName,profileEmail,profileBirthdate,profileCity,profileGender;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore fireStore;
    private FirebaseUser currentUser;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Profile");

        profileName = view.findViewById(R.id.ProfileName);
        profileEmail = view.findViewById(R.id.ProfileEmail);
        profileBirthdate = view.findViewById(R.id.ProfileBirthdate);
        profileCity = view.findViewById(R.id.ProfileCity);
        profileGender = view.findViewById(R.id.ProfilGender);

        firebaseAuth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();

        getData();


    }

    private void getData() {


        currentUser = firebaseAuth.getCurrentUser();

        DocumentReference docRef = fireStore.collection("User Profile Information").document(currentUser.getUid());

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists())
                {
                    String resultBirthdate = documentSnapshot.getString("birthdate");
                    String resultCity = documentSnapshot.getString("city");
                    String resultEmail = documentSnapshot.getString("email");
                    String resultGender = documentSnapshot.getString("gender");
                    String resultName = documentSnapshot.getString("name");

                    profileBirthdate.setText(resultBirthdate);
                    profileCity.setText(resultCity);
                    profileEmail.setText(resultEmail);
                    profileGender.setText(resultGender);
                    profileName.setText(resultName);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getActivity(), "Something Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
