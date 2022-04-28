package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    ArrayList<String> ID, Task, Desc, Prior, Date;

    CustomAdapter(Activity activity, Context context, ArrayList ID, ArrayList Task, ArrayList Desc,
                  ArrayList Prior, ArrayList Date){
        this.activity = activity;
        this.context = context;
        this.ID = ID;
        this.Task = Task;
        this.Desc = Desc;
        this.Prior = Prior;
        this.Date = Date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.TaskCard.setText(String.valueOf(Task.get(position)));
        holder.DescCard.setText(String.valueOf(Desc.get(position)));
        holder.PriorCard.setText(String.valueOf(Prior.get(position)));
        holder.DateCard.setText(String.valueOf(Date.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChangeTask.class);
                intent.putExtra("Tasks", String.valueOf(Task.get(position)));
                intent.putExtra("Desciptions", String.valueOf(Desc.get(position)));
                intent.putExtra("Prioritys", String.valueOf(Prior.get(position)));
                intent.putExtra("Dates", String.valueOf(Date.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {

        return ID.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView TaskCard, DescCard, PriorCard, DateCard;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TaskCard = itemView.findViewById(R.id.TaskCard);
            DescCard = itemView.findViewById(R.id.DescCard);
            PriorCard = itemView.findViewById(R.id.PriorCard);
            DateCard = itemView.findViewById(R.id.DateCard);
            mainLayout = itemView.findViewById(R.id.l);
            //Animate Recyclerview
           // Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            //mainLayout.setAnimation(translate_anim);
        }

    }

}