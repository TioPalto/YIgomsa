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

import com.example.testapplication.R;
import com.example.testapplication.bena.RechargeListBean.RechargeBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressLint("SetTextI18n")
public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.RecordHolder> {

    private Context mContext;
    private List<RechargeBean> mList;

    public RecordListAdapter(Context context) {
        this.mContext = context;
    }

    public void setGoodList(List<RechargeBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.item_record_holder, parent, false);
        return new RecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecordHolder holder, int position) {
        if(mList.size() > position) {
            RechargeBean bean = mList.get(position);
            holder.itemTime.setText("充值时间:" + bean.getRechargeTime());
            holder.itemMoney.setText("充值金额:" + bean.getMoney());
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

    class RecordHolder extends RecyclerView.ViewHolder {

        private ImageView itemImg;
        private TextView itemTime, itemMoney;

        public RecordHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.itemImg = itemView.findViewById(R.id.item_recharge_img);
            this.itemTime = itemView.findViewById(R.id.item_recharge_time);
            this.itemMoney = itemView.findViewById(R.id.item_recharge_money);
        }
    }
}
