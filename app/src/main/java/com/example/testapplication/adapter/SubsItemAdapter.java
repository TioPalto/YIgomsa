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
import com.example.testapplication.bena.SubListBean;
import com.google.android.material.imageview.ShapeableImageView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressLint("SetTextI18n")
public class SubsItemAdapter extends RecyclerView.Adapter<SubsItemAdapter.SubsHolder> implements View.OnClickListener {
    private Context mContext;
    private List<SubListBean.SubsData> mList;
    private RecyclerView rvParent;//这是全局变量

    public SubsItemAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setGoodList(List<SubListBean.SubsData> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        int position = rvParent.getChildAdapterPosition(view);
        if (typeItemClickListener!=null){
            SubListBean.SubsData bean = mList.get(position);
            typeItemClickListener.onItemClick(position,bean);
        }

    }

    @NonNull
    @NotNull
    @Override
    public SubsHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.item_subs_msg, parent, false);
        rvParent = (RecyclerView) parent;
        view.setOnClickListener(this);
        return new SubsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SubsHolder holder, int position) {
        if (mList.size()>position){
            SubListBean.SubsData data = mList.get(position);
            holder.itemName.setText(data.getBookItem());
            holder.itemTime.setText("预约时间:" + data.getBookTime());
            String status = data.getStatus();
            if(status.equals("0")) {
                holder.itemImg.setBackgroundColor(
                        ContextCompat.getColor(holder.itemImg.getContext(), R.color.txt_gray_deep));
            }
            if(status.equals("1")) {
                holder.itemImg.setBackgroundColor(
                        ContextCompat.getColor(holder.itemImg.getContext(), R.color.btn_green));
            }
            if(status.equals("2")) {
                holder.itemImg.setBackgroundColor(
                        ContextCompat.getColor(holder.itemImg.getContext(), R.color.dark_red));
            }
            if(status.equals("3")) {
                holder.itemImg.setBackgroundColor(
                        ContextCompat.getColor(holder.itemImg.getContext(), R.color.btn_blue));
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList ==null){
            return 0;
        } else {
            return mList.size();
        }
    }

    private TypeItemClickListener typeItemClickListener;

    public void setTypeItemClickListener(TypeItemClickListener listener) {
        this.typeItemClickListener = listener;
    }

    public interface TypeItemClickListener {
        void onItemClick(int position, SubListBean.SubsData bean);
    }



    class SubsHolder extends RecyclerView.ViewHolder{
        private ShapeableImageView itemImg;
        private TextView itemName, itemTime;

        public SubsHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.item_subs_state);
            itemName = itemView.findViewById(R.id.item_subs_name);
            itemTime = itemView.findViewById(R.id.item_subs_time);
        }
    }
}
