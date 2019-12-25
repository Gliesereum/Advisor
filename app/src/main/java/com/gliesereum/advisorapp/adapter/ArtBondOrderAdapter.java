package com.gliesereum.advisorapp.adapter;

import androidx.databinding.ViewDataBinding;

import com.gliesereum.advisorapp.R;
import com.gliesereum.advisorapp.adapter.customadapterrecycleview.AdapterRecyclerView;
import com.gliesereum.advisorapp.adapter.customadapterrecycleview.viewHolder.ItemViewHolder;
import com.gliesereum.advisorapp.databinding.ArtbondOrderItemBinding;
import com.gliesereum.advisorapp.network.json.order.ArtBondOrderResponse;
import com.gliesereum.advisorapp.util.Util;

public class ArtBondOrderAdapter extends AdapterRecyclerView<ArtBondOrderResponse> {

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.artbond_order_item;
    }

    @Override
    public int onProgressLayout() {
        return R.layout.progress_item;
    }

    @Override
    public void onBindView(ViewDataBinding binding, ItemViewHolder viewDataBinding, int position, int viewType, ArtBondOrderResponse element) {
        ArtbondOrderItemBinding itemBinding = (ArtbondOrderItemBinding) binding;
        itemBinding.firstName.setText(element.getUser().getFirstName());
        itemBinding.middleName.setText(element.getUser().getMiddleName());
        itemBinding.sumInvestment.setText(String.valueOf(element.getSumInvestment()));
        itemBinding.stockCount.setText(String.valueOf(element.getStockCount()));
        itemBinding.create.setText(Util.getStringDate(element.getCreate()));
        itemBinding.stateType.setText(element.getStateType());

        if (element.getStateType().equals("REFUSED")) {
            itemBinding.stateType.setText("Отклонена");
            itemBinding.statusImg.setImageResource(R.drawable.ic_outline_block_24px);
        } else {
            switch (element.getStateType()) {
                case "REQUEST":
                    itemBinding.stateType.setText("В ожидании рассмотрения");
                    itemBinding.statusImg.setImageResource(R.drawable.ic_material_icons_timer_24px);
                    break;
                case "IN_PROCESS":
                    itemBinding.stateType.setText("В процессе рассмотрения");
                    itemBinding.statusImg.setImageResource(R.drawable.ic_outline_pause_circle_outline_24px);
                    break;
                case "COMPLETED":
                    itemBinding.stateType.setText("Одобрена");
                    itemBinding.statusImg.setImageResource(R.drawable.ic_outline_check_circle_outline_24px);
                    break;
                default:
                    itemBinding.stateType.setText("");
                    itemBinding.statusImg.setImageResource(R.drawable.ic_outline_block_24px);
                    break;
            }
        }
    }


}
