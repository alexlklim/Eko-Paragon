CREATE TABLE IF NOT EXISTS companies
(
    id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
    created              DATETIME,
    updated              DATETIME,
    created_by           BIGINT,
    deleted              BOOLEAN DEFAULT FALSE,

    company_name         VARCHAR(255),
    company_address      TEXT,
    company_email        VARCHAR(255),
    company_phone_number VARCHAR(255),
    company_website      VARCHAR(255),
    image_id             BIGINT,
    taxpayer_name        VARCHAR(255),
    sale_address         TEXT,
    nip                  VARCHAR(255),
    owner_user_id        BIGINT,
    FOREIGN KEY (image_id) REFERENCES images (id)
);

CREATE TABLE IF NOT EXISTS cash_registers
(
    id                                     BIGINT AUTO_INCREMENT PRIMARY KEY,
    created                                DATETIME,
    updated                                DATETIME,
    created_by                             BIGINT,
    deleted                                BOOLEAN DEFAULT FALSE,

    cash_register_number                   VARCHAR(255),
    cash_register_type                     ENUM ('PHYSICAL', 'VIRTUAL'),
    last_sequential_print_number           BIGINT,
    last_consecutive_number_fiscal_receipt BIGINT,
    company_id                             BIGINT,
    FOREIGN KEY (company_id) REFERENCES companies (id)
);


CREATE TABLE IF NOT EXISTS employees
(
    id                     BIGINT AUTO_INCREMENT PRIMARY KEY,
    created                DATETIME,
    updated                DATETIME,
    created_by             BIGINT,
    deleted                BOOLEAN DEFAULT FALSE,

    belong_to_user_id      BIGINT,
    first_name             VARCHAR(255),
    last_name              VARCHAR(255),
    email                  VARCHAR(255),
    phone_number           VARCHAR(255),
    address                TEXT,
    profile_image_id       BIGINT,
    company_id             BIGINT,
    cashier_identification VARCHAR(255),
    FOREIGN KEY (profile_image_id) REFERENCES images (id),
    FOREIGN KEY (company_id) REFERENCES companies (id)

);


CREATE TABLE IF NOT EXISTS employees_cash_registers
(
    employee_id      BIGINT,
    cash_register_id BIGINT,
    PRIMARY KEY (employee_id, cash_register_id),
    FOREIGN KEY (employee_id) REFERENCES employees (id),
    FOREIGN KEY (cash_register_id) REFERENCES cash_registers (id)
);


