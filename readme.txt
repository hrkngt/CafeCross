﻿【ソフト名】	café CROSS beta
【製作者】　　　樋口榛紀
【制作日】　　　2017年1月
【種　別】	オンラインショッピングアプリ
【連絡先】　　　haruki.higuchi1994@gmail.com

―――――――――――――――――――――――――――――――――――――

・はじめに
  このプログラムは、清田区にある喫茶店CafeCrossのために有志で製作したAndroid端末用オンラインショッピングアプリです。
　現在製作段階にあり、実用には最終的な調整が必要なため実際の発表は未だしていません。
　アプリ使用の流れは、購入したい商品をショッピングカートに追加し決済する、という形になっています。

　ー主な特徴ー
　・MySQLデータベース内にある商品の情報を、PHPを使ってアプリと連携をさせている。
　・購入完了時にネットワーク接続状況を確認し、購入者に購入成功/失敗のフィードバックをする。
　・購入が成功すると、クライアントである喫茶店に購入者の情報を含むメールが送信される。

　現在、データベース及びPHPファイルの実物はXdomainのフリーサーバーに上がっています。
　簡単なものではありますが、データベースの構築に使った.sqlのソースコードをSqlフォルダに同封しています。


・使用方法
　実行方法につきましては、以下の方法をお試しください。
　　方法１：CafeCross_v2 > app > release > app-release.apk　を任意のAndroid端末にインストールする
　　方法２：AndroidStudioでCafeCross_v2を開いて実行する
　Android 8.0.0以下の端末で動作を確認できています。

--以上--

※※　追記　※※
現在サーバーが稼働していない状態にあります。
そのため商品データの取得が出来ません。
