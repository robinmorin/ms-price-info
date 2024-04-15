-- Populating table Currency
INSERT INTO CURRENCY VALUES ('EUR', 'Euros');
INSERT INTO CURRENCY VALUES ('BRL', 'Real Brasilero');
INSERT INTO CURRENCY VALUES ('USD', 'Dolares Americanos');

-- Populating table Brand
INSERT INTO BRAND VALUES (1, 'Zara');
INSERT INTO BRAND VALUES (2, 'Corte Ingles');
INSERT INTO BRAND VALUES (3, 'Amazon Store');

-- Populating table Product
INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_NAME) VALUES (35455, 'Producto 1');
INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_NAME) VALUES (35456, 'Producto 2');
INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_NAME) VALUES (35457, 'Producto 3');

-- Populating table Prices
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURRENCY_ISO) VALUES (1, TIMESTAMP'2020-06-14 00.00.00', TIMESTAMP'2020-12-31 23.59.59', 1, 35455, 0, 35.50, 'EUR');
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURRENCY_ISO) VALUES (1, TIMESTAMP'2020-06-14 15.00.00', TIMESTAMP'2020-06-14 18.30.00', 2, 35455, 1, 25.45, 'EUR');
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURRENCY_ISO) VALUES (1, TIMESTAMP'2020-06-15 00.00.00', TIMESTAMP'2020-06-15 11.00.00', 3, 35455, 1, 30.50, 'EUR');
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURRENCY_ISO) VALUES (1, TIMESTAMP'2020-06-15 16.00.00', TIMESTAMP'2020-12-31 23.59.59', 4, 35455, 1, 38.95, 'EUR');


