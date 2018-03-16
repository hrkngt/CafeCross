USE cafecross_main;

DELETE FROM products;
INSERT INTO products(id, product_name, description, price, image) VALUES
      (null, "クロスブレンド（深煎り）", "深味のある調和のとれた味わいです", 520, "http://cafecross.php.xdomain.jp/Blend.png"),
      (null, "マイルドブレンド（中浅煎り）", "まろやかな味わいが魅力です", 480, "http://cafecross.php.xdomain.jp/LightBlend.png"),
      (null, "ケニア（深煎り）", "丹念に焼き込み、スッキリした苦みと程よい酸味が魅力です", 600, null),
      (null, "タンザニア（中浅煎り）", "強い酸味とほのかな甘みが魅力です", 550, null),
      (null, "グァテマラ（中深煎り）", "程よい苦みと深い味わいが魅力です", 500, null)
