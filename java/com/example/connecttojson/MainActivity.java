import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.example.connecttojson.ModelPelanggan;
import com.example.connecttojson.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterPelanggan adapter;  // Perbaiki typo di sini
    private List<ModelPelanggan> pelangganList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pelangganList = new ArrayList<>();
        adapter = new AdapterPelanggan(this, pelangganList);  // Perbaiki typo di sini
        recyclerView.setAdapter(adapter);

        fetchPelangganData();
    }

    private void fetchPelangganData() {
        String url = "http://192.168.43.85/belajar_api/costumer.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject pelangganObj = response.getJSONObject(i);
                                ModelPelanggan pelanggan = new ModelPelanggan(
                                        pelangganObj.getString("id"),
                                        pelangganObj.getString("nama"),
                                        pelangganObj.getString("alamat"),
                                        pelangganObj.getString("telepon")
                                );
                                pelangganList.add(pelanggan);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        queue.add(request);
    }
}
