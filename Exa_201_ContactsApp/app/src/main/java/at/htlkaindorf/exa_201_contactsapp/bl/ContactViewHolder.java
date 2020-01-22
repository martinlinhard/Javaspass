package at.htlkaindorf.exa_201_contactsapp.bl;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.htlkaindorf.exa_201_contactsapp.DetailActivity;
import at.htlkaindorf.exa_201_contactsapp.MainActivity;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    private ImageView contactImage;
    private TextView contactText;
    private LinearLayout layout;
    private Contact contact;

    public ContactViewHolder(@NonNull View itemView, ImageView contactImage, TextView contactText, LinearLayout layout) {
        super(itemView);
        this.contactImage = contactImage;
        this.contactText = contactText;
        this.layout = layout;

        this.layout.setOnClickListener(v -> {
            Intent newIntent = new Intent(MainActivity.main, DetailActivity.class);
            newIntent.putExtra("contact", this.contact);
            MainActivity.main.startActivity(newIntent);
        });
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public ImageView getContactImage() {
        return contactImage;
    }

    public void setContactImage(ImageView contactImage) {
        this.contactImage = contactImage;
    }

    public TextView getContactText() {
        return contactText;
    }

    public void setContactText(TextView contactText) {
        this.contactText = contactText;
    }
}
