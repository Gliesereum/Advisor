package com.gliesereum.advisorapp.adapter;

import androidx.databinding.ViewDataBinding;

import com.gliesereum.advisorapp.R;
import com.gliesereum.advisorapp.adapter.customadapterrecycleview.AdapterRecyclerView;
import com.gliesereum.advisorapp.adapter.customadapterrecycleview.viewHolder.ItemViewHolder;
import com.gliesereum.advisorapp.databinding.ArtbondItemBinding;
import com.gliesereum.advisorapp.network.json.artbond.ArtBondResponse;
import com.squareup.picasso.Picasso;

public class ChooseAtrBondAdapter extends AdapterRecyclerView<ArtBondResponse> {

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.artbond_item;
    }

    @Override
    public int onProgressLayout() {
        return R.layout.progress_item;
    }

    @Override
    public void onBindView(ViewDataBinding binding, ItemViewHolder viewDataBinding, int position, int viewType, ArtBondResponse element) {
        ArtbondItemBinding itemBinding = (ArtbondItemBinding) binding;
        if (!element.getImages().isEmpty()) {
            Picasso.get().load(element.getImages().get(0).getUrl()).centerCrop().fit().into(itemBinding.artBondImageView);
        }
        if (element.getName() != null) {
            itemBinding.artBondName.setText(element.getName());
        }

    }


}
