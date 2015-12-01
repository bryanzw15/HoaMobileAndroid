package com.seng4100.hoamobile.View;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.seng4100.hoamobile.API.EndpointInterface;
import com.seng4100.hoamobile.API.ServiceGenerator;
import com.seng4100.hoamobile.Model.Tasklist;
import com.seng4100.hoamobile.R;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AddTasklistViewFragment extends Fragment{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static AddTasklistViewFragment newInstance(String param1, String param2) {
        AddTasklistViewFragment fragment = new AddTasklistViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_tasklist, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        final EditText addTasklist = (EditText) getView().findViewById(R.id.tl_name);


        final Button sumbitButton = (Button) getView().findViewById(R.id.tl_b_add);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumbitButton.setEnabled(false);

                Tasklist tasklist = new Tasklist();

                tasklist.setName(addTasklist.getText().toString());
                tasklist.setActive("1");
                tasklist.setCompleted("0");
                tasklist.setActivityId(mParam1);

                createTasklist(tasklist);

            }
        });
    }
    private void createTasklist(Tasklist tasklist){
        EndpointInterface endpoint = ServiceGenerator.createService(EndpointInterface.class);
        Call<Tasklist> call = endpoint.createTasklist(tasklist);
        call.enqueue(new Callback<Tasklist>() {
            @Override
            public void onResponse(Response<Tasklist> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    TasklistFragmentView tasklistfragment = TasklistFragmentView.newInstance(mParam1, "");;
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, tasklistfragment)
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


}
