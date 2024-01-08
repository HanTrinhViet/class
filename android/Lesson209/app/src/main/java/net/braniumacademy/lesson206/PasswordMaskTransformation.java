package net.braniumacademy.lesson206;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

import androidx.annotation.NonNull;

public class PasswordMaskTransformation extends PasswordTransformationMethod {
    private static final PasswordMaskTransformation instance = new PasswordMaskTransformation();

    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharSequence(source);
    }

    private static class PasswordCharSequence implements CharSequence {
        private final CharSequence source;

        public PasswordCharSequence(CharSequence source) {
            this.source = source;
        }

        @Override
        public int length() {
            return source.length();
        }

        @Override
        public char charAt(int index) {
            return '*';
        }

        @NonNull
        @Override
        public CharSequence subSequence(int start, int end) {
            return source.subSequence(start, end);
        }
    }

    public static PasswordMaskTransformation getInstance() {
        return instance;
    }
}
