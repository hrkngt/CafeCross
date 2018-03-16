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



public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>{

    String TAG = "CartItemAdapter: ";

    private Context mCtx;
    private List<Product> productList;


    public CartItemAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cart_item_layout, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        );

        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartItemViewHolder holder, final int position) {
        final Product product = productList.get(position);

        String itemName = product.getProduct_name();
        String itemPrice = "¥ " + String.valueOf(product.getPrice()) + "/100g";
        String itemQuantity = String.valueOf(ShoppingCartHelper.getProductQuantity(product)) + " 個 x100g";
        String itemTotalPrice = "¥ " + ShoppingCartHelper.getProductQuantity(product) * product.getPrice();

        holder.tvCartProductName.setText(itemName);
        holder.tvCartProductPrice.setText(itemPrice);
        holder.tvCartProductQuantity.setText(itemQuantity);

        holder.tvCartProductTotalPrice.setText(itemTotalPrice);

        holder.btRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mCtx);
                builder.setMessage(product.getProduct_name() + "をカートから削除しますか?");
                builder.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ShoppingCartHelper.removeProduct(product);
                        productList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, productList.size());

                        if(ShoppingCartHelper.getCartList().size() == 0){
                            ((Activity)mCtx).recreate();
                        }
                    }
                }).setNegativeButton("いいえ", null);

                builder.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder{

        TextView tvCartProductName, tvCartProductPrice, tvCartProductQuantity, tvCartProductTotalPrice;
        Button btRemove;

        public CartItemViewHolder(View itemView) {
            super(itemView);

            tvCartProductName = itemView.findViewById(R.id.cart_item_name);
            tvCartProductPrice = itemView.findViewById(R.id.cart_item_price);
            tvCartProductQuantity = itemView.findViewById(R.id.cart_item_quantity);
            tvCartProductTotalPrice = itemView.findViewById(R.id.cart_item_totalprice);

            btRemove = itemView.findViewById(R.id.btRemove);
        }
    }
}
