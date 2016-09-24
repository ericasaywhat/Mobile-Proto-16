package erica.todolist2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyListFragment extends Fragment {

    @BindView(R.id.listView_container) ListView listView;
    @BindView(R.id.newTask) Button newTask;

    ListAdapter adapter;                //declares a list adapter called adapter
    ArrayList <ToDo> taskList;          //declares an array list called taskList
    DBService service;

    public MyListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_list_adapter, container, false);
        service = new DBService(getContext());
        taskList = service.getAll();

        adapter = new ListAdapter(getActivity(), taskList);             //applies the adapter

        ButterKnife.bind(this,mainView);
        listView.setAdapter(adapter);
        // You should use this instead of putting listeners on the individual rows. This will allow
        // you to keep track of which element you clicked on (using the position field)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                adapter.dialogMaker();
                ListAdapter.dialogMaker(taskList, view, position, adapter);
                adapter.notifyDataSetChanged();
             }
         });

        newTask.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder input = new AlertDialog.Builder(getContext());
               input.setTitle("Input");
               input.setCancelable(false);

               final EditText task = new EditText(getContext());
               task.setHint("What do you need to do?");

               input.setView(task);

               input.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       String userString = task.getText().toString();
                       ToDo newTask = new ToDo(userString, 0);
                       service.addToDo(newTask);
                       ArrayList<ToDo> temp = service.getAll();
                       taskList.clear();
                       taskList.addAll(temp);

                       adapter.notifyDataSetChanged();                                  //notifies the adapter that the array list has been modified so that it can regenerate the view

                   }
               });

               input.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.cancel();
                   }
               });

               AlertDialog userInput = input.create();
               userInput.show();                                                       //when the button is clicked view goes back to list fragment
           }
       });

        return mainView;

    }
}

