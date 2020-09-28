package com.example.momento1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.momento1.models.ContactoModel;
import com.example.momento1.operations.ContactoOperations;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab_main_nuevo;
    private FloatingActionButton fab_main_foreach;
    private ListView lv_main_contactos;
    private ContactoOperations contactoOperations;
    private ArrayList<ContactoModel> list;
    private ArrayList<String> listString;
    private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab_main_nuevo = findViewById(R.id.fab_main_nuevo);
        fab_main_foreach = findViewById(R.id.fab_main_foreach);
        lv_main_contactos = findViewById(R.id.lv_main_contactos);
        contactoOperations = new ContactoOperations(getApplicationContext());

        listString = contactoOperations.selectAllString();
        contactoOperations.close();


        listString = new ArrayList<>();
        listString.add("Pablo");
        listString.add("German");
        listString.add("Juan");


        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listString);
        lv_main_contactos.setAdapter(itemsAdapter);



        lv_main_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String texto = listString.get(position);
                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();
            }
        });


        fab_main_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        fab_main_foreach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForeachActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
