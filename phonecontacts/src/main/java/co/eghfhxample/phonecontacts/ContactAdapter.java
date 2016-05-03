package co.eghfhxample.phonecontacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Шикунец on 25.04.2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.Holder> {

    private List<Object> data =  new ArrayList<>();
    public void setData(List<PhoneNumbers> data, List<PhoneAdress> data2){
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }
        if (data2 != null) {
            this.data.addAll(data2);
        }
        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position){

        return super.getItemViewType(position);
    }

    @Override
    public ContactAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.Holder holder, int position) {
        final Object object = data.get(position);
        final Context context = holder.itemView.getContext();
        if (object instanceof PhoneNumbers) {
            final PhoneNumbers phoneNumbers = (PhoneNumbers) object;
            holder.textView1.setText(phoneNumbers.number);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();
                    Intent intent;
                    intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+phoneNumbers.number));
                    context.startActivity(Intent.createChooser(intent,null));
                }
            });
        }else if ( object instanceof  PhoneAdress){
            final PhoneAdress  phoneAdress = (PhoneAdress) object;
            holder.textVIew2.setText(phoneAdress.adress);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uriText =
                            "mailto:" + phoneAdress.adress +
                                    "?subject=" + Uri.encode("some subject text here") +
                                    "&body=" + Uri.encode("some text here");

                    Uri uri = Uri.parse(uriText);

                    Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                    sendIntent.setData(uri);
                    context.startActivity(Intent.createChooser(sendIntent, "Send email"));

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public class Holder extends  RecyclerView.ViewHolder {

        TextView textView1;
        TextView textVIew2;
        public Holder(View itemView) {
            super(itemView);
            textVIew2= (TextView) itemView.findViewById(R.id.textView2);
            textView1 = (TextView) itemView.findViewById(R.id.textView1);
        }
    }
}
