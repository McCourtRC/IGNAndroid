package com.example.corey.ignandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.corey.ignandroid.models.IGNObject;

import java.util.List;

/**
 * Created by corey on 5/2/17.
 */

public class IGNAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<IGNObject> dataSource;


    public IGNAdapter(Context context, List<IGNObject> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View view = inflater.inflate(R.layout.list_item_ign, parent, false);

        // Get data
        IGNObject data = dataSource.get(position);

        // Get view elements
        TextView dateView = (TextView) view.findViewById(R.id.ign_list_date);
        TextView titleView = (TextView) view.findViewById(R.id.ign_list_title);
        ImageView thumbnailView = (ImageView) view.findViewById(R.id.ign_list_thumbnail);

        // Set view elements
        dateView.setText(data.date);
        titleView.setText(data.title);
        Glide.with(context).load(data.imageUrl).into(thumbnailView);

        return view;
    }
}
