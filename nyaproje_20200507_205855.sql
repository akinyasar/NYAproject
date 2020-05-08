--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12rc1

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
-- Name: kullanicigiris; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kullanicigiris (
    id integer NOT NULL,
    kullaniciadi text NOT NULL,
    sifre text NOT NULL
);


ALTER TABLE public.kullanicigiris OWNER TO postgres;

--
-- Data for Name: kullanicigiris; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.kullanicigiris VALUES (1, 'akinyasar', '8520');
INSERT INTO public.kullanicigiris VALUES (2, 'gamzeagirtas', '8520');


--
-- Name: kullanicigiris kullanicigiris_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kullanicigiris
    ADD CONSTRAINT kullanicigiris_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

