package com.example.project2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImgAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Integer> imgs = null;

    public ImgAdapter(Context c, ArrayList<Integer> imgs){
        mContext = c;
        this.imgs = imgs;
    }


    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView image = new ImageView(mContext);
        image.setImageResource(imgs.get(i));
        image.setLayoutParams(new Gallery.LayoutParams(400,400));
        image.setScaleType(ImageView.ScaleType.FIT_XY);

        return image;
    }
}
