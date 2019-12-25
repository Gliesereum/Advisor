package com.gliesereum.advisorapp.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.gliesereum.advisorapp.R;
import com.gliesereum.advisorapp.adapter.customadapterrecycleview.AdapterRecyclerView;
import com.gliesereum.advisorapp.adapter.customadapterrecycleview.viewHolder.ItemViewHolder;
import com.gliesereum.advisorapp.databinding.InvestorItemBinding;
import com.gliesereum.advisorapp.network.json.investor.InvestorResponse;

public class InvestorAdapter extends AdapterRecyclerView<InvestorResponse> {

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.investor_item;
    }

    @Override
    public int onProgressLayout() {
        return R.layout.progress_item;
    }

    @Override
    public void onBindView(ViewDataBinding binding, ItemViewHolder viewDataBinding, int position, int viewType, InvestorResponse element) {
        InvestorItemBinding itemBinding = (InvestorItemBinding) binding;
        itemBinding.firstName.setText(element.getUser().getFirstName());
        itemBinding.middleName.setText(element.getUser().getMiddleName());
        if (element.getPhone() != null && !element.getPhone().equals("")) {
            itemBinding.phone.setText("+" + element.getPhone());
            itemBinding.phoneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:+" + element.getPhone()));
                    getContext().startActivity(callIntent);
                }
            });
        } else {
            itemBinding.phoneLabel.setVisibility(View.GONE);
            itemBinding.phone.setVisibility(View.GONE);
            itemBinding.phoneBtn.setVisibility(View.GONE);
        }

        if (element.getEmail() != null && !element.getEmail().equals("")) {
            itemBinding.email.setText(element.getEmail());
            itemBinding.emailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:" + element.getEmail()));
//                    intent.putExtra(Intent.EXTRA_EMAIL, element.getEmail()); // String[] addresses
                    getContext().startActivity(intent);
                }
            });
        } else {
            itemBinding.emailLabel.setVisibility(View.GONE);
            itemBinding.email.setVisibility(View.GONE);
            itemBinding.emailBtn.setVisibility(View.GONE);
        }

    }

}
