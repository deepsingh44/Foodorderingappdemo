package com.deepsingh44.foodapp.ui.productlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deepsingh44.foodapp.R;
import com.deepsingh44.foodapp.adapater.CustomProductAdapter;
import com.deepsingh44.foodapp.database.MyDatabase;
import com.deepsingh44.foodapp.model.Product;

import java.util.List;

public class ProductListFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyDatabase database;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_productlist, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = new MyDatabase(getActivity());
        recyclerView = view.findViewById(R.id.myrecylcerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final List<Product> productList = database.getAllProducts();

        final CustomProductAdapter customProductAdapter = new CustomProductAdapter(productList);

        customProductAdapter.setDeepClickListener(new CustomProductAdapter.DeepItemClickListener() {
            @Override
            public void clickOnItem(View view, int position) {
                Product product = productList.get(position);
                //Toast.makeText(getActivity(), product.getName(), Toast.LENGTH_SHORT).show();
                Bundle bundle=new Bundle();
                bundle.putSerializable("myitem",product);
                Navigation.findNavController(view).navigate(R.id.action_nav_product_list_to_detailFragment,bundle);
            }
        });

        recyclerView.setAdapter(customProductAdapter);


        ItemTouchHelper.SimpleCallback iSimpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int k=database.deleteItem(productList.get(viewHolder.getAdapterPosition()));
                if(k>0){
                    customProductAdapter.removeItem(viewHolder.getAdapterPosition());
                }
            }

        };
        new ItemTouchHelper(iSimpleCallback).attachToRecyclerView(recyclerView);
    }


}