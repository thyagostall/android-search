package com.thyago.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thyago on 6/1/16.
 */
public class SomeDataAdapter extends BaseAdapter implements Filterable {

    List<SomeDataEntity> mFilteredData;
    List<SomeDataEntity> mData;
    LayoutInflater mInflater;

    public SomeDataAdapter(LayoutInflater inflater) {
        SomeDataModel model = new SomeDataModel();
        mData = model.findAll();
        mFilteredData = model.findAll();
        mInflater = inflater;
    }

    @Override
    public int getCount() {
        return mFilteredData.size();
    }

    @Override
    public Object getItem(int position) {
        return mFilteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mFilteredData.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.some_data_item, parent, false);

            viewHolder = new ViewHolderItem();
            viewHolder.mId = (TextView) convertView.findViewById(R.id.field_id);
            viewHolder.mFirst = (TextView) convertView.findViewById(R.id.field_first);
            viewHolder.mSecond = (TextView) convertView.findViewById(R.id.field_second);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        SomeDataEntity entity = mFilteredData.get(position);
        if (entity != null) {
            viewHolder.mId.setText("" + entity.id);
            viewHolder.mFirst.setText(entity.first);
            viewHolder.mSecond.setText(entity.second);
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new SomeAdapterFilter();
    }

    public static class ViewHolderItem {
        TextView mId;
        TextView mFirst;
        TextView mSecond;
    }

    private class SomeAdapterFilter extends Filter {

        private boolean doesMeetCriteria(SomeDataEntity entity, String constraint) {
            String first = entity.first.toLowerCase();
            String second = entity.second.toLowerCase();
            String id = String.valueOf(entity.id);

            return (first.startsWith(constraint) ||
                    second.startsWith(constraint) ||
                    id.startsWith(constraint));
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<SomeDataEntity> filtered = new LinkedList<>();

            String search = constraint.toString().toLowerCase();
            if (search.isEmpty()) {
                filtered.addAll(mData);
            } else {
                for (SomeDataEntity item : mData) {
                    if (doesMeetCriteria(item, search)) {
                        filtered.add(item);
                    }
                }
            }

            results.count = filtered.size();
            results.values = filtered;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mFilteredData = (List<SomeDataEntity>) results.values;
            notifyDataSetChanged();
        }
    }
}
