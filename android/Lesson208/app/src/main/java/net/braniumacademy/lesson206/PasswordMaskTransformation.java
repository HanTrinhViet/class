package net.braniumacademy.lesson206;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

import androidx.annotation.NonNull;

public class PasswordMaskTransformation extends PasswordTransformationMethod {
    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharsequence(source);
    }

    private static class PasswordCharsequence implements CharSequence {
        private CharSequence source;


        public PasswordCharsequence(CharSequence source) {
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
}
