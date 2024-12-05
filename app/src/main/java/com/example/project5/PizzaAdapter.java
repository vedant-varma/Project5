package com.example.project5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.Pizza;
import model.BuildYourOwn;
import model.Deluxe;
import model.Meatzza;
import model.BBQChicken;

/**
 * Adapter class for displaying a list of Pizza items in a RecyclerView.
 * @author Vedant Varma
 * @author Jimmy Mishan
 */
public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    /**
     * List of Pizza objects to be displayed
     */
    private List<Pizza> pizzaList;

    /**
     * Position of the currently selected item in the RecyclerView
     */
    private int selectedPosition = RecyclerView.NO_POSITION;

    /**
     * Context for inflating layout resources
     */
    private Context context;

    /**
     * Constructor for the PizzaAdapter
     * @param context the context for layout inflation
     * @param pizzaList the list of Pizza objects to display
     */
    public PizzaAdapter(Context context, List<Pizza> pizzaList) {
        this.context = context;
        this.pizzaList = pizzaList;
    }

    /**
     * Called when a new ViewHolder is created
     * @param parent the parent ViewGroup.
     * @param viewType the view type of the new View.
     * @return a new instance of PizzaViewHolder.
     */
    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pizza_item, parent, false);
        return new PizzaViewHolder(view);
    }

    /**
     * Binds data to a ViewHolder at a given position
     *
     * @param holder the ViewHolder to bind data to
     * @param position the position of the item in the data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.pizzaDescriptionTextView.setText(pizza.toString());

        // Set the image based on pizza type
        if (pizza instanceof BuildYourOwn) {
            holder.pizzaImageView.setImageResource(R.drawable.build_your_own);
        } else if (pizza instanceof Deluxe) {
            holder.pizzaImageView.setImageResource(R.drawable.deluxe);
        } else if (pizza instanceof Meatzza) {
            holder.pizzaImageView.setImageResource(R.drawable.meatzza);
        } else if (pizza instanceof BBQChicken) {
            holder.pizzaImageView.setImageResource(R.drawable.bbq_chicken);
        } else {
            holder.pizzaImageView.setImageResource(R.drawable.orderbasket);
        }

        // Highlight the selected item
        holder.itemView.setSelected(position == selectedPosition);
    }

    /**
     * Returns the total number of items in the data set
     * @return the item count.
     */
    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    /**
     * Returns the position of the currently selected item
     * @return the selected position or NO_POSITION if none is selected
     */
    public int getSelectedPosition() {
        return selectedPosition;
    }

    /**
     * Clears the selection, resetting the selected position to NO_POSITION.
     */
    public void clearSelection() {
        int oldPosition = selectedPosition;
        selectedPosition = RecyclerView.NO_POSITION;
        if (oldPosition != RecyclerView.NO_POSITION) {
            notifyItemChanged(oldPosition);
        }
    }

    /**
     * ViewHolder class for representing a Pizza item in the RecyclerView.
     */
    public class PizzaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * ImageView for displaying the pizza image.
         */
        ImageView pizzaImageView;

        /**
         * TextView for displaying the pizza description.
         */
        TextView pizzaDescriptionTextView;

        /**
         * Constructor for PizzaViewHolder.
         * @param itemView the itemView representing a single item.
         */
        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaImageView = itemView.findViewById(R.id.pizzaImageView);
            pizzaDescriptionTextView = itemView.findViewById(R.id.pizzaDescriptionTextView);
            itemView.setOnClickListener(this);
        }

        /**
         * Handles click events for the itemView.
         * @param v the clicked View.
         */
        @Override
        public void onClick(View v) {
            int oldPosition = selectedPosition;
            selectedPosition = getAdapterPosition();
            notifyItemChanged(oldPosition);
            notifyItemChanged(selectedPosition);
        }
    }
}
