package com.example.wecycle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SupportFrag extends Fragment {

    Button btnSend;
    TextInputEditText edtSubject, edtMsg;


    public SupportFrag() {
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
        View view= inflater.inflate(R.layout.fragment_support, container, false);

        btnSend=view.findViewById(R.id.btnSend);
        edtSubject=view.findViewById(R.id.edtSubject);
        edtMsg=view.findViewById(R.id.edtMsg);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });

        return view;
    }

    private void sendData() {

        String subject=edtSubject.getText().toString().trim();
        String msg=edtMsg.getText().toString().trim();
        String to="wecycle.admin@gmail.com";


        if (!subject.equals("") && !msg.equals("")){




            Intent mIntent=new Intent(Intent.ACTION_VIEW,Uri.parse("mailto:"+to.toString()));

            mIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
            mIntent.putExtra(Intent.EXTRA_TEXT,msg);

            try {
                startActivity(Intent.createChooser(mIntent,"Choose Email Client"));


            }catch (Exception e){
                Toast.makeText(getActivity(),"Required App is not installed",Toast.LENGTH_SHORT).show();
            }

        }
        else{
            if (subject.isEmpty()){

                edtSubject.setError("Required");
                edtSubject.requestFocus();
                return;
            }
            if (msg.isEmpty()){
                edtMsg.setError("Required");
                edtMsg.requestFocus();
                return;
            }
        }
    }
}