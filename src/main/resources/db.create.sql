--
-- PostgreSQL database dump
--

-- Dumped from database version 10.23 (Ubuntu 10.23-0ubuntu0.18.04.2)
-- Dumped by pg_dump version 10.23 (Ubuntu 10.23-0ubuntu0.18.04.2)

-- Started on 2023-06-21 01:41:42 PDT

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
-- TOC entry 14 (class 2615 OID 24588)
-- Name: defect; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA defect;


ALTER SCHEMA defect OWNER TO admin;

--
-- TOC entry 5 (class 2615 OID 40960)
-- Name: issue; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA issue;


ALTER SCHEMA issue OWNER TO admin;

--
-- TOC entry 9 (class 2615 OID 24624)
-- Name: knowledge_asset; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA knowledge_asset;


ALTER SCHEMA knowledge_asset OWNER TO admin;

--
-- TOC entry 13 (class 2615 OID 24753)
-- Name: opa; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA opa;


ALTER SCHEMA opa OWNER TO admin;

--
-- TOC entry 4 (class 2615 OID 24612)
-- Name: org_asset; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA org_asset;


ALTER SCHEMA org_asset OWNER TO admin;

--
-- TOC entry 11 (class 2615 OID 24784)
-- Name: project; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA project;


ALTER SCHEMA project OWNER TO admin;

--
-- TOC entry 8 (class 2615 OID 24576)
-- Name: risk; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA risk;


ALTER SCHEMA risk OWNER TO admin;

--
-- TOC entry 12 (class 2615 OID 24600)
-- Name: security; Type: SCHEMA; Schema: -; Owner: admin
--

CREATE SCHEMA security;


ALTER SCHEMA security OWNER TO admin;

--
-- TOC entry 1 (class 3079 OID 13039)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3213 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 229 (class 1259 OID 41190)
-- Name: defect; Type: TABLE; Schema: defect; Owner: admin
--

CREATE TABLE defect.defect (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    name character varying NOT NULL,
    description character varying,
    solution character varying,
    attachment character varying,
    status integer,
    level integer,
    activity_id integer
);


ALTER TABLE defect.defect OWNER TO admin;

--
-- TOC entry 235 (class 1259 OID 41261)
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
-- TOC entry 222 (class 1259 OID 40971)
-- Name: issue; Type: TABLE; Schema: issue; Owner: admin
--

CREATE TABLE issue.issue (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    assignee_id integer NOT NULL,
    description character varying,
    summary character varying NOT NULL,
    status integer NOT NULL,
    type integer NOT NULL,
    priority integer NOT NULL,
    create_date date,
    parent_issue_id integer,
    attachment character varying,
    name character varying,
    activity_id integer
);


ALTER TABLE issue.issue OWNER TO admin;

--
-- TOC entry 221 (class 1259 OID 40961)
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
-- TOC entry 231 (class 1259 OID 41216)
-- Name: conf_asset; Type: TABLE; Schema: knowledge_asset; Owner: admin
--

CREATE TABLE knowledge_asset.conf_asset (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying,
    project_id integer NOT NULL,
    status integer,
    attachment character varying NOT NULL,
    user_id integer NOT NULL,
    activity_id integer
);


ALTER TABLE knowledge_asset.conf_asset OWNER TO admin;

--
-- TOC entry 232 (class 1259 OID 41224)
-- Name: fin_asset; Type: TABLE; Schema: knowledge_asset; Owner: admin
--

CREATE TABLE knowledge_asset.fin_asset (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying,
    project_id integer NOT NULL,
    status integer,
    attachment character varying NOT NULL,
    user_id integer NOT NULL,
    activity_id integer
);


ALTER TABLE knowledge_asset.fin_asset OWNER TO admin;

--
-- TOC entry 233 (class 1259 OID 41232)
-- Name: metric_asset; Type: TABLE; Schema: knowledge_asset; Owner: admin
--

CREATE TABLE knowledge_asset.metric_asset (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying,
    project_id integer NOT NULL,
    status integer,
    attachment character varying NOT NULL,
    user_id integer NOT NULL,
    activity_id integer
);


ALTER TABLE knowledge_asset.metric_asset OWNER TO admin;

--
-- TOC entry 234 (class 1259 OID 41240)
-- Name: plan_asset; Type: TABLE; Schema: knowledge_asset; Owner: admin
--

CREATE TABLE knowledge_asset.plan_asset (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying,
    project_id integer NOT NULL,
    status integer,
    attachment character varying NOT NULL,
    user_id integer NOT NULL,
    activity_id integer
);


ALTER TABLE knowledge_asset.plan_asset OWNER TO admin;

--
-- TOC entry 244 (class 1259 OID 57612)
-- Name: activity_asset; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.activity_asset (
    id integer NOT NULL,
    name character varying,
    description character varying,
    user_id integer NOT NULL,
    project_id integer NOT NULL
);


ALTER TABLE opa.activity_asset OWNER TO admin;

--
-- TOC entry 237 (class 1259 OID 41294)
-- Name: defect_asset; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.defect_asset (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    level integer NOT NULL,
    name character varying NOT NULL,
    description character varying,
    solution character varying,
    status integer NOT NULL,
    attachment character varying,
    activity_id integer
);


ALTER TABLE opa.defect_asset OWNER TO admin;

--
-- TOC entry 227 (class 1259 OID 41160)
-- Name: issue_asset; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.issue_asset (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    assignee_id integer NOT NULL,
    description character varying,
    summary character varying NOT NULL,
    status integer NOT NULL,
    type integer NOT NULL,
    priority integer NOT NULL,
    create_date date,
    parent_issue_id integer,
    attachment character varying,
    name character varying,
    activity_id integer
);


ALTER TABLE opa.issue_asset OWNER TO admin;

