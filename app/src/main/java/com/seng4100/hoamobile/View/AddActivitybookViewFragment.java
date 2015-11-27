package com.seng4100.hoamobile.View;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.seng4100.hoamobile.API.EndpointInterface;
import com.seng4100.hoamobile.API.ServiceGenerator;
import com.seng4100.hoamobile.Adapter.ListViewActivityAdapter;
import com.seng4100.hoamobile.Model.Activitybook;
import com.seng4100.hoamobile.R;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class AddActivitybookViewFragment extends Fragment {

    //RadioGroup activeRadioGroup = (RadioGroup) getView().findViewById(R.id.active_radiogroup);


    //int selectedID = activeRadioGroup.getCheckedRadioButtonId();

    //RadioButton selectedButton = (RadioButton) getView().findViewById(selectedID);
    //String activeness = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_activitybook, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);


    }

    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public void onStart() {
        super.onStart();

        final EditText addActivityBook = (EditText) getView().findViewById(R.id.add_activitybook_name);


        final Button sumbitButton = (Button) getView().findViewById(R.id.add_button);
        sumbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumbitButton.setEnabled(false);

                Activitybook activitybook = new Activitybook();

                activitybook.setName(addActivityBook.getText().toString());
                activitybook.setActive("1");

                createActivitybook(activitybook);

            }
        });
    }


    private void createActivitybook(Activitybook activitybook){
        EndpointInterface endpoint = ServiceGenerator.createService(EndpointInterface.class);
        Call<Activitybook> call = endpoint.createActivityBook(activitybook);
        call.enqueue(new Callback<Activitybook>() {
            @Override
            public void onResponse(Response<Activitybook> response, Retrofit retrofit) {
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
}
