package br.com.volunapp.volunapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.volunapp.volunapp.R;

public class VacancyListFragment extends Fragment {
    public VacancyListFragment() {
    }

    public static VacancyListFragment newInstance() {
        return new VacancyListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vacancy_list, container, false);
    }
}
