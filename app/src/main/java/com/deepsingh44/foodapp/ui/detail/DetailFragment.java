package com.deepsingh44.foodapp.ui.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.deepsingh44.foodapp.HomePage;
import com.deepsingh44.foodapp.R;
import com.deepsingh44.foodapp.SingleTask;
import com.deepsingh44.foodapp.model.Product;

public class DetailFragment extends Fragment {
    private Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        product = (Product) getArguments().getSerializable("myitem");
        TextView ttile = view.findViewById(R.id.title);
        TextView tprice = view.findViewById(R.id.price);
        Button addtocart = view.findViewById(R.id.addtocart);
        final HomePage hp = (HomePage) getActivity();


        ttile.setText(product.getName());
        tprice.setText(String.valueOf(product.getPrice()));

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hp.getMybadge().setBadgeValue(SingleTask.cartCount++);
            }
        });

    }
}