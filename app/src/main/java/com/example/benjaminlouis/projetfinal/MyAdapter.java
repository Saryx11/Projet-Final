package com.example.benjaminlouis.projetfinal;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
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

    public interface OnItemClickListener{
        void onItemClick(User item);
    }

    private List<User> list;
    private OnItemClickListener listener;
    private Context context;
    private Fragment frag;
    private int pos;




    public MyAdapter(List<User> list,Fragment frag){
        this.list=list;
        //this.listener=listener;
        this.frag=frag;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        //holder.bind(list.get(position),listener);
        holder.mTextView.setText(list.get(position).toString());
        final User u=list.get(position);
        holder.mTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                //On récupère le fragment fraginfo
                AfficheUserFragment usrFrag = (AfficheUserFragment) frag.getFragmentManager().findFragmentById(R.id.fraginfo);
                //s'il existe ->large donc on update le fragment
                if (usrFrag != null) {
                    usrFrag.updateUserView(u);
                }
                else{
                    //Sinon on créé le fragment avec un user en argument
                    AfficheUserFragment newFragment = new AfficheUserFragment();
                    Bundle args =new Bundle();
                    args.putParcelable("user",u);
                    newFragment.setArguments(args);
                    FragmentTransaction transaction = frag.getFragmentManager().beginTransaction();
                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack so the user can navigate back
                    //et on le met à la place de ce qu'il y a dans fragment container
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
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

        /*public void bind (final User user, final OnItemClickListener listener){
            mTextView.setText(user.toString());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user){

                    };
                }
            });
        }*/
    }
}

