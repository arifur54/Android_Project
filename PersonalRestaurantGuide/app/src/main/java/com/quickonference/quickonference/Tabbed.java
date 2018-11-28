package com.quickonference.quickonference;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.view.ViewGroup;
import com.quickonference.quickonference.conference.Conference;
import com.google.gson.*;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Tabbed extends Fragment {
    TabHost tabHost;
    SharedPreferences confPref;
    ListView sponsorListView;
    ListView attendeeListView;
    String [] sponsorList = {"Coca Cola", "Scotia Bank", "RBC", "Ferrari", "Labatt", "LCBO"};
    String [] addtendeeList = {"James McPherson", "Arun Patel", "Shawn Mendes", "Jospeh Martini", "Mosh Hamadani", "Andrew Suarez", "Aman Singh"};
    private ArrayAdapter sponsorAdapter;
    private ArrayAdapter attendeeAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String con_name = getArguments().getString("con_name");
        getActivity().setTitle(con_name);
        confPref = this.getActivity().getSharedPreferences("conferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = confPref.getString(con_name, "");
        final Conference conference = gson.fromJson(json, Conference.class);
        View view = inflater.inflate(R.layout.fragment_tabbed, container, false);
        Button button =  view.findViewById(R.id.btn_attend_conf);
        TabHost host = (TabHost) view.findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Details");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Details");
        host.addTab(spec);
        TextView conf_name = (TextView)view.findViewById(R.id.tab1_name);
        conf_name.setText(conference.getName());
        TextView conf_address = (TextView)view.findViewById(R.id.tab1_address);
        conf_address.setText(conference.getAddress());
        TextView conf_datetime = (TextView)view.findViewById(R.id.tab1_date_time);
        conf_datetime.setText("On " + conference.getDate() + " at " + conference.getTime());
        TextView conf_details = (TextView)view.findViewById(R.id.tab1_details);
        conf_details.setText(conference.getDetails());
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Conference.addToSchedule(conference,getActivity());
                Toast.makeText(getActivity(), conference.getName() + " has been added to your schedule.", Toast.LENGTH_LONG).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                        new my_schedule()).commit();
            }
        });
        //Tab 2

        attendeeListView = (ListView) view.findViewById(R.id.conf_attendees_listview);
        attendeeAdapter= new ArrayAdapter<>(this.getActivity(), R.layout.listview_item, addtendeeList);
        attendeeListView.setAdapter(attendeeAdapter);
        spec = host.newTabSpec("Attendees");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Attendees");
        host.addTab(spec);

        //Tab 3
        sponsorListView = (ListView) view.findViewById(R.id.conf_sponsors_listview);
        sponsorAdapter= new ArrayAdapter<>(this.getActivity(), R.layout.listview_item, sponsorList);
        sponsorListView.setAdapter(sponsorAdapter);
        spec = host.newTabSpec("Sponsors");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Sponsors");
        host.addTab(spec);
        return view;
    }
}
