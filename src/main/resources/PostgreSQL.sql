-- Создание таблиц
-- Создание таблицы для Equipment
CREATE TABLE equipment (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    manufacturer_country VARCHAR(255) NOT NULL,
    manufacturer_firm VARCHAR(255) NOT NULL,
    order_online BOOLEAN NOT NULL,
    installment_available BOOLEAN NOT NULL
);

-- Создание базовой таблицы для моделей
CREATE TABLE models (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    serial_number VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    size VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    in_stock BOOLEAN NOT NULL,
    equipment_id INTEGER NOT NULL,
    CONSTRAINT fk_equipment FOREIGN KEY (equipment_id) REFERENCES equipment(id)
);

-- Создание таблицы для TV моделей
CREATE TABLE tv_models (
    id INTEGER PRIMARY KEY,
    category VARCHAR(255) NOT NULL,
    technology VARCHAR(255) NOT NULL,
    CONSTRAINT fk_tv_model FOREIGN KEY (id) REFERENCES models(id)
);

-- Создание таблицы для моделей пылесосов
CREATE TABLE vacuum_models (
    id INTEGER PRIMARY KEY,
    dust_collector_volume DOUBLE PRECISION NOT NULL,
    mode_count INTEGER NOT NULL,
    CONSTRAINT fk_vacuum_model FOREIGN KEY (id) REFERENCES models(id)
);

-- Создание таблицы для Fridge моделей
CREATE TABLE fridge_models (
    id INTEGER PRIMARY KEY,
    door_count INTEGER NOT NULL,
    compressor_type VARCHAR(255) NOT NULL,
    CONSTRAINT fk_fridge_model FOREIGN KEY (id) REFERENCES models(id)
);

-- Создание таблицы для Smartphone моделей
CREATE TABLE smartphone_models (
    id INTEGER PRIMARY KEY,
    memory INTEGER NOT NULL,
    camera_count INTEGER NOT NULL,
    CONSTRAINT fk_smartphone_model FOREIGN KEY (id) REFERENCES models(id)
);

-- Создание таблицы для Computer моделей
CREATE TABLE computer_models (
    id INTEGER PRIMARY KEY,
    category VARCHAR(255) NOT NULL,
    processor_type VARCHAR(255) NOT NULL,
    CONSTRAINT fk_computer_model FOREIGN KEY (id) REFERENCES models(id)
);


-- Заполнение таблиц
-- Вставка данных в таблицу equipment
INSERT INTO equipment (name, manufacturer_country, manufacturer_firm, order_online, installment_available) VALUES
('Телевизор', 'Южная Корея', 'Samsung', true, true),
('Пылесос', 'Германия', 'Bosch', true, true),
('Холодильник', 'США', 'General Electric', true, true),
('Смартфон', 'США', 'Apple', true, true),
('Компьютер', 'США', 'Dell', true, true);

-- Вставка данных в таблицу models и соответствующие таблицы моделей
-- Телевизоры
INSERT INTO models (name, serial_number, color, size, price, in_stock, equipment_id) VALUES
('Samsung QLED', 'SN12345', 'Черный', '65 дюймов', 1500.00, true, 1),
('LG OLED', 'SN12346', 'Белый', '55 дюймов', 1200.00, true, 1),
('Sony Bravia', 'SN12347', 'Серебристый', '75 дюймов', 2000.00, true, 1);

INSERT INTO tv_models (id, category, technology) VALUES
((SELECT id FROM models WHERE serial_number='SN12345'), 'Premium', 'QLED'),
((SELECT id FROM models WHERE serial_number='SN12346'), 'High-end', 'OLED'),
((SELECT id FROM models WHERE serial_number='SN12347'), 'Premium', 'LED');

-- Пылесосы
INSERT INTO models (name, serial_number, color, size, price, in_stock, equipment_id) VALUES
('Bosch Series 6', 'SN12348', 'Красный', 'Компактный', 300.00, true, 2),
('Dyson V11', 'SN12349', 'Синий', 'Компактный', 700.00, true, 2),
('Samsung PowerStick', 'SN12350', 'Черный', 'Компактный', 500.00, true, 2);

INSERT INTO vacuum_models (id, dust_collector_volume, mode_count) VALUES
((SELECT id FROM models WHERE serial_number='SN12348'), 2.5, 3),
((SELECT id FROM models WHERE serial_number='SN12349'), 3.0, 5),
((SELECT id FROM models WHERE serial_number='SN12350'), 2.0, 4);

-- Холодильники
INSERT INTO models (name, serial_number, color, size, price, in_stock, equipment_id) VALUES
('GE Profile', 'SN12351', 'Белый', 'Большой', 2000.00, true, 3),
('LG Smart', 'SN12352', 'Черный', 'Средний', 1500.00, true, 3),
('Samsung Family Hub', 'SN12353', 'Серебристый', 'Большой', 2500.00, true, 3);

INSERT INTO fridge_models (id, door_count, compressor_type) VALUES
((SELECT id FROM models WHERE serial_number='SN12351'), 4, 'Инверторный'),
((SELECT id FROM models WHERE serial_number='SN12352'), 2, 'Линейный'),
((SELECT id FROM models WHERE serial_number='SN12353'), 3, 'Инверторный');

-- Смартфоны
INSERT INTO models (name, serial_number, color, size, price, in_stock, equipment_id) VALUES
('iPhone 12', 'SN12354', 'Черный', '6.1 дюймов', 800.00, true, 4),
('iPhone 13', 'SN12355', 'Белый', '6.1 дюймов', 1000.00, true, 4),
('Samsung Galaxy S21', 'SN12356', 'Серебристый', '6.2 дюймов', 900.00, true, 4);

INSERT INTO smartphone_models (id, memory, camera_count) VALUES
((SELECT id FROM models WHERE serial_number='SN12354'), 128, 2),
((SELECT id FROM models WHERE serial_number='SN12355'), 256, 3),
((SELECT id FROM models WHERE serial_number='SN12356'), 128, 4);

-- Компьютеры
INSERT INTO models (name, serial_number, color, size, price, in_stock, equipment_id) VALUES
('Dell XPS 13', 'SN12357', 'Серебристый', '13 дюймов', 1200.00, true, 5),
('MacBook Pro 16', 'SN12358', 'Серый', '16 дюймов', 2500.00, true, 5),
('HP Envy 15', 'SN12359', 'Черный', '15 дюймов', 1300.00, true, 5);

INSERT INTO computer_models (id, category, processor_type) VALUES
((SELECT id FROM models WHERE serial_number='SN12357'), 'Ультрабук', 'Intel Core i7'),
((SELECT id FROM models WHERE serial_number='SN12358'), 'Профессиональный', 'Apple M1 Pro'),
((SELECT id FROM models WHERE serial_number='SN12359'), 'Ультрабук', 'AMD Ryzen 7');
