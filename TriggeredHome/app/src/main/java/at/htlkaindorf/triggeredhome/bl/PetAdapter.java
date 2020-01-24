package at.htlkaindorf.triggeredhome.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.triggeredhome.R;
import at.htlkaindorf.triggeredhome.beans.Cat;
import at.htlkaindorf.triggeredhome.beans.Dog;
import at.htlkaindorf.triggeredhome.beans.Gender;
import at.htlkaindorf.triggeredhome.beans.Pet;


public class PetAdapter extends RecyclerView.Adapter<PetViewHolder> {
    private List<Pet> allPets;
    private List<Pet> filteredPets;

    public PetAdapter(List<Pet> allPets) {
        this.allPets = allPets;
        this.filteredPets = new ArrayList<>();
        this.filteredPets.addAll(allPets);
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item, parent, false);

        ImageView petIMG = layout.findViewById(R.id.petIMG);
        TextView petName = layout.findViewById(R.id.petName);
        TextView petBirthDate = layout.findViewById(R.id.petBirthDate);
        TextView additionalInfo = layout.findViewById(R.id.additionalInfo);
        ImageView petGender = layout.findViewById(R.id.petGender);

        return new PetViewHolder(layout, petIMG, petName, petBirthDate, additionalInfo, petGender);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        Pet p = this.filteredPets.get(position);
        holder.getPetName().setText(p.getName());
        holder.getPetBirthDate().setText(Pet.dtf.format(p.getDateOfBirth()));

        if (p.getGender() == Gender.MALE) {
            holder.getPetGender().setImageResource(R.drawable.male);
        } else {
            holder.getPetGender().setImageResource(R.drawable.female);
        }

        if (p instanceof Cat) {
            holder.getAdditionalInfo().setText(((Cat) p).getColor().toString().toLowerCase());
            Picasso.get().load(((Cat) p).getPictureURI().toString()).into(holder.getPetIMG());
        } else {
            holder.getAdditionalInfo().setText(((Dog) p).getSize().toString().toLowerCase());
            holder.getPetIMG().setImageResource(R.drawable.ic_dog_silhouette);
        }
    }

    @Override
    public int getItemCount() {
        return filteredPets.size();
    }
}
