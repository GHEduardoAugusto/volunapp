package br.com.volunapp.volunapp.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import br.com.volunapp.volunapp.R;

public enum VacancyType {

    CHILD(R.string.volunteering_type_child, R.drawable.volunteering_type_child),
    CULTURE(R.string.volunteering_type_culture, R.drawable.volunteering_type_culture),
    ELDER(R.string.volunteering_type_elder, R.drawable.volunteering_type_elder),
    HEALTH(R.string.volunteering_type_health, R.drawable.volunteering_type_health),
    LEARN(R.string.volunteering_type_learn, R.drawable.volunteering_type_learn),
    SOCIAL(R.string.volunteering_type_social, R.drawable.volunteering_type_social);

    @StringRes
    private final int name;

    @DrawableRes
    private final int icon;

    VacancyType(@StringRes int name, @DrawableRes int icon) {
        this.name = name;
        this.icon = icon;
    }
}