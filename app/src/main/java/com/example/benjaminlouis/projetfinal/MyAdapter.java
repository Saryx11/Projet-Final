package com.example.benjaminlouis.projetfinal;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by benjaminlouis on 19/10/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<User> list;
    Context context;
    Fragment frag;
    int pos;

    public MyAdapter(List<User> list, Context context,Fragment frag){
        this.list=list;
        this.context=context;
        this.frag=frag;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(list.get(position).toString());
        this.pos=position;
        holder.mTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                AfficheUser usrFrag = (AfficheUser) frag.getFragmentManager().findFragmentById(R.id.userInfo);
                if (usrFrag != null) {
                    usrFrag.updateUserView(list.get(pos));
                }

            }
    });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textView) ;
        }
    }
}

