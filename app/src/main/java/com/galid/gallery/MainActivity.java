package com.galid.gallery;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.galid.gallery.model.PhotoModel;
import com.galid.gallery.rest.RetrofitClient;
import com.galid.gallery.rest.PhotoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_main);
        final MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PhotoService userService = RetrofitClient.getInstance().create(PhotoService.class);
        Call<List<PhotoModel>> call = userService.getAllAnimals();
        call.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                if(response.isSuccessful()){
                    System.out.println(response.body().toString());
                    List<PhotoModel> photoModelList = response.body();
                    myAdapter.setUserList(photoModelList);
                }
                else{
                    Log.d("@@@@@@",response.message().toString());
                }
            }
            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                Log.d("@@@@@@",t.getMessage().toString());
            }
        });
    }

    static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<PhotoModel> photoModelList = new ArrayList<>();

        public void setUserList(List<PhotoModel> photoModels){
            this.photoModelList.clear();
            this.photoModelList.addAll(photoModels);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitem_main, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.bind(photoModelList.get(position));
        }

        @Override
        public int getItemCount() {
            return photoModelList.size();
        }
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageview_recyclerviewitem_main_photoimage);
            name = itemView.findViewById(R.id.textview_recyclerviewitem_main_photoname);
        }

        public void bind(PhotoModel photoModel){
            Glide.with(image)
                .load(photoModel.getPhotoUrl())
                .into(image);
            name.setText(photoModel.getPhotoName());
        }
    }
}
