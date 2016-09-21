package erica.todolist2;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class ListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView = inflater.inflate(R.layout.fragment_list, container, false);

        //each TextView can make a dialog input when clicked using the method dialogMaker defined below
        final TextView textView = (TextView) mainView.findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogMaker(textView);
            }
        });

        final TextView textView2 = (TextView) mainView.findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogMaker(textView2);
            }
        });

        final TextView textView3 = (TextView) mainView.findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogMaker(textView3);
            }
        });

        final TextView textView4 = (TextView) mainView.findViewById(R.id.textView4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogMaker(textView4);
            }
        });

        final TextView textView5 = (TextView) mainView.findViewById(R.id.textView5);
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogMaker(textView5);
            }
        });

        return mainView;
    }

    /**
     * creates an AlertDialog that takes in a TextView in order to set the user
     * input as the text for the TextView
     */
    public void dialogMaker(final TextView t) {
        AlertDialog.Builder input = new AlertDialog.Builder(getContext());
        input.setTitle("Input");
        input.setCancelable(false);

        final EditText task = new EditText(getContext());
        task.setHint("What do you need to do?");

        input.setView(task);

        input.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String userString = task.getText().toString();
                t.setText(userString);
            }
        });

        input.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog userInput = input.create();
        userInput.show();
    }
}

