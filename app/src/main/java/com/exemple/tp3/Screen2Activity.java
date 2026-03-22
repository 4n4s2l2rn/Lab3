package com.exemple.tp3;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Screen2Activity extends AppCompatActivity {

    private TextView txtRecap;
    private Button btnBack;
    private Button btnShare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Liaison avec le layout XML de l'étape 2
        setContentView(R.layout.activity_screen2);

        // Initialisation des composants
        txtRecap = findViewById(R.id.recap);
        btnBack = findViewById(R.id.btnRetour);

        // 1. Récupération de l'objet Intent
        Intent reception = getIntent();

        if (reception != null) {
            // 2. Extraction des données avec les MÊMES clés que dans MainActivity
            String nom     = reception.getStringExtra("DATA_NOM");
            String email   = reception.getStringExtra("DATA_EMAIL");
            String phone   = reception.getStringExtra("DATA_PHONE");
            String adresse = reception.getStringExtra("DATA_ADRESSE");
            String ville   = reception.getStringExtra("DATA_VILLE");

            // 3. Mise en forme du texte de manière lisible
            StringBuilder builder = new StringBuilder();
            builder.append("👤 Nom : ").append(valider(nom)).append("\n\n");
            builder.append("📧 Email : ").append(valider(email)).append("\n\n");
            builder.append("📞 Tél : ").append(valider(phone)).append("\n\n");
            builder.append("🏠 Adresse : ").append(valider(adresse)).append("\n\n");
            builder.append("📍 Ville : ").append(valider(ville));
            Button btnPartager = findViewById(R.id.btnPartage); // Assure-toi d'avoir l'ID dans le XML

            btnPartager.setOnClickListener(v -> {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Récapitulatif de mes infos");
                shareIntent.putExtra(Intent.EXTRA_TEXT, builder.toString()); // On partage le texte déjà construit

                startActivity(Intent.createChooser(shareIntent, "Partager via..."));
            });
            // 4. Affichage dans le TextView
            txtRecap.setText(builder.toString());
        }

        // 5. Gestion du bouton Retour
        btnBack.setOnClickListener(v -> {
            // Termine l'activité actuelle pour revenir à la précédente
            finish();
        });
    }

    /**
     * Méthode utilitaire pour éviter d'afficher "null" si un champ est vide
     */
    private String valider(String donnee) {
        if (donnee == null || donnee.trim().isEmpty()) {
            return "Non renseigné";
        }
        return donnee;
    }
}
