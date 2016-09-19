package erica.todolist2;


import android.content.Context;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends ArrayAdapter<String> {

    /**
     * This adapter takes one layout for one element in the list view and applies it to
     * all elements in the list view for all of the array list.
     */

    private ArrayList<String> taskList;
    @BindView(R.id.delete) Button delete;
    @BindView(R.id.taskName) TextView textView;

    public ListAdapter(Context context, ArrayList<String> tasks) {
        super(context, 0, tasks);
        taskList = tasks;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        final String task = getItem(pos);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        ButterKnife.bind(this,convertView);
        textView.setText(task);                                                                     //sets text in the textView to the string at the position
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View convertView) {                                                 //when textView is clicked, a dialog box is generated using the function below
                dialogMaker(textView);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskList.remove(task);                                                              //removes the task when the delete button is clicked
                notifyDataSetChanged();                                                             //notifies the adapter that the array list has been modified so that it will regenerate the view
            }
            });

            return convertView;
    }




    public void dialogMaker(final TextView t) {
        /**
         * creates an AlertDialog that takes in a TextView in order to set the user
         * input as the text for the TextView
         */

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





