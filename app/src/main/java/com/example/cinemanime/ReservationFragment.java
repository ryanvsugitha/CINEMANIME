package com.example.cinemanime;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReservationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReservationFragment extends Fragment {

    View view;
    Button buy_btn;
    int i = 0;
    String url;
    Spinner anime_name;
    TextView counter, minus_ticket, plus_ticket, confirmation;

    String[] anime;
    String selectedAnime = "Select Anime";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReservationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReservationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReservationFragment newInstance(String param1, String param2) {
        ReservationFragment fragment = new ReservationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reservation, container, false);

        anime_name = (Spinner) view.findViewById(R.id.anime_name);
        anime_name.setOnItemSelectedListener(new animeSpinner());
        buy_btn = view.findViewById(R.id.buy_btn);
        counter = view.findViewById(R.id.counter);
        minus_ticket = view.findViewById(R.id.minus_ticket);
        plus_ticket = view.findViewById(R.id.plus_ticket);
        confirmation = view.findViewById(R.id.confirmation);

        counter.setText(Integer.toString(i));

        url = "https://api.jikan.moe/v4/seasons/now";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            anime = new String[jsonArray.length()+1];
                            anime[0] = "Select Anime";

                            for(int i = 1; i < jsonArray.length()+1; i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i-1);

                                String animeName = jsonObject.getString("title");;

                                anime[i] = animeName;
                            }

                            ArrayAdapter aa = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, anime);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            anime_name.setAdapter(aa);

                        }catch(Exception e){

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        Volley.newRequestQueue(view.getContext()).add(request);

        minus_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i != 1){
                    i -= 1;
                    counter.setText(Integer.toString(i));
                    changeString();
                } else {
                    Toast.makeText(view.getContext(), "Minimum 1 Ticket(s)!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        plus_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i != 20){
                    i += 1;
                    counter.setText(Integer.toString(i));
                    changeString();
                } else {
                    Toast.makeText(view.getContext(), "Maximum 20 Ticket(s)!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedAnime.equals("Select Anime")){
                    Toast.makeText(view.getContext(), "Please select a title!", Toast.LENGTH_SHORT).show();
                } else if (i == 0){
                    Toast.makeText(view.getContext(), "Can't buy 0 ticket", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "Successfully buy " + Integer.toString(i) + " Ticket(s)!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    class animeSpinner implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            selectedAnime = parent.getItemAtPosition(pos).toString();
        }
        @Override
        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }

    void changeString(){
        confirmation.setText("Buy " + selectedAnime + " for " + Integer.toString(i) + " ticket(s)!");
    }
}