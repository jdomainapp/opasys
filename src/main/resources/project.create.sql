-- Table: project.user

-- DROP TABLE IF EXISTS project."user";

CREATE TABLE IF NOT EXISTS project."user"
(
    id integer NOT NULL,
    username character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS project."user"
    OWNER to admin;

-- Table: project.project_type

-- DROP TABLE IF EXISTS project.project_type;

CREATE TABLE IF NOT EXISTS project.project_type
(
    id SERIAL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT project_type_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS project.project_type
    OWNER to admin;


-- Table: project.project

-- DROP TABLE IF EXISTS project.project;

CREATE TABLE IF NOT EXISTS project.project
(
    id SERIAL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    description character varying(200) COLLATE pg_catalog."default",
    type_id integer NOT NULL,
    user_id integer NOT NULL,
    status integer,
    start_date date,
    end_date date,
    CONSTRAINT project_pkey PRIMARY KEY (id),
    CONSTRAINT project_type_fk FOREIGN KEY (type_id)
        REFERENCES project.project_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT project_user_fk FOREIGN KEY (user_id)
        REFERENCES project."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS project.project
    OWNER to admin;

-- Table: project.activity

-- DROP TABLE IF EXISTS project.activity;

CREATE TABLE IF NOT EXISTS project.activity
(
    id SERIAL,
    name character varying COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    user_id integer NOT NULL,
    project_id integer NOT NULL,
    CONSTRAINT activity_pkey PRIMARY KEY (id),
    CONSTRAINT activity_project_fk FOREIGN KEY (project_id)
        REFERENCES project.project (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT activity_user_fk FOREIGN KEY (user_id)
        REFERENCES project."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS project.activity
    OWNER to admin;

-- Table: project.common_knowledge

-- DROP TABLE IF EXISTS project.common_knowledge;

CREATE TABLE IF NOT EXISTS project.common_knowledge
(
    id SERIAL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    common_knowledge_type character varying COLLATE pg_catalog."default" NOT NULL,
    user_id integer NOT NULL,
    attachment character varying COLLATE pg_catalog."default",
    project_id integer,
    description character varying COLLATE pg_catalog."default",
    status integer,
    activity_id integer,
    CONSTRAINT cknowledge_pkey PRIMARY KEY (id),
    CONSTRAINT cknowledge_project_fk FOREIGN KEY (project_id)
        REFERENCES project.project (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT cknowledge_user_fk FOREIGN KEY (user_id)
        REFERENCES project."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS project.common_knowledge
    OWNER to admin;

-- Table: project.defect

-- DROP TABLE IF EXISTS project.defect;

CREATE TABLE IF NOT EXISTS project.defect
(
    id SERIAL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    level integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    solution character varying COLLATE pg_catalog."default",
    status integer,
    attachment character varying COLLATE pg_catalog."default",
    activity_id integer,
    CONSTRAINT defect_pkey PRIMARY KEY (id),
    CONSTRAINT defect_project_fk FOREIGN KEY (project_id)
        REFERENCES project.project (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT defect_user_fk FOREIGN KEY (user_id)
        REFERENCES project."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS project.defect
    OWNER to admin;


-- Table: project.risk

-- DROP TABLE IF EXISTS project.risk;

CREATE TABLE IF NOT EXISTS project.risk
(
    id SERIAL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    level integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    occurence character varying COLLATE pg_catalog."default",
    impact character varying COLLATE pg_catalog."default",
    solution character varying COLLATE pg_catalog."default",
    status integer,
    attachment character varying COLLATE pg_catalog."default",
    activity_id integer,
    CONSTRAINT risk_pkey PRIMARY KEY (id),
    CONSTRAINT risk_project_fk FOREIGN KEY (project_id)
        REFERENCES project.project (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT risk_user_fk FOREIGN KEY (user_id)
        REFERENCES project."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS project.risk
    OWNER to admin;

-- Table: project.issue

-- DROP TABLE IF EXISTS project.issue;

CREATE TABLE IF NOT EXISTS project.issue
(
    id SERIAL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    assignee_id integer NOT NULL,
    description character varying COLLATE pg_catalog."default",
    summary character varying COLLATE pg_catalog."default" NOT NULL,
    status integer NOT NULL,
    type integer NOT NULL,
    priority integer NOT NULL,
    create_date date,
    parent_issue_id integer,
    name character varying COLLATE pg_catalog."default",
    attachment character varying COLLATE pg_catalog."default",
    activity_id integer,
    CONSTRAINT issue_pkey PRIMARY KEY (id),
    CONSTRAINT issue_project_fk FOREIGN KEY (project_id)
        REFERENCES project.project (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT issue_user_assignee_fk FOREIGN KEY (assignee_id)
        REFERENCES project."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT issue_user_create_fk FOREIGN KEY (user_id)
        REFERENCES project."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS project.issue
    OWNER to admin;

-- Table: project.issue_comment

-- DROP TABLE IF EXISTS project.issue_comment;

CREATE TABLE IF NOT EXISTS project.issue_comment
(
    id SERIAL,
    issue_id integer NOT NULL,
    comment character varying COLLATE pg_catalog."default",
    create_date date,
    user_id integer NOT NULL,
    title character varying COLLATE pg_catalog."default",
    CONSTRAINT comment_pkey PRIMARY KEY (id),
    CONSTRAINT comment_issue_fk FOREIGN KEY (issue_id)
        REFERENCES project.issue (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT comment_user_fk FOREIGN KEY (user_id)
        REFERENCES project."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS project.issue_comment
    OWNER to admin;
