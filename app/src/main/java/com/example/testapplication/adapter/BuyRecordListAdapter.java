package com.example.testapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;
import com.example.testapplication.bena.BuyListBean.BuyRecord;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressLint("SetTextI18n")
public class BuyRecordListAdapter extends RecyclerView.Adapter<BuyRecordListAdapter.BuyHolder>
        implements View.OnClickListener{

    private Context mContext;
    private List<BuyRecord> mList;
    private RecyclerView rvParent;//这是全局变量

    public BuyRecordListAdapter(Context context) {
        this.mContext = context;
    }

    public void setGoodList(List<BuyRecord> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public BuyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.item_buy_record, parent, false);
        rvParent = (RecyclerView) parent;
        view.setOnClickListener(this);
        return new BuyHolder(view);
    }

    @Override
    public void onClick(View v) {
        int position = rvParent.getChildAdapterPosition(v);
        if(typeItemClickListener != null) {
            BuyRecord bean = mList.get(position);
            typeItemClickListener.onItemClick(position, bean);
        }
    }

    private TypeItemClickListener typeItemClickListener;

    public void setTypeItemClickListener(TypeItemClickListener listener) {
        this.typeItemClickListener = listener;
    }

    public interface TypeItemClickListener {
        void onItemClick(int position, BuyRecord bean);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BuyHolder holder, int position) {
        if(mList.size() > position) {
            BuyRecord data = mList.get(position);
            holder.itemOrder.setText("订单号:" + data.getOrderNum());
            holder.itemTime.setText("下单时间:" + data.getCreateTime());
            holder.itemMoney.setText("订单金额:" + data.getMoney());
            String status = data.getStatus();
            if(status.equals("1")) {
                holder.itemState.setText("待发货");
                holder.itemState.setTextColor(ContextCompat.getColor(mContext, R.color.light_pure_green));
            }
            if(status.equals("2")) {
                holder.itemState.setText("待收货");
                holder.itemState.setTextColor(ContextCompat.getColor(mContext, R.color.btn_blue));
            }
            if(status.equals("3")) {
                holder.itemState.setText("已完成");
                holder.itemState.setTextColor(ContextCompat.getColor(mContext, R.color.big_red));
            }
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

    class BuyHolder extends RecyclerView.ViewHolder {

        private TextView itemOrder, itemMoney, itemTime, itemState;

        public BuyHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemOrder = itemView.findViewById(R.id.item_txt_order);
            itemMoney = itemView.findViewById(R.id.item_txt_money);
            itemTime = itemView.findViewById(R.id.item_txt_time);
            itemState = itemView.findViewById(R.id.item_txt_state);
        }
    }
}
