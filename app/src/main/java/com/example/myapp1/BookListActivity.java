package com.example.myapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;


public class BookListActivity extends AppCompatActivity implements BookListFragment.OnListFragmentInteractionListener {
    //private static final String LOG_TAG = BookListActivity.class.getSimpleName();
        BookListAdapter b1;



    private FirebaseAuth firebaseAuth;
    RecyclerView mrecyclerView;
    //private RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        firebaseAuth=FirebaseAuth.getInstance();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        BookListAdapter.hashMap = new HashMap<String, String>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.logout){
            firebaseAuth.signOut();
            finish();
            Intent intent=new Intent(BookListActivity.this,MainActivity.class);
            startActivity(intent);

        }
        if(item.getItemId()==R.id.viewkart){
           // finish();
            Intent intent=new Intent(BookListActivity.this,Main3Activity.class);
            startActivity(intent);

            //startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
