package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ui.home.HomeFragment;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ListItemViewHolder> {
    private List<Books> books;

    public RecycleViewAdapter(List<Books> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_view, parent, false);

        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        holder.setName(books.get(position).getName());
        holder.setAuthor(books.get(position).getAuthor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView author;

        public void setName(String name) {
            this.name.setText(name);
        }
        public void setAuthor(String author) {
            this.author.setText(author);
        }
        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.elementTitleTextView);
            author = itemView.findViewById(R.id.elementPriceTextView);
        }
    }
}
