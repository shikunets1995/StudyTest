package co.eghfhxample.phonecontacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Шикунец on 26.04.2016.
 */
public class SpinnerAdapter extends BaseAdapter{
    private List<PhoneId> data;

    public void setData(List<PhoneId> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public PhoneId getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        PhoneId phoneContacts = data.get(position);
        if (view == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner, parent, false);
        }
        ((TextView) view.findViewById(R.id.textView1)).setText(phoneContacts.name);

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }
}
