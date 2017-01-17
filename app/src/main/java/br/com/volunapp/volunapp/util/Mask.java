package br.com.volunapp.volunapp.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class Mask {

    public static final String CEP_MASK = "##.###-###";
    public static final String PHONE = "phone";
    private static final String LANDLINE_MASK = "(##) ####-####";
    private static final String CELPHONE_MASK = "(##) #####-####";

    public static String unmask(String str) {
        return str.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "").replaceAll("[ ]", "");
    }

    public static TextWatcher getTextWatcher(final String mask, final EditText ediTxt) {
        return new TextWatcher() {

            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = Mask.unmask(s.toString());
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                String mascara = verifyPhoneMask(str);
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

            private String mask(String mask, String str) {
                int i = 0;
                String mascara = "";

                for (char maskChar : mask.toCharArray()) {
                    if (str.length() > i) {
                        if (maskChar != '#') {
                            mascara += maskChar;
                        } else {
                            mascara += str.charAt(i);
                            i++;
                        }
                    } else {
                        break;
                    }
                }
                return mascara;
            }

            private String verifyPhoneMask(String str) {
                if (mask.equals(PHONE)) {
                    if (str.length() <= 10) {
                        return mask(LANDLINE_MASK, str);
                    } else {
                        return mask(CELPHONE_MASK, str);
                    }
                } else {
                    return mask(mask, str);
                }
            }
        };
    }
}
