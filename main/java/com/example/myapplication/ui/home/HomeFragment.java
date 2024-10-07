package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication.JsonBookItem;
import com.example.myapplication.Books;
import com.example.myapplication.MyDatabase;
import com.example.myapplication.R;
import com.example.myapplication.RecycleViewAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static View binding;
    private RecyclerView mRecyclerView;
    MyDatabase db;
    int countBook;
    public static List<Books> books = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = binding.findViewById(R.id.recyclerView);

        db = Room.databaseBuilder(binding.getContext(), MyDatabase.class, "BooksBd").build();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                countBook = db.getDaoBooks().getCountBook();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (countBook == 0) {
            try {
                responseJSON();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            initRecyclerView(binding);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //recyclerView.setAdapter(new RecycleViewAdapter(createElements(), this));

        return binding;
    }

    public static void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("titleBook", books.get(position).getName());
        bundle.putString("authorBook", books.get(position).getAuthor());
        bundle.putString("genreBook", books.get(position).getGenre());
        bundle.putString("dateBook", books.get(position).getDate());
        bundle.putString("ratingBook", books.get(position).getRating());

        Navigation.findNavController(binding).navigate(R.id.action_navigation_home_to_navigation_dashboard, bundle);
    }

//    public ListViewElement[] createElements() {
//        ListViewElement[] result = new ListViewElement[19];
//        for (int i = 0; i < 19; i++) {
//            final int index = i;
//            result[i] = new ListViewElement() {
//                @Override
//                public String getNameItem() {
//                    return books[index].getName();
//                }
//
//                @Override
//                public String getAuth() {
//                    return books[index].getAuthor();
//                }
//            };
//        }
//        return result;
//    }

    private void responseJSON() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/Books2022.json");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        StringBuilder response = new StringBuilder();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }

                        Gson gson = new Gson();
                        String jsonResponse = response.toString();
                        JsonArray jsonArray = gson.fromJson(jsonResponse, JsonArray.class);

                        for (int i = 0; i < jsonArray.size(); i++) {
                            JsonElement firstObject = jsonArray.get(i);
                            JsonBookItem booksElement = gson.fromJson(firstObject, JsonBookItem.class);
                            Books currentBook11 = new Books(booksElement.getAuthor(),booksElement.getGenre(),booksElement.getName(),
                                                                                      booksElement.getDate(),booksElement.getRating());
                            Books currentBook = db.getDaoBooks().getBook(booksElement.getName(),booksElement.getAuthor());
                            if (currentBook == null) {
                                db.getDaoBooks().addBook(currentBook11);
                            }
                        }

                        in.close();
                        connection.disconnect();

                    } else {
                        System.out.println("Ошибка. Код ответа: " + responseCode);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
    }

    private void initRecyclerView(View view) throws InterruptedException {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                books = db.getDaoBooks().getAllBooks();
            }
        });
        thread.start();
        thread.join();
        mRecyclerView.setAdapter(new RecycleViewAdapter(books));
    }
}

