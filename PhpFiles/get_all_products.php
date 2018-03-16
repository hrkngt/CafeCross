<?php

    $response = array();

    require_once __DIR__ . '/db_connect.php';

    $db = new DB_CONNECT();

    $link = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

    $result = mysqli_query($link, "SELECT * FROM products") or die(mysqli_connect_error());


    if(mysqli_num_rows($result) > 0){
        $response["products"] = array();
        $response["success"] = true;

        while($row = mysqli_fetch_array($result)){
            $temp = array();
            $temp["id"] = $row["id"];
            $temp["product_name"] = $row["product_name"];
            $temp["description"] = $row["description"];
            $temp["price"] = $row["price"];
            $temp["image"] = $row["image"];

            array_push($response["products"], $temp);
        }

        echo json_encode($response);

    }else{
        $response["success"] = false;
        $response["message"] = "NO PRODUCTS FOUND.";
        echo json_encode($response);
    }

?>