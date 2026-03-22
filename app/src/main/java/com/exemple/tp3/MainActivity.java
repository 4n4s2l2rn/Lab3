package com.exemple.tp3;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Patterns;
public class MainActivity extends AppCompatActivity {

    // Déclaration des composants de l'interface
    private EditText inputNom, inputEmail, inputPhone, inputAdresse, inputVille;
    private Button btnValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Liaison avec le fichier XML que nous avons créé à l'étape 1
        setContentView(R.layout.activity_main);

        // Initialisation des vues par leurs IDs respectifs
        initViews();

        // Gestion de l'événement clic
        btnValider.setOnClickListener(view -> {
            envoyerDonnees();
        });
    }
    protected void onResume() {
        super.onResume();
        // Vide tous les champs
        inputNom.setText("");
        inputEmail.setText("");
        inputPhone.setText("");
        inputAdresse.setText("");
        inputVille.setText("");
        // Redonne le focus au premier champ
        inputNom.requestFocus();
    }

    private void initViews() {
        inputNom = findViewById(R.id.nom);
        inputEmail = findViewById(R.id.email);
        inputPhone = findViewById(R.id.phone);
        inputAdresse = findViewById(R.id.adresse);
        inputVille = findViewById(R.id.ville);
        btnValider = findViewById(R.id.btnEnvoyer);
    }

    private void envoyerDonnees() {
        // Extraction des valeurs saisies
        String nomValue = inputNom.getText().toString().trim();
        String emailValue = inputEmail.getText().toString().trim();
        String phoneValue = inputPhone.getText().toString().trim();
        String adresseValue = inputAdresse.getText().toString().trim();
        String villeValue = inputVille.getText().toString().trim();

        // Vérification si les champs cruciaux sont vides
        // 1. On vérifie d'abord si les champs sont vides
        if (nomValue.isEmpty() || emailValue.isEmpty()) {
            Toast.makeText(this, "Nom et Email sont obligatoires", Toast.LENGTH_SHORT).show();
            return; // On arrête TOUT ici si c'est vide
        }

// 2. EN suite (seulement si les champs ne sont pas vides), on vérifie le format de l'email
        if (!Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
            Toast.makeText(this, "Format d'email invalide", Toast.LENGTH_SHORT).show();
            return; // On arrête TOUT ici si l'email est mal écrit
        }

// 3. Si on arrive ici, c'est que TOUT est bon -> On lance l'Intent
        Intent passage = new Intent(MainActivity.this, Screen2Activity.class);

        passage.putExtra("DATA_NOM", nomValue);
        passage.putExtra("DATA_EMAIL", emailValue);
        passage.putExtra("DATA_PHONE", phoneValue);
        passage.putExtra("DATA_ADRESSE", adresseValue);
        passage.putExtra("DATA_VILLE", villeValue);

        startActivity(passage);
    }
}