package com.example.testapplication.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testapplication.R;
import com.example.testapplication.bena.BannerBean;
import com.youth.banner.adapter.BannerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BannerImgAdapter extends BannerAdapter<BannerBean,BannerImgAdapter.ImgTxtHolder> {
    public BannerImgAdapter(List<BannerBean> list) {
        super(list);
    }
    public void updateList(List<BannerBean> data){
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ImgTxtHolder onCreateHolder(ViewGroup parent, int i) {
        return new ImgTxtHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.banner_img_txt,
                parent,false));
    }

    @Override
    public void onBindView(ImgTxtHolder holder, BannerBean data, int position, int size) {
        holder.txtTitle.setText(data.getTitle());
        if(!TextUtils.isEmpty(data.getImgUrl())) {
            Glide.with(holder.image.getContext()).load(data.getImgUrl()).into(holder.image);
        }
    }

    class ImgTxtHolder extends RecyclerView.ViewHolder {
        private final TextView txtTitle;
        private final ImageView image;

        public ImgTxtHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.bannerTitle);
            image = itemView.findViewById(R.id.image);
        }
    }

}
