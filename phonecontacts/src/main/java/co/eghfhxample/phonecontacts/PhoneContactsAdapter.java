package co.eghfhxample.phonecontacts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Шикунец on 24.04.2016.
 */
public class PhoneContactsAdapter extends RecyclerView.Adapter<PhoneContactsAdapter.Holder> {
    private List<PhoneContacts> data;

    public void setData(List<PhoneContacts> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    @Override
    public PhoneContactsAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item1, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(PhoneContactsAdapter.Holder holder, int position) {

        final PhoneContacts phoneContacts = data.get(position);
        final Context context = holder.itemView.getContext();
        holder.textView1.setText(phoneContacts.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent;
                Toast.makeText(context, "Click to connect",Toast.LENGTH_LONG ).show();
                intent = new Intent(context,ContactActivity.class);
                intent.putExtra("id", phoneContacts.id );
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textView1;

        public Holder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.textView1);
        }
    }
}
