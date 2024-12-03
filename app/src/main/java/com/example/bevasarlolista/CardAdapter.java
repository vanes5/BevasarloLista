package com.example.bevasarlolista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CardAdapter extends BaseAdapter {
    private Context context;
    private List<ListItem> items;

    public CardAdapter(Context context, List<ListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.card, viewGroup, false);

        TextView name = view.findViewById(R.id.itemNameTextView);
        TextView quantity = view.findViewById(R.id.itemquantityTextView);

        ListItem item = (ListItem) getItem(i);

        name.setText(item.getName());
        quantity.setText(String.valueOf(item.getQuantity()));

        return view;
    }
}
