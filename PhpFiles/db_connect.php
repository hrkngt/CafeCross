<?php
/**
 * Created by PhpStorm.
 * User: HARUKI
 * Date: 2018/01/17
 * Time: 19:51
 */

    class DB_CONNECT
    {
        //constructor
        function __construct()
        {
            //connecting to database
            $this -> connect();
        }

        function __destruct()
        {
            //closing database connection
            $this -> close();
        }

        function connect(){
            require_once __DIR__ . '/db_config.php';
            $link = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
            $db = mysqli_select_db($link, DB_DATABASE) or die(mysqli_connect_error());

            return $link;
        }

        function close(){
            $link = mysqli_init();
            mysqli_close($link);
        }

    }

?>