package br.com.volunapp.volunapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.volunapp.volunapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VacancyListFragment extends Fragment {

    @BindView(R.id.vacancy_recycler)
    RecyclerView vacancyRecycler;

    public VacancyListFragment() {
    }

    public static VacancyListFragment newInstance() {
        return new VacancyListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vacancy_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
