package com.example.eduorigin.featurefragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eduorigin.DashboardActivity;
import com.example.eduorigin.R;
import com.example.eduorigin.adapters.BookLibraryAdapter;
import com.example.eduorigin.controllers.ApiController;
import com.example.eduorigin.models.ResponseModelBookLibrary;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookLibraryFragment extends Fragment {





    private RecyclerView recyclerView;
    private BookLibraryAdapter adapter;
    Context context;
    String receive;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_book_library, container, false);

        recyclerView=view.findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        processData();

        SearchView sv;
        sv=view.findViewById(R.id.searchViewId);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });



        return view;
    }



    private void processData() {

        Call<List<ResponseModelBookLibrary>> call= ApiController.getInstance().getapi().getBookLibraryData();
        call.enqueue(new Callback<List<ResponseModelBookLibrary>>() {
            @Override
            public void onResponse(Call<List<ResponseModelBookLibrary>> call, Response<List<ResponseModelBookLibrary>> response) {

                List<ResponseModelBookLibrary> data=response.body();
                adapter=new BookLibraryAdapter(data);
                recyclerView.setAdapter(adapter);



            }

            @Override
            public void onFailure(Call<List<ResponseModelBookLibrary>> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }


//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//
//        inflater.inflate(R.menu.search_menu,menu);
//        MenuItem menuItem=menu.findItem(R.id.search_from_menuId);
//        SearchView searchView= (SearchView) menuItem.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//       // return super.onCreateOptionsMenu(menu,inflater);
//    }
}