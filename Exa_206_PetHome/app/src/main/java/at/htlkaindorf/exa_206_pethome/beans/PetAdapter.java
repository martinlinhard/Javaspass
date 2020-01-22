package at.htlkaindorf.exa_206_pethome.beans;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import at.htlkaindorf.exa_206_pethome.R;

public class PetAdapter extends RecyclerView.Adapter<PetsViewHolder> {

    private List<Pet> pets;

    public PetAdapter(List<Pet> pets) {
        this.pets = pets;
    }

    @NonNull
    @Override
    public PetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item, parent, false);

        ImageView image = layout.findViewById(R.id.petIMG);
        ImageView imageG = layout.findViewById(R.id.petGender);
        TextView petName = layout.findViewById(R.id.petName);
        TextView petBirthDate = layout.findViewById(R.id.petBirthDate);
        TextView additionalInfo = layout.findViewById(R.id.additionalInfo);

        return new PetsViewHolder(layout, image, imageG, petName, petBirthDate, additionalInfo);
    }

    @Override
    public void onBindViewHolder(@NonNull PetsViewHolder holder, int position) {
        Pet p = this.pets.get(position);
        String additionalInfo;

        if (p instanceof Cat) {
            additionalInfo = ((Cat) p).getColor().toString().toLowerCase();
            Picasso.get().load(((Cat) p).getPictureURI().toString()).into(holder.getPetIMG());
        } else {
            additionalInfo = ((Dog) p).getSize().toString().toLowerCase();
            holder.getPetIMG().setImageResource(R.drawable.ic_dog_silhouette);
        }

        if (p.getGender() == MyGender.MALE) {
            holder.getPetGender().setImageResource(R.drawable.male);
        } else {
            holder.getPetGender().setImageResource(R.drawable.female);
        }

        holder.getPetName().setText(p.getName());
        holder.getPetBirthDate().setText(Pet.dtf.format(p.getDateOfBirth()));
        holder.getAdditionalInfo().setText(additionalInfo);
    }

    @Override
    public int getItemCount() {
        return this.pets.size();
    }
}
