package com.example.footballmonitor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    List<Kilpailu> kilpailuLista;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.kilpailuLista);
        kilpailuLista = new ArrayList<>();
        jsonParse();
    }

    private void jsonParse() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.football-data.org/v2/competitions?areas=2072";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("competitions");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject footballObject = jsonArray.getJSONObject(i);
                                Kilpailu kilpailu = new Kilpailu();
                                kilpailu.setCompetition(footballObject.getString("name"));
                                kilpailuLista.add(kilpailu);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new Adapter(getApplicationContext(), kilpailuLista);
                        recycler.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

}