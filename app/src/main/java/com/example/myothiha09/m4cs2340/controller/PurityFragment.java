package com.example.myothiha09.m4cs2340.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.Model;
import com.example.myothiha09.m4cs2340.model.WaterPurityReport;

import java.util.ArrayList;

/**
 * Created by myothiha09 on 3/28/2017.
 */

public class PurityFragment extends ListFragment {
    private CustomPurityAdapter adapter;

    /**
     * Returns a simple list view.
     * @param inflater The LayoutInflater object that can be used to inflate any views in the
     *                 fragment
     * @param container If non-null, this is the parent view that the fragment's UI is attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a
     *                           previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.water_purity_listview, container, false);
    }

    /**
     * Called when the fragment's activity has been created and this fragment's view
     * hierarchy instantiated. Creates and sets up the adapter for the water reports.
     * @param savedInstanceState If the fragment is being re-created from a
     *                           previous saved state, this is the state.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Model model = Model.getInstance();
        ArrayList<WaterPurityReport> report = model.getWaterPurityReports();
        adapter = new CustomPurityAdapter(getActivity(), report);

        final SwipeRefreshLayout mySwipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swiperefresh2);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        adapter.notifyDataSetChanged();
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
        setListAdapter(adapter);
    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This notifies the adapter that the data set may have been changed.
     */
    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
