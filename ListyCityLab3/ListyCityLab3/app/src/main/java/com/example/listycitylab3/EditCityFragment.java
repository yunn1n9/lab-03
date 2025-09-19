package com.example.listycitylab3;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.listycitylab3.City;

public class EditCityFragment extends DialogFragment {
    public interface EditCityDialogListener {
        void onCityEdited(int position, String newName, String newProvince);
    }

    private static final String ARG_CITY = "city";
    private static final String ARG_POS = "pos";

    public static EditCityFragment newInstance(City city, int position) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY, city);
        args.putInt(ARG_POS, position);
        EditCityFragment fragment = new EditCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_edit_city, null);
        EditText name = view.findViewById(R.id.edit_text_city_text_edit);
        EditText prov = view.findViewById(R.id.edit_text_province_text_edit);

        City c = (City) requireArguments().getSerializable(ARG_CITY);
        int pos = requireArguments().getInt(ARG_POS);
        name.setText(c.getName());
        prov.setText(c.getProvince());

        AlertDialog.Builder b = new AlertDialog.Builder(requireContext());
        return b.setView(view)
                .setTitle("Edit city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", (d, w) -> {
                    EditCityDialogListener l = (EditCityDialogListener) requireActivity();
                    l.onCityEdited(pos, name.getText().toString(), prov.getText().toString());
                })
                .create();
    }
}
