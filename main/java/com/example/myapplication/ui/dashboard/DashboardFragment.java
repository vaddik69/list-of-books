package com.example.myapplication.ui.dashboard;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private View binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String selectedElement = bundle.getString("selectedElement", "");
            // Теперь у вас есть выбранный элемент для использования во втором фрагменте
        }

        init(binding);

        return binding;
    }

    private void init(View view) {
        TextView name_text = view.findViewById(R.id.name_text);
        TextView author_text = view.findViewById(R.id.author_text);
        TextView date_text = view.findViewById(R.id.date_text);
        TextView genre_text = view.findViewById(R.id.genre_text);
        TextView rating_text = view.findViewById(R.id.rating_text);
        TextView no_text = view.findViewById(R.id.no_text);

        Bundle bundle = getArguments();
        if (bundle != null) {
            name_text.setText(bundle.getString("titleBook"));
            author_text.setText("Author: " + bundle.getString("authorBook"));
            date_text.setText("Date: " + bundle.getString("dateBook"));
            genre_text.setText("Genre: " + bundle.getString("genreBook"));
            rating_text.setText("Rating: " + bundle.getString("ratingBook"));
            no_text.setVisibility(View.INVISIBLE);
        }
        else {
            name_text.setVisibility(View.INVISIBLE);
            author_text.setVisibility(View.INVISIBLE);
            date_text.setVisibility(View.INVISIBLE);
            genre_text.setVisibility(View.INVISIBLE);
            rating_text.setVisibility(View.INVISIBLE);
            no_text.setVisibility(View.VISIBLE);
        }
    }

}