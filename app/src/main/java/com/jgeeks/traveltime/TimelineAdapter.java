package com.jgeeks.traveltime;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jgeeks.traveltime.model.Trip;

import java.util.List;

/**
 * Created by alexgaluska on 04/04/15.
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private List<Trip> dataSource;

    public TimelineAdapter(List<Trip> dataArgs){
        dataSource = dataArgs;

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView =  (TextView) itemView.findViewById(R.id.list_item);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "position = " + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public TimelineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.timeline_item, parent, false);
        // set the view's size, margins, paddings and layout parameters


        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TimelineAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText(dataSource.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if(dataSource!=null) {
            return dataSource.size();
        }
        else return 0;
    }
}
