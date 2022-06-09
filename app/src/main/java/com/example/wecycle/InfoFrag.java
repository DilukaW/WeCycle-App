package com.example.wecycle;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class InfoFrag extends Fragment {

    TextView txtTitle;

    public InfoFrag() {
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
        View view =inflater.inflate(R.layout.fragment_info, container, false);

        txtTitle=view.findViewById(R.id.txtTitle);
        txtTitle.setText("Your Cycle Id: "+getArguments().getString("title"));

        Button btnFind=view.findViewById(R.id.btnReserve);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Scan.class);
                startActivity(intent);
            }
        });

        return view;
    }
}