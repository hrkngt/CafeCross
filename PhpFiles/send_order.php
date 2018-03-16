<?php
    mb_language("Japanese");
    mb_internal_encoding("UTF-8");

    //get values from the app
    $customer_name      = $_POST["customer_name"];
    $customer_zip       = $_POST["customer_zip"];
    $customer_address1  = $_POST["customer_address1"];
    $customer_address2  = $_POST["customer_address2"];
    $customer_company   = $_POST["customer_company"];
    $customer_email     = $_POST["customer_email"];
    $customer_phone     = $_POST["customer_phone"];
    $ordered_items = $_POST["ordered_items"];
    $comments = $_POST["comments"];

    //Send orders via Email
    $to = 'haruki.higuchi1994@gmail.com';
    $subject = 'Cofee Order';
    $message = 'Order has been placed' . "\r\n" .
        "NAME     : ". $customer_name . "\r\n".
        "ZIP      : ". $customer_zip . "\r\n" .
        "ADDRESS1 : ". $customer_address1 . "\r\n".
        "ADDRESS2 : ". $customer_address2 . "\r\n".
        "COMPANY  : ". $customer_company . "\r\n".
        "EMAIL    : ". $customer_email . "\r\n".
        "PHONE    : ". $customer_phone . "\r\n".
        "ORDERS   : ". $ordered_items . "\r\n".
        "COMMENTS : ". $comments;

    //Set header of the email
    $headers = 'From: CafeCrossApp' . "\r\n";

    //Wrap and set
    mail($to, $subject, $message, $headers);

    //return response to the app
    $response = array();
    $response["success"] = true;
    echo json_encode($response);

?>