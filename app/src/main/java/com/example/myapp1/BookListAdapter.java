package com.example.myapp1;

import android.content.Context;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import static android.widget.Toast.LENGTH_SHORT;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    public static int finalbill ;
    public  static String addedtokart;
    public static HashMap<String,String>hashMap=new HashMap<>();
    /*BookListAdapter() {
          int finalBill = 0;
          String addedToKart = "";
    }*/
    private static final String LOG_TAG = BookListAdapter.class.getSimpleName();
    private final ArrayList<Book> mValues;
    private final Context mContext;
    private int position;
    public static ArrayList<String> booksInList = new ArrayList<>();
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public BookListAdapter(ArrayList<Book> items, Context context) {
        mValues = items;
        mContext = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_booklist, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).title);
        final String bookname=mValues.get(position).title;
        final String authorsString = "Cost (Rs):" + mValues.get(position).authors;
        final String bill=mValues.get(position).authors;
        holder.mAuthorsView.setText(authorsString);
        String smallThumbnailLink = mValues.get(position).smallThumbnailLink;
        Picasso.with(mContext).load(smallThumbnailLink).into(holder.mBookCoverView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                        menu.add("Add to Cart").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                               // Toast.makeText(me,"Added to Cart!",Toast.LENGTH_SHORT).show();
                                //do what u want
                                return true;
                            }
                        });
                    }
                });


            addedtokart = bookname+" ----------------------->"+authorsString;
            hashMap.put(bookname,bill);
            booksInList.add(bookname);


            }
        });





    }

    @Override
    public int getItemCount() {
        if (mValues != null) {
            return mValues.size();
        } else {
            return 0;
        }
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public final TextView mTitleView;
        public final TextView mAuthorsView;
        public final ImageView mBookCoverView;
        public Book mItem;

        public ViewHolder(View view) {
            super(view);
            mTitleView = (TextView) view.findViewById(R.id.title);
            mAuthorsView = (TextView) view.findViewById(R.id.authors);
            mBookCoverView = (ImageView) view.findViewById(R.id.book_cover);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem cart = menu.add(Menu.NONE, 1, 1, "Add to Cart");

        }
    }
}
