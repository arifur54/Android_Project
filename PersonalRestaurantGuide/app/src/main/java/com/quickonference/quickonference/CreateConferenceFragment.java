package com.quickonference.quickonference;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.ViewGroup;
import android.widget.Toast;
import com.quickonference.quickonference.conference.Conference;


public class CreateConferenceFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_create_conference, container, false);
        getActivity().setTitle("Create Conference");
        Button button =  view.findViewById(R.id.create_conf_btn);
        final EditText conferenceName_txt =  view.findViewById(R.id.create_conf_name);
        final EditText conferenceAddress_txt =  view.findViewById(R.id.create_conf_address);
        final EditText conferenceDate_txt =  view.findViewById(R.id.create_conf_date);
        final EditText conferenceTime_txt = view.findViewById(R.id.create_conf_time);
        final EditText conferenceDetails_txt =  view.findViewById(R.id.create_conf_details);
        final Activity currentActivity = getActivity();
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
                {
                 String conferenceName = conferenceName_txt.getText().toString();
                 String conferenceAddress = conferenceAddress_txt.getText().toString();
                 String conferenceDate = conferenceDate_txt.getText().toString();
                 String conferenceTime= conferenceTime_txt.getText().toString();
                 String conferenceDetails = conferenceDetails_txt.getText().toString();
                Conference conference = new Conference(conferenceName, conferenceAddress, conferenceDate, conferenceTime, conferenceDetails);
                Conference.storeConference(conference,currentActivity);
                Toast.makeText(currentActivity, conference.getName() + " has been created.", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new GeneralScheduleFragment()).commit();
            }
        });
        return view;
    }
}
