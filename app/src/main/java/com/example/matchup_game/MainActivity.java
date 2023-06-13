
package com.example.matchup_game;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    TextView playerScore;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12;

    Integer[] cardArray = {1, 2, 3, 4, 5, 6, 11, 12, 13, 14, 15, 16};

    int asset1, asset2, asset3, asset4, asset5, asset6, asset01 , asset02, asset03, asset04, asset05, asset06;

    int firstCard, secondCard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;
    int playerPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerScore = findViewById(R.id.score);


        image1 = findViewById(R.id.image01);
        image2 = findViewById(R.id.image02);
        image3 = findViewById(R.id.image03);
        image4 = findViewById(R.id.image04);
        image5 = findViewById(R.id.image05);
        image6 = findViewById(R.id.image06);
        image7 = findViewById(R.id.image07);
        image8 = findViewById(R.id.image08);
        image9 = findViewById(R.id.image09);
        image10 = findViewById(R.id.image10);
        image11 = findViewById(R.id.image11);
        image12 = findViewById(R.id.image12);

        image1.setTag("0");
        image2.setTag("1");
        image3.setTag("2");
        image4.setTag("3");
        image5.setTag("4");
        image6.setTag("5");
        image7.setTag("6");
        image8.setTag("7");
        image9.setTag("8");
        image10.setTag("9");
        image11.setTag("10");
        image12.setTag("11");

        frontOfCardsResources();
        Collections.shuffle(Arrays.asList(cardArray));

        image1.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(image1, theCard);
        });
        image2.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(image2, theCard);
        });

        image3.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(image3, theCard);
        });

        image4.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(image4, theCard);
        });
        image5.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(image5, theCard);
        });

        image6.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(image6, theCard);
        });

        image7.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(image7, theCard);
        });

        image8.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(image8, theCard);
        });

        image9.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(image9, theCard);
        });

        image10.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(image10, theCard);
        });

        image11.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(image11, theCard);
        });

        image12.setOnClickListener(v -> {
            int theCard = Integer.parseInt((String) v.getTag());
            doStuff(image12, theCard);
        });
        // ... add click listeners for the other images

    }

    private void frontOfCardsResources() {
                asset1 = R.drawable.asset1;
        asset2 = R.drawable.asset2;
        asset3 = R.drawable.asset3;
        asset4 = R.drawable.asset4;
        asset5 = R.drawable.asset5;
        asset6 = R.drawable.asset6;
        asset01 = R.drawable.asset01;
        asset02 = R.drawable.asset02;
        asset03 = R.drawable.asset03;
        asset04 = R.drawable.asset04;
        asset05 = R.drawable.asset05;
        asset06 = R.drawable.asset06;
    }

    private void doStuff(ImageView image, int card) {
        if (cardArray[card] == 1) {
            image.setImageResource(asset1);
        } else if (cardArray[card] == 2) {
            image.setImageResource(asset2);
        } else if (cardArray[card] == 3) {
            image.setImageResource(asset3);
        } else if (cardArray[card] == 4) {
            image.setImageResource(asset4);
        } else if (cardArray[card] == 5) {
            image.setImageResource(asset5);
        } else if (cardArray[card] == 6) {
            image.setImageResource(asset6);
        } else if (cardArray[card] == 11) {
            image.setImageResource(asset01);
        } else if (cardArray[card] == 12) {
            image.setImageResource(asset02);
        } else if (cardArray[card] == 13) {
            image.setImageResource(asset03);
        } else if (cardArray[card] == 14) {
            image.setImageResource(asset04);
        } else if (cardArray[card] == 15) {
            image.setImageResource(asset05);
        } else if (cardArray[card] == 16) {
            image.setImageResource(asset06);
        }
        if (cardNumber == 1) {
            firstCard = cardArray[card];
            if (firstCard > 200) {
                firstCard = firstCard - 100;
            }
            cardNumber = 2;
            clickedFirst = card;
            image.setEnabled(false);
        } else if (cardNumber == 2) {
            secondCard = cardArray[card];
            if (secondCard > 200) {
                secondCard = secondCard - 100;
            }
            cardNumber = 1;
            clickedSecond = card;

            // Disable all images while calculating
            disableAllImages();

            Handler handler = new Handler();
            handler.postDelayed(this::calculate, 1000);
        }
    }

    @SuppressLint("SetTextI18n")
    private void calculate() {
        if (firstCard == secondCard) {
            // Disable clickability for matching cards
            disableCard(clickedFirst);
            disableCard(clickedSecond);

            playerPoints++;
            playerScore.setText("Score: " + playerPoints); // Update the score text
        } else {
            resetImages();
        }

        // Enable all images after calculation
        enableAllImages();

        checkEnd();
    }
    private void disableCard(int clickedCard) {
        ImageView imageView = getImageViewByTag(clickedCard);
        if (imageView != null) {
            imageView.setEnabled(false);
        }
    }


    private void checkEnd() {
        if (image1.getVisibility() == View.INVISIBLE &&
                image2.getVisibility() == View.INVISIBLE &&
                image3.getVisibility() == View.INVISIBLE &&
                image4.getVisibility() == View.INVISIBLE &&
                image5.getVisibility() == View.INVISIBLE &&
                image6.getVisibility() == View.INVISIBLE &&
                image7.getVisibility() == View.INVISIBLE &&
                image8.getVisibility() == View.INVISIBLE &&
                image9.getVisibility() == View.INVISIBLE &&
                image10.getVisibility() == View.INVISIBLE &&
                image11.getVisibility() == View.INVISIBLE &&
                image12.getVisibility() == View.INVISIBLE) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Game Over! \nScore: " + playerPoints)
                    .setCancelable(false)
                    .setPositiveButton("NEW", (dialogInterface, i) -> {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("EXIT", (dialogInterface, i) -> finish());
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    private void hideCard(int clickedCard) {
        ImageView imageView = getImageViewByTag(clickedCard);
        if (imageView != null) {
            imageView.setImageResource(View.INVISIBLE);
            imageView.setEnabled(false);
        }
    }


    private void resetImages() {
        image1.setImageResource(R.drawable.assets7);
        image2.setImageResource(R.drawable.assets7);
        image3.setImageResource(R.drawable.assets7);
        image4.setImageResource(R.drawable.assets7);
        image5.setImageResource(R.drawable.assets7);
        image6.setImageResource(R.drawable.assets7);
        image7.setImageResource(R.drawable.assets7);
        image8.setImageResource(R.drawable.assets7);
        image9.setImageResource(R.drawable.assets7);
        image10.setImageResource(R.drawable.assets7);
        image11.setImageResource(R.drawable.assets7);
        image12.setImageResource(R.drawable.assets7);
    }

    private void disableAllImages() {
        image1.setEnabled(false);
        image2.setEnabled(false);
        image3.setEnabled(false);
        image4.setEnabled(false);
        image5.setEnabled(false);
        image6.setEnabled(false);
        image7.setEnabled(false);
        image8.setEnabled(false);
        image9.setEnabled(false);
        image10.setEnabled(false);
        image11.setEnabled(false);
        image12.setEnabled(false);
    }

    private void enableAllImages() {
        image1.setEnabled(true);
        image2.setEnabled(true);
        image3.setEnabled(true);
        image4.setEnabled(true);
        image5.setEnabled(true);
        image6.setEnabled(true);
        image7.setEnabled(true);
        image8.setEnabled(true);
        image9.setEnabled(true);
        image10.setEnabled(true);
        image11.setEnabled(true);
        image12.setEnabled(true);
    }

    private ImageView getImageViewByTag(int tag) {
        switch (tag) {
            case 0:
                return image1;
            case 1:
                return image2;
            case 2:
                return image3;
            case 3:
                return image4;
            case 4:
                return image5;
            case 5:
                return image6;
            case 6:
                return image7;
            case 7:
                return image8;
            case 8:
                return image9;
            case 9:
                return image10;
            case 10:
                return image11;
            case 11:
                return image12;
            default:
                return null;
        }
    }
}
