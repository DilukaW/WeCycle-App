package com.example.wecycle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wecycle.model.Student;


public class ProfileFrag extends Fragment {

   CardView edtProfile,support,logout;
   TextView txtUNname;

   Student student;

    public ProfileFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);

        edtProfile=view.findViewById(R.id.edtProfile);
        support=view.findViewById(R.id.support);
        logout=view.findViewById(R.id.logout);

        txtUNname = view.findViewById(R.id.txtUname);

        student = SharedPrefManager.getInstance(getActivity().getApplicationContext()).getUser();
        txtUNname.setText(student.getFirstName() + " " + student.getLastName());

        edtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment profileUpdate = new ProfileUpdateFrag();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, profileUpdate);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment supportFrag = new SupportFrag();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, supportFrag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Log Out");
                builder.setMessage("Are you sure you want to Log Out");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPrefManager.getInstance(getActivity().getApplicationContext()).logout();
                        getActivity().finishAffinity();
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });



        return view;
    }
}