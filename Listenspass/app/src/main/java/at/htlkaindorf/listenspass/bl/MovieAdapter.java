package at.htlkaindorf.listenspass.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.listenspass.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {
    private List<Movie> movieList = new ArrayList<Movie>(){{
        add(new Movie("Herr der Ringe", YearMonth.of(2003, 3), 228));
        add(new Movie("Star Wars", YearMonth.of(1980, 3), 228));
        add(new Movie("Spectre", YearMonth.of(2015, 11), 160));
    }};

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);

        TextView tvMovieName = layout.findViewById(R.id.tvMovieName);
        TextView tvReleased= layout.findViewById(R.id.tvReleaseDate);
        TextView tvDuration= layout.findViewById(R.id.tvMovieDuration);

        return new MovieHolder(layout, tvMovieName, tvDuration, tvReleased);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie m = movieList.get(position);
        holder.getTvMovieName().setText(m.getName());
        holder.getTvReleaseDate().setText(m.getReleaseDateString());
        holder.getTvDuration().setText(m.getDurationString());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
