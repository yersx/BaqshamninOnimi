package kz.baqshamninonimi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import kz.baqshamninonimi.R;
import kz.baqshamninonimi.TypeItem;

public class AutoCompleteTypeAdapter extends ArrayAdapter<TypeItem> {
    private List<TypeItem> TypeItemListFull;


    public AutoCompleteTypeAdapter(@NonNull Context context,  @NonNull List<TypeItem> typeItemList) {
        super(context,0, typeItemList);
        TypeItemListFull=new ArrayList<>(typeItemList);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return  typeFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null) {
        convertView= LayoutInflater.from(getContext()).inflate(
                R.layout.type_ac_row, parent,false);
        }
        TextView textViewName = convertView.findViewById(R.id.text_view_name);

        TypeItem typeItem = getItem(position);

        if(typeItem!=null) {
        textViewName.setText(typeItem.getProdtype());
        }
        return convertView;
    }

    private Filter typeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<TypeItem> suggestions = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                suggestions.addAll(TypeItemListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (TypeItem item : TypeItemListFull) {
                    if (item.getProdtype().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
             return  results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            addAll((List)filterResults.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((TypeItem)resultValue).getProdtype();
        }
    };

    }
