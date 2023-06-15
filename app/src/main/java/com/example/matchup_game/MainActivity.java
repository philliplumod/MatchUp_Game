package com.example.matchup_game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ImageView[] images = new ImageView[12];
    Integer[] cardArray = {1, 2, 3, 4, 5, 6, 11, 12, 13, 14, 15, 16};
    int[] frontOfCardsResources = {
            R.drawable.asset1,
            R.drawable.asset2,
            R.drawable.asset3,
            R.drawable.asset4,
            R.drawable.asset5,
            R.drawable.asset6,
            R.drawable.asset01,
            R.drawable.asset02,
            R.drawable.asset03,
            R.drawable.asset04,
            R.drawable.asset05,
            R.drawable.asset06
    };

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images[0] = findViewById(R.id.image01);
        images[1] = findViewById(R.id.image02);
        images[2] = findViewById(R.id.image03);
        images[3] = findViewById(R.id.image04);
        images[4] = findViewById(R.id.image05);
        images[5] = findViewById(R.id.image06);
        images[6] = findViewById(R.id.image07);
        images[7] = findViewById(R.id.image08);
        images[8] = findViewById(R.id.image09);
        images[9] = findViewById(R.id.image10);
        images[10] = findViewById(R.id.image11);
        images[11] = findViewById(R.id.image12);

        for (int i = 0; i < images.length; i++) {
            images[i].setTag(i);
            images[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int theCard = (int) v.getTag();
                    doStuff(images[theCard], theCard);
                }
            });
        }

        Collections.shuffle(Arrays.asList(cardArray));
    }
    private void doStuff(ImageView image, int card) {
        int currentCard = cardArray[card] > 10 ? cardArray[card] - 10 : cardArray[card];
        image.setImageResource(frontOfCardsResources[currentCard - 1]);
        image.setEnabled(false);

        if (cardNumber == 1) {
            firstCard = currentCard;
            clickedFirst = card;
        } else if (cardNumber == 2) {
            secondCard = currentCard;
            clickedSecond = card;
            disableAllImages();
            new Handler().postDelayed(this::checkCard, 1000);
        }

        cardNumber = cardNumber % 2 + 1;
    }

    private void checkCard() {
        ImageView image1 = images[clickedFirst];
        ImageView image2 = images[clickedSecond];
        if (firstCard == secondCard) {
            disableCard(image1);
            disableCard(image2);
        } else {
            hideCard(image1);
            hideCard(image2);
        }
        enableAllImages();
        checkEnd();
    }
    private void disableCard(ImageView image) {
        image.setEnabled(false);
        image.setVisibility(View.INVISIBLE);
    }
    private void hideCard(ImageView image) {
        image.setImageResource(R.drawable.assets7);
    }
    private void checkEnd() {
        boolean allCardsMatched = true;
        for (ImageView image : images) {
            if (image.getVisibility() == View.VISIBLE) {
                allCardsMatched = false;
                break;
            }
        }
        if (allCardsMatched) {
            showGameOverDialog();
        }
    }
    private void showGameOverDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setMessage("YOU WIN!")
                .setCancelable(false)
                .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(
                                new Intent(getApplicationContext(), MainActivity.class)
                        );
                        finish();
                    }
                })
                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void disableAllImages() {
        int i = 0;
        while (i < images.length) {
            images[i].setEnabled(false);
            i++;
        }
    }
    private void enableAllImages() {
        int i = 0;
        while (i < images.length) {
            if (images[i].getVisibility() == View.VISIBLE) {
                images[i].setEnabled(true);
            }
            i++;
        }
    }
}
