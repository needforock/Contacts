package ve.needforock.contacts.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ve.needforock.contacts.ContactClickListener;
import ve.needforock.contacts.R;
import ve.needforock.contacts.data.Queries;
import ve.needforock.contacts.models.Contact;

/**
 * Created by Soporte on 14-Aug-17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> contacts = new Queries().contacts();
    private ContactClickListener listener;



    public ContactAdapter(ContactClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        Contact contact = contacts.get(position);
        holder.textView.setText(contact.getName());



        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(holder.itemView.getContext(), "", Toast.LENGTH_SHORT).show();
                int auxPosition = holder.getAdapterPosition();

                Contact auxContact = contacts.get(auxPosition);

                listener.clickedId(auxContact.getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public void update(Contact contact) {

        contacts.add(contact);
        List<Contact> refreshedContacts = new Queries().contacts();
        contacts.clear();
        contacts.addAll(refreshedContacts);
        notifyDataSetChanged();


    }



    public void updateIdDeleted(final long id) {
        //Log.d("ID", String.valueOf(id));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Contact> refreshedContacts = new Queries().contactRemove(id);
                contacts.clear();
                contacts.addAll(refreshedContacts);
                notifyDataSetChanged();
            }
        }, 400);


    }


    static class ViewHolder extends RecyclerView.ViewHolder {


        private TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.nameTv);


        }
    }
}
