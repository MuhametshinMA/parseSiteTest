package com.example.parsesitetest;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<Currencies> {
    private LayoutInflater inflater;
    private List<Currencies> listItem;

    public CustomArrayAdapter(@NonNull Context context, int resource, LayoutInflater inflater, List<Currencies> listItem) {
        super(context, resource, listItem);
        this.inflater = inflater;
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        Currencies currencies = listItem.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_1, null, false);
            viewHolder = new ViewHolder();
            viewHolder.tvCurrency = convertView.findViewById(R.id.tvCurrency);
            viewHolder.tvFirstBank = convertView.findViewById(R.id.tvFirstBank);
            viewHolder.tvFirstVal = convertView.findViewById(R.id.tvFirstVal);
            viewHolder.tvSecBank = convertView.findViewById(R.id.tvSecBank);
            viewHolder.tvSecVal = convertView.findViewById(R.id.tvSecVal);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvCurrency.setText(currencies.getCurrName());
        viewHolder.tvFirstBank.setText(currencies.getFirstBank());
        viewHolder.tvFirstVal.setText(currencies.getFirstVal());
        viewHolder.tvSecBank.setText(currencies.getSecBank());
        viewHolder.tvSecVal.setText(currencies.getSecVal());
        return convertView;
    }
    private class ViewHolder {
        TextView tvCurrency;
        TextView tvFirstBank;
        TextView tvFirstVal;
        TextView tvSecBank;
        TextView tvSecVal;
    }
}
