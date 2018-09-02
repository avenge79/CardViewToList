package com.example.rado.cardviewtolist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

class CustomListAdapter extends ArrayAdapter<Cards> {

    private static final String TAG = "CustomListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;
    ImageView imgView;

    public CustomListAdapter(@NonNull Context context, int resource, ArrayList<Cards> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    private static class ViewHolder {
        TextView title;
        ImageView image;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        setupImageLoader();

        final String title = getItem(position).getTitle();
        final String imgUrl = getItem(position).getImgURL();

        final View result;

        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.cardTitle);
            holder.image = (ImageView) convertView.findViewById(R.id.cardImage);
            final View finalConvertView = convertView;



            result = convertView;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }



        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.title.setText(title);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(mContext, Image.class);
                i.putExtra("img", imgUrl);
                mContext.startActivity(i);
                Toast.makeText(mContext, imgUrl, Toast.LENGTH_SHORT).show();
            }
        });

        ImageLoader imageLoader = ImageLoader.getInstance();

        int defaultImage = mContext.getResources().getIdentifier("@drawable/image_failed", null, mContext.getPackageName());

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage).showImageOnFail(defaultImage).showImageOnLoading(defaultImage).build();

        imageLoader.displayImage(imgUrl, holder.image, options);
        Log.d(TAG, "IMAGE: " + imgUrl + " ::: " + holder.image);

        return convertView;
    }

    private void setupImageLoader() {
         DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY)
                 .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext).defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache()).diskCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
    }
}






























