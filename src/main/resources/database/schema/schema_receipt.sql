CREATE TABLE IF NOT EXISTS receipts
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    created           DATETIME,
    updated           DATETIME,
    created_by        BIGINT,
    deleted           BOOLEAN DEFAULT FALSE,


    uuid              VARCHAR(255),
    receiver_id       BIGINT,
    sequential_print_number BIGINT,
    consecutive_number_fiscal_receipt BIGINT,
    sales_date        DATETIME,
    currency          ENUM ('PLN', 'UA', 'EUR'),
    net_price         DOUBLE,
    cash_register_id  BIGINT,
    employee_id       BIGINT,
    FOREIGN KEY (cash_register_id) REFERENCES cash_registers (id),
    FOREIGN KEY (employee_id) REFERENCES employees (id)
);

CREATE TABLE IF NOT EXISTS products
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    created           DATETIME,
    updated           DATETIME,
    created_by        BIGINT,
    deleted           BOOLEAN DEFAULT FALSE,

    product_title     VARCHAR(255),
    category          ENUM ('A', 'B', 'C'),
    amount            INTEGER,
    unit_price        DOUBLE,
    discount          DOUBLE,
    company_id        BIGINT,
    FOREIGN KEY (company_id) REFERENCES companies (id)

);


CREATE TABLE IF NOT EXISTS receipts_products
(
    receipt_id        BIGINT,
    product_id        BIGINT,
    PRIMARY KEY (receipt_id, product_id),
    FOREIGN KEY (receipt_id) REFERENCES receipts (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);




