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

import org.w3c.dom.Text;

public class GeneralScheduleFragment extends Fragment {

    View view;
    ListView LV;
    //List<String> conferenceList;
    List<String> resName, address, _tag;
    TextView restName, resAdd, resTag;
    //TextView textView;
    Activity currentActivity;
    //private ArrayAdapter listViewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        view = inflater.inflate(R.layout.fragment_general_schedule, container, false);
        getActivity().setTitle("General Schedule");
        //conferenceList = new ArrayList<>();
        resName = new ArrayList<>();
        address = new ArrayList<>();
        _tag = new ArrayList<>();
        SharedPreferences confPref = this.getActivity().getSharedPreferences("restaurants", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Map<String, ?> allEntries = confPref.getAll();
        currentActivity = getActivity();
        if(allEntries.size() <= 0) {
            View tview = inflater.inflate(R.layout.listview_layout, container, false);
            restName = (TextView) tview.findViewById(R.id.txtView_ResName);
            restName.setText("No conferences found. Create one.");
        } else {
            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                Restaurant conference = gson.fromJson(entry.getValue().toString(), Restaurant.class);
                //String conferenceName = conference.getName();
                // String CapatitalizedName = conferenceName.substring(0,1).toUpperCase() + conferenceName.substring(1);
                resName.add(conference.getName());
                address.add(conference.getAddress());
                _tag.add(conference.getTag());

            }

            LV = view.findViewById(R.id.conference_list);
            final CustomeListView customeList = new CustomeListView(this.getActivity(),resName,address,_tag);
            //listViewAdapter = new ArrayAdapter<>(this.getActivity(), R.layout.listview_item, conferenceList);
            LV.setAdapter(customeList);
            LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle args = new Bundle();
                    args.putString("con_name", resName.get(position));
                    Tabbed tabbed = new Tabbed();
                    tabbed.setArguments(args);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, tabbed).commit();
                }
            });
        }
        return view;
    }
}
