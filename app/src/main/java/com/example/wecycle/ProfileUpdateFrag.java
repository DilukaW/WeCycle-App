package com.example.wecycle;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wecycle.model.Student;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileUpdateFrag extends Fragment {

    String usrCheckMsg = "";

    private TextView txtProfStuID;
    private TextView txtProfNIC;
    private TextView txtProfFName;
    private TextView txtProfLName;
    private TextView txtProfGender;
    private TextView txtProfDOB;
    private TextView txtProfAddress;
    private TextView txtProfMobNum;
    private TextView txtProfEmail;

    ProgressDialog progressDialog;

    RequestQueue queue; // Volley RequestQueue
    StringRequest stringRequest; // Volley StringRequest
    Student student;


    public ProfileUpdateFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        queue = Volley.newRequestQueue(getActivity().getApplicationContext()); // Instantiate the RequestQueue

        student = SharedPrefManager.getInstance(getActivity().getApplicationContext()).getUser();
        fetchUser(student.getStuID());

        progressDialog = new ProgressDialog(getActivity().getApplicationContext());
        progressDialog.setMessage("Loading...");

        progressDialog.show(); // Show ProgressDialog

        // Update fields with fetched data
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();

                if (usrCheckMsg.equals("Request Successfully Completed!")) {
                    setDataToFields();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Something unexpected happened!", Toast.LENGTH_SHORT).show();
                }
            }
        }, 3000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile, container, false);

        txtProfStuID = view.findViewById(R.id.txtProfStuID);
        txtProfNIC = view.findViewById(R.id.txtProfNIC);
        txtProfFName = view.findViewById(R.id.txtProfFName);
        txtProfLName = view.findViewById(R.id.txtProfLName);
        txtProfGender = view.findViewById(R.id.txtProfGender);
        txtProfDOB = view.findViewById(R.id.txtProfDOB);
        txtProfAddress = view.findViewById(R.id.txtProfAddress);
        txtProfMobNum = view.findViewById(R.id.txtProfMobNum);
        txtProfEmail = view.findViewById(R.id.txtProfEmail);


        // Inflate the layout for this fragment
        return view;
    }

    // Fetch user details
    private void fetchUser(String userID) {

        stringRequest = new StringRequest(Request.Method.POST, URLs.getUsr,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            usrCheckMsg = jsonObject.getString("message");

                            // Creating a new user object
                            if (jsonObject.getString("message").equals("Request Successfully Completed!")) {
                                student = new Student(
                                        jsonObject.getJSONObject("user").getString("stu_id"),
                                        jsonObject.getJSONObject("user").getString("nic"),
                                        jsonObject.getJSONObject("user").getString("first_name"),
                                        jsonObject.getJSONObject("user").getString("last_name"),
                                        jsonObject.getJSONObject("user").getString("gender"),
                                        jsonObject.getJSONObject("user").getString("dob"),
                                        jsonObject.getJSONObject("user").getString("address"),
                                        jsonObject.getJSONObject("user").getString("mob_number"),
                                        jsonObject.getJSONObject("user").getString("email"),
                                        jsonObject.getJSONObject("user").getString("status")
                                );
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("stuID", userID);

                return params;
            }
        };
        queue.add(stringRequest);
    }

    // Update fields with fetched data
    private void setDataToFields() {
        txtProfStuID.setText(student.getStuID());
        txtProfNIC.setText(student.getNic());
        txtProfFName.setText(student.getFirstName());
        txtProfLName.setText(student.getLastName());
        txtProfGender.setText(student.getGender());
        txtProfDOB.setText(student.getDob());
        txtProfAddress.setText(student.getAddress());
        txtProfMobNum.setText("+94" + student.getMobNumber());
        txtProfEmail.setText(student.getEmail());
    }
}