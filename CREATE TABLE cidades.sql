
CREATE TABLE cidades(
  ibge_id  NUMBER NOT NULL 
, uf VARCHAR2(2) 
, name VARCHAR2(250)
, capital VARCHAR2(250)
, lon NUMERIC(16,10)
, lat NUMERIC(16,10)
, no_accents VARCHAR2(250)
, alternative_names VARCHAR2(250)
, microregion VARCHAR2(250)
, mesoregion VARCHAR2(250) 
);

