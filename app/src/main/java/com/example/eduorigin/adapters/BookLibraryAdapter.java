package com.example.eduorigin.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eduorigin.PdfViewerActivity;
import com.example.eduorigin.R;
import com.example.eduorigin.models.ResponseModelBookLibraryBackup;

import java.util.ArrayList;
import java.util.List;

public class BookLibraryAdapter extends RecyclerView.Adapter<BookLibraryAdapter.viewHolder> implements Filterable {

    List<ResponseModelBookLibraryBackup> data;
    List<ResponseModelBookLibraryBackup>backup;
    Context context;



    public BookLibraryAdapter(List<ResponseModelBookLibraryBackup> data) {

        this.data = data;
        backup=new ArrayList<ResponseModelBookLibraryBackup>(data);
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design_template,parent,false);
        viewHolder viewholder=new viewHolder(view);




        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

             holder.singleRowName.setText(data.get(position).getName());
             holder.singleRowDescription.setText(data.get(position).getDescription());
             Glide.with(holder.singleRowName.getContext()).load("http://192.168.0.104/EduOriginAPI/Registration/images/"+data.get(position).getImage()).into(holder.singleRowImage);

             String curPos=data.get(position).toString();
             String pdfName=data.get(position).getPdf().toString();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   //Toast.makeText(holder.singleRowName.getContext(), curPos, Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(holder.singleRowName.getContext(),PdfViewerActivity.class);
                  // String pdfName=data.get(position).getPdf();
                   intent.putExtra("pdf",pdfName);
                   holder.singleRowName.getContext().startActivity(intent);

               }
           });
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
            List<ResponseModelBookLibraryBackup> filteredData=new ArrayList<>();
            if(keyword.toString().isEmpty())
            {
                filteredData.addAll(backup);
            }
            else{

                for(ResponseModelBookLibraryBackup obj:backup)
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
            data.addAll((ArrayList<ResponseModelBookLibraryBackup>)results.values);
            notifyDataSetChanged();
        }
    };



    public class viewHolder extends RecyclerView.ViewHolder{

        private final ImageView singleRowImage;
        private final TextView singleRowName;
        private final TextView singleRowDescription;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            singleRowImage=itemView.findViewById(R.id.singleRowImageVIewId);
            singleRowName=itemView.findViewById(R.id.singleRowNameId);
            singleRowDescription=itemView.findViewById(R.id.singleRowDescriptionId);


        }
    }

}
