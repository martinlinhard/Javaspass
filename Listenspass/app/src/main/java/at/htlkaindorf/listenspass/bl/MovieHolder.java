package at.htlkaindorf.listenspass.bl;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieHolder extends RecyclerView.ViewHolder {
    //Klasse mit instanzvariablen aus der list_element.xml
    private TextView tvMovieName;
    private TextView tvDuration;
    private TextView tvReleaseDate;

    public TextView getTvMovieName() {
        return tvMovieName;
    }

    public void setTvMovieName(TextView tvMovieName) {
        this.tvMovieName = tvMovieName;
    }

    public TextView getTvDuration() {
        return tvDuration;
    }

    public void setTvDuration(TextView tvDuration) {
        this.tvDuration = tvDuration;
    }

    public TextView getTvReleaseDate() {
        return tvReleaseDate;
    }

    public void setTvReleaseDate(TextView tvReleaseDate) {
        this.tvReleaseDate = tvReleaseDate;
    }

    public MovieHolder(@NonNull View itemView, TextView tvMovieName, TextView tvDuration, TextView tvReleaseDate) {
        super(itemView);
        this.tvMovieName = tvMovieName;
        this.tvDuration = tvDuration;
        this.tvReleaseDate = tvReleaseDate;
    }
}
