package com.example.haruki.cafecross_v2;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class SendOrderRequest extends StringRequest {
    private final static String SEND_ORDER_URL = "http://cafecross.php.xdomain.jp/send_order.php";
    private Map<String, String> params;

    public SendOrderRequest(String name, String zip, String prefecture, String address1, String address2,
                            String apartment, String company, String email, String phone, String orderedItems,
                            String comments, Response.Listener<String> listener){
        super(Method.POST, SEND_ORDER_URL, listener, null);
        params = new HashMap<>();
        params.put("customer_name", name);
        params.put("customer_zip", zip);
        params.put("customer_prefecture", prefecture );
        params.put("customer_address1", address1);
        params.put("customer_address2", address2);
        params.put("customer_apartment", apartment);
        params.put("customer_company", company);
        params.put("customer_email", email);
        params.put("customer_phone", phone);
        params.put("ordered_items", orderedItems);
        params.put("comments", comments);
    }

    public Map<String, String> getParams() {
        return params;
    }

}
