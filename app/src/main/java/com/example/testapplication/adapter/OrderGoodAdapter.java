package com.example.testapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testapplication.R;
import com.example.testapplication.bena.OrderInfoBean.Good;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressLint("SetTextI18n")
public class OrderGoodAdapter extends RecyclerView.Adapter<OrderGoodAdapter.GoodHolder> {
    private Context mContext;
    private List<Good> mList;

    public OrderGoodAdapter(Context context) {
        this.mContext = context;
    }
    public void setGoodList(List<Good> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public GoodHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.item_order_good, parent, false);
        return new GoodHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull GoodHolder holder, int position) {
        if(mList.size() > position) {
            Good good = mList.get(position);
            holder.itemTitle.setText(good.getName());
            holder.itemPrice.setText("￥" + good.getPrice());
            holder.itemNum.setText("*" + good.getNum());
            Glide.with(holder.itemImg.getContext()).load(good.getImage()).into(holder.itemImg);
        }
    }

    @Override
    public int getItemCount() {
        if(mList == null) {
            return 0;
        } else {
            return mList.size();
        }
    }

    class GoodHolder extends RecyclerView.ViewHolder{
        private ImageView itemImg;
        private TextView itemTitle, itemPrice, itemNum;

        public GoodHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.item_img_good);
            itemTitle = itemView.findViewById(R.id.item_txt_title);
            itemPrice = itemView.findViewById(R.id.item_txt_price);
            itemNum = itemView.findViewById(R.id.item_txt_number);
        }
    }


}
