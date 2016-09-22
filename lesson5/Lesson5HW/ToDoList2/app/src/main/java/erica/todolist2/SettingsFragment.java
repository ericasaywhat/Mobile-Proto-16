package erica.todolist2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingsFragment extends Fragment {


//    @BindView(R.id.linear_layout) View activityView;
    @BindView(R.id.blue) FloatingActionButton blue;
    @BindView(R.id.red) FloatingActionButton red;
    @BindView(R.id.green) FloatingActionButton green;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
//        View settingView = inflater.inflate(R.layout.fragment_settings, container, false);

        View settingView = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this,settingView);

        final View activityView = getActivity().findViewById(R.id.act_main);



        red = (FloatingActionButton) settingView.findViewById(R.id.red);

//        FloatingActionButtons red, blue, and green set the background of the activity to respective colors when clicked
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityView.setBackgroundColor(Color.RED);
                ((MainActivity) getActivity()).setMyColor(Color.RED);
            }
        });


        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityView.setBackgroundColor(Color.BLUE);
                ((MainActivity) getActivity()).setMyColor(Color.BLUE);
            }
        });


        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityView.setBackgroundColor(Color.GREEN);
                ((MainActivity) getActivity()).setMyColor(Color.GREEN);


            }
        });


        return settingView;


    }
}


