package com.example.books_app.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.books_app.R;
import com.example.books_app.adapter.BestSellerAdapter;
import com.example.books_app.adapter.BookAdapter;
import com.example.books_app.databinding.FragmentHomeBinding;
import com.example.books_app.models.Book;
import com.example.books_app.utils.CheckConnection;
import com.example.books_app.utils.Server;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Context context;
    ArrayList<Book> arrBookBestSeller;
    ArrayList<Book> arrBookNewRelease;

    BestSellerAdapter bestSellerAdapter;
    BestSellerAdapter newReleaseAdapter;

    FragmentHomeBinding binding;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
        // Inflate the layout for this fragment
        context=getActivity();
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        addControls();

        if (CheckConnection.haveNetworkConnection(context)) {
            getDataBooks();
        } else {
            CheckConnection.showToast(context, "Bạn hãy kiểm tra lại kết nối");
        }

        getDataBooks();
        return binding.getRoot();
    }

    private void getDataBooks() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Server.apiListBook, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int length=response.getJSONArray("data").length();

                            if(response!=null){
                                for (int i=0;i<length;i++){
                                    JSONObject jsonObject = response.getJSONArray("data").getJSONObject(i);

                                    int Id = jsonObject.getInt("id");
                                    String Name = jsonObject.getString("name");
                                    Double Price = jsonObject.getDouble("price");
                                    String Img = jsonObject.getString("thumbnail_url");
                                    Integer rate=jsonObject.getInt("rating_average");

                                    arrBookBestSeller.add(new Book(Id, Name,"","",Img, Price,rate));

                                    bestSellerAdapter.notifyDataSetChanged();
                                }

                                for (int i=length-1;i>=0;i--){
                                    JSONObject jsonObject = response.getJSONArray("data").getJSONObject(i);

                                    int Id = jsonObject.getInt("id");
                                    String Name = jsonObject.getString("name");
                                    Double Price = jsonObject.getDouble("price");
                                    String Img = jsonObject.getString("thumbnail_url");
                                    Integer rate=jsonObject.getInt("rating_average");

                                    arrBookNewRelease.add(new Book(Id, Name,"","",Img, Price,rate));

                                    newReleaseAdapter.notifyDataSetChanged();
                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "JsonObjectRequest onErrorResponse: " + error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void addControls() {
        arrBookBestSeller=new ArrayList<>();
        arrBookNewRelease=new ArrayList<>();

        bestSellerAdapter=new BestSellerAdapter(context,arrBookBestSeller);
        binding.rvBestSeller.setHasFixedSize(true);

        binding.rvBestSeller.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        binding.rvBestSeller.setItemAnimator(new DefaultItemAnimator());
        binding.rvBestSeller.setAdapter(bestSellerAdapter);

        newReleaseAdapter=new BestSellerAdapter(context, arrBookNewRelease);
        binding.rvNewRelease.setHasFixedSize(true);

        binding.rvNewRelease.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        binding.rvNewRelease.setItemAnimator(new DefaultItemAnimator());
        binding.rvNewRelease.setAdapter(newReleaseAdapter);
    }

}