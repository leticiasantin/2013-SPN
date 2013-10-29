﻿--Função que retorna os top de uma determinada categoria pelo orderby

CREATE OR REPLACE FUNCTION spn.gettopcategory(catid integer,nrows integer, orderBy int)
  RETURNS TABLE(provider integer, price numeric, deadlines numeric, qos numeric, qoc numeric) AS
$BODY$
BEGIN
CASE $3
WHEN 1 THEN
RETURN QUERY 
 SELECT spn.service.provider_id, avg(c_price) as price, avg(c_respect_for_deadlines) as deadlines,
	avg(c_quality_of_service) as qos, avg(c_quality_of_care) as qoc
 FROM spn.service_evaluation NATURAL JOIN spn.service WHERE  provider_id IN
 (SELECT provider_id from spn.prov_has_cat WHERE cat_id=$1)
 GROUP BY provider_id 
 ORDER BY price desc
 LIMIT $2;
 RETURN;
 WHEN 2 THEN
 RETURN QUERY 
 SELECT spn.service.provider_id, avg(c_price) as price, avg(c_respect_for_deadlines) as deadlines,
	avg(c_quality_of_service) as qos, avg(c_quality_of_care) as qoc
 FROM spn.service_evaluation NATURAL JOIN spn.service WHERE  provider_id IN
 (SELECT provider_id from spn.prov_has_cat WHERE cat_id=$1)
 GROUP BY provider_id 
 ORDER BY deadlines desc
 LIMIT $2;
 RETURN;
 WHEN 3 THEN
  RETURN QUERY 
 SELECT spn.service.provider_id, avg(c_price) as price, avg(c_respect_for_deadlines) as deadlines,
	avg(c_quality_of_service) as qos, avg(c_quality_of_care) as qoc
 FROM spn.service_evaluation NATURAL JOIN spn.service WHERE  provider_id IN
 (SELECT provider_id from spn.prov_has_cat WHERE cat_id=$1)
 GROUP BY provider_id 
 ORDER BY qos desc
 LIMIT $2;
 RETURN;
WHEN 4 THEN 
 RETURN QUERY 
 SELECT spn.service.provider_id, avg(c_price) as price, avg(c_respect_for_deadlines) as deadlines,
	avg(c_quality_of_service) as qos, avg(c_quality_of_care) as qoc
 FROM spn.service_evaluation NATURAL JOIN spn.service WHERE  provider_id IN
 (SELECT provider_id from spn.prov_has_cat WHERE cat_id=$1)
 GROUP BY provider_id 
 ORDER BY qoc desc
 LIMIT $2;
 RETURN;
END CASE;
END;
$BODY$
  LANGUAGE plpgsql



﻿--Função que retorna os top de uma categoria pelo orderby

CREATE OR REPLACE FUNCTION spn.gettopallcategoriesbycriterion(IN orderby integer)
  RETURNS TABLE(category integer, provider integer, price numeric, deadlines numeric, qos numeric, qoc numeric) AS
$BODY$
   DECLARE
        registro RECORD;
   BEGIN
        FOR registro IN SELECT * FROM spn.category LOOP
	RETURN QUERY
               SELECT registro.cat_id, * FROM spn.gettopcategory(registro.cat_id,1,orderBy);
        END LOOP;
        RETURN;
   END;
$BODY$
  LANGUAGE plpgsql VOLATILE



﻿--Função que retorna os top de uma determinada categoria dos ultimos dias
CREATE OR REPLACE FUNCTION spn.gettopcategorybydate(id int,daysago int, nrows int)
 RETURNS TABLE (provider int, n_services bigint) AS
 $$
 BEGIN 
  RETURN QUERY
  SELECT provider_id as provider, COUNT(*) as n_services
  FROM spn.service_evaluation NATURAL JOIN spn.service
  WHERE provider_id IN 
  (SELECT provider_id from spn.prov_has_cat WHERE cat_id=$1)
  AND provider_response_dat IS NOT NULL AND
  c_real_start_date BETWEEN CURRENT_DATE-$2 AND CURRENT_DATE
  GROUP BY provider
  ORDER BY n_services desc
  LIMIT $3;
   RETURN;
 END;
$$
  LANGUAGE plpgsql 

--retorna todas as categorias e seus respectvos top prestadores dos ultimos dias

CREATE OR REPLACE FUNCTION spn.gettopallcategoriesbydate(daysago int, nrows int)
 RETURNS TABLE (category int,provider int, n_services bigint) AS
 $$
  DECLARE
        reg RECORD;
   BEGIN
        FOR reg IN SELECT * FROM spn.category LOOP
	RETURN QUERY
               SELECT reg.cat_id, * FROM spn.gettopcategorybydate(reg.cat_id,$1,$2);
        END LOOP;
        END;
$$
  LANGUAGE plpgsql 


﻿--Função que retorna os top de uma determinada categoria por combinação linear;
-- DROP FUNCTION spn.gettopcategorybylinearcombination(int,int,int,int,int,int)
--Exemplo
-- SELECT * FROM spn.gettopcategorybylinearcombination(5,5,10,1,1,1) 
--	ORDER BY lin_comb desc

CREATE OR REPLACE FUNCTION spn.gettopcategorybylinearcombination(IN catid integer, IN nrows integer, w1 integer, w2 integer, w3 integer, w4 integer)
  RETURNS TABLE(provider integer, lin_comb numeric) AS
$BODY$
BEGIN
RETURN QUERY 
 SELECT spn.service.provider_id, avg(c_price)*w1 + avg(c_respect_for_deadlines)*w2 +
 avg(c_quality_of_service)*w3 + avg(c_quality_of_care)*w4 
 FROM spn.service_evaluation NATURAL JOIN spn.service WHERE  provider_id IN
 (SELECT provider_id from spn.prov_has_cat WHERE cat_id=catid)
 GROUP BY provider_id 
 LIMIT nrows;
 RETURN;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE

----retorna todas as categorias e seus respectvos top de acordo com a combinação linear
CREATE OR REPLACE FUNCTION spn.gettopallcategoriesbylinearcombination(IN nrows integer, 
IN w1 integer, IN w2 integer, IN w3 integer, IN w4 integer)
  RETURNS TABLE(category integer, provider integer, lin_comb numeric) AS
$BODY$
   DECLARE
        reg RECORD;
   BEGIN
        FOR reg IN SELECT * FROM spn.category LOOP
	RETURN QUERY
               SELECT reg.cat_id, * FROM spn.gettopcategorybylinearcombination(reg.cat_id,nrows , w1, w2 ,  w3, w4 );
        END LOOP;
        RETURN;
   END;
$BODY$
  LANGUAGE plpgsql VOLATILE

--  DROP FUNCTION spn.gettopallcategoriesbylinearcombination(int, int, int, int ,int, int)

 -- SELECT * FROM spn.gettopallcategoriesbylinearcombination(5,1,1,1,190) ORDER BY category, lin_comb desc

