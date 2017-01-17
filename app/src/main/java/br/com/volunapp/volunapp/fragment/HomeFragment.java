package br.com.volunapp.volunapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.volunapp.volunapp.R;
import br.com.volunapp.volunapp.model.VacancyType;
import br.com.volunapp.volunapp.util.Mask;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class HomeFragment extends Fragment {

    public static final int MAX_VOLUTEERING_TYPES = 3;
    @BindView(R.id.account_input_layout_name)
    TextInputLayout inputLayoutName;

    @BindView(R.id.account_input_name)
    EditText inputName;

    @BindView(R.id.account_input_layout_cep)
    TextInputLayout inputLayoutCep;

    @BindView(R.id.account_input_cep)
    EditText inputCep;

    @BindView(R.id.account_input_layout_phone)
    TextInputLayout inputLayoutPhone;

    @BindView(R.id.account_input_phone)
    EditText inputPhone;

    @BindView(R.id.account_input_layout_mail)
    TextInputLayout inputLayoutMail;

    @BindView(R.id.account_input_mail)
    EditText inputMail;

    private boolean firstAccessName = true;
    private boolean firstAccessCep = true;
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

        TextWatcher cepTextWatcher = Mask.getTextWatcher(Mask.CEP_MASK, inputCep);
        inputCep.addTextChangedListener(cepTextWatcher);

        TextWatcher phoneTextWatcher = Mask.getTextWatcher(Mask.PHONE, inputPhone);
        inputPhone.addTextChangedListener(phoneTextWatcher);

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

    @OnFocusChange(R.id.account_input_cep)
    void cepFocusChange(EditText editText) {
        if (firstAccessCep) {
            firstAccessCep = false;
        } else {
            cepTextChange(editText.getText());
        }
    }

    @OnTextChanged(value = R.id.account_input_cep, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void cepTextChange(Editable text) {
        if (text.toString().isEmpty()) {
            inputLayoutCep.setErrorEnabled(true);
            inputLayoutCep.setError(getString(R.string.erro_field_cant_be_empty));
        } else {
            inputLayoutCep.setErrorEnabled(false);
            inputLayoutCep.setError(null);
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
    void onElderTypeCheckedChanged(CheckBox checkBox, boolean checked) {
        if (checked) {
            if (canSelectMoreVolunteeringTypes()) {
                vacancyTypes.add(VacancyType.ELDER);
            } else {
                displayMaximumVolunteeringTypesReachedMessage();
                checkBox.setChecked(false);
            }
        } else {
            vacancyTypes.remove(VacancyType.ELDER);
        }
    }

    @OnCheckedChanged(R.id.volunteering_type_health)
    void onHealthTypeCheckedChanged(CheckBox checkBox, boolean checked) {
        if (checked) {
            if (canSelectMoreVolunteeringTypes()) {
                vacancyTypes.add(VacancyType.HEALTH);
            } else {
                displayMaximumVolunteeringTypesReachedMessage();
                checkBox.setChecked(false);
            }
        } else {
            vacancyTypes.remove(VacancyType.HEALTH);
        }
    }

    @OnCheckedChanged(R.id.volunteering_type_culture)
    void onCultureTypeCheckedChanged(CheckBox checkBox, boolean checked) {
        if (checked) {
            if (canSelectMoreVolunteeringTypes()) {
                vacancyTypes.add(VacancyType.CULTURE);
            } else {
                displayMaximumVolunteeringTypesReachedMessage();
                checkBox.setChecked(false);
            }
        } else {
            vacancyTypes.remove(VacancyType.CULTURE);
        }
    }

    @OnCheckedChanged(R.id.volunteering_type_animal)
    void onAnimalTypeCheckedChanged(CheckBox checkBox, boolean checked) {
        if (checked) {
            if (canSelectMoreVolunteeringTypes()) {
                vacancyTypes.add(VacancyType.ANIMAL);
            } else {
                displayMaximumVolunteeringTypesReachedMessage();
                checkBox.setChecked(false);
            }
        } else {
            vacancyTypes.remove(VacancyType.ANIMAL);
        }
    }

    @OnCheckedChanged(R.id.volunteering_type_education)
    void onEducationTypeCheckedChanged(CheckBox checkBox, boolean checked) {
        if (checked) {
            if (canSelectMoreVolunteeringTypes()) {
                vacancyTypes.add(VacancyType.EDUCATION);
            } else {
                displayMaximumVolunteeringTypesReachedMessage();
                checkBox.setChecked(false);
            }
        } else {
            vacancyTypes.remove(VacancyType.EDUCATION);
        }
    }

    @OnCheckedChanged(R.id.volunteering_type_environment)
    void onEnvironmentTypeCheckedChanged(CheckBox checkBox, boolean checked) {
        if (checked) {
            if (canSelectMoreVolunteeringTypes()) {
                vacancyTypes.add(VacancyType.ENVIRONMENT);
            } else {
                displayMaximumVolunteeringTypesReachedMessage();
                checkBox.setChecked(false);
            }
        } else {
            vacancyTypes.remove(VacancyType.ENVIRONMENT);
        }
    }

    @OnCheckedChanged(R.id.volunteering_type_child)
    void onChildTypeCheckedChanged(CheckBox checkBox, boolean checked) {
        if (checked) {
            if (canSelectMoreVolunteeringTypes()) {
                vacancyTypes.add(VacancyType.CHILD);
            } else {
                displayMaximumVolunteeringTypesReachedMessage();
                checkBox.setChecked(false);
            }
        } else {
            vacancyTypes.remove(VacancyType.CHILD);
        }
    }

    @OnCheckedChanged(R.id.volunteering_type_disabled)
    void onDisabledTypeCheckedChanged(CheckBox checkBox, boolean checked) {
        if (checked) {
            if (canSelectMoreVolunteeringTypes()) {
                vacancyTypes.add(VacancyType.DISABLED);
            } else {
                displayMaximumVolunteeringTypesReachedMessage();
                checkBox.setChecked(false);
            }
        } else {
            vacancyTypes.remove(VacancyType.DISABLED);
        }
    }

    private boolean canSelectMoreVolunteeringTypes() {
        return vacancyTypes.size() < MAX_VOLUTEERING_TYPES;
    }

    private void displayMaximumVolunteeringTypesReachedMessage() {
        Toast.makeText(getContext(), R.string.message_max_volunteering_types_reached, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.register_button)
    void onRegisterClicked() {

    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
