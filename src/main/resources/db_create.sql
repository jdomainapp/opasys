--
-- PostgreSQL database dump
--

-- Dumped from database version 10.23 (Ubuntu 10.23-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.23 (Ubuntu 10.23-0ubuntu0.18.04.1)

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

--
-- Name: opasys; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE opasys WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE opasys OWNER TO postgres;

\connect opasys

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

--
-- Name: defect; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA defect;


ALTER SCHEMA defect OWNER TO admin;

--
-- Name: issue; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA issue;


ALTER SCHEMA issue OWNER TO admin;

--
-- Name: knowledge_asset; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA knowledge_asset;


ALTER SCHEMA knowledge_asset OWNER TO admin;

--
-- Name: opa; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA opa;


ALTER SCHEMA opa OWNER TO admin;

--
-- Name: organisational_asset; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA organisational_asset;


ALTER SCHEMA organisational_asset OWNER TO admin;

--
-- Name: project; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA project;


ALTER SCHEMA project OWNER TO admin;

--
-- Name: risk; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA risk;


ALTER SCHEMA risk OWNER TO admin;

--
-- Name: security; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA security;


ALTER SCHEMA security OWNER TO admin;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: defect; Type: TABLE; Schema: defect; Owner: admin
--

CREATE TABLE defect.defect (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    level character varying(20) NOT NULL,
    name character varying NOT NULL,
    description character varying,
    solution character varying,
    status character varying
);


ALTER TABLE defect.defect OWNER TO admin;

--
-- Name: comment; Type: TABLE; Schema: issue; Owner: admin
--

CREATE TABLE issue.comment (
    id integer NOT NULL,
    issue_id integer NOT NULL,
    comment character varying,
    create_date date,
    user_id integer NOT NULL
);


ALTER TABLE issue.comment OWNER TO admin;

--
-- Name: issue; Type: TABLE; Schema: issue; Owner: admin
--

CREATE TABLE issue.issue (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_create_id integer NOT NULL,
    assignee_id integer NOT NULL,
    description character varying,
    summary character varying NOT NULL,
    status integer NOT NULL,
    type integer NOT NULL,
    priority integer NOT NULL,
    create_date date,
    parent_issue_id integer
);


ALTER TABLE issue.issue OWNER TO admin;

--
-- Name: user; Type: TABLE; Schema: issue; Owner: admin
--

CREATE TABLE issue."user" (
    id integer NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL,
    name character varying NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE issue."user" OWNER TO admin;

--
-- Name: conf_asset; Type: TABLE; Schema: knowledge_asset; Owner: admin
--

CREATE TABLE knowledge_asset.conf_asset (
    id integer NOT NULL,
    opa_id integer NOT NULL
);


ALTER TABLE knowledge_asset.conf_asset OWNER TO admin;

--
-- Name: conf_asset_id_seq; Type: SEQUENCE; Schema: knowledge_asset; Owner: admin
--

CREATE SEQUENCE knowledge_asset.conf_asset_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE knowledge_asset.conf_asset_id_seq OWNER TO admin;

--
-- Name: conf_asset_id_seq; Type: SEQUENCE OWNED BY; Schema: knowledge_asset; Owner: admin
--

ALTER SEQUENCE knowledge_asset.conf_asset_id_seq OWNED BY knowledge_asset.conf_asset.id;


--
-- Name: fin_asset; Type: TABLE; Schema: knowledge_asset; Owner: admin
--

CREATE TABLE knowledge_asset.fin_asset (
    id integer NOT NULL,
    opa_id integer NOT NULL
);


ALTER TABLE knowledge_asset.fin_asset OWNER TO admin;

--
-- Name: fin_asset_id_seq; Type: SEQUENCE; Schema: knowledge_asset; Owner: admin
--

CREATE SEQUENCE knowledge_asset.fin_asset_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE knowledge_asset.fin_asset_id_seq OWNER TO admin;

--
-- Name: fin_asset_id_seq; Type: SEQUENCE OWNED BY; Schema: knowledge_asset; Owner: admin
--

ALTER SEQUENCE knowledge_asset.fin_asset_id_seq OWNED BY knowledge_asset.fin_asset.id;


--
-- Name: knowledge_asset; Type: TABLE; Schema: knowledge_asset; Owner: admin
--

CREATE TABLE knowledge_asset.knowledge_asset (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    project_id integer NOT NULL,
    activity_type integer NOT NULL,
    status integer,
    attachment character varying NOT NULL
);


ALTER TABLE knowledge_asset.knowledge_asset OWNER TO admin;

--
-- Name: metric_asset; Type: TABLE; Schema: knowledge_asset; Owner: admin
--

CREATE TABLE knowledge_asset.metric_asset (
    id integer NOT NULL,
    opa_id integer NOT NULL
);


ALTER TABLE knowledge_asset.metric_asset OWNER TO admin;

--
-- Name: metric_asset_id_seq; Type: SEQUENCE; Schema: knowledge_asset; Owner: admin
--

CREATE SEQUENCE knowledge_asset.metric_asset_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE knowledge_asset.metric_asset_id_seq OWNER TO admin;

--
-- Name: metric_asset_id_seq; Type: SEQUENCE OWNED BY; Schema: knowledge_asset; Owner: admin
--

ALTER SEQUENCE knowledge_asset.metric_asset_id_seq OWNED BY knowledge_asset.metric_asset.id;


--
-- Name: plan_asset; Type: TABLE; Schema: knowledge_asset; Owner: admin
--

CREATE TABLE knowledge_asset.plan_asset (
    id integer NOT NULL,
    opa_id integer NOT NULL
);


ALTER TABLE knowledge_asset.plan_asset OWNER TO admin;

--
-- Name: plan_asset_id_seq; Type: SEQUENCE; Schema: knowledge_asset; Owner: admin
--

CREATE SEQUENCE knowledge_asset.plan_asset_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE knowledge_asset.plan_asset_id_seq OWNER TO admin;

--
-- Name: plan_asset_id_seq; Type: SEQUENCE OWNED BY; Schema: knowledge_asset; Owner: admin
--

ALTER SEQUENCE knowledge_asset.plan_asset_id_seq OWNED BY knowledge_asset.plan_asset.id;


--
-- Name: project; Type: TABLE; Schema: knowledge_asset; Owner: admin
--

CREATE TABLE knowledge_asset.project (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(200),
    type_id integer NOT NULL,
    username character varying(50) NOT NULL,
    status integer,
    start_date date,
    end_date date
);


ALTER TABLE knowledge_asset.project OWNER TO admin;

--
-- Name: risk_asset; Type: TABLE; Schema: knowledge_asset; Owner: admin
--

CREATE TABLE knowledge_asset.risk_asset (
    id integer NOT NULL,
    opa_id integer NOT NULL
);


ALTER TABLE knowledge_asset.risk_asset OWNER TO admin;

--
-- Name: risk_asset_id_seq; Type: SEQUENCE; Schema: knowledge_asset; Owner: admin
--

CREATE SEQUENCE knowledge_asset.risk_asset_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE knowledge_asset.risk_asset_id_seq OWNER TO admin;

--
-- Name: risk_asset_id_seq; Type: SEQUENCE OWNED BY; Schema: knowledge_asset; Owner: admin
--

ALTER SEQUENCE knowledge_asset.risk_asset_id_seq OWNED BY knowledge_asset.risk_asset.id;


--
-- Name: comment; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.comment (
    id integer NOT NULL,
    issue_id integer NOT NULL,
    comment character varying,
    create_date date,
    user_id integer NOT NULL
);


ALTER TABLE opa.comment OWNER TO admin;

--
-- Name: defect; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.defect (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    level character varying(20) NOT NULL,
    name character varying NOT NULL,
    description character varying,
    solution character varying,
    status character varying
);


ALTER TABLE opa.defect OWNER TO admin;

--
-- Name: issue; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.issue (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_create_id integer NOT NULL,
    assignee_id integer NOT NULL,
    description character varying,
    summary character varying NOT NULL,
    status integer NOT NULL,
    type integer NOT NULL,
    priority integer NOT NULL,
    create_date date,
    parent_issue_id integer
);


ALTER TABLE opa.issue OWNER TO admin;

--
-- Name: knowledge_asset; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.knowledge_asset (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    project_id integer NOT NULL,
    activity_type integer NOT NULL,
    status integer,
    attachment character varying NOT NULL
);


ALTER TABLE opa.knowledge_asset OWNER TO admin;

--
-- Name: knowledge_asset_id_seq; Type: SEQUENCE; Schema: opa; Owner: admin
--

CREATE SEQUENCE opa.knowledge_asset_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE opa.knowledge_asset_id_seq OWNER TO admin;

--
-- Name: knowledge_asset_id_seq; Type: SEQUENCE OWNED BY; Schema: opa; Owner: admin
--

ALTER SEQUENCE opa.knowledge_asset_id_seq OWNED BY opa.knowledge_asset.id;


--
-- Name: org_asset; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.org_asset (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    project_type integer,
    activity_type integer,
    status integer,
    attachment character varying NOT NULL
);


ALTER TABLE opa.org_asset OWNER TO admin;

--
-- Name: project; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.project (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(200),
    project_type integer NOT NULL,
    username character varying(50) NOT NULL,
    status integer,
    start_date date,
    end_date date
);


ALTER TABLE opa.project OWNER TO admin;

--
-- Name: risk; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.risk (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    level character varying(20) NOT NULL,
    name character varying NOT NULL,
    description character varying,
    occurence character varying,
    impact character varying,
    solution character varying,
    status character varying
);


ALTER TABLE opa.risk OWNER TO admin;

--
-- Name: org_asset; Type: TABLE; Schema: organisational_asset; Owner: admin
--

CREATE TABLE organisational_asset.org_asset (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    project_type integer,
    activity_type integer,
    status integer,
    attachment character varying NOT NULL
);


ALTER TABLE organisational_asset.org_asset OWNER TO admin;

--
-- Name: org_asset_id_seq; Type: SEQUENCE; Schema: organisational_asset; Owner: admin
--

CREATE SEQUENCE organisational_asset.org_asset_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE organisational_asset.org_asset_id_seq OWNER TO admin;

--
-- Name: org_asset_id_seq; Type: SEQUENCE OWNED BY; Schema: organisational_asset; Owner: admin
--

ALTER SEQUENCE organisational_asset.org_asset_id_seq OWNED BY organisational_asset.org_asset.id;


--
-- Name: activity; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.activity (
    id integer NOT NULL,
    name character varying NOT NULL,
    type_id integer,
    user_id integer NOT NULL,
    attachment character varying,
    project_id integer
);


ALTER TABLE project.activity OWNER TO admin;

--
-- Name: activity_id_seq; Type: SEQUENCE; Schema: project; Owner: admin
--

CREATE SEQUENCE project.activity_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE project.activity_id_seq OWNER TO admin;

--
-- Name: activity_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.activity_id_seq OWNED BY project.activity.id;


--
-- Name: activity_type; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.activity_type (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE project.activity_type OWNER TO admin;

--
-- Name: activity_type_id_seq; Type: SEQUENCE; Schema: project; Owner: admin
--

CREATE SEQUENCE project.activity_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE project.activity_type_id_seq OWNER TO admin;

--
-- Name: activity_type_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.activity_type_id_seq OWNED BY project.activity_type.id;


--
-- Name: comment; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.comment (
    id integer NOT NULL,
    issue_id integer NOT NULL,
    comment character varying,
    create_date date,
    user_id integer NOT NULL
);


ALTER TABLE project.comment OWNER TO admin;

--
-- Name: comment_id_seq; Type: SEQUENCE; Schema: project; Owner: admin
--

CREATE SEQUENCE project.comment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE project.comment_id_seq OWNER TO admin;

--
-- Name: comment_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.comment_id_seq OWNED BY project.comment.id;


--
-- Name: defect; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.defect (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    level character varying(20) NOT NULL,
    name character varying NOT NULL,
    description character varying,
    solution character varying,
    status character varying
);


ALTER TABLE project.defect OWNER TO admin;

--
-- Name: defect_id_seq; Type: SEQUENCE; Schema: project; Owner: admin
--

CREATE SEQUENCE project.defect_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE project.defect_id_seq OWNER TO admin;

--
-- Name: defect_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.defect_id_seq OWNED BY project.defect.id;


--
-- Name: issue; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.issue (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_create_id integer NOT NULL,
    assignee_id integer NOT NULL,
    description character varying,
    summary character varying NOT NULL,
    status integer NOT NULL,
    type integer NOT NULL,
    priority integer NOT NULL,
    create_date date,
    parent_issue_id integer
);


ALTER TABLE project.issue OWNER TO admin;

--
-- Name: issue_id_seq; Type: SEQUENCE; Schema: project; Owner: admin
--

CREATE SEQUENCE project.issue_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE project.issue_id_seq OWNER TO admin;

--
-- Name: issue_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.issue_id_seq OWNED BY project.issue.id;


--
-- Name: project; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.project (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(200),
    type_id integer NOT NULL,
    user_id integer NOT NULL,
    status integer,
    start_date date,
    end_date date
);


ALTER TABLE project.project OWNER TO admin;

--
-- Name: project_id_seq; Type: SEQUENCE; Schema: project; Owner: admin
--

CREATE SEQUENCE project.project_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE project.project_id_seq OWNER TO admin;

--
-- Name: project_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.project_id_seq OWNED BY project.project.id;


--
-- Name: project_type; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.project_type (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE project.project_type OWNER TO admin;

--
-- Name: project_type_id_seq; Type: SEQUENCE; Schema: project; Owner: admin
--

CREATE SEQUENCE project.project_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE project.project_type_id_seq OWNER TO admin;

--
-- Name: project_type_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.project_type_id_seq OWNED BY project.project_type.id;


--
-- Name: risk; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.risk (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    level character varying(20) NOT NULL,
    name character varying NOT NULL,
    description character varying,
    occurence character varying,
    impact character varying,
    solution character varying,
    status character varying
);


ALTER TABLE project.risk OWNER TO admin;

--
-- Name: risk_id_seq; Type: SEQUENCE; Schema: project; Owner: admin
--

CREATE SEQUENCE project.risk_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE project.risk_id_seq OWNER TO admin;

--
-- Name: risk_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.risk_id_seq OWNED BY project.risk.id;


--
-- Name: user; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project."user" (
    id integer NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL,
    name character varying NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE project."user" OWNER TO admin;

--
-- Name: risk; Type: TABLE; Schema: risk; Owner: admin
--

CREATE TABLE risk.risk (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    level character varying(20) NOT NULL,
    name character varying NOT NULL,
    description character varying,
    occurence character varying,
    impact character varying,
    solution character varying,
    status character varying
);


ALTER TABLE risk.risk OWNER TO admin;

--
-- Name: user; Type: TABLE; Schema: security; Owner: admin
--

CREATE TABLE security."user" (
    id integer NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL,
    name character varying NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE security."user" OWNER TO admin;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: security; Owner: admin
--

CREATE SEQUENCE security.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.user_id_seq OWNER TO admin;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: admin
--

ALTER SEQUENCE security.user_id_seq OWNED BY security."user".id;


--
-- Name: conf_asset id; Type: DEFAULT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.conf_asset ALTER COLUMN id SET DEFAULT nextval('knowledge_asset.conf_asset_id_seq'::regclass);


--
-- Name: fin_asset id; Type: DEFAULT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.fin_asset ALTER COLUMN id SET DEFAULT nextval('knowledge_asset.fin_asset_id_seq'::regclass);


--
-- Name: metric_asset id; Type: DEFAULT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.metric_asset ALTER COLUMN id SET DEFAULT nextval('knowledge_asset.metric_asset_id_seq'::regclass);


--
-- Name: plan_asset id; Type: DEFAULT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.plan_asset ALTER COLUMN id SET DEFAULT nextval('knowledge_asset.plan_asset_id_seq'::regclass);


--
-- Name: risk_asset id; Type: DEFAULT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.risk_asset ALTER COLUMN id SET DEFAULT nextval('knowledge_asset.risk_asset_id_seq'::regclass);


--
-- Name: knowledge_asset id; Type: DEFAULT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.knowledge_asset ALTER COLUMN id SET DEFAULT nextval('opa.knowledge_asset_id_seq'::regclass);


--
-- Name: org_asset id; Type: DEFAULT; Schema: organisational_asset; Owner: admin
--

ALTER TABLE ONLY organisational_asset.org_asset ALTER COLUMN id SET DEFAULT nextval('organisational_asset.org_asset_id_seq'::regclass);


--
-- Name: activity id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.activity ALTER COLUMN id SET DEFAULT nextval('project.activity_id_seq'::regclass);


--
-- Name: activity_type id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.activity_type ALTER COLUMN id SET DEFAULT nextval('project.activity_type_id_seq'::regclass);


--
-- Name: comment id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.comment ALTER COLUMN id SET DEFAULT nextval('project.comment_id_seq'::regclass);


--
-- Name: defect id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.defect ALTER COLUMN id SET DEFAULT nextval('project.defect_id_seq'::regclass);


--
-- Name: issue id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue ALTER COLUMN id SET DEFAULT nextval('project.issue_id_seq'::regclass);


--
-- Name: project id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.project ALTER COLUMN id SET DEFAULT nextval('project.project_id_seq'::regclass);


--
-- Name: project_type id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.project_type ALTER COLUMN id SET DEFAULT nextval('project.project_type_id_seq'::regclass);


--
-- Name: risk id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.risk ALTER COLUMN id SET DEFAULT nextval('project.risk_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: security; Owner: admin
--

ALTER TABLE ONLY security."user" ALTER COLUMN id SET DEFAULT nextval('security.user_id_seq'::regclass);


--
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- Name: issue issue_pkey; Type: CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue.issue
    ADD CONSTRAINT issue_pkey PRIMARY KEY (id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: fin_asset fin_asset_pkey; Type: CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.fin_asset
    ADD CONSTRAINT fin_asset_pkey PRIMARY KEY (id);


--
-- Name: metric_asset metric_asset_pkey; Type: CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.metric_asset
    ADD CONSTRAINT metric_asset_pkey PRIMARY KEY (id);


--
-- Name: knowledge_asset opa_pkey; Type: CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.knowledge_asset
    ADD CONSTRAINT opa_pkey PRIMARY KEY (id);


--
-- Name: plan_asset plan_asset_pkey; Type: CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.plan_asset
    ADD CONSTRAINT plan_asset_pkey PRIMARY KEY (id);


--
-- Name: project project_pkey; Type: CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (id);


--
-- Name: risk_asset risk_asset_pkey; Type: CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.risk_asset
    ADD CONSTRAINT risk_asset_pkey PRIMARY KEY (id);


--
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- Name: issue issue_pkey; Type: CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.issue
    ADD CONSTRAINT issue_pkey PRIMARY KEY (id);


--
-- Name: knowledge_asset knowledge_asset_pkey; Type: CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.knowledge_asset
    ADD CONSTRAINT knowledge_asset_pkey PRIMARY KEY (id);


--
-- Name: org_asset org_asset_pkey; Type: CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.org_asset
    ADD CONSTRAINT org_asset_pkey PRIMARY KEY (id);


--
-- Name: project project_pkey; Type: CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (id);


--
-- Name: org_asset org_asset_pkey; Type: CONSTRAINT; Schema: organisational_asset; Owner: admin
--

ALTER TABLE ONLY organisational_asset.org_asset
    ADD CONSTRAINT org_asset_pkey PRIMARY KEY (id);


--
-- Name: activity activity_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.activity
    ADD CONSTRAINT activity_pkey PRIMARY KEY (id);


--
-- Name: activity_type activity_type_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.activity_type
    ADD CONSTRAINT activity_type_pkey PRIMARY KEY (id);


--
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- Name: defect defect_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.defect
    ADD CONSTRAINT defect_pkey PRIMARY KEY (id);


--
-- Name: issue issue_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue
    ADD CONSTRAINT issue_pkey PRIMARY KEY (id);


--
-- Name: project project_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (id);


--
-- Name: project_type project_type_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.project_type
    ADD CONSTRAINT project_type_pkey PRIMARY KEY (id);


--
-- Name: risk risk_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.risk
    ADD CONSTRAINT risk_pkey PRIMARY KEY (id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: security; Owner: admin
--

ALTER TABLE ONLY security."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: comment comment_issue_fk; Type: FK CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue.comment
    ADD CONSTRAINT comment_issue_fk FOREIGN KEY (issue_id) REFERENCES issue.issue(id);


--
-- Name: comment comment_user_fk; Type: FK CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue.comment
    ADD CONSTRAINT comment_user_fk FOREIGN KEY (user_id) REFERENCES issue."user"(id) NOT VALID;


--
-- Name: issue issue_user_assignee_fk; Type: FK CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue.issue
    ADD CONSTRAINT issue_user_assignee_fk FOREIGN KEY (assignee_id) REFERENCES issue."user"(id);


--
-- Name: issue issue_user_create_fk; Type: FK CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue.issue
    ADD CONSTRAINT issue_user_create_fk FOREIGN KEY (user_create_id) REFERENCES issue."user"(id);


--
-- Name: conf_asset conf_opa_fk; Type: FK CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.conf_asset
    ADD CONSTRAINT conf_opa_fk FOREIGN KEY (opa_id) REFERENCES knowledge_asset.knowledge_asset(id);


--
-- Name: fin_asset fin_opa_fk; Type: FK CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.fin_asset
    ADD CONSTRAINT fin_opa_fk FOREIGN KEY (opa_id) REFERENCES knowledge_asset.knowledge_asset(id);


--
-- Name: metric_asset metric_opa_fk; Type: FK CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.metric_asset
    ADD CONSTRAINT metric_opa_fk FOREIGN KEY (opa_id) REFERENCES knowledge_asset.knowledge_asset(id);


--
-- Name: knowledge_asset opa_project_fk; Type: FK CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.knowledge_asset
    ADD CONSTRAINT opa_project_fk FOREIGN KEY (project_id) REFERENCES knowledge_asset.project(id);


--
-- Name: plan_asset plan_opa_fk; Type: FK CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.plan_asset
    ADD CONSTRAINT plan_opa_fk FOREIGN KEY (opa_id) REFERENCES knowledge_asset.knowledge_asset(id);


--
-- Name: risk_asset risk_opa_fk; Type: FK CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.risk_asset
    ADD CONSTRAINT risk_opa_fk FOREIGN KEY (opa_id) REFERENCES knowledge_asset.knowledge_asset(id);


--
-- Name: comment comment_issue_fk; Type: FK CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.comment
    ADD CONSTRAINT comment_issue_fk FOREIGN KEY (issue_id) REFERENCES issue.issue(id);


--
-- Name: knowledge_asset knowledge_project_fk; Type: FK CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.knowledge_asset
    ADD CONSTRAINT knowledge_project_fk FOREIGN KEY (project_id) REFERENCES opa.project(id);


--
-- Name: activity activity_project_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.activity
    ADD CONSTRAINT activity_project_fk FOREIGN KEY (project_id) REFERENCES project.project(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: activity activity_type_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.activity
    ADD CONSTRAINT activity_type_fk FOREIGN KEY (type_id) REFERENCES project.activity_type(id);


--
-- Name: activity activity_user_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.activity
    ADD CONSTRAINT activity_user_fk FOREIGN KEY (user_id) REFERENCES project."user"(id);


--
-- Name: comment comment_issue_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.comment
    ADD CONSTRAINT comment_issue_fk FOREIGN KEY (issue_id) REFERENCES project.issue(id);


--
-- Name: comment comment_user_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.comment
    ADD CONSTRAINT comment_user_fk FOREIGN KEY (user_id) REFERENCES project."user"(id);


--
-- Name: defect defect_project_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.defect
    ADD CONSTRAINT defect_project_fk FOREIGN KEY (project_id) REFERENCES project.project(id);


--
-- Name: defect defect_user_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.defect
    ADD CONSTRAINT defect_user_fk FOREIGN KEY (user_id) REFERENCES project."user"(id);


--
-- Name: issue issue_issue_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue
    ADD CONSTRAINT issue_issue_fk FOREIGN KEY (parent_issue_id) REFERENCES project.issue(id);


--
-- Name: issue issue_project_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue
    ADD CONSTRAINT issue_project_fk FOREIGN KEY (project_id) REFERENCES project.project(id) NOT VALID;


--
-- Name: issue issue_user_assignee_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue
    ADD CONSTRAINT issue_user_assignee_fk FOREIGN KEY (assignee_id) REFERENCES project."user"(id);


--
-- Name: issue issue_user_create_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue
    ADD CONSTRAINT issue_user_create_fk FOREIGN KEY (user_create_id) REFERENCES project."user"(id);


--
-- Name: project project_type_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.project
    ADD CONSTRAINT project_type_fk FOREIGN KEY (type_id) REFERENCES project.project_type(id);


--
-- Name: project project_user_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.project
    ADD CONSTRAINT project_user_fk FOREIGN KEY (user_id) REFERENCES project."user"(id);


--
-- Name: risk risk_project_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.risk
    ADD CONSTRAINT risk_project_fk FOREIGN KEY (project_id) REFERENCES project.project(id);


--
-- Name: risk risk_user_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.risk
    ADD CONSTRAINT risk_user_fk FOREIGN KEY (user_id) REFERENCES project."user"(id);


--
-- PostgreSQL database dump complete
--

