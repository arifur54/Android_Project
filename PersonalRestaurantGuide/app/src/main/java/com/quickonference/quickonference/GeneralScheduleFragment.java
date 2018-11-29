package com.quickonference.quickonference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import java.util.*;
import com.google.gson.*;
import com.quickonference.quickonference.conference.Restaurant;

public class GeneralScheduleFragment extends Fragment {

    View view;
    ListView LV;
    List<String> conferenceList;
    TextView textView;
    Activity currentActivity;
    private ArrayAdapter listViewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        view = inflater.inflate(R.layout.fragment_general_schedule, container, false);
        getActivity().setTitle("General Schedule");
        conferenceList = new ArrayList<String>();
        SharedPreferences confPref = this.getActivity().getSharedPreferences("restaurants", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Map<String, ?> allEntries = confPref.getAll();
        currentActivity = getActivity();
        if(allEntries.size() <= 0) {
            View tview = inflater.inflate(R.layout.listview_item, container, false);
            textView = (TextView) tview.findViewById(R.id.listview_item_1);
            textView.setText("No conferences found. Create one.");
        } else {
            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                Restaurant conference = gson.fromJson(entry.getValue().toString(), Restaurant.class);
                String conferenceName = conference.getName();
                // String CapatitalizedName = conferenceName.substring(0,1).toUpperCase() + conferenceName.substring(1);
                conferenceList.add(conferenceName);
            }

            LV = view.findViewById(R.id.conference_list);
            listViewAdapter = new ArrayAdapter<>(this.getActivity(), R.layout.listview_item, conferenceList);
            LV.setAdapter(listViewAdapter);
            LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle args = new Bundle();
                    args.putString("con_name", conferenceList.get(position));
                    Tabbed tabbed = new Tabbed();
                    tabbed.setArguments(args);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, tabbed).commit();
                }
            });
        }
        return view;
    }
}
