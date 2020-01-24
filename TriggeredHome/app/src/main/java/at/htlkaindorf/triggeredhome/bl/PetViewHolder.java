package at.htlkaindorf.triggeredhome.bl;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PetViewHolder extends RecyclerView.ViewHolder {
    private ImageView petIMG;
    private TextView petName;
    private TextView petBirthDate;
    private TextView additionalInfo;
    private ImageView petGender;

    public PetViewHolder(@NonNull View itemView, ImageView petIMG, TextView petName, TextView petBirthDate, TextView additionalInfo, ImageView petGender) {
        super(itemView);
        this.petIMG = petIMG;
        this.petName = petName;
        this.petBirthDate = petBirthDate;
        this.additionalInfo = additionalInfo;
        this.petGender = petGender;
    }

    public ImageView getPetIMG() {
        return petIMG;
    }

    public void setPetIMG(ImageView petIMG) {
        this.petIMG = petIMG;
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

    public ImageView getPetGender() {
        return petGender;
    }

    public void setPetGender(ImageView petGender) {
        this.petGender = petGender;
    }
}
