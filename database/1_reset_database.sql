-- Kustutab public schema (mis põhimõtteliselt kustutab kõik tabelid)
DROP SCHEMA IF EXISTS bank CASCADE;
-- Loob uue public schema vajalikud õigused
CREATE SCHEMA shroomshare
-- taastab vajalikud andmebaasi õigused
    GRANT ALL ON SCHEMA shroomshare TO postgres;
GRANT ALL ON SCHEMA shroomshare TO PUBLIC;