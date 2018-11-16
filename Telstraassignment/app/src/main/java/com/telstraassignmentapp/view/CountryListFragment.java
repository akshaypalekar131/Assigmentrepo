package com.telstraassignmentapp.view;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.telstraassignmentapp.databinding.FragmentCountryDetailsBinding;
import com.telstraassignmentapp.model.ApiResponse;
import com.telstraassignmentapp.model.CountryDetails;
import com.telstraassignmentapp.utils.NetworkConnectionCheck;
import com.telstraassignmentapp.viewmodel.CountryListViewModel;
import java.util.List;
import com.telstraassignmentapp.R;

public class CountryListFragment extends Fragment {

    static final String TAG = "CountryListFragment";

    private CountryListRecyclerAdapter countryListRecyclerAdapter;
    private FragmentCountryDetailsBinding fragmentCountryDetailsBinding;

    private CountryListViewModel countryListViewModel;
    private List<CountryDetails> countryDetailsList;
    private List<CountryDetails> refreshList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        countryListViewModel = ViewModelProviders.of(this).get(CountryListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentCountryDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_details, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        fragmentCountryDetailsBinding.rvCountryDetails.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(fragmentCountryDetailsBinding.rvCountryDetails.getContext(), LinearLayoutManager.VERTICAL);
        fragmentCountryDetailsBinding.rvCountryDetails.addItemDecoration(dividerItemDecoration);
        countryListRecyclerAdapter = new CountryListRecyclerAdapter();
        fragmentCountryDetailsBinding.rvCountryDetails.setAdapter(countryListRecyclerAdapter);
        return fragmentCountryDetailsBinding.getRoot();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
                if (!apiResponse.getCountryDetailsList().isEmpty() && !isContentRefresh) {
                    fragmentCountryDetailsBinding.toolbarLayout.toolbarTitle.setText(apiResponse.getTitle());
                    fragmentCountryDetailsBinding.rvCountryDetails.setVisibility(View.VISIBLE);
                    fragmentCountryDetailsBinding.toolbarLayout.imgRefresh.setVisibility(View.VISIBLE);
                    fragmentCountryDetailsBinding.txtNoInternetConnection.setVisibility(View.GONE);
                    countryDetailsList = apiResponse.getCountryDetailsList();
                    countryListRecyclerAdapter.setCountryDetailsList(countryDetailsList);
                    refreshList = countryDetailsList;
                } else {
                    fragmentCountryDetailsBinding.toolbarLayout.toolbarTitle.setText(apiResponse.getTitle());
                    refreshList.addAll(apiResponse.getCountryDetailsList());
                    fragmentCountryDetailsBinding.rvCountryDetails.setVisibility(View.VISIBLE);
                    fragmentCountryDetailsBinding.toolbarLayout.imgRefresh.setVisibility(View.VISIBLE);
                    fragmentCountryDetailsBinding.txtNoInternetConnection.setVisibility(View.GONE);
                    countryListRecyclerAdapter.setCountryDetailsList(refreshList);
                }

            }
        });
    }

    private void setListeners() {

        fragmentCountryDetailsBinding.toolbarLayout.imgRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentCountryDetailsBinding.rvCountryDetails.setVisibility(View.GONE);
                observeViewModel(countryListViewModel, true);
            }
        });
    }
}
