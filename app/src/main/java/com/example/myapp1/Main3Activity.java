package com.example.myapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.InputType;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import static com.example.myapp1.BookListAdapter.finalbill;
import static com.example.myapp1.BookListAdapter.hashMap;


public class Main3Activity extends AppCompatActivity {
   // private FirebaseAuth firebaseAuth;

   // private List<Map<String,String>>b1.hashMap;
    private ListView listView;
    EditText E1;
    Button button;
    private ArrayAdapter<String> arrayAdapter;
    private HashMap<String, String> h1;
    ArrayList <String>list;
    ArrayList<String> books;
    int finalbill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Main3Activity.this,Main4Activity.class);
               // String passvalue=E1.getText().toString();
                //intent1.putExtra("s1",passvalue);
                startActivity(intent1);
            }
        });


        h1 = BookListAdapter.hashMap;
        listView = findViewById(R.id.listView);
        finalbill=0;
        E1=findViewById(R.id.editText4);
       list = new ArrayList<>();
        Set<String> items = h1.keySet();
        for(String item : items){
            String data = item + "\t\tCost Rs:" + h1.get(item);
            if(!h1.get(item).equals("Price not available")){
                list.add(data);
                finalbill+=Double.parseDouble(h1.get(item));
            }

        }
        E1.setInputType(InputType.TYPE_NULL);
        E1.setText(String.valueOf(finalbill));

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        registerForContextMenu(listView);



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.billcontextmenu, menu);
        menu.setHeaderTitle("Select The Action");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        if(item.getItemId()==R.id.delete){
            int pos = info.position;
            Button button;

            arrayAdapter.remove(list.get(pos));
            finalbill -= Double.parseDouble(h1.get(BookListAdapter.booksInList.get(pos)));
            BookListAdapter.booksInList.remove(pos);
            E1.setText(Double.toString(finalbill));

        }
        if(item.getItemId()==R.id.repeatorder){
            int pos = info.position;
            arrayAdapter.add(list.get(pos));
            finalbill += Double.parseDouble(h1.get(BookListAdapter.booksInList.get(pos)));
            BookListAdapter.booksInList.add(BookListAdapter.booksInList.get(pos));
            E1.setText(Double.toString(finalbill));
        }
        return super.onContextItemSelected(item);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /if(item.getItemId()==R.id.logout){
            firebaseAuth.signOut();
            finish();
            Intent intent=new Intent(Main3Activity.this,MainActivity.class);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }*/
}
