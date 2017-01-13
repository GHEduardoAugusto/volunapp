package br.com.volunapp.volunapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.volunapp.volunapp.R;

public class AssignmentFragment extends Fragment {
    public AssignmentFragment() {
    }

    public static AssignmentFragment newInstance() {
        return new AssignmentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_assignment, container, false);
    }
}
