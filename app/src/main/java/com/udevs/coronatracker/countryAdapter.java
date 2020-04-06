package com.udevs.coronatracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class countryAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<singleRow> arraylist;

    public countryAdapter(Context context, ArrayList<singleRow> arraylist) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = arraylist;

    }

    public class ViewHolder {
        TextView country,t_cases;
        ImageView flags;
        CardView cardViewItems;
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public singleRow getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.country_list, null);
            holder.country = (TextView) view.findViewById(R.id.country);
            holder.t_cases = (TextView) view.findViewById(R.id.t_cases);
            holder.flags = (ImageView) view.findViewById(R.id.flag);
            holder.cardViewItems = (CardView) view.findViewById(R.id.item_box);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.country.setText(arraylist.get(position).getCountry());
        holder.t_cases.setText(Udevs_utils.getFormatedAmount(Integer.parseInt(arraylist.get(position).getCases())));
        Picasso.get().load(arraylist.get(position).getImageLink()).into(holder.flags);

        holder.cardViewItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Udevs_utils udevs = new Udevs_utils();
                singleRow sr = arraylist.get(position);
                if(arraylist.contains(sr.getCountry().toString())){
                    Toast.makeText(mContext, "tama", Toast.LENGTH_SHORT).show();
                }else{
                    udevs.show_status(mContext,sr.getImageLink(),sr.getCases(),sr.getDeaths(),sr.getToday_Cases(),sr.getToday_deaths(),sr.getRecovered());
                }

            }
        });
        return view;
    }


}