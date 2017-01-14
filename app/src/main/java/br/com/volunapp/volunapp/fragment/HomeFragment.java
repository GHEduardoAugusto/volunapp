package br.com.volunapp.volunapp.fragment;

import android.content.Context;
import android.os.Bundle;
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

import br.com.volunapp.volunapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
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

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
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
    public void nameTextChange(Editable text) {
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
    public void adressTextChange(Editable text) {
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
    public void phoneTextChange(CharSequence text) {
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
    public void mailTextChange(CharSequence text) {
        if (text.toString().isEmpty()) {
            inputLayoutMail.setErrorEnabled(true);
            inputLayoutMail.setError(getString(R.string.erro_field_cant_be_empty));
        } else {
            inputLayoutMail.setErrorEnabled(false);
            inputLayoutMail.setError(null);
        }
    }

    @OnEditorAction(R.id.account_input_mail)
    public boolean nameEditorAction(EditText currentEdit, int actionId, KeyEvent event) {
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

    @OnClick(R.id.register_button)
    void onRegisterClicked() {

    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