--
-- TOC entry 228 (class 1259 OID 41169)
-- Name: issue_comment; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.issue_comment (
    id integer NOT NULL,
    issue_id integer NOT NULL,
    comment character varying,
    create_date date,
    user_id integer NOT NULL
);


ALTER TABLE opa.issue_comment OWNER TO admin;

--
-- TOC entry 211 (class 1259 OID 24769)
-- Name: knowledge_asset; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.knowledge_asset (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying,
    project_id integer NOT NULL,
    knowledge_type integer NOT NULL,
    status integer,
    attachment character varying NOT NULL,
    user_id integer,
    activity_id integer
);


ALTER TABLE opa.knowledge_asset OWNER TO admin;

--
-- TOC entry 210 (class 1259 OID 24767)
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
-- TOC entry 3214 (class 0 OID 0)
-- Dependencies: 210
-- Name: knowledge_asset_id_seq; Type: SEQUENCE OWNED BY; Schema: opa; Owner: admin
--

ALTER SEQUENCE opa.knowledge_asset_id_seq OWNED BY opa.knowledge_asset.id;


--
-- TOC entry 208 (class 1259 OID 24754)
-- Name: org_asset; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.org_asset (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    project_type integer,
    knowledge_type integer,
    status integer,
    attachment character varying NOT NULL,
    user_id integer
);


ALTER TABLE opa.org_asset OWNER TO admin;

--
-- TOC entry 209 (class 1259 OID 24762)
-- Name: project_asset; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.project_asset (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(200),
    project_type integer NOT NULL,
    user_id integer NOT NULL,
    status integer,
    start_date date,
    end_date date
);


ALTER TABLE opa.project_asset OWNER TO admin;

--
-- TOC entry 236 (class 1259 OID 41279)
-- Name: risk_asset; Type: TABLE; Schema: opa; Owner: admin
--

CREATE TABLE opa.risk_asset (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    level integer NOT NULL,
    name character varying NOT NULL,
    description character varying,
    occurence character varying,
    impact character varying,
    solution character varying,
    status integer NOT NULL,
    attachment character varying,
    activity_id integer
);


ALTER TABLE opa.risk_asset OWNER TO admin;

--
-- TOC entry 207 (class 1259 OID 24615)
-- Name: org_asset; Type: TABLE; Schema: org_asset; Owner: admin
--

