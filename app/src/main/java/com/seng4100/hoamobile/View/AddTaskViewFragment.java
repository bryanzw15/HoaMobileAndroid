package com.seng4100.hoamobile.View;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.seng4100.hoamobile.API.EndpointInterface;
import com.seng4100.hoamobile.API.ServiceGenerator;
import com.seng4100.hoamobile.Adapter.ExpandbleListViewTasklistAdapter;
import com.seng4100.hoamobile.Model.Activity;
import com.seng4100.hoamobile.Model.Task;
import com.seng4100.hoamobile.Model.Tasklist;
import com.seng4100.hoamobile.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AddTaskViewFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private ExpandbleListViewTasklistAdapter mAdapter;
    Spinner spinner;
    List<Tasklist> tasks;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_task, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        final EditText addTask = (EditText) getView().findViewById(R.id.t_name);
        spinner = (Spinner) getView().findViewById(R.id.t_spinner);

        spinner.setOnItemSelectedListener(this);



        final Button sumbitButton = (Button) getView().findViewById(R.id.t_b_add);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumbitButton.setEnabled(false);

                Task task = new Task();

                task.setDescription(addTask.getText().toString());

                createTask(task);

            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }



    private void createTask(Task task){
        EndpointInterface endpoint = ServiceGenerator.createService(EndpointInterface.class);
        Call<Task> call = endpoint.createTask(task);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Response<Task> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    ActivitybookFragmentView activitybookfragment = new ActivitybookFragmentView();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, activitybookfragment)
                            .addToBackStack(null)
                            .commit();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public void getContent(String mParam1){
        EndpointInterface endpoint = ServiceGenerator.createService(EndpointInterface.class);
        Call<Activity> call = endpoint.getActivity(Integer.parseInt(mParam1));
        call.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Response<Activity> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    tasks = response.body().getTasklists();

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getTasksNames(tasks));

                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinner.setAdapter(dataAdapter);
                    Log.d("Success", response.raw().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public List<String> getTasksNames(List<Tasklist> tasklists){
        List<String> tasksNames = new ArrayList<>();

        for (int i = 0; i < tasklists.size(); i++){
            tasksNames.add(tasklists.get(i).getName());
        }

        return tasksNames;
    }


}
