package com.example.bevasarlolista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText quantityEditText;
    private ListView itemListView;
    private Button addButton;
    private ArrayList<ListItem> items;
    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                int qua = Integer.parseInt(quantityEditText.getText().toString());
                items.add(new ListItem(name,qua));
                cardAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Sikeres felvétel", Toast.LENGTH_SHORT).show();
            }
        });

        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListItem clicked = items.get(i);
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("item_name", clicked.getName());
                intent.putExtra("item_quantity",String.valueOf(clicked.getQuantity()));
                startActivity(intent);
                finish();
            }
        });

        itemListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                items.remove(i);
                cardAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }


    private void init(){
        nameEditText = findViewById(R.id.termekNeveEditText);
        quantityEditText = findViewById(R.id.termekMennyisegeEditText);
        itemListView = findViewById(R.id.itemListView);
        addButton = findViewById(R.id.addItemButton);
        items = new ArrayList<ListItem>();
        cardAdapter = new CardAdapter(MainActivity.this, items);
        itemListView.setAdapter(cardAdapter);
    }
}