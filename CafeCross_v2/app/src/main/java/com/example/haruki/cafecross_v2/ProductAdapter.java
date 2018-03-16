package com.example.haruki.cafecross_v2;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;



public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    String TAG = "PRODUCT_ADAPTER: ";

    private Context mCtx;
    private List<Product> productList;

    int limitPerProduct = 10;
    int limitTotal = 10;


    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        );

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Product product = productList.get(position);

        String priceView = "¥ " + String.valueOf(product.getPrice() + "/100g");

        holder.tvProductName.setText(product.getProduct_name());
        holder.tvDescription.setText(product.getDescription());
        holder.tvPrice.setText(priceView);

        if(product.getImage()!= null) {
            Glide.with(mCtx).load(product.getImage()).into(holder.imageView);
        }

        holder.btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

//                final EditText etQuantity = createQuantityEntry();

                final NumberPicker numPicker = createQuantityEntry();


                final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle(product.getProduct_name() + "を追加");
                builder.setView(numPicker);

                builder.setPositiveButton("追加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(exceedingCartItemLimitPerProduct(product, numPicker.getValue(), limitPerProduct)) {
                            //This product is exceeding maximum quantity

                            String message = "一度に購入できるのは、商品当たり"+ limitPerProduct +"個までです。\n" +
                                    "(お客様のカートには既に、" + product.getProduct_name() + "が" + ShoppingCartHelper.getProductQuantity(product) +
                                    "個入っています)";

                            AlertDialog.Builder rejecter = new AlertDialog.Builder(mCtx);
                            rejecter.setTitle("上限を超えています：");
                            rejecter.setMessage(message)
                                    .setPositiveButton("了解", null).show();

                        }else if(exceedingCartLimitTotal(numPicker.getValue(), limitTotal)){

                            String message = "一度に購入できるのは商品の数は、"+ limitTotal +"個までです。\n" +
                                    "(お客様のカートには既に、" + ShoppingCartHelper.getAllProductQuantity() +
                                    "個商品が入っています)";

                            AlertDialog.Builder rejecter = new AlertDialog.Builder(mCtx);
                            rejecter.setTitle("上限を超えています：");
                            rejecter.setMessage(message)
                                    .setPositiveButton("了解", null).show();

                        }else{
                            ShoppingCartHelper.setQuantity(product,
                                    ShoppingCartHelper.getProductQuantity(product)+ numPicker.getValue()
                            );
                            Toast.makeText(mCtx,
                                    product.getProduct_name() + "を" + String.valueOf(numPicker.getValue())
                                            + "つ追加しました。", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("取り消し", null);
                builder.show();
            }
        });
    }

    private NumberPicker createQuantityEntry(){
        NumberPicker numberPicker = new NumberPicker(mCtx);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        return numberPicker;
    }


    private boolean exceedingCartItemLimitPerProduct(Product p, int orderedQuantity, int limit) {
        if(ShoppingCartHelper.getProductQuantity(p) + orderedQuantity > limit){
            return true;
        }else {
            return false;
        }
    }

    private boolean exceedingCartLimitTotal(int orderedQuantity, int limit) {
        if(ShoppingCartHelper.getAllProductQuantity() + orderedQuantity > limit){
            return true;
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvProductName, tvDescription, tvPrice;
        Button btAdd;

        public ProductViewHolder(View itemView) {
            super(itemView);

            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imageView = itemView.findViewById(R.id.image_view);

            btAdd = itemView.findViewById(R.id.btAdd);

        }
    }

}
