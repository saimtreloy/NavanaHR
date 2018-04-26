package saim.com.navanahr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import saim.com.navanahr.Utils.ApiURL;
import saim.com.navanahr.Utils.MySingleton;
import saim.com.navanahr.Utils.SharedPrefDatabase;

public class Login extends AppCompatActivity {

    ProgressDialog progressDialog;
    EditText inputEmail, inputPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait check your login information");
        progressDialog.setCanceledOnTouchOutside(false);

        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputPassword = (EditText) findViewById(R.id.inputPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        cuttonClicked();
    }


    private void cuttonClicked() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(inputEmail.getText().toString()) && !TextUtils.isEmpty(inputEmail.getText().toString())) {
                    SaveUserLogin(inputEmail.getText().toString(), inputPassword.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Input field can not be empty!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public void SaveUserLogin(final String email, final String password) {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiURL.login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            if (code.equals("success")) {

                                String id = jsonObject.getString("id");
                                String name = jsonObject.getString("name");
                                String designation = jsonObject.getString("designation");
                                String department = jsonObject.getString("department");
                                String project_name = jsonObject.getString("project_name");


                                new SharedPrefDatabase(getApplicationContext()).StoreID(id);
                                new SharedPrefDatabase(getApplicationContext()).StoreNAME(name);
                                new SharedPrefDatabase(getApplicationContext()).StoreDESIGNATION(designation);
                                new SharedPrefDatabase(getApplicationContext()).StoreDEPARTMENT(department);
                                new SharedPrefDatabase(getApplicationContext()).StorePROJECT_NAME(project_name);

                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();

                            } else {
                                String message = jsonObject.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.d("HDHD ", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_email", email);
                params.put("user_pass", password);

                return params;
            }
        };
        stringRequest.setShouldCache(false);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}