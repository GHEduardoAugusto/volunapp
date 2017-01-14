package br.com.volunapp.volunapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.volunapp.volunapp.R;
import br.com.volunapp.volunapp.model.VacancyType;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class HomeFragment extends Fragment {

    @BindView(R.id.account_input_layout_name)
    TextInputLayout inputLayoutName;

    @BindView(R.id.account_input_name)
    EditText inputName;

    @BindView(R.id.account_input_layout_adress)
    TextInputLayout inputLayoutAdress;

    @BindView(R.id.account_input_adress)
    EditText inputAdress;

    @BindView(R.id.account_input_layout_phone)
    TextInputLayout inputLayoutPhone;

    @BindView(R.id.account_input_phone)
    EditText inputPhone;

    @BindView(R.id.account_input_layout_mail)
    TextInputLayout inputLayoutMail;

    @BindView(R.id.account_input_mail)
    EditText inputMail;

    private boolean firstAccessName = true;
    private boolean firstAccessAdress = true;
    private boolean firstAccessPhone = true;
    private boolean firstAccessMail = true;

    private List<VacancyType> vacancyTypes;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vacancyTypes = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnFocusChange(R.id.account_input_name)
    void nameFocusChange(EditText editText) {
        if (firstAccessName) {
            firstAccessName = false;
        } else {
            nameTextChange(editText.getText());
        }
    }

    @OnTextChanged(value = R.id.account_input_name, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void nameTextChange(Editable text) {
        if (text.toString().isEmpty()) {
            inputLayoutName.setErrorEnabled(true);
            inputLayoutName.setError(getString(R.string.erro_field_cant_be_empty));
        } else {
            inputLayoutName.setErrorEnabled(false);
            inputLayoutName.setError(null);
        }
    }

    @OnFocusChange(R.id.account_input_adress)
    void adressFocusChange(EditText editText) {
        if (firstAccessAdress) {
            firstAccessAdress = false;
        } else {
            adressTextChange(editText.getText());
        }
    }

    @OnTextChanged(value = R.id.account_input_adress, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void adressTextChange(Editable text) {
        if (text.toString().isEmpty()) {
            inputLayoutAdress.setErrorEnabled(true);
            inputLayoutAdress.setError(getString(R.string.erro_field_cant_be_empty));
        } else {
            inputLayoutAdress.setErrorEnabled(false);
            inputLayoutAdress.setError(null);
        }
    }

    @OnFocusChange(R.id.account_input_phone)
    void phoneFocusChange(EditText editText) {
        if (firstAccessPhone) {
            firstAccessPhone = false;
        } else {
            phoneTextChange(editText.getText());
        }
    }

    @OnTextChanged(value = R.id.account_input_phone, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void phoneTextChange(CharSequence text) {
        if (text.toString().isEmpty()) {
            inputLayoutPhone.setErrorEnabled(true);
            inputLayoutPhone.setError(getString(R.string.erro_field_cant_be_empty));
        } else {
            inputLayoutPhone.setErrorEnabled(false);
            inputLayoutPhone.setError(null);
        }
    }

    @OnFocusChange(R.id.account_input_mail)
    void mailFocusChange(EditText editText) {
        if (firstAccessMail) {
            firstAccessMail = false;
        } else {
            mailTextChange(editText.getText());
        }
    }

    @OnTextChanged(value = R.id.account_input_mail, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void mailTextChange(CharSequence text) {
        if (text.toString().isEmpty()) {
            inputLayoutMail.setErrorEnabled(true);
            inputLayoutMail.setError(getString(R.string.erro_field_cant_be_empty));
        } else {
            inputLayoutMail.setErrorEnabled(false);
            inputLayoutMail.setError(null);
        }
    }

    @OnEditorAction(R.id.account_input_mail)
    boolean nameEditorAction(EditText currentEdit, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            String userMail = currentEdit.getText().toString();
            if (TextUtils.isEmpty(userMail)) {
                inputLayoutMail.setErrorEnabled(true);
                inputLayoutMail.setError(getString(R.string.erro_field_cant_be_empty));
            } else {
                hideKeyboard();
            }
            return true;
        }
        return false;
    }


    @OnCheckedChanged(R.id.volunteering_type_elder)
    void onElderTypeCheckedChanged(boolean checked) {
        if (checked) {
            vacancyTypes.add(VacancyType.ELDER);
        } else {
            vacancyTypes.remove(VacancyType.ELDER);
        }
    }

    @OnCheckedChanged(R.id.volunteering_type_health)
    void onHealthTypeCheckedChanged(boolean checked) {
        if (checked) {
            vacancyTypes.add(VacancyType.HEALTH);
        } else {
            vacancyTypes.remove(VacancyType.HEALTH);
        }
    }

    @OnCheckedChanged(R.id.volunteering_type_culture)
    void onCultureTypeCheckedChanged(boolean checked) {
        if (checked) {
            vacancyTypes.add(VacancyType.CULTURE);
        } else {
            vacancyTypes.remove(VacancyType.CULTURE);
        }
    }

    @OnCheckedChanged(R.id.volunteering_type_animal)
    void onAnimalTypeCheckedChanged(boolean checked) {
        if (checked) {
            vacancyTypes.add(VacancyType.ANIMAL);
        } else {
            vacancyTypes.remove(VacancyType.ANIMAL);
        }
    }


    @OnCheckedChanged(R.id.volunteering_type_education)
    void onEducationTypeCheckedChanged(boolean checked) {
        if (checked) {
            vacancyTypes.add(VacancyType.EDUCATION);
        } else {
            vacancyTypes.remove(VacancyType.EDUCATION);
        }
    }

    @OnCheckedChanged(R.id.volunteering_type_environment)
    void onEnvironmentTypeCheckedChanged(boolean checked) {
        if (checked) {
            vacancyTypes.add(VacancyType.ENVIRONMENT);
        } else {
            vacancyTypes.remove(VacancyType.ENVIRONMENT);
        }
    }

    @OnCheckedChanged(R.id.volunteering_type_child)
    void onChildTypeCheckedChanged(boolean checked) {
        if (checked) {
            vacancyTypes.add(VacancyType.CHILD);
        } else {
            vacancyTypes.remove(VacancyType.CHILD);
        }
    }

    @OnCheckedChanged(R.id.volunteering_type_disabled)
    void onDisabledTypeCheckedChanged(boolean checked) {
        if (checked) {
            vacancyTypes.add(VacancyType.DISABLED);
        } else {
            vacancyTypes.remove(VacancyType.DISABLED);
        }
    }

    @OnClick(R.id.register_button)
    void onRegisterClicked() {

    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
