-- Database: web_customer_tracker

-- DROP DATABASE web_customer_tracker;

CREATE DATABASE web_customer_tracker
    WITH
    OWNER = springstudent
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE spring_mvc
    IS 'Database for study project from Udemy';

-- Table: public.customer

-- DROP TABLE public.customer;

create table customer
(
  id         serial not null
    constraint customer_pk
      primary key,
  first_name varchar(45) default NULL::character varying,
  last_name  varchar(45) default NULL::character varying,
  email      varchar(45) default NULL::character varying
);

ALTER TABLE customer
    OWNER to springstudent;

INSERT INTO customer VALUES
  (1,'David','Adams','david@luv2code.com'),
  (2,'John','Doe','john@luv2code.com'),
  (3,'Ajay','Rao','ajay@luv2code.com'),
  (4,'Mary','Public','mary@luv2code.com'),
  (5,'Maxwell','Dixon','max@luv2code.com');