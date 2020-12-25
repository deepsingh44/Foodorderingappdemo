package com.deepsingh44.foodapp.ui.addproduct;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.deepsingh44.foodapp.R;
import com.deepsingh44.foodapp.database.MyDatabase;
import com.deepsingh44.foodapp.model.Product;

import java.util.Calendar;

public class AddProductFragment extends Fragment {
    private Button button;
    private EditText tname, tprice, tquantity;
    private TextView tdate;
    private MyDatabase database;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_addproduct, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = new MyDatabase(getActivity());
        tname = view.findViewById(R.id.productname);
        tprice = view.findViewById(R.id.productprice);
        tquantity = view.findViewById(R.id.productquantity);
        tdate = view.findViewById(R.id.datetext);
        ImageView image = view.findViewById(R.id.dateimage);
        button = view.findViewById(R.id.btn);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int y = calendar.get(Calendar.YEAR);
                int m = calendar.get(Calendar.MONTH);
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tdate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, y, m, d);
                dp.show();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid()) {
                    //store product in database
                    Product product = new Product();
                    product.setName(name);
                    product.setPrice(Float.parseFloat(price));
                    product.setQuantity(Integer.parseInt(quantity));
                    product.setDate(date);

                    long t = database.insert(product);
                    if (t > 0) {
                        Toast.makeText(getActivity(), "Successfully product added", Toast.LENGTH_SHORT).show();
                        reset();
                    } else {
                        Toast.makeText(getActivity(), "Product added failed", Toast.LENGTH_SHORT).show();
                        reset();
                    }

                }
            }
        });

    }

    private void reset() {
        tname.setText("");
        tprice.setText("");
        tdate.setText("Select Date");
        tquantity.setText("");
    }

    private String name, price, quantity, date;

    private boolean valid() {
        name = tname.getText().toString();
        price = tprice.getText().toString();
        quantity = tquantity.getText().toString();
        date = tdate.getText().toString();
        if (TextUtils.isEmpty(name)) {
            tname.setError("enter product name");
            tname.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(price)) {
            tprice.setError("enter price");
            tprice.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(quantity)) {
            tquantity.setError("enter quantity");
            tquantity.requestFocus();
            return false;
        } else if (date.equalsIgnoreCase("select date")) {
            tdate.setError("select date");
            tdate.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}