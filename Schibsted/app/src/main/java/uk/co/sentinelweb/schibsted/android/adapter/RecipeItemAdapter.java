package uk.co.sentinelweb.schibsted.android.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import uk.co.sentinelweb.schibsted.R;
import uk.co.sentinelweb.schibsted.databinding.ListItemRecpieBinding;
import uk.co.sentinelweb.schibsted.model.Recipe;

/**
 * The adapter fort he list of items.
 */
public class RecipeItemAdapter extends Adapter {

    private List<Recipe> _recipeList;
    private OnItemClickListener listener;
    private int selectedItemPosition = -1;

    public RecipeItemAdapter(final List<Recipe> recipeList) {
        this._recipeList = recipeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ListItemRecpieBinding listItemGameBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_recpie, parent, false);
        return new RecipeItemViewHolder(listItemGameBinding);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((RecipeItemViewHolder) holder).setCurrentPosition(position);
    }

    @Override
    public int getItemCount() {
        if (_recipeList != null) {
            return _recipeList.size();
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public int getSelectedItemPosition() {
        return selectedItemPosition;
    }

    public void setSelectedItemPosition(final int selectedItemPosition) {
        int oldSelectedPosition = selectedItemPosition;
        this.selectedItemPosition = selectedItemPosition;
        if (oldSelectedPosition > -1) {
            notifyItemChanged(selectedItemPosition);
        }
        notifyItemChanged(selectedItemPosition);
    }

    public void setItems(final List<Recipe> items) {
        _recipeList = items;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position, Recipe game);
    }

    /**
     * This view holder is public to use for databinding onClick.
     *
     * We use the currentPosition to handle click calls.
     */

    public class RecipeItemViewHolder extends RecyclerView.ViewHolder {
        ListItemRecpieBinding _listItemRecpieBinding;
        int currentPosition;
        Recipe game;

        public RecipeItemViewHolder(final ListItemRecpieBinding _listItemRecpieBinding) {
            super(_listItemRecpieBinding.getRoot());
            this._listItemRecpieBinding = _listItemRecpieBinding;
            _listItemRecpieBinding.setHandlers(this);
        }

         public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(currentPosition, _recipeList.get(currentPosition));
                setSelectedItemPosition(currentPosition);
            }
        }

        public void setCurrentPosition(int position) {
            this.currentPosition = position;
            final Recipe recipe = _recipeList.get(position);
            _listItemRecpieBinding.setRecipe(recipe);
            _listItemRecpieBinding.getRoot().setSelected(selectedItemPosition == position);
            Picasso.with(_listItemRecpieBinding.getRoot().getContext()).load(recipe.getImage_url()).into(_listItemRecpieBinding.recipeImg);
        }
    }

}
