create database library_manager_system;
use library_manager_system;

CREATE USER 'library_admin'@'localhost' IDENTIFIED BY '123456';
GRANT SELECT, INSERT, UPDATE, DELETE ON library_manager_system.* TO 'library_admin'@'localhost';

CREATE TABLE tblcategory (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255),
    category_total_book INT
);

CREATE TABLE tblauthor (
    author_id INT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(255),
    author_born DATE,
    author_date_of_birth DATE,
    author_description TEXT,
    author_image VARCHAR(255)
);

CREATE TABLE tblpublisher (
    publisher_id INT AUTO_INCREMENT PRIMARY KEY,
    publisher_name VARCHAR(255),
    publisher_phone_number VARCHAR(20),
    publisher_address TEXT
);

CREATE TABLE tbluser (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255),
    user_image VARCHAR(255),
    user_phone_number VARCHAR(20),
    user_address TEXT,
    user_account_name VARCHAR(255),
    user_account_password VARCHAR(255),
    user_role VARCHAR(50)
);

CREATE TABLE tblbook (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    book_name VARCHAR(255),
    book_publishing_year YEAR,
    book_image VARCHAR(255),
    book_description TEXT,
    book_price DECIMAL(10, 2),
    book_inventory_number INT,
    book_page_number INT,
    book_status VARCHAR(50),
    book_language VARCHAR(50),
    author_id INT,
    category_id INT,
    publisher_id INT,
    FOREIGN KEY (author_id) REFERENCES tblauthor(author_id),
    FOREIGN KEY (category_id) REFERENCES tblcategory(category_id),
    FOREIGN KEY (publisher_id) REFERENCES tblpublisher(publisher_id)
);

CREATE TABLE tblborrowing_form (
    borrowing_form_id INT AUTO_INCREMENT PRIMARY KEY,
    borrowing_form_date DATE,
    borrowing_form_type VARCHAR(50),
    borrowing_form_deposit DECIMAL(10, 2),
    borrowing_form_due_date DATE,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES tbluser(user_id)
);

CREATE TABLE tblreturn_slip (
    return_slip_id INT AUTO_INCREMENT PRIMARY KEY,
    return_slip_date DATE,
    return_slip_refund DECIMAL(10, 2),
    return_slip_late_fee DECIMAL(10, 2),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES tbluser(user_id)
);

CREATE TABLE book_borrowing_form (
    book_id INT,
    borrowing_form_id INT,
    FOREIGN KEY (book_id) REFERENCES tblbook(book_id),
    FOREIGN KEY (borrowing_form_id) REFERENCES tblborrowing_form(borrowing_form_id),
    PRIMARY KEY (book_id, borrowing_form_id)
);

CREATE TABLE book_return_slip (
    book_id INT,
    return_slip_id INT,
    FOREIGN KEY (book_id) REFERENCES tblbook(book_id),
    FOREIGN KEY (return_slip_id) REFERENCES tblreturn_slip(return_slip_id),
    PRIMARY KEY (book_id, return_slip_id)
);


-- Thêm cột created_at và updated_at vào tblcategory
ALTER TABLE tblcategory
ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- Thêm cột created_at và updated_at vào tblauthor
ALTER TABLE tblauthor
ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- Thêm cột created_at và updated_at vào tblpublisher
ALTER TABLE tblpublisher
ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- Thêm cột created_at và updated_at vào tbluser
ALTER TABLE tbluser
ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- Thêm cột created_at và updated_at vào tblbook
ALTER TABLE tblbook
ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- Thêm cột created_at và updated_at vào tblborrowing_form
ALTER TABLE tblborrowing_form
ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- Thêm cột created_at và updated_at vào tblreturn_slip
ALTER TABLE tblreturn_slip
ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- Thêm cột created_at và updated_at vào book_borrowing_form
ALTER TABLE book_borrowing_form
ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- Thêm cột created_at và updated_at vào book_return_slip
ALTER TABLE book_return_slip
ADD created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- Thay đổi collation
ALTER TABLE tbluser CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE book_borrowing_form CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE book_return_slip CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE tblauthor CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE tblbook CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE tblborrowing_form CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE tblcategory CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE tblpublisher CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE tblreturn_slip CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
