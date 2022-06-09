package com.example.wecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {

    String usrCheckMsg = "";

    Button btnSignIn;
    TextView txtError;
    EditText edtEmail;
    EditText edtPwd;

    ProgressDialog progressDialog;

    RequestQueue queue; // Volley RequestQueue
    StringRequest stringRequest; // Volley StringRequest
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If the user is already logged in, this will redirect the user to the Dashboard
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Navigation.class));
        }

        queue = Volley.newRequestQueue(this); // Instantiate the RequestQueue

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");


        txtError = findViewById(R.id.txtError);
        btnSignIn = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPwd = findViewById(R.id.edtPwd);

    }

    private void validateTxt() {

        String email=edtEmail.getText().toString().trim();
        String pwd=edtPwd.getText().toString().trim();

        if (email.isEmpty()){
            edtEmail.setError("Email Required");
            edtEmail.requestFocus();
            return;
        }
        if (pwd.isEmpty()){
            edtPwd.setError("Password Required");
            edtPwd.requestFocus();
            return;
        }
    }

    public void login(View view) {

        validateTxt();
        String email=edtEmail.getText().toString().trim();
        String pwd=edtPwd.getText().toString().trim();

        if (!(email.isEmpty() && pwd.isEmpty())) {
            userLogin(email, pwd); // User login check

            progressDialog.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    progressDialog.dismiss();
                    if (usrCheckMsg.equals("Login Successful!")) {
                        // Session management and redirection
                        // Storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(student);
                        // Direct to the Dashboard activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), Navigation.class));
                    } else {
                        txtError.setVisibility(View.VISIBLE);
                        txtError.setText("Invalid username or password!");
                    }
                }
            }, 3000);
        }
    }

    private void userLogin(String email, String password) {

        stringRequest = new StringRequest(Request.Method.POST, URLs.login,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            usrCheckMsg = jsonObject.getString("message");

                            // Creating a new user object
                            if (jsonObject.getString("message").equals("Login Successful!")) {
                                student = new Student(
                                        jsonObject.getJSONObject("user").getString("stu_id"),
                                        jsonObject.getJSONObject("user").getString("nic"),
                                        jsonObject.getJSONObject("user").getString("first_name"),
                                        jsonObject.getJSONObject("user").getString("last_name"),
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        queue.add(stringRequest);
    }

}

