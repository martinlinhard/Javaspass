package at.htlkaindorf.exa_201_contactsapp.bl;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.exa_201_contactsapp.MainActivity;
import at.htlkaindorf.exa_201_contactsapp.R;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    private List<Contact> allContacts;
    private List<Contact> filteredContacts;
    private String filter = "";

    public static ContactAdapter contactAdapter;

    public ContactAdapter() {
        ContactAdapter.contactAdapter = this;

        this.allContacts = IOHandler.getContacts();
        this.filteredContacts = new ArrayList<>();
        this.filteredContacts.addAll(this.allContacts);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        ImageView image = layout.findViewById(R.id.contactImage);
        TextView text = layout.findViewById(R.id.contactText);
        return new ContactViewHolder(layout, image, text, (LinearLayout) layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact c = filteredContacts.get(position);
        holder.setContact(c);
        Picasso.get().load(c.getPicture()).into(holder.getContactImage());
        holder.getContactText().setText(c.getLastname() + ", " + c.getFirstname());
    }

    @Override
    public int getItemCount() {
        return filteredContacts.size();
    }

    private void filter() {
        this.filteredContacts.clear();
        this.filteredContacts.addAll(allContacts);

        this.filteredContacts.removeIf(contact -> !contact.getLastname().toUpperCase().contains(this.filter.toUpperCase()));
        this.notifyDataSetChanged();
    }

    public void setFilter(String filter) {
        this.filter = filter;
        this.filter();
    }

    public void updateContact(Contact oldContact, Contact newContact) {
        int i;
        for (i = 0; i < this.allContacts.size(); i++)
            if (this.allContacts.get(i).equals(oldContact)) break;

        //Log.i(MainActivity.class.getSimpleName(), newContact.toString());
        //Log.i(MainActivity.class.getSimpleName(), this.allContacts.get(i).toString());
        this.allContacts.set(i, newContact);
        this.filter();
    }
}
