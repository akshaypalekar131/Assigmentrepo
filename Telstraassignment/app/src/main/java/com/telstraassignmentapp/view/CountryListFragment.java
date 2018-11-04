package com.telstraassignmentapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.telstraassignmentapp.R;
import com.telstraassignmentapp.databinding.FragmentCountryDetailsBinding;
import com.telstraassignmentapp.model.ApiResponse;
import com.telstraassignmentapp.model.CountryDetails;
import com.telstraassignmentapp.utils.NetworkConnectionCheck;
import com.telstraassignmentapp.viewmodel.CountryListViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CountryListFragment extends Fragment {

    public static final String TAG = "CountryListFragment";

    private CountryListRecyclerAdapter countryListRecyclerAdapter;
    private FragmentCountryDetailsBinding fragmentCountryDetailsBinding;

    private CountryListViewModel countryListViewModel;
    private List<CountryDetails> countryDetailsList;
    private List<CountryDetails> refreshList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getContext(), "inconcreateview", Toast.LENGTH_LONG).show();
        fragmentCountryDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_details, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        fragmentCountryDetailsBinding.rvCountry.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(fragmentCountryDetailsBinding.rvCountry.getContext(), LinearLayoutManager.VERTICAL);
        fragmentCountryDetailsBinding.rvCountry.addItemDecoration(dividerItemDecoration);
        countryListRecyclerAdapter = new CountryListRecyclerAdapter();
        fragmentCountryDetailsBinding.rvCountry.setAdapter(countryListRecyclerAdapter);
        // binding.setIsLoading(true);

        return fragmentCountryDetailsBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getContext(), "onActivityCreated", Toast.LENGTH_LONG).show();
        countryListViewModel = ViewModelProviders.of(this).get(CountryListViewModel.class);

        observeViewModel(countryListViewModel, false);

        setListeners();


    }

    private void observeViewModel(CountryListViewModel countryListViewModel, final boolean isContentRefresh) {
        if (!NetworkConnectionCheck.getNetworkConnectionCheck().isNetworkAvailable(getActivity())) {
            fragmentCountryDetailsBinding.txtNoInternetConnection.setVisibility(View.VISIBLE);
            fragmentCountryDetailsBinding.txtNoInternetConnection.setText(getActivity().getResources().getString(R.string.no_internet_connection));
            return;
        }
        countryListViewModel.getApiResponseLiveData().observe(this, new Observer<ApiResponse>() {

            @Override
            public void onChanged(ApiResponse apiResponse) {
                Toast.makeText(getContext(), "onChanged" + apiResponse.getCountryDetailsList().size(), Toast.LENGTH_LONG).show();
                if (!apiResponse.getCountryDetailsList().isEmpty() && !isContentRefresh) {
                    Toast.makeText(getContext(), "inresponse", Toast.LENGTH_LONG).show();
                    fragmentCountryDetailsBinding.toolbarLayout.toolbarTitle.setText(apiResponse.getTitle());
                    fragmentCountryDetailsBinding.rvCountry.setVisibility(View.VISIBLE);
                    fragmentCountryDetailsBinding.toolbarLayout.imgNotificationBell.setVisibility(View.VISIBLE);
                    fragmentCountryDetailsBinding.txtNoInternetConnection.setVisibility(View.GONE);
                    countryDetailsList = apiResponse.getCountryDetailsList();
                    countryListRecyclerAdapter.setCountryDetailsList(countryDetailsList);
                    refreshList = countryDetailsList;
                } else {
                    Toast.makeText(getContext(), "in else for refresh"+refreshList.size(), Toast.LENGTH_LONG).show();
                    fragmentCountryDetailsBinding.toolbarLayout.toolbarTitle.setText(apiResponse.getTitle());
                    refreshList.addAll(apiResponse.getCountryDetailsList());
                    fragmentCountryDetailsBinding.rvCountry.setVisibility(View.VISIBLE);
                    fragmentCountryDetailsBinding.toolbarLayout.imgNotificationBell.setVisibility(View.VISIBLE);
                    fragmentCountryDetailsBinding.txtNoInternetConnection.setVisibility(View.GONE);
                    countryListRecyclerAdapter.setCountryDetailsList(refreshList);
                }

            }
        });
    }

    private void setListeners() {

        fragmentCountryDetailsBinding.toolbarLayout.imgNotificationBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentCountryDetailsBinding.rvCountry.setVisibility(View.GONE);
                observeViewModel(countryListViewModel, true);
            }
        });
    }

}
