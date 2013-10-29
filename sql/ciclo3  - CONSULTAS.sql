
﻿--SELECIONAR ULTIMOS 10 PRESTADORES QUE TIVERAM PRESTAÇÃO DE SERVIÇO CONTRATADA
SELECT * FROM spn.user u INNER JOIN (
SELECT DISTINCT provider_id FROM (
SELECT DISTINCT provider_id, client_request_dat
  FROM spn.service
  ORDER BY client_request_dat) ord
  LIMIT 10) prov
  ON prov.provider_id=u.user_id

--ultimos prestadores de serviço cadastrados
SELECT u.user_id, u.name, u.city || '-' || u.state AS address  FROM spn.user u
INNER JOIN (SELECT * FROM spn.provider LIMIT 5) prov 	ON
u.user_id=prov.provider_id
ORDER BY prov.dat desc

--SELECIONA OS 5 ULTIMOS PRESTADORES CADASTRADOS NA CATEGORIA 5
SELECT provider_id, cat_id, dat
  FROM spn.prov_has_cat
  WHERE cat_id=5
  ORDER BY dat DESC
  LIMIT 5

  --TOP 5 DA CATEGORIA 5 COM SERVIÇOS PRESTADOS NOS ULTIMOS 7 DIAS
  SELECT provider_id, COUNT(*) as n_services
  FROM spn.service_evaluation NATURAL JOIN spn.service 
  WHERE provider_id IN 
  (SELECT provider_id from spn.prov_has_cat WHERE cat_id=5)
  AND provider_response_dat IS NOT NULL AND
  c_real_start_date BETWEEN CURRENT_DATE-1000 AND CURRENT_DATE
  GROUP BY provider_id 
  ORDER BY n_services desc
  

