package com.example.testapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testapplication.R;
import com.example.testapplication.bena.GoodListBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GoodListAdapter extends RecyclerView.Adapter<GoodListAdapter.GoldHolder>{
    private Context mContext;
    private List<GoodListBean.GoodBean> mList;

    public GoodListAdapter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setGoodList(List<GoodListBean.GoodBean> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public GoldHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.item_good_list, parent, false);
        GoldHolder holder = new GoldHolder(view);
        holder.getAdapterPosition();
        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                GoodListBean.GoodBean bean = mList.get(position);
                if(typeItemClickListener != null) {
                    typeItemClickListener.onBuyClick(position, bean);
                }
            }
        });
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull GoldHolder holder, int position) {
        if (mList.size()>position){
            GoodListBean.GoodBean data = mList.get(position);
            holder.itemTitle.setText(data.getName());
            holder.itemPrice.setText("￥" + data.getPrice());
            if (!TextUtils.isEmpty(data.getImage())){
                Glide.with(holder.itemImg.getContext()).load(data.getImage()).into(holder.itemImg);
            }
        }
    }


    private TypeItemClickListener typeItemClickListener;
    public void setTypeItemClickListener(TypeItemClickListener listener){
        this.typeItemClickListener = listener;
    }
    public interface TypeItemClickListener {
        void onBuyClick(int position, GoodListBean.GoodBean bean);
    }
    @Override
    public int getItemCount() {
        if(mList == null) {
            return 0;
        } else {
            return mList.size();
        }
    }

    class GoldHolder extends RecyclerView.ViewHolder{
        private final ImageView itemImg;
        private final TextView itemTitle, itemPrice;
        private final Button btnBuy;

        public GoldHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.item_good_img);
            itemTitle = itemView.findViewById(R.id.item_good_title);
            itemPrice = itemView.findViewById(R.id.item_good_price);
            btnBuy = itemView.findViewById(R.id.btn_good_buy);
        }
    }
}
