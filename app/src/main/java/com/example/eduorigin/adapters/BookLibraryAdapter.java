package com.example.eduorigin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eduorigin.R;
import com.example.eduorigin.models.ResponseModelBookLibrary;

import java.util.ArrayList;
import java.util.List;

public class BookLibraryAdapter extends RecyclerView.Adapter<BookLibraryAdapter.viewHolder> implements Filterable {

    List<ResponseModelBookLibrary> data;
    List<ResponseModelBookLibrary>backup;



    public BookLibraryAdapter(List<ResponseModelBookLibrary> data) {

        this.data = data;
        backup=new ArrayList<ResponseModelBookLibrary>(data);
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design_template,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
             holder.singleRowName.setText(data.get(position).getName());
             holder.singleRowDescription.setText(data.get(position).getDescription());
             Glide.with(holder.singleRowName.getContext()).load("http://192.168.0.104/EduOriginAPI/Registration/images/"+data.get(position).getImage()).into(holder.singleRowImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            List<ResponseModelBookLibrary> filteredData=new ArrayList<>();
            if(keyword.toString().isEmpty())
            {
                filteredData.addAll(backup);
            }
            else{

                for(ResponseModelBookLibrary obj:backup)
                {
                    if(obj.getName().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                        filteredData.add(obj);
                }
            }

            FilterResults results=new FilterResults();
            results.values=filteredData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            data.clear();
            data.addAll((ArrayList<ResponseModelBookLibrary>)results.values);
            notifyDataSetChanged();
        }
    };



    public class viewHolder extends RecyclerView.ViewHolder{

        private ImageView singleRowImage;
        private TextView singleRowName,singleRowDescription;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            singleRowImage=itemView.findViewById(R.id.singleRowImageVIewId);
            singleRowName=itemView.findViewById(R.id.singleRowNameId);
            singleRowDescription=itemView.findViewById(R.id.singleRowDescriptionId);
        }
    }

}
