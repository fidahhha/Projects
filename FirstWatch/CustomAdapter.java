package com.example.firstwatch;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class CustomAdapter extends ArrayAdapter<Date> {
        private Context context;
        private OnRectangleClickListener onRectangleClickListener;


    public CustomAdapter(Context context, List<Date> dateList) {
            super(context, R.layout.alarm_notification_box, dateList);
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View rowView = inflater.inflate(R.layout.alarm_notification_box, parent, false);

            // Get the container for rectangles
            //LinearLayout rectangleContainer = rowView.findViewById(R.id.rectangleContainer);
            RelativeLayout rectangleContainer = rowView.findViewById(R.id.rectangleContainer);

            // Create a Random object
            Random random = new Random();

            // Determine the number of rectangles you want to display for each item
            // Generate a random number between 1 and 5 (inclusive)
            int numberOfRectangles = random.nextInt(5) + 1;


            // Add the rectangles to the container
            for (int i = 0; i < numberOfRectangles; i++) {
                View rectangleView = new View(context);
                rectangleView.setId(View.generateViewId()); // Generate a unique ID for each rectangle

                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(950, (int) context.getResources().getDimension(R.dimen.rectangle_height));

                // Set margins to create the gap
                int marginInDp = 20; // Adjust this value to control the gap size in dp

                //layoutParams.setMargins(0, 0, 0, marginInDp);
                layoutParams.setMargins(60, i * (marginInDp + (int) context.getResources().getDimension(R.dimen.rectangle_height)), 0, 0);
                rectangleView.setLayoutParams(layoutParams);


                rectangleView.setLayoutParams(layoutParams); //Set the layout
                rectangleView.setBackgroundResource(R.drawable.list_item); // Set the background drawable

                final int rectanglePosition = i; // Create a final variable to capture the position
                rectangleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onRectangleClickListener != null) {
                            onRectangleClickListener.onRectangleClick(rectanglePosition);
                        }
                    }
                });

                //Add the rectangle to the RelativeLayout
                rectangleContainer.addView(rectangleView);

            }


            int textViewMargin = 158; // Set the margin between TextView elements
            int currentMargin = 45; //Set the initial margin

            // Create a new TextView for each item
            for (int i = 0; i < numberOfRectangles; i++) {
                TextView itemTextView = new TextView(context);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT
                );

                // Set margins between TextView elements
                layoutParams.setMargins(230, currentMargin, 0, 0);
                currentMargin += textViewMargin;


                //Set layout for the TextView
                itemTextView.setLayoutParams(layoutParams);

                //Set the text for the TextView
                itemTextView.setText("hellohellohellohellohellohellohello");

                // Add the TextView to the RelativeLayout
                rectangleContainer.addView(itemTextView);

            }



            // Get the TextView in your item layout
            TextView textView = rowView.findViewById(R.id.text_box_title);

            // Get the date for the current item
            Date itemDate = getItem(position);

            // Calculate the time difference
            long currentTimeMillis = System.currentTimeMillis();
            long itemTimeMillis = itemDate.getTime();
            long timeDifference = currentTimeMillis - itemTimeMillis;

            // Set the text based on the time difference
            if (timeDifference < TimeUnit.DAYS.toMillis(1)) {
                textView.setText("Today");
            } else if (timeDifference < TimeUnit.DAYS.toMillis(2)) {
                textView.setText("Yesterday");
            } else if (timeDifference < TimeUnit.DAYS.toMillis(3)) {
                    textView.setText("2 days ago");
            } else if (timeDifference < TimeUnit.DAYS.toMillis(4)) {
                textView.setText("3 days ago");
            } else if (timeDifference < TimeUnit.DAYS.toMillis(5)){
                textView.setText("A while ago");
            }

            return rowView;
        }



     //Method to set rectangle click listener
    public void setOnRectangleClickListener(OnRectangleClickListener listener) {
        this.onRectangleClickListener = listener;
    }


    public interface OnRectangleClickListener {
        void onRectangleClick(int position);
    }















}


