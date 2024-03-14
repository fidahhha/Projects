package com.example.firstwatch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class alarmaddcomments extends Fragment {

    View view;
    TextView commentTextView;
    Button addButton;
    ArrayList<String> commentsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_alarmaddcomments, container, false);
        commentTextView = view.findViewById(R.id.allComments);

        Log.d("MyApp", "commentTextView: " + commentTextView);
        addButton = view.findViewById(R.id.addCommentBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = "New Comment"; // You can change this text
                commentsList.add(comment);
                updateCommentTextView();
            }
        });

        return view;
    }

    private void updateCommentTextView() {
        // Concatenate and display the comments in the TextView
        StringBuilder sb = new StringBuilder();
        for (String comment : commentsList) {
            sb.append(comment).append("\n");
        }
        commentTextView.setText(sb.toString());
    }
}
