package com.elhefny.askdoctor2.AllFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.elhefny.askdoctor2.Covid_19_API.CountriesData;
import com.elhefny.askdoctor2.Covid_19_API.CountriesRecyclerAdapter;
import com.elhefny.askdoctor2.Covid_19_API.Country;
import com.elhefny.askdoctor2.Covid_19_API.Covid_19_Client;
import com.elhefny.askdoctor2.Covid_19_API.Covid_19_Interface;
import com.elhefny.askdoctor2.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Covid_19_NewsFragment extends Fragment {
    RecyclerView rv;
    CountriesRecyclerAdapter adapter;
    ArrayList<Country> countries;


    public Covid_19_NewsFragment() {
        // Required empty public constructor
    }

    public static Covid_19_NewsFragment newInstance() {
        Covid_19_NewsFragment fragment = new Covid_19_NewsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getCountries();
        return inflater.inflate(R.layout.fragment_new_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countries = new ArrayList<>();
        adapter = new CountriesRecyclerAdapter(getContext(), new CountriesRecyclerAdapter.GetDetails() {
            @Override
            public void getDetails(Country country) {
                Log.e("TAG", "getDetails: " + country.toString());
                Covid_19_Details_Fragment details_fragment =
                        Covid_19_Details_Fragment.newInstance(country.getNewConfirmed(), country.getNewDeaths(), country.getNewRecovered());
                details_fragment.show(getFragmentManager(), null);
            }
        });
        rv = view.findViewById(R.id.fragment_new_news_recycler);
        rv.setAdapter(adapter);
        adapter.setCountries(countries);
        adapter.notifyDataSetChanged();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);
    }

    private void getCountries() {
        Wait_Fragment wait_fragment = Wait_Fragment.newInstance("Data Is Loading ...");
        wait_fragment.show(getFragmentManager(), null);
        Covid_19_Interface api = Covid_19_Client.getService();
        api.getData().enqueue(new Callback<CountriesData>() {
            @Override
            public void onResponse(Call<CountriesData> call, Response<CountriesData> response) {
                if (response.isSuccessful()) {
                    countries.addAll(response.body().getCountries());
                    adapter.notifyDataSetChanged();
                    wait_fragment.dismiss();
                } else {
                    Toast.makeText(getContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    wait_fragment.dismiss();
                    Error_Fragment error_fragment = Error_Fragment.newInstance("Error Occurred");
                    error_fragment.show(getFragmentManager(), null);
                }
            }

            @Override
            public void onFailure(Call<CountriesData> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                wait_fragment.dismiss();
                Error_Fragment error_fragment = Error_Fragment.newInstance("Error Occurred");
                error_fragment.show(getFragmentManager(), null);
            }
        });
    }
}