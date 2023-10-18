--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.customer (
    userid integer NOT NULL,
    paymenttype character varying(255),
    leasestart date,
    leaseend date,
    isapproved boolean,
    unitid integer,
    propertyid integer
);


--
-- Name: maintenancerequest; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.maintenancerequest (
    isdealtwith boolean,
    requestid integer NOT NULL,
    "timestamp" date,
    propertyid integer,
    unitid integer,
    userid integer,
    propertid integer
);


--
-- Name: property; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.property (
    amenities text[],
    propertyid integer NOT NULL,
    address character varying(255),
    name character varying(255),
    communityannouncements text[]
);


--
-- Name: propertymanager; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.propertymanager (
    userid integer NOT NULL,
    isowner boolean,
    propertyid integer
);


--
-- Name: unit; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.unit (
    unitid integer NOT NULL,
    isfurnished boolean,
    rentamount double precision,
    floorplan character varying(255),
    condition character varying(255),
    isrented boolean,
    appliances text[],
    propertyid integer,
    rentpaid boolean,
    rentdue timestamp without time zone,
    userid integer
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    userid integer NOT NULL,
    role character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    email character varying(255),
    password character varying(255),
    phonenumber character varying(255)
);


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.customer (userid, paymenttype, leasestart, leaseend, isapproved, unitid, propertyid) FROM stdin;
\.


--
-- Data for Name: maintenancerequest; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.maintenancerequest (isdealtwith, requestid, "timestamp", propertyid, unitid, userid, propertid) FROM stdin;
\.


--
-- Data for Name: property; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.property (amenities, propertyid, address, name, communityannouncements) FROM stdin;
\.


--
-- Data for Name: propertymanager; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.propertymanager (userid, isowner, propertyid) FROM stdin;
\.


--
-- Data for Name: unit; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.unit (unitid, isfurnished, rentamount, floorplan, condition, isrented, appliances, propertyid, rentpaid, rentdue, userid) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.users (userid, role, firstname, lastname, email, password, phonenumber) FROM stdin;
\.


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (userid);


--
-- Name: maintenancerequest maintenancerequest_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.maintenancerequest
    ADD CONSTRAINT maintenancerequest_pkey PRIMARY KEY (requestid);


--
-- Name: property property_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.property
    ADD CONSTRAINT property_pkey PRIMARY KEY (propertyid);


--
-- Name: propertymanager propertymanager_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.propertymanager
    ADD CONSTRAINT propertymanager_pkey PRIMARY KEY (userid);


--
-- Name: unit unit_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.unit
    ADD CONSTRAINT unit_pkey PRIMARY KEY (unitid);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (userid);


--
-- Name: customer customer_unitid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_unitid_fkey FOREIGN KEY (unitid) REFERENCES public.unit(unitid) ON DELETE SET NULL;


--
-- Name: customer fk_customer_property; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fk_customer_property FOREIGN KEY (propertyid) REFERENCES public.property(propertyid) ON DELETE SET NULL;


--
-- Name: maintenancerequest fk_maintenancerequest_property; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.maintenancerequest
    ADD CONSTRAINT fk_maintenancerequest_property FOREIGN KEY (propertyid) REFERENCES public.property(propertyid) ON DELETE CASCADE;


--
-- Name: unit fk_unit_customer; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.unit
    ADD CONSTRAINT fk_unit_customer FOREIGN KEY (userid) REFERENCES public.customer(userid) ON DELETE SET NULL;


--
-- Name: maintenancerequest maintenancerequest_unitid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.maintenancerequest
    ADD CONSTRAINT maintenancerequest_unitid_fkey FOREIGN KEY (unitid) REFERENCES public.unit(unitid) ON DELETE CASCADE;


--
-- Name: maintenancerequest maintenancerequest_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.maintenancerequest
    ADD CONSTRAINT maintenancerequest_userid_fkey FOREIGN KEY (userid) REFERENCES public.customer(userid) ON DELETE SET NULL;


--
-- Name: propertymanager propertymanager_propertyid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.propertymanager
    ADD CONSTRAINT propertymanager_propertyid_fkey FOREIGN KEY (propertyid) REFERENCES public.property(propertyid) ON DELETE SET NULL;


--
-- Name: unit unit_propertyid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.unit
    ADD CONSTRAINT unit_propertyid_fkey FOREIGN KEY (propertyid) REFERENCES public.property(propertyid) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

