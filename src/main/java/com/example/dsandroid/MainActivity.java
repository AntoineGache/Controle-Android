package com.example.dsandroid;

import static java.lang.Math.sqrt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private int[] tab = {R.id.d2, R.id.d1, R.id.d0};
    private Toast toast;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText a = findViewById(R.id.a);
        EditText b = findViewById(R.id.b);
        EditText c = findViewById(R.id.c);

        textView = findViewById(R.id.result);
        toast = new Toast(this);

        final int[] idBouton = new int[1];
        idBouton[0] = R.id.d2;

        Button boutonCalcul = findViewById(R.id.calcul);
        boutonCalcul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calcul(idBouton[0], a, b, c, textView);
            }
        });

        for (int i = 0; i < tab.length; i++) {
            Button bouton = findViewById(tab[i]);
            bouton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    idBouton[0] = bouton.getId();
                    toast.makeText(MainActivity.this,bouton.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void calcul(int id, EditText parA, EditText parB, EditText parC, TextView textView) {

        boolean check =  check(parA,parB,parC);
        System.out.println(check);
        if(check == false) {
            Toast saisie = new Toast(MainActivity.this);
            saisie.makeText(MainActivity.this, "Erreur de saisie, veuillez saisir toutes les valeurs", Toast.LENGTH_SHORT);
            saisie.show();
            return;
        }

        double chA = Double.parseDouble(parA.getText().toString());
        double chB = Double.parseDouble(parB.getText().toString());
        double chC = Double.parseDouble(parC.getText().toString());

        if (id == tab[0]) {
            degre2(chA,chB,chC);
        }
        else if(id == tab[1]) {
            degre1(chA,chB,chC);
        }
        else if(id == tab[2]) {
            droite(chA,chB,chC);
        }
    }

    private void degre2(double parA, double parB, double parC) {

        double d= parB*parB - (4*parA*parC);
        if(d > 0) {
            double x1 = (parB - sqrt(d))/(2 * parA);
            double x2 = (parB + sqrt(d))/(2 * parA);;
            textView.setText("L'équation est : " + parA + "x^2 + " + parB + " x " + parC +" = 0" + "\n"
                    + "----------------------------------------------------------------------" +
                    "\n" + "La solution est x1 = " + x1 + " et x2 = " + x2);
        }
        else if(d == 0) {
            double x = (- parB)/(2 * parA);
            textView.setText("L'équation est : " + parA + "x" + " = " + parC + "\n"
                    + "----------------------------------------------------------------------" +
                    "\n" + "La solution est x = " + x);
        }
        else {
            textView.setText("Il n'y a pas de solution pour les valeurs saisies");
        }
    }

    private void degre1(double parA, double parB, double parC) {

        double x;

        if(parA == 0 && parB != 0) {
            textView.setText("Il n'y a pas de solution car a = 0");
        }
        else if(parA != 0 && parB == 0) {
            x = (parC - parB)/parA;
            textView.setText("L'équation est : " + parA + "x" + " = " + parC + "\n"
                    + "----------------------------------------------------------------------" +
                    "\n" + "La solution est x = " + x);
        }
        else if(parA == 0 & parB == 0) {
            textView.setText("Il n'y a pas de solution car a et b sont égaux à 0");
        }
        else {
            x = (parC - parB)/parA;
            textView.setText("L'équation est : " + parA + "x + " + parB + " = " + parC + "\n"
                    + "----------------------------------------------------------------------" +
                    "\n" + "La solution est x = " + x );
        }
    }

    private void droite(double parA, double parB, double parC) {

        if(parA == 0 && parB == 0) {
            textView.setText("Il n'y a pas de solution par les valeurs saisies");
        }
        else if(parA == 0 && parB != 0) {
            textView.setText("L'équation de la droite est : " + parA + "x + " + parB + "y + " + parC + " = 0" + "\n"
                    + "----------------------------------------------------------------------" +
                    "\n" + "Il n'y a pas d'équation réduite");
        }
        else if(parA != 0 && parB == 0) {
            textView.setText("L'équation de la droite est : " + parA + "x + " + parB + "y + " + parC + " = 0" + "\n"
                    + "----------------------------------------------------------------------" +
                    "\n" + "Il n'y a pas d'équation réduite");
        }
        else {
            double m = -parA/parB;
            double p = -(parC/parB);
            textView.setText("L'équation de la droite est : " + parA + "x + " + parB + "y + " + parC + " = 0" + "\n"
                    + "----------------------------------------------------------------------" +
                    "\n" + "L'équation réduite est : y = " + m + "x + " + p);
        }
    }

    private boolean check(EditText parA, EditText parB, EditText parC) {

        if(parA.getText().toString().matches("") || parB.getText().toString().matches("") || parC.getText().toString().matches("")) {
            System.out.println(parA.getText().toString());
            System.out.println(parB.getText().toString());
            System.out.println(parC.getText().toString());
            return false;
        }
        else
            return true;
    }
}