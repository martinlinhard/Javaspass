package at.htlkaindorf.exa_201_zodiacsign.bl;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.htlkaindorf.exa_201_zodiacsign.MainActivity;
import at.htlkaindorf.exa_201_zodiacsign.R;

public class ZodiacViewHolder extends RecyclerView.ViewHolder {
    private LinearLayout layout;
    private ImageView zodiacImage;
    private TextView nameOfZodiac;
    private TextView timeSpan;

    public ZodiacViewHolder(@NonNull View itemView, View layout, ImageView zodiacImage, TextView nameOfZodiac, TextView timeSpan) {
        super(itemView);
        this.layout = (LinearLayout) layout;
        this.zodiacImage = zodiacImage;
        this.nameOfZodiac = nameOfZodiac;
        this.timeSpan = timeSpan;
        this.layout.setOnClickListener(this::handleClick);
    }

    public ImageView getZodiacImage() {
        return zodiacImage;
    }

    public void setZodiacImage(ImageView zodiacImage) {
        this.zodiacImage = zodiacImage;
    }

    public TextView getNameOfZodiac() {
        return nameOfZodiac;
    }

    public void setNameOfZodiac(TextView nameOfZodiac) {
        this.nameOfZodiac = nameOfZodiac;
    }

    public TextView getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(TextView timeSpan) {
        this.timeSpan = timeSpan;
    }

    public void handleClick(View v) {
        String url = MainActivity.main.getString(R.string.wikipedia_url, nameOfZodiac.getText());
        Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        MainActivity.main.startActivity(viewIntent);
    }
}
