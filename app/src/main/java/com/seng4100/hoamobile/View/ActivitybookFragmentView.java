package com.seng4100.hoamobile.View;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.seng4100.hoamobile.API.EndpointInterface;
import com.seng4100.hoamobile.API.ServiceGenerator;
import com.seng4100.hoamobile.Adapter.ListViewActivitybookAdapter;
import com.seng4100.hoamobile.Model.Activitybook;
import com.seng4100.hoamobile.R;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ActivitybookFragmentView extends Fragment implements AbsListView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private DynamicListView mListView;

    /**
     * The fragment's Scroll Down Swipe Layout
     */
    SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     *
     * This can also be animathed by: AlphaInAnimationAdapter [Library]
     *
     */
    private ListViewActivitybookAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static ActivitybookFragmentView newInstance(String param1, String param2) {
        ActivitybookFragmentView fragment = new ActivitybookFragmentView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ActivitybookFragmentView() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        getContent();

        // TODO: Change Adapter to display your content
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activitybook, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        // Set the adapter
        mListView = (DynamicListView) view.findViewById(android.R.id.list);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        mListView.setAdapter(mAdapter);


        mListView.enableSwipeToDismiss(new OnDismissCallback() {
            @Override
            public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
                for (int position : reverseSortedPositions) {
                    String id = (mAdapter.getItem(position)).getId();
                    deleteContent(Integer.parseInt(id));
                }
            }
        });

        return view;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getContent();
                if (mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            Log.d("sal", "" + id);
            mListener.onFragmentInteraction(((Activitybook) parent.getItemAtPosition(position)).getId(), "Activity");
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String id, String requestClass);
    }

    private void getContent(){
        EndpointInterface endpoint = ServiceGenerator.createService(EndpointInterface.class);
        Call<List<Activitybook>> call = endpoint.getActivitybooks();
        call.enqueue(new Callback<List<Activitybook>>() {
            @Override
            public void onResponse(Response<List<Activitybook>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    mAdapter = new ListViewActivitybookAdapter(getActivity(), response.body());
                    //mAdapter.setAbsListView(mListView);
                    mListView.setAdapter(mAdapter);

                    if(mAdapter.getCount() == 0){
                        setEmptyText("You don't have any activitybook. Add some of them!");
                    }
                    Log.d("Success", "" + response.raw());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }


    private void deleteContent(int id){
        EndpointInterface endpoint = ServiceGenerator.createService(EndpointInterface.class);
        Call<String> call = endpoint.deleteActivitybook(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                getContent();
                Log.d("Success", "" + response.raw());
            }

            @Override
            public void onFailure(Throwable t) {
                getContent();
                Log.d("Error", t.getMessage());
            }
        });
    }

}
