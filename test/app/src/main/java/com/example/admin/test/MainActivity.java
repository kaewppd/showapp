package com.example.admin.test;


import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {

    String idCustStr;
    TextView name_cus, lasname_cus, id_cus;
//    private MySQLConnect mySQLConnect;
    private List<String> items;
    private ArrayAdapter<String> adt;
    private RecyclerView rv;
    MyAdapter adapter;
    List<Model> register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_cus = findViewById(R.id.name_cus);
        lasname_cus = findViewById(R.id.lastname_cus);
        id_cus = findViewById(R.id.id_cus);


        rv = findViewById(R.id.recyclerview2);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        register = new ArrayList<>();

        loaddata();

        return;
    }

    public void loaddata(){
        String url = "http://192.168.2.43/spa/test22.php";

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
//                Toast.makeText(main, list.get(0), Toast.LENGTH_LONG).show();
                MyAdapter adapter = new MyAdapter(getApplicationContext(), register);
                rv.setAdapter(adapter);
                adapter.setOnItemClickListener(MainActivity.this);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//            Toast.makeText(main, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        requestQueue.add(stringRequest);


    }

    public void showJSON(String response){

        String id_application = "";
        String name_cus = "";
        String lastname_cus = "";
        try{

            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i = 0; i < result.length(); i++){
                JSONObject collectData = result.getJSONObject(i);

                id_application = collectData.getString("id_application");
                name_cus = collectData.getString("name_cus");
                lastname_cus = collectData.getString("lastname_cus");

                Model model = new Model(id_application,name_cus,lastname_cus);
                register.add(model);

            }

            //MyAdapter adapter = new MyAdapter(getApplicationContext(), register);
            //rv.setAdapter(adapter);
            //adapter.setOnItemClickListener(MainActivity.this);

        }catch(JSONException ex){ ex.printStackTrace(); }
    }

    @Override
    public void OnItemClick(int position) {
        Model clickitem = register.get(position);
        idCustStr = clickitem.getId_cus();
        //showdetail showdetail_1 = new showdetail();
        Intent intent1 = new Intent(MainActivity.this, DetailActivity.class);
        intent1.putExtra("id_app" , idCustStr);
    }
}
