package com.deepsingh44.foodapp.adapater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.deepsingh44.foodapp.R;
import com.deepsingh44.foodapp.model.Product;

import java.util.List;

public class CustomProductAdapter extends RecyclerView.Adapter<CustomProductAdapter.MyViewHolder> {
    private List<Product> productList;
    private DeepItemClickListener deepItemClickListener;

    public CustomProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tname.setText(product.getName());
        holder.tprice.setText(String.valueOf(product.getPrice()));
        holder.tquantity.setText(String.valueOf(product.getQuantity()));
    }

    public void removeItem(int index){
        productList.remove(index);
        notifyItemChanged(index);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tname, tprice, tquantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tname = itemView.findViewById(R.id.proname);
            tprice = itemView.findViewById(R.id.proprice);
            tquantity = itemView.findViewById(R.id.proquantity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            deepItemClickListener.clickOnItem(v, getAdapterPosition());
        }
    }

    public void setDeepClickListener(DeepItemClickListener deepClickListener) {
        this.deepItemClickListener = deepClickListener;
    }

    public interface DeepItemClickListener {
        void clickOnItem(View view, int position);
    }

}
