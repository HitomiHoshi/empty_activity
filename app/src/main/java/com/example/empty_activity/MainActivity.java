package com.example.empty_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.http_text);
        Button buttonGet = findViewById(R.id.button_http_get);
        Button buttonPost = findViewById(R.id.button_http_post);
        Button buttonPut = findViewById(R.id.button_http_put);
        Button buttonDelete = findViewById(R.id.button_http_delete);

        mQueue = Volley.newRequestQueue(this);

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(
                new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            String fcmToken = task.getResult().getToken();
                            Log.e("fcmToken", fcmToken);
                        }else {
                            Log.e("fcmError", task.getException().getMessage());
                        }


                    }
                }
        );
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHttpJson();
            }
        });

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postHttpJson();
            }
        });

        buttonPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putHttpJson();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteHttpJson();
            }
        });
    }

    public void deleteHttpJson(){
        String url = "https://jsonplaceholder.typicode.com/albums/1";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("response",String.valueOf(response));
                mTextViewResult.setText("Delete : " + String.valueOf(response));
//                try {
//                    int userId = response.getInt("userId");
//                    int id = response.getInt("id");
//                    String title = response.getString("title");
//                    Log.i("JSON",title);
//                    mTextViewResult.setText("Get : " + String.valueOf(userId) + " " + String.valueOf(id) + " " + title);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("JSON","Error Delete");
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        mQueue.add(request);
    }

    public void putHttpJson(){
        String url = "https://jsonplaceholder.typicode.com/albums/1";

        JSONObject js = new JSONObject();
        try {
            js.put("title", "new album");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("js",String.valueOf(js));

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("response",String.valueOf(response));
                try {
                    int id = response.getInt("id");
                    String title = response.getString("title");
                    mTextViewResult.setText("Put : " + String.valueOf(id) + " " + title);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("JSON","Error Put");
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        mQueue.add(request);
    }

    public void postHttpJson(){
        String url = "https://jsonplaceholder.typicode.com/albums";

        JSONObject js = new JSONObject();
        try {
            js.put("title", "new album");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("js",String.valueOf(js));

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("response",String.valueOf(response));
                try {
                    int id = response.getInt("id");
                    String title = response.getString("title");
                    mTextViewResult.setText("Post : " + String.valueOf(id) + " " + title);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("JSON","Error Post");
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        mQueue.add(request);
    }

    public void getHttpJson(){
        String url = "https://jsonplaceholder.typicode.com/albums/1";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("response",String.valueOf(response));
                try {
                    int userId = response.getInt("userId");
                    int id = response.getInt("id");
                    String title = response.getString("title");
                    Log.i("JSON",title);
                    mTextViewResult.setText("Get : " + String.valueOf(userId) + " " + String.valueOf(id) + " " + title);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("JSON","Error Get");
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        //        // Request a string response from the provided URL.
//        StringRequest request = new StringRequest(Request.Method.GET,
//                url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        mTextViewResult.setText("Response is: "+ response.substring(0,500));
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                        mTextViewResult.setText("That didn't work!");
//                    }
//                }){
//
//                @Override
//                public Map<String, String> getHeaders() {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("Content-Type", "application/json; charset=UTF-8");
//                params.put("x-access-token", "token");
//
//                return params;
//            }
//        };

        mQueue.add(request);
    }
}