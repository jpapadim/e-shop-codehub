ALTER TABLE products drop column cart_id;
INSERT INTO products (keywords, metric_unit, other_product_info, price, product_desc, stock, stock_level, tax, category_id)
VALUES ('παντελονι,καλοκαιρινο,καθημερινο', 'PIECE', 'Παντελόνι καθημερινό.', 19.99, 'Παντελόνι καθημερινό', 10.00, 2.00, 24.00, 1),
('παντελονι,καλοκαιρινο,βραδινο', 'PIECE', 'Παντελόνι βραδινό.', 21.99, 'Παντελόνι βραδινό.', 10.00, 2.00, 24.00, 1),
('μπλουζα,καλοκαιρινη,καθημερινη', 'PIECE', 'Μπλούζα.', 9.99, 'Μπλούζα καθημερινή', 10.00, 2.00, 24.00, 2),
('μπλουζα,καλοκαιρινη,βραδινη', 'PIECE', 'Μπλούζα βραδινή.', 10.99, 'Μπλούζα βραδινή', 10.00, 2.00, 24.00, 2),
('παπουτσια,καλοκαιρινα,αθλητικα', 'PIECE', 'Παπούτσια αθλητικα.', 10.99, 'Παπούτσια αθλητικά', 10.00, 2.00, 24.00, 3),
('παπουτσια,καλοκαιρινα', 'PIECE', 'Παπούτσια', 10.99, 'Παπούτσια', 10.00, 2.00, 24.00, 3);
