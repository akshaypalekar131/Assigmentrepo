package com.telstraassignmentapp.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.telstraassignmentapp.databinding.CountryListItemBinding;
import com.telstraassignmentapp.model.CountryDetails;
import java.util.List;


public class CountryListRecyclerAdapter extends RecyclerView.Adapter<CountryListRecyclerAdapter.CountryListViewHolder> {

    private List<CountryDetails> countryDetailsList;


    public void setCountryDetailsList(List<CountryDetails> countryDetailsList) {
        this.countryDetailsList = countryDetailsList;
        notifyDataSetChanged();
    }

    class CountryListViewHolder extends RecyclerView.ViewHolder {

        final CountryListItemBinding countryListItemBinding;

        CountryListViewHolder(CountryListItemBinding countryListItemBinding) {

            super(countryListItemBinding.getRoot());
            this.countryListItemBinding = countryListItemBinding;

        }

    }

    @NonNull
    @Override
    public CountryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CountryListItemBinding countryListItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.country_list_item,
                        parent, false);

        return new CountryListViewHolder(countryListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListViewHolder holder, int position) {
        holder.countryListItemBinding.setCountryDetails(countryDetailsList.get(position));
        holder.countryListItemBinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return countryDetailsList == null ? 0 : countryDetailsList.size();

    }
}
