package at.htlkaindorf.exa_206_pethome.beans;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PetsViewHolder extends RecyclerView.ViewHolder {
    private ImageView petIMG;
    private ImageView petGender;
    private TextView petName;
    private TextView petBirthDate;
    private TextView additionalInfo;

    public PetsViewHolder(@NonNull View itemView, ImageView petIMG, ImageView petGender, TextView petName, TextView petBirthDate, TextView additionalInfo) {
        super(itemView);
        this.petIMG = petIMG;
        this.petGender = petGender;
        this.petName = petName;
        this.petBirthDate = petBirthDate;
        this.additionalInfo = additionalInfo;
    }

    public ImageView getPetIMG() {
        return petIMG;
    }

    public void setPetIMG(ImageView petIMG) {
        this.petIMG = petIMG;
    }

    public ImageView getPetGender() {
        return petGender;
    }

    public void setPetGender(ImageView petGender) {
        this.petGender = petGender;
    }

    public TextView getPetName() {
        return petName;
    }

    public void setPetName(TextView petName) {
        this.petName = petName;
    }

    public TextView getPetBirthDate() {
        return petBirthDate;
    }

    public void setPetBirthDate(TextView petBirthDate) {
        this.petBirthDate = petBirthDate;
    }

    public TextView getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(TextView additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
