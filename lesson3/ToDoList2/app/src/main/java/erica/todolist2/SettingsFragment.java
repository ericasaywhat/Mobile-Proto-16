package erica.todolist2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View settingView = inflater.inflate(R.layout.fragment_settings, container, false);

        //FloatingActionButtons red, blue, and green set the background of the activity to respective colors when clicked
        FloatingActionButton red = (FloatingActionButton) settingView.findViewById(R.id.red);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View activityView = getActivity().findViewById(R.id.linear_layout);                 //defines the view whose background we want to set the color to
                activityView.setBackgroundColor(Color.RED);
            }
        });


        FloatingActionButton blue = (FloatingActionButton) settingView.findViewById(R.id.blue);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View activityView = getActivity().findViewById(R.id.linear_layout);
                activityView.setBackgroundColor(Color.BLUE);
            }
        });


        FloatingActionButton green = (FloatingActionButton) settingView.findViewById(R.id.green);
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View activityView = getActivity().findViewById(R.id.linear_layout);
                activityView.setBackgroundColor(Color.GREEN);


            }
        });


        return settingView;


    }
}


