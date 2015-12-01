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
import com.seng4100.hoamobile.Model.Activity;
import com.seng4100.hoamobile.Model.Activitybook;
import com.seng4100.hoamobile.R;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AddActivityViewFragment extends Fragment{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static AddActivityViewFragment newInstance(String param1, String param2) {
        AddActivityViewFragment fragment = new AddActivityViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_activity, container, false);
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

        final EditText addActivity = (EditText) getView().findViewById(R.id.ac_name);


        final Button sumbitButton = (Button) getView().findViewById(R.id.ac_b_add);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumbitButton.setEnabled(false);

                Activity activity = new Activity();

                activity.setName(addActivity.getText().toString());
                activity.setActivitybookId(mParam1);
                createActivity(activity);

            }
        });
    }
    private void createActivity(Activity activity){
        EndpointInterface endpoint = ServiceGenerator.createService(EndpointInterface.class);
        Call<Activity> call = endpoint.createActivity(activity);
        call.enqueue(new Callback<Activity>() {
            @Override
            public void onResponse(Response<Activity> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    ActivityFragmentView activityfragment = ActivityFragmentView.newInstance(mParam1, "");
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, activityfragment)
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
