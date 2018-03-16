package com.example.haruki.cafecross_v2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;



public class ConfirmationCartItemAdapter extends RecyclerView.Adapter<ConfirmationCartItemAdapter.ConfirmationCartItemViewHolder>{

    String TAG = "CartItemAdapter: ";

    private Context mCtx;
    private List<Product> productList;


    public ConfirmationCartItemAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ConfirmationCartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cart_item_confirmation_layout, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        );

        return new ConfirmationCartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ConfirmationCartItemViewHolder holder, final int position) {
        final Product product = productList.get(position);

        String itemName = product.getProduct_name();
        String itemPrice = "¥ " + String.valueOf(product.getPrice());
        String itemQuantity = String.valueOf(ShoppingCartHelper.getProductQuantity(product)) + " x100g";
        String itemTotalPerItem = "¥ " + String.valueOf(product.getPrice() * ShoppingCartHelper.getProductQuantity(product));
        String itemTotalPrice = "¥ " + ShoppingCartHelper.getProductQuantity(product) * product.getPrice();

        holder.tvConfirmationProductName.setText(itemName);
        holder.tvConfirmationPrice.setText(itemPrice);
        holder.tvConfirmationQuantity.setText(itemQuantity);
        holder.tvConfirmationTotalPerItem.setText(itemTotalPerItem);

        holder.tvConfirmationTotalPrice.setText(itemTotalPrice);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ConfirmationCartItemViewHolder extends RecyclerView.ViewHolder{

        TextView tvConfirmationProductName, tvConfirmationPrice, tvConfirmationQuantity, tvConfirmationTotalPerItem, tvConfirmationTotalPrice;

        public ConfirmationCartItemViewHolder(View itemView) {
            super(itemView);

            tvConfirmationProductName = itemView.findViewById(R.id.tvConfirmProductName);
            tvConfirmationPrice = itemView.findViewById(R.id.tvConfirmPrice);
            tvConfirmationQuantity = itemView.findViewById(R.id.tvConfirmQuantity);
            tvConfirmationTotalPerItem = itemView.findViewById(R.id.tvConfirmTotalPerItem);
            tvConfirmationTotalPrice = itemView.findViewById(R.id.tvConfirmTotalPrice);
        }
    }
}
