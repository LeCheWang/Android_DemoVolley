package com.example.exam;

import static com.example.exam.MainActivity.DOMAIN;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    EditText edtSearch;
    Button btnSearch;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        anhxa();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLySearch();
            }
        });

    }

    private void xuLySearch() {
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity2.this);

        Response.Listener<String> listener= new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity2.this, response, Toast.LENGTH_SHORT).show();

                try {
                    JSONArray jsonArray= new JSONArray(response);
                    List<HangHoa> list= new ArrayList<>();
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject json= jsonArray.getJSONObject(i);
                        String ma= json.getString("ma");
                        String ten= json.getString("ten");
                        String gia= json.getString("gia");
                        list.add(new HangHoa(ma, ten, gia));
                        HangHoaAdapter adapter= new HangHoaAdapter(list, MainActivity2.this, R.layout.layout);
                        lv.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        };

        Response.ErrorListener errorListener= new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        Uri.Builder builder= Uri.parse(DOMAIN + "gethanghoa.php").buildUpon();
        builder.appendQueryParameter("loaihanghoa", edtSearch.getText().toString().trim());

        String url= builder.build().toString();

        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, listener, errorListener);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        requestQueue.add(stringRequest);

    }

    private void anhxa() {
        edtSearch= findViewById(R.id.edtSearch);
        btnSearch= findViewById(R.id.btnSearch);
        lv= findViewById(R.id.lv);
    }
}