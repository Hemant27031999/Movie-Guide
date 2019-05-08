package com.example.movieguide;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private List<String> imageList;
    private LayoutInflater mInflater;
    private List<Results> results;
    private OnItemClickListener mListener;

    // data is passed into the constructor
    public GridAdapter(Context context, List imageList, List results) {
        this.mInflater = LayoutInflater.from(context);
        this.imageList = imageList;
        this.results=results;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.gridlayout, parent, false);
        return new ViewHolder(view);

    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
//        holder.myTextView.setText(mData[position]);
        final String path = imageList.get(position);

        Picasso.with(mInflater.getContext())
                .load(path)
                .resize(250, 250)
                .centerCrop()
                .into(holder.imageView);

        holder.getAdapterPosition();
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return imageList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.info_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

