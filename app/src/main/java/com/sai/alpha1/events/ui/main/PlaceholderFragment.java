package com.sai.alpha1.events.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sai.alpha1.R;
import com.sai.alpha1.events.Fevent1;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static Fragment newInstance(int index) {
        Fragment fragment = null;
        switch(index){
            case 1:fragment=new Fevent1("Pagina 1","https://concepto.de/wp-content/uploads/2018/08/monta%C3%B1as-e1533762816593.jpg","Descripcion " +
                    "de la actividad numero uno de la seccion cultura"); break;
            case 2:fragment=new Fevent1("Pagina 2","https://concepto.de/wp-content/uploads/2018/08/monta%C3%B1as-e1533762816593.jpg","Descripcion " +
                    "de la actividad numero dos de la seccion cultura"); break;
            case 3:fragment=new Fevent1("Pagina 3","https://concepto.de/wp-content/uploads/2018/08/monta%C3%B1as-e1533762816593.jpg","Descripcion " +
                    "de la actividad numero tres de la seccion cultura"); break;


        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main2, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}