package com.denshiksmle.githubee.presentation.ui;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public abstract class BaseFragment extends Fragment {

    protected void showSnakbar(final String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }
}