CREATE TABLE org_asset.org_asset (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    project_type integer,
    knowledge_type integer,
    status integer,
    attachment character varying NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE org_asset.org_asset OWNER TO admin;

--
-- TOC entry 206 (class 1259 OID 24613)
-- Name: org_asset_id_seq; Type: SEQUENCE; Schema: org_asset; Owner: admin
--

CREATE SEQUENCE org_asset.org_asset_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE org_asset.org_asset_id_seq OWNER TO admin;

--
-- TOC entry 3215 (class 0 OID 0)
-- Dependencies: 206
-- Name: org_asset_id_seq; Type: SEQUENCE OWNED BY; Schema: org_asset; Owner: admin
--

ALTER SEQUENCE org_asset.org_asset_id_seq OWNED BY org_asset.org_asset.id;


--
-- TOC entry 243 (class 1259 OID 57573)
-- Name: activity; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.activity (
    id integer NOT NULL,
    name character varying,
    description character varying,
    user_id integer NOT NULL,
    project_id integer NOT NULL
);


ALTER TABLE project.activity OWNER TO admin;

--
-- TOC entry 220 (class 1259 OID 24835)
-- Name: knowledge; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.knowledge (
    id integer NOT NULL,
    name character varying NOT NULL,
    knowledge_type integer,
    user_id integer NOT NULL,
    attachment character varying,
    project_id integer,
    description character varying,
    status integer,
    activity_id integer
);


ALTER TABLE project.knowledge OWNER TO admin;

--
-- TOC entry 219 (class 1259 OID 24833)
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
-- TOC entry 3216 (class 0 OID 0)
-- Dependencies: 219
-- Name: activity_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.activity_id_seq OWNED BY project.knowledge.id;


--
-- TOC entry 242 (class 1259 OID 57571)
-- Name: activity_id_seq1; Type: SEQUENCE; Schema: project; Owner: admin
--

CREATE SEQUENCE project.activity_id_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE project.activity_id_seq1 OWNER TO admin;

--
-- TOC entry 3217 (class 0 OID 0)
-- Dependencies: 242
-- Name: activity_id_seq1; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.activity_id_seq1 OWNED BY project.activity.id;


--
-- TOC entry 213 (class 1259 OID 24787)
-- Name: knowledge_type; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.knowledge_type (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE project.knowledge_type OWNER TO admin;

--
-- TOC entry 212 (class 1259 OID 24785)
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
-- TOC entry 3218 (class 0 OID 0)
-- Dependencies: 212
-- Name: activity_type_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.activity_type_id_seq OWNED BY project.knowledge_type.id;


--
-- TOC entry 226 (class 1259 OID 41136)
-- Name: issue_comment; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.issue_comment (
    id integer NOT NULL,
    issue_id integer NOT NULL,
    comment character varying,
    create_date date,
    user_id integer NOT NULL,
    title character varying
);


ALTER TABLE project.issue_comment OWNER TO admin;

--
-- TOC entry 225 (class 1259 OID 41134)
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
-- TOC entry 3219 (class 0 OID 0)
-- Dependencies: 225
-- Name: comment_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.comment_id_seq OWNED BY project.issue_comment.id;


--
-- TOC entry 239 (class 1259 OID 41312)
-- Name: defect; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.defect (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    level integer NOT NULL,
    name character varying NOT NULL,
    description character varying,
    solution character varying,
    status integer,
    attachment character varying,
    activity_id integer
);


ALTER TABLE project.defect OWNER TO admin;

--
-- TOC entry 238 (class 1259 OID 41310)
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
-- TOC entry 3220 (class 0 OID 0)
-- Dependencies: 238
-- Name: defect_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.defect_id_seq OWNED BY project.defect.id;


--
-- TOC entry 224 (class 1259 OID 41110)
-- Name: issue; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.issue (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    assignee_id integer NOT NULL,
    description character varying,
    summary character varying NOT NULL,
    status integer NOT NULL,
    type integer NOT NULL,
    priority integer NOT NULL,
    create_date date,
    parent_issue_id integer,
    name character varying,
    attachment character varying,
    activity_id integer
);


ALTER TABLE project.issue OWNER TO admin;

--
-- TOC entry 223 (class 1259 OID 41108)
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
-- TOC entry 3221 (class 0 OID 0)
-- Dependencies: 223
-- Name: issue_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.issue_id_seq OWNED BY project.issue.id;


--
-- TOC entry 218 (class 1259 OID 24817)
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
-- TOC entry 217 (class 1259 OID 24815)
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
-- TOC entry 3222 (class 0 OID 0)
-- Dependencies: 217
-- Name: project_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.project_id_seq OWNED BY project.project.id;


--
-- TOC entry 215 (class 1259 OID 24798)
-- Name: project_type; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.project_type (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE project.project_type OWNER TO admin;

--
-- TOC entry 214 (class 1259 OID 24796)
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
-- TOC entry 3223 (class 0 OID 0)
-- Dependencies: 214
-- Name: project_type_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.project_type_id_seq OWNED BY project.project_type.id;


--
-- TOC entry 241 (class 1259 OID 41333)
-- Name: risk; Type: TABLE; Schema: project; Owner: admin
--

CREATE TABLE project.risk (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    level integer NOT NULL,
    name character varying NOT NULL,
    description character varying,
    occurence character varying,
    impact character varying,
    solution character varying,
    status integer,
    attachment character varying,
    activity_id integer
);


ALTER TABLE project.risk OWNER TO admin;

--
-- TOC entry 240 (class 1259 OID 41331)
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
-- TOC entry 3224 (class 0 OID 0)
-- Dependencies: 240
-- Name: risk_id_seq; Type: SEQUENCE OWNED BY; Schema: project; Owner: admin
--

ALTER SEQUENCE project.risk_id_seq OWNED BY project.risk.id;


--
-- TOC entry 216 (class 1259 OID 24807)
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
-- TOC entry 230 (class 1259 OID 41196)
-- Name: risk; Type: TABLE; Schema: risk; Owner: admin
--

CREATE TABLE risk.risk (
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    name character varying NOT NULL,
    description character varying,
    occurence character varying,
    impact character varying,
    solution character varying,
    level integer,
    status integer,
    attachment character varying,
    activity_id integer
);


ALTER TABLE risk.risk OWNER TO admin;

--
-- TOC entry 205 (class 1259 OID 24603)
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
-- TOC entry 204 (class 1259 OID 24601)
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
-- TOC entry 3225 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: admin
--

ALTER SEQUENCE security.user_id_seq OWNED BY security."user".id;


--
-- TOC entry 2955 (class 2604 OID 24772)
-- Name: knowledge_asset id; Type: DEFAULT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.knowledge_asset ALTER COLUMN id SET DEFAULT nextval('opa.knowledge_asset_id_seq'::regclass);


--
-- TOC entry 2954 (class 2604 OID 24618)
-- Name: org_asset id; Type: DEFAULT; Schema: org_asset; Owner: admin
--

ALTER TABLE ONLY org_asset.org_asset ALTER COLUMN id SET DEFAULT nextval('org_asset.org_asset_id_seq'::regclass);


--
-- TOC entry 2964 (class 2604 OID 57576)
-- Name: activity id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.activity ALTER COLUMN id SET DEFAULT nextval('project.activity_id_seq1'::regclass);


--
-- TOC entry 2962 (class 2604 OID 41315)
-- Name: defect id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.defect ALTER COLUMN id SET DEFAULT nextval('project.defect_id_seq'::regclass);


--
-- TOC entry 2960 (class 2604 OID 41113)
-- Name: issue id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue ALTER COLUMN id SET DEFAULT nextval('project.issue_id_seq'::regclass);


--
-- TOC entry 2961 (class 2604 OID 41139)
-- Name: issue_comment id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue_comment ALTER COLUMN id SET DEFAULT nextval('project.comment_id_seq'::regclass);


--
-- TOC entry 2959 (class 2604 OID 24838)
-- Name: knowledge id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.knowledge ALTER COLUMN id SET DEFAULT nextval('project.activity_id_seq'::regclass);


--
-- TOC entry 2956 (class 2604 OID 24790)
-- Name: knowledge_type id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.knowledge_type ALTER COLUMN id SET DEFAULT nextval('project.activity_type_id_seq'::regclass);


--
-- TOC entry 2958 (class 2604 OID 24820)
-- Name: project id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.project ALTER COLUMN id SET DEFAULT nextval('project.project_id_seq'::regclass);


--
-- TOC entry 2957 (class 2604 OID 24801)
-- Name: project_type id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.project_type ALTER COLUMN id SET DEFAULT nextval('project.project_type_id_seq'::regclass);


--
-- TOC entry 2963 (class 2604 OID 41336)
-- Name: risk id; Type: DEFAULT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.risk ALTER COLUMN id SET DEFAULT nextval('project.risk_id_seq'::regclass);


--
-- TOC entry 2953 (class 2604 OID 24606)
-- Name: user id; Type: DEFAULT; Schema: security; Owner: admin
--

ALTER TABLE ONLY security."user" ALTER COLUMN id SET DEFAULT nextval('security.user_id_seq'::regclass);


--
-- TOC entry 3190 (class 0 OID 41190)
-- Dependencies: 229
-- Data for Name: defect; Type: TABLE DATA; Schema: defect; Owner: admin
--

COPY defect.defect (id, project_id, user_id, name, description, solution, attachment, status, level, activity_id) FROM stdin;
2	1	2	Bug 3	Can't upload file			1	1	0
5	3	2	Bug 1	Can't upload file			1	1	0
1	1	2	Bug 1	Can't upload file			1	1	2
\.


--
-- TOC entry 3196 (class 0 OID 41261)
-- Dependencies: 235
-- Data for Name: comment; Type: TABLE DATA; Schema: issue; Owner: admin
--

COPY issue.comment (id, issue_id, comment, create_date, user_id) FROM stdin;
3	1	Test Comment3	\N	4
4	8	Test Comment3	\N	4
\.


--
-- TOC entry 3183 (class 0 OID 40971)
-- Dependencies: 222
-- Data for Name: issue; Type: TABLE DATA; Schema: issue; Owner: admin
--

COPY issue.issue (id, project_id, user_id, assignee_id, description, summary, status, type, priority, create_date, parent_issue_id, attachment, name, activity_id) FROM stdin;
1	5	2	4	Test issue1	Test issue1	1	1	2	\N	0		Test issue1	0
2	5	2	4	Test issue3	Test issue2	1	1	2	\N	0		Test issue3	0
8	3	2	4	Test issue7	Test issue7	1	1	2	\N	0		Test issue2	0
\.


--
-- TOC entry 3182 (class 0 OID 40961)
-- Dependencies: 221
-- Data for Name: user; Type: TABLE DATA; Schema: issue; Owner: admin
--

COPY issue."user" (id, username, password, name, role_id) FROM stdin;
2	test2	123456	Vu Thanh Ha2	1
4	test	123456	Vu Thanh Hai3	1
\.


--
-- TOC entry 3192 (class 0 OID 41216)
-- Dependencies: 231
-- Data for Name: conf_asset; Type: TABLE DATA; Schema: knowledge_asset; Owner: admin
--

COPY knowledge_asset.conf_asset (id, name, description, project_id, status, attachment, user_id, activity_id) FROM stdin;
2	Config3	Config2	1	1		2	0
\.


--
-- TOC entry 3193 (class 0 OID 41224)
-- Dependencies: 232
-- Data for Name: fin_asset; Type: TABLE DATA; Schema: knowledge_asset; Owner: admin
--

COPY knowledge_asset.fin_asset (id, name, description, project_id, status, attachment, user_id, activity_id) FROM stdin;
2	Finace3	Finace2	1	1		2	0
\.


--
-- TOC entry 3194 (class 0 OID 41232)
-- Dependencies: 233
-- Data for Name: metric_asset; Type: TABLE DATA; Schema: knowledge_asset; Owner: admin
--

COPY knowledge_asset.metric_asset (id, name, description, project_id, status, attachment, user_id, activity_id) FROM stdin;
1	Metric1	Metric1	1	1		2	0
2	Metric3	Metric2	1	1		2	0
\.


--
-- TOC entry 3195 (class 0 OID 41240)
-- Dependencies: 234
-- Data for Name: plan_asset; Type: TABLE DATA; Schema: knowledge_asset; Owner: admin
--

COPY knowledge_asset.plan_asset (id, name, description, project_id, status, attachment, user_id, activity_id) FROM stdin;
1	Plan1	Plan1	1	1		2	0
2	Plan12	Plan3	1	1		2	0
7	Plan he thong	\N	3	0	/home/vietdo/Ha/JDA/file_upload	2	0
\.


--
-- TOC entry 3205 (class 0 OID 57612)
-- Dependencies: 244
-- Data for Name: activity_asset; Type: TABLE DATA; Schema: opa; Owner: admin
--

COPY opa.activity_asset (id, name, description, user_id, project_id) FROM stdin;
1	test	test	2	1
\.


--
-- TOC entry 3198 (class 0 OID 41294)
-- Dependencies: 237
-- Data for Name: defect_asset; Type: TABLE DATA; Schema: opa; Owner: admin
--

COPY opa.defect_asset (id, project_id, user_id, level, name, description, solution, status, attachment, activity_id) FROM stdin;
1	1	2	1	Bug 1	Can't upload file		1		0
2	1	2	1	Bug 3	Can't upload file		1		0
5	3	2	1	Bug 1	Can't upload file		1		0
\.


--
-- TOC entry 3188 (class 0 OID 41160)
-- Dependencies: 227
-- Data for Name: issue_asset; Type: TABLE DATA; Schema: opa; Owner: admin
--

COPY opa.issue_asset (id, project_id, user_id, assignee_id, description, summary, status, type, priority, create_date, parent_issue_id, attachment, name, activity_id) FROM stdin;
1	5	2	4	Test issue1	Test issue1	1	1	2	\N	0		Test issue1	0
2	5	2	4	Test issue3	Test issue1	1	1	2	\N	0		Test issue3	0
9	5	2	4	Test issue9	Test issue1	1	1	2	\N	0		Test issue9	0
8	3	2	4	Test issue7	Test issue7	1	1	2	\N	0		Test issue2	0
\.


--
-- TOC entry 3189 (class 0 OID 41169)
-- Dependencies: 228
-- Data for Name: issue_comment; Type: TABLE DATA; Schema: opa; Owner: admin
--

COPY opa.issue_comment (id, issue_id, comment, create_date, user_id) FROM stdin;
2	1	Test Comment6	\N	4
1	8	Test Comment8	\N	4
4	8	Test Comment3	\N	4
\.


--
-- TOC entry 3172 (class 0 OID 24769)
-- Dependencies: 211
-- Data for Name: knowledge_asset; Type: TABLE DATA; Schema: opa; Owner: admin
--

COPY opa.knowledge_asset (id, name, description, project_id, knowledge_type, status, attachment, user_id, activity_id) FROM stdin;
1	Metric2	Metric1	1	1	1		2	0
7	Plan he thong	\N	3	1	0	/home/vietdo/Ha/JDA/file_upload	2	0
\.


--
-- TOC entry 3169 (class 0 OID 24754)
-- Dependencies: 208
-- Data for Name: org_asset; Type: TABLE DATA; Schema: opa; Owner: admin
--

COPY opa.org_asset (id, name, description, project_type, knowledge_type, status, attachment, user_id) FROM stdin;
2	Plan CNTT	Bieu mau lap ke hoach du an XD	2	1	1		2
3	Plan CNTT	Bieu mau lap ke hoach du an XD	2	1	1		2
\.


--
-- TOC entry 3170 (class 0 OID 24762)
-- Dependencies: 209
-- Data for Name: project_asset; Type: TABLE DATA; Schema: opa; Owner: admin
--

COPY opa.project_asset (id, name, description, project_type, user_id, status, start_date, end_date) FROM stdin;
1	Mail Exchange3	Nang cap he thong mail	1	2	0	\N	\N
3	Mail Exchange3	Nang cap he thong mail2	1	1	0	\N	\N
\.


--
-- TOC entry 3197 (class 0 OID 41279)
-- Dependencies: 236
-- Data for Name: risk_asset; Type: TABLE DATA; Schema: opa; Owner: admin
--

COPY opa.risk_asset (id, project_id, user_id, level, name, description, occurence, impact, solution, status, attachment, activity_id) FROM stdin;
2	3	2	1	Risk 2	Can't upload file	Create org_asset	added time and cost		1		0
\.


--
-- TOC entry 3168 (class 0 OID 24615)
-- Dependencies: 207
-- Data for Name: org_asset; Type: TABLE DATA; Schema: org_asset; Owner: admin
--

COPY org_asset.org_asset (id, name, description, project_type, knowledge_type, status, attachment, user_id) FROM stdin;
2	Plan CNTT	Bieu mau lap ke hoach du an XD	2	1	1		2
3	Plan CNTT	Bieu mau lap ke hoach du an XD	2	1	1		2
\.


--
-- TOC entry 3204 (class 0 OID 57573)
-- Dependencies: 243
-- Data for Name: activity; Type: TABLE DATA; Schema: project; Owner: admin
--

COPY project.activity (id, name, description, user_id, project_id) FROM stdin;
1	Activity1	Activity1	2	3
2	Activity2	Activity22	2	3
\.


--
-- TOC entry 3200 (class 0 OID 41312)
-- Dependencies: 239
-- Data for Name: defect; Type: TABLE DATA; Schema: project; Owner: admin
--

COPY project.defect (id, project_id, user_id, level, name, description, solution, status, attachment, activity_id) FROM stdin;
4	2	2	1	Bug 3	Can't upload file		1		0
3	2	2	1	Bug 3	Can't upload file		1		0
5	3	2	1	Bug 1	Can't upload file		1		0
\.


--
-- TOC entry 3185 (class 0 OID 41110)
-- Dependencies: 224
-- Data for Name: issue; Type: TABLE DATA; Schema: project; Owner: admin
--

COPY project.issue (id, project_id, user_id, assignee_id, description, summary, status, type, priority, create_date, parent_issue_id, name, attachment, activity_id) FROM stdin;
0	2	2	4	Test issue1	Test issue1	1	1	2	\N	0	\N	\N	0
1	2	2	4	Test issue2	Test issue2	1	1	2	\N	0	\N	\N	0
3	2	2	4	Test issue5	Test issue5	1	1	2	\N	0	\N	\N	0
6	2	2	4	Test issue3	Test issue3	1	1	2	\N	0	Test issue2		0
7	2	2	4	Test issue7	Test issue7	1	1	2	\N	0	Test issue2		0
5	2	2	4	Test issue5	Test issue3	1	1	2	\N	0	Test issue5		0
8	3	2	4	Test issue7	Test issue7	1	1	2	\N	0	Test issue2		0
\.


--
-- TOC entry 3187 (class 0 OID 41136)
-- Dependencies: 226
-- Data for Name: issue_comment; Type: TABLE DATA; Schema: project; Owner: admin
--

COPY project.issue_comment (id, issue_id, comment, create_date, user_id, title) FROM stdin;
1	1	Test Comment1	\N	4	\N
2	1	Test Comment2	\N	4	\N
4	8	Test Comment3	\N	4	\N
\.


--
-- TOC entry 3181 (class 0 OID 24835)
-- Dependencies: 220
-- Data for Name: knowledge; Type: TABLE DATA; Schema: project; Owner: admin
--

COPY project.knowledge (id, name, knowledge_type, user_id, attachment, project_id, description, status, activity_id) FROM stdin;
8	Plan he thong	1	2	/home/vietdo/Ha/JDA/file_upload	3	\N	0	2
2	Plan Mo ta he thong	1	2	/home/vietdo/Ha/JDA/file_upload	2	\N	1	0
3	Metric Mo ta he thong	1	2	/home/vietdo/Ha/JDA/file_upload	2	\N	1	0
6	Plan he thong	1	2	/home/vietdo/Ha/JDA/file_upload	2	\N	0	0
7	Plan he thong	1	2	/home/vietdo/Ha/JDA/file_upload	3	\N	0	0
\.


--
-- TOC entry 3174 (class 0 OID 24787)
-- Dependencies: 213
-- Data for Name: knowledge_type; Type: TABLE DATA; Schema: project; Owner: admin
--

COPY project.knowledge_type (id, name) FROM stdin;
1	Plan
2	Metric
3	Conf
4	Fin
\.


--
-- TOC entry 3179 (class 0 OID 24817)
-- Dependencies: 218
-- Data for Name: project; Type: TABLE DATA; Schema: project; Owner: admin
--

COPY project.project (id, name, description, type_id, user_id, status, start_date, end_date) FROM stdin;
2	Mail Exchange3	Nang cap he thong mail	1	2	0	\N	\N
3	Mail Exchange3	Nang cap he thong mail2	1	1	0	\N	\N
\.


--
-- TOC entry 3176 (class 0 OID 24798)
-- Dependencies: 215
-- Data for Name: project_type; Type: TABLE DATA; Schema: project; Owner: admin
--

COPY project.project_type (id, name) FROM stdin;
1	CNTT
2	XD
\.


--
-- TOC entry 3202 (class 0 OID 41333)
-- Dependencies: 241
-- Data for Name: risk; Type: TABLE DATA; Schema: project; Owner: admin
--

COPY project.risk (id, project_id, user_id, level, name, description, occurence, impact, solution, status, attachment, activity_id) FROM stdin;
1	2	2	1	Risk 2	Can't upload file2	Create org_asset	added time and cost		1		0
2	3	2	1	Risk 2	Can't upload file	Create org_asset	added time and cost		1		0
\.


--
-- TOC entry 3177 (class 0 OID 24807)
-- Dependencies: 216
-- Data for Name: user; Type: TABLE DATA; Schema: project; Owner: admin
--

COPY project."user" (id, username, password, name, role_id) FROM stdin;
1	test	123456	Vu Thanh Ha	3
2	test2	123456	Vu Thanh Ha	1
4	test	123456	Vu Thanh Hai3	1
\.


--
-- TOC entry 3191 (class 0 OID 41196)
-- Dependencies: 230
-- Data for Name: risk; Type: TABLE DATA; Schema: risk; Owner: admin
--

COPY risk.risk (id, project_id, user_id, name, description, occurence, impact, solution, level, status, attachment, activity_id) FROM stdin;
1	2	2	Risk 1	Can't upload file	Create org_asset	added time and cost		1	1		0
2	3	2	Risk 2	Can't upload file	Create org_asset	added time and cost		1	1		0
\.


--
-- TOC entry 3166 (class 0 OID 24603)
-- Dependencies: 205
-- Data for Name: user; Type: TABLE DATA; Schema: security; Owner: admin
--

COPY security."user" (id, username, password, name, role_id) FROM stdin;
1	test	123456	Vu Thanh Ha	3
2	test2	123456	Vu Thanh Ha2	1
4	test	123456	Vu Thanh Hai3	1
\.


--
-- TOC entry 3226 (class 0 OID 0)
-- Dependencies: 210
-- Name: knowledge_asset_id_seq; Type: SEQUENCE SET; Schema: opa; Owner: admin
--

SELECT pg_catalog.setval('opa.knowledge_asset_id_seq', 1, false);


--
-- TOC entry 3227 (class 0 OID 0)
-- Dependencies: 206
-- Name: org_asset_id_seq; Type: SEQUENCE SET; Schema: org_asset; Owner: admin
--

SELECT pg_catalog.setval('org_asset.org_asset_id_seq', 3, true);


--
-- TOC entry 3228 (class 0 OID 0)
-- Dependencies: 219
-- Name: activity_id_seq; Type: SEQUENCE SET; Schema: project; Owner: admin
--

SELECT pg_catalog.setval('project.activity_id_seq', 8, true);


--
-- TOC entry 3229 (class 0 OID 0)
-- Dependencies: 242
-- Name: activity_id_seq1; Type: SEQUENCE SET; Schema: project; Owner: admin
--

SELECT pg_catalog.setval('project.activity_id_seq1', 3, true);


--
-- TOC entry 3230 (class 0 OID 0)
-- Dependencies: 212
-- Name: activity_type_id_seq; Type: SEQUENCE SET; Schema: project; Owner: admin
--

SELECT pg_catalog.setval('project.activity_type_id_seq', 1, false);


--
-- TOC entry 3231 (class 0 OID 0)
-- Dependencies: 225
-- Name: comment_id_seq; Type: SEQUENCE SET; Schema: project; Owner: admin
--

SELECT pg_catalog.setval('project.comment_id_seq', 4, true);


--
-- TOC entry 3232 (class 0 OID 0)
-- Dependencies: 238
-- Name: defect_id_seq; Type: SEQUENCE SET; Schema: project; Owner: admin
--

SELECT pg_catalog.setval('project.defect_id_seq', 5, true);


--
-- TOC entry 3233 (class 0 OID 0)
-- Dependencies: 223
-- Name: issue_id_seq; Type: SEQUENCE SET; Schema: project; Owner: admin
--

SELECT pg_catalog.setval('project.issue_id_seq', 8, true);


--
-- TOC entry 3234 (class 0 OID 0)
-- Dependencies: 217
-- Name: project_id_seq; Type: SEQUENCE SET; Schema: project; Owner: admin
--

SELECT pg_catalog.setval('project.project_id_seq', 4, true);


--
-- TOC entry 3235 (class 0 OID 0)
-- Dependencies: 214
-- Name: project_type_id_seq; Type: SEQUENCE SET; Schema: project; Owner: admin
--

SELECT pg_catalog.setval('project.project_type_id_seq', 1, false);


--
-- TOC entry 3236 (class 0 OID 0)
-- Dependencies: 240
-- Name: risk_id_seq; Type: SEQUENCE SET; Schema: project; Owner: admin
--

SELECT pg_catalog.setval('project.risk_id_seq', 2, true);


--
-- TOC entry 3237 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: security; Owner: admin
--

SELECT pg_catalog.setval('security.user_id_seq', 4, true);


--
-- TOC entry 3006 (class 2606 OID 41268)
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- TOC entry 2988 (class 2606 OID 41060)
-- Name: issue issue_pkey; Type: CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue.issue
    ADD CONSTRAINT issue_pkey PRIMARY KEY (id);


--
-- TOC entry 2986 (class 2606 OID 40968)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 2998 (class 2606 OID 41223)
-- Name: conf_asset conf_pkey; Type: CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.conf_asset
    ADD CONSTRAINT conf_pkey PRIMARY KEY (id);


--
-- TOC entry 3000 (class 2606 OID 41231)
-- Name: fin_asset fin_pkey; Type: CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.fin_asset
    ADD CONSTRAINT fin_pkey PRIMARY KEY (id);


--
-- TOC entry 3002 (class 2606 OID 41239)
-- Name: metric_asset metric_pkey; Type: CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.metric_asset
    ADD CONSTRAINT metric_pkey PRIMARY KEY (id);


--
-- TOC entry 3004 (class 2606 OID 41247)
-- Name: plan_asset plan_pkey; Type: CONSTRAINT; Schema: knowledge_asset; Owner: admin
--

ALTER TABLE ONLY knowledge_asset.plan_asset
    ADD CONSTRAINT plan_pkey PRIMARY KEY (id);


--
-- TOC entry 3018 (class 2606 OID 57619)
-- Name: activity_asset activity_asset_pkey; Type: CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.activity_asset
    ADD CONSTRAINT activity_asset_pkey PRIMARY KEY (id);


--
-- TOC entry 2996 (class 2606 OID 41176)
-- Name: issue_comment comment_pkey; Type: CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.issue_comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- TOC entry 3010 (class 2606 OID 41301)
-- Name: defect_asset defect_pkey; Type: CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.defect_asset
    ADD CONSTRAINT defect_pkey PRIMARY KEY (id);


--
-- TOC entry 2994 (class 2606 OID 41167)
-- Name: issue_asset issue_pkey; Type: CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.issue_asset
    ADD CONSTRAINT issue_pkey PRIMARY KEY (id);


--
-- TOC entry 2974 (class 2606 OID 24777)
-- Name: knowledge_asset knowledge_asset_pkey; Type: CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.knowledge_asset
    ADD CONSTRAINT knowledge_asset_pkey PRIMARY KEY (id);


--
-- TOC entry 2970 (class 2606 OID 24761)
-- Name: org_asset org_asset_pkey; Type: CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.org_asset
    ADD CONSTRAINT org_asset_pkey PRIMARY KEY (id);


--
-- TOC entry 2972 (class 2606 OID 24766)
-- Name: project_asset project_pkey; Type: CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.project_asset
    ADD CONSTRAINT project_pkey PRIMARY KEY (id);


--
-- TOC entry 3008 (class 2606 OID 41286)
-- Name: risk_asset risk_pkey; Type: CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.risk_asset
    ADD CONSTRAINT risk_pkey PRIMARY KEY (id);


--
-- TOC entry 2968 (class 2606 OID 24623)
-- Name: org_asset org_asset_pkey; Type: CONSTRAINT; Schema: org_asset; Owner: admin
--

ALTER TABLE ONLY org_asset.org_asset
    ADD CONSTRAINT org_asset_pkey PRIMARY KEY (id);


--
-- TOC entry 3016 (class 2606 OID 57581)
-- Name: activity activity_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.activity
    ADD CONSTRAINT activity_pkey PRIMARY KEY (id);


--
-- TOC entry 2992 (class 2606 OID 41144)
-- Name: issue_comment comment_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue_comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- TOC entry 3012 (class 2606 OID 41320)
-- Name: defect defect_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.defect
    ADD CONSTRAINT defect_pkey PRIMARY KEY (id);


--
-- TOC entry 2990 (class 2606 OID 41118)
-- Name: issue issue_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue
    ADD CONSTRAINT issue_pkey PRIMARY KEY (id);


--
-- TOC entry 2984 (class 2606 OID 24843)
-- Name: knowledge knowledge_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.knowledge
    ADD CONSTRAINT knowledge_pkey PRIMARY KEY (id);


--
-- TOC entry 2976 (class 2606 OID 24795)
-- Name: knowledge_type knowledge_type_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.knowledge_type
    ADD CONSTRAINT knowledge_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2982 (class 2606 OID 24822)
-- Name: project project_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (id);


--
-- TOC entry 2978 (class 2606 OID 24806)
-- Name: project_type project_type_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.project_type
    ADD CONSTRAINT project_type_pkey PRIMARY KEY (id);


--
-- TOC entry 3014 (class 2606 OID 41341)
-- Name: risk risk_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.risk
    ADD CONSTRAINT risk_pkey PRIMARY KEY (id);


--
-- TOC entry 2980 (class 2606 OID 24814)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 2966 (class 2606 OID 24611)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: security; Owner: admin
--

ALTER TABLE ONLY security."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 3034 (class 2606 OID 41269)
-- Name: comment comment_issue_fk; Type: FK CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue.comment
    ADD CONSTRAINT comment_issue_fk FOREIGN KEY (issue_id) REFERENCES issue.issue(id) ON DELETE CASCADE;


--
-- TOC entry 3035 (class 2606 OID 41274)
-- Name: comment comment_user_fk; Type: FK CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue.comment
    ADD CONSTRAINT comment_user_fk FOREIGN KEY (user_id) REFERENCES issue."user"(id);


--
-- TOC entry 3025 (class 2606 OID 40978)
-- Name: issue issue_user_assignee_fk; Type: FK CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue.issue
    ADD CONSTRAINT issue_user_assignee_fk FOREIGN KEY (assignee_id) REFERENCES issue."user"(id);


--
-- TOC entry 3026 (class 2606 OID 40983)
-- Name: issue issue_user_create_fk; Type: FK CONSTRAINT; Schema: issue; Owner: admin
--

ALTER TABLE ONLY issue.issue
    ADD CONSTRAINT issue_user_create_fk FOREIGN KEY (user_id) REFERENCES issue."user"(id);


--
-- TOC entry 3033 (class 2606 OID 49379)
-- Name: issue_comment comment_issue; Type: FK CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.issue_comment
    ADD CONSTRAINT comment_issue FOREIGN KEY (issue_id) REFERENCES opa.issue_asset(id) NOT VALID;


--
-- TOC entry 3037 (class 2606 OID 41302)
-- Name: defect_asset defect_project_fk; Type: FK CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.defect_asset
    ADD CONSTRAINT defect_project_fk FOREIGN KEY (project_id) REFERENCES opa.project_asset(id) ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3019 (class 2606 OID 24778)
-- Name: knowledge_asset knowledge_project_fk; Type: FK CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.knowledge_asset
    ADD CONSTRAINT knowledge_project_fk FOREIGN KEY (project_id) REFERENCES opa.project_asset(id);


--
-- TOC entry 3036 (class 2606 OID 41287)
-- Name: risk_asset risk_project_fk; Type: FK CONSTRAINT; Schema: opa; Owner: admin
--

ALTER TABLE ONLY opa.risk_asset
    ADD CONSTRAINT risk_project_fk FOREIGN KEY (project_id) REFERENCES opa.project_asset(id) ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3042 (class 2606 OID 57582)
-- Name: activity activity_project_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.activity
    ADD CONSTRAINT activity_project_fk FOREIGN KEY (project_id) REFERENCES project.project(id);


--
-- TOC entry 3043 (class 2606 OID 57587)
-- Name: activity activity_user_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.activity
    ADD CONSTRAINT activity_user_fk FOREIGN KEY (user_id) REFERENCES project."user"(id);


--
-- TOC entry 3031 (class 2606 OID 41145)
-- Name: issue_comment comment_issue_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue_comment
    ADD CONSTRAINT comment_issue_fk FOREIGN KEY (issue_id) REFERENCES project.issue(id);


--
-- TOC entry 3032 (class 2606 OID 41150)
-- Name: issue_comment comment_user_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue_comment
    ADD CONSTRAINT comment_user_fk FOREIGN KEY (user_id) REFERENCES project."user"(id);


--
-- TOC entry 3038 (class 2606 OID 41321)
-- Name: defect defect_project_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.defect
    ADD CONSTRAINT defect_project_fk FOREIGN KEY (project_id) REFERENCES project.project(id);


--
-- TOC entry 3039 (class 2606 OID 41326)
-- Name: defect defect_user_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.defect
    ADD CONSTRAINT defect_user_fk FOREIGN KEY (user_id) REFERENCES project."user"(id);


--
-- TOC entry 3027 (class 2606 OID 41119)
-- Name: issue issue_issue_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue
    ADD CONSTRAINT issue_issue_fk FOREIGN KEY (parent_issue_id) REFERENCES project.issue(id);


--
-- TOC entry 3030 (class 2606 OID 41155)
-- Name: issue issue_project_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue
    ADD CONSTRAINT issue_project_fk FOREIGN KEY (project_id) REFERENCES project.project(id) NOT VALID;


--
-- TOC entry 3028 (class 2606 OID 41124)
-- Name: issue issue_user_assignee_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue
    ADD CONSTRAINT issue_user_assignee_fk FOREIGN KEY (assignee_id) REFERENCES project."user"(id);


--
-- TOC entry 3029 (class 2606 OID 41129)
-- Name: issue issue_user_create_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.issue
    ADD CONSTRAINT issue_user_create_fk FOREIGN KEY (user_id) REFERENCES project."user"(id);


--
-- TOC entry 3022 (class 2606 OID 24844)
-- Name: knowledge knowledge_project_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.knowledge
    ADD CONSTRAINT knowledge_project_fk FOREIGN KEY (project_id) REFERENCES project.project(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3023 (class 2606 OID 24849)
-- Name: knowledge knowledge_type_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.knowledge
    ADD CONSTRAINT knowledge_type_fk FOREIGN KEY (knowledge_type) REFERENCES project.knowledge_type(id);


--
-- TOC entry 3024 (class 2606 OID 24854)
-- Name: knowledge knowledge_user_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.knowledge
    ADD CONSTRAINT knowledge_user_fk FOREIGN KEY (user_id) REFERENCES project."user"(id);


--
-- TOC entry 3020 (class 2606 OID 24823)
-- Name: project project_type_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.project
    ADD CONSTRAINT project_type_fk FOREIGN KEY (type_id) REFERENCES project.project_type(id);


--
-- TOC entry 3021 (class 2606 OID 24828)
-- Name: project project_user_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.project
    ADD CONSTRAINT project_user_fk FOREIGN KEY (user_id) REFERENCES project."user"(id);


--
-- TOC entry 3040 (class 2606 OID 41342)
-- Name: risk risk_project_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.risk
    ADD CONSTRAINT risk_project_fk FOREIGN KEY (project_id) REFERENCES project.project(id);


--
-- TOC entry 3041 (class 2606 OID 41347)
-- Name: risk risk_user_fk; Type: FK CONSTRAINT; Schema: project; Owner: admin
--

ALTER TABLE ONLY project.risk
    ADD CONSTRAINT risk_user_fk FOREIGN KEY (user_id) REFERENCES project."user"(id);


-- Completed on 2023-06-21 01:41:42 PDT

--
-- PostgreSQL database dump complete
--

