package com.example.admin.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyModel>{


    private Context MyContext;
    private List<Model> register ;
    private OnItemClickListener mListener;

    public  interface OnItemClickListener {
        void OnItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public MyAdapter (Context MyContext,List<Model> register ){
        this.MyContext = MyContext;
        this.register = register ;
    }

    @NonNull
    @Override
    public MyModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(MyContext);
        View view = inflater.inflate(R.layout.show, null);
        return new MyAdapter.MyModel(view);
    }

    public void onBindViewHolder(@NonNull MyAdapter.MyModel holder, int position) {
        Model model = register.get(position);

        String id_cus = model.getId_cus();
        String name_cus = model.getName_cus();
        String lastname_cus = model.getLasname_cus();

        holder.a.setText(model.getId_cus());
        holder.b.setText(model.getName_cus());
        holder.c.setText(model.getLasname_cus());
    }

    @Override
    public int getItemCount() {
        return register.size();
    }

    class MyModel extends RecyclerView.ViewHolder{
       TextView a, b,c;

        public MyModel(View itemView){
            super(itemView);

            a = itemView.findViewById(R.id.a);
            b = itemView.findViewById(R.id.b);
            c = itemView.findViewById(R.id.c);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (register != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION);
     //                  mListener.onItemClick(position);
                    }
                }
            });
        }
    }





}
