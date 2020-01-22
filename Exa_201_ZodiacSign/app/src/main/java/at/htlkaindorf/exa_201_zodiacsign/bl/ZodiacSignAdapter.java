package at.htlkaindorf.exa_201_zodiacsign.bl;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.htlkaindorf.exa_201_zodiacsign.R;

public class ZodiacSignAdapter extends RecyclerView.Adapter<ZodiacViewHolder> {
    private List<ZodiacSign> signs = Arrays.asList(
            new ZodiacSign("Aquarius", MonthDay.of(1, 21), R.drawable.aquarius),
            new ZodiacSign("Pisces", MonthDay.of(2, 18), R.drawable.pisces),
            new ZodiacSign("Aries", MonthDay.of(3, 20), R.drawable.aries),
            new ZodiacSign("Taurus", MonthDay.of(4, 20), R.drawable.aries),
            new ZodiacSign("Gemini", MonthDay.of(5, 21), R.drawable.gemini),
            new ZodiacSign("Cancer", MonthDay.of(6, 21), R.drawable.cancer),
            new ZodiacSign("Leo", MonthDay.of(7, 23), R.drawable.leo),
            new ZodiacSign("Virgo", MonthDay.of(8, 23), R.drawable.aries),
            new ZodiacSign("Libra", MonthDay.of(9, 23), R.drawable.libra),
            new ZodiacSign("Scorpius", MonthDay.of(10, 23), R.drawable.aries),
            new ZodiacSign("Sagittarius", MonthDay.of(11, 22), R.drawable.aries),
            new ZodiacSign("Capricornus", MonthDay.of(12, 22), R.drawable.capricornus)
    );

    @NonNull
    @Override
    public ZodiacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.zodiac_item, parent, false);

        ImageView image = layout.findViewById(R.id.zodiacImage);
        TextView name = layout.findViewById(R.id.zodiacName);
        TextView date = layout.findViewById(R.id.zodiacDate);

        return new ZodiacViewHolder(layout, layout, image, name, date);
    }

    @Override
    public void onBindViewHolder(@NonNull ZodiacViewHolder holder, int position) {
        ZodiacSign s = signs.get(position);
        int other = position + 1 == signs.size() ? 0 : position + 1;

        holder.getNameOfZodiac().setText(s.getName());
        holder.getTimeSpan().setText(s.getFormattedDate(signs.get(other)));
        holder.getZodiacImage().setImageResource(s.getDrawableID());
    }

    @Override
    public int getItemCount() {
        return signs.size();
    }
}
