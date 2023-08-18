
CREATE TABLE IF NOT EXISTS risk.risk
(
    id integer NOT NULL,
    project_id integer NOT NULL,
    user_id integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    occurence character varying COLLATE pg_catalog."default",
    impact character varying COLLATE pg_catalog."default",
    solution character varying COLLATE pg_catalog."default",
    level integer,
    status integer,
    attachment character varying COLLATE pg_catalog."default",
    activity_id integer,
    CONSTRAINT risk_pkey PRIMARY KEY (id)
)