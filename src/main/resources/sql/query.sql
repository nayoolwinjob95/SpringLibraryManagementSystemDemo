CREATE DATABASE librarymanagement;

-- category table

CREATE TABLE public.category
(
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.category
    OWNER to postgres;

-- user table

CREATE TABLE public.user_tbl
(
    id serial NOT NULL,
    username character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    phoneno integer NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.user_tbl
    OWNER to postgres;

-- book table

CREATE TABLE public.book
(
    id character varying(255) NOT NULL,
    "bookname" character varying(255) NOT NULL,
    "bookcategoryid" integer NOT NULL,
    author character varying(255) NOT NULL,
    "produceyear" integer NOT NULL,
    "booktype" character varying(255) NOT NULL,
    "bookimage" character varying(255),
    "ebookpdf" character varying(255),
    PRIMARY KEY (id),
    CONSTRAINT "fk_bookcategoryid" FOREIGN KEY ("bookcategoryid")
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.book
    OWNER to postgres;

-- borrowed_book table

CREATE TABLE public.borrowed_book
(
    id serial NOT NULL,
    bookid character varying(255) NOT NULL,
    userid integer NOT NULL,
    borrowedflag integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_bookid FOREIGN KEY (bookid)
        REFERENCES public.book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_userid FOREIGN KEY (userid)
        REFERENCES public.user_tbl (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.borrowed_book
    OWNER to postgres;

ALTER TABLE IF EXISTS public.borrowed_book
    ALTER COLUMN borrowedflag SET DEFAULT 0;
