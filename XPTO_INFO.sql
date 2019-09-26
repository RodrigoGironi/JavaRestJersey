---------------------------------------------------------------------------
1. Ler o arquivo CSV das cidades para a base de dados;

----------------------------------------------------------------------------
2. Retornar somente as cidades que são capitais ordenadas por nome;
SELECT * FROM CIDADES WHERE CAPITAL = 'TRUE'
ORDER BY NAME
----------------------------------------------------------------------------
3. Retornar o nome do estado com a maior e menor quantidade de cidades e a
quantidade de cidades;
DROP TABLE temp;
CREATE TABLE temp (
  myuf     VARCHAR2(50),
  myquant  NUMBER
);

INSERT INTO temp
SELECT UF, COUNT(UF) AS QUANTIDADE
             FROM CIDADES
             GROUP BY  UF
             ORDER BY QUANTIDADE ASC;

SELECT myuf, myquant FROM temp
WHERE myquant = ( SELECT MAX(myquant) FROM temp )
UNION
SELECT myuf, myquant FROM temp
WHERE myquant = ( SELECT MIN(myquant) FROM temp );
-----------------------------------------------------------------------------
4. Retornar a quantidade de cidades por estado;
SELECT UF, COUNT(UF) AS QUANTIDADE
             FROM CIDADES
             GROUP BY  UF
             ORDER BY QUANTIDADE ASC;
------------------------------------------------------------------------------
5. Obter os dados da cidade informando o id do IBGE;
SELECT *
FROM CIDADES
WHERE ibge_id = 1100031;
------------------------------------------------------------------------------
6. Retornar o nome das cidades baseado em um estado selecionado;
SELECT *
FROM CIDADES
WHERE upper(uf) = upper('al');
------------------------------------------------------------------------------
7. Permitir adicionar uma nova Cidade;
INSERT INTO CIDADES (IBGE_ID, UF, NAME, CAPITAL, LON, LAT, NO_ACCENTS, ALTERNATIVE_NAMES, MICROREGION, MESOREGION) 
VALUES (2700300, 'AL', 'Arapiraca', '', -36.6572965063, -9.7559070107, 'Arapiraca', '', 'Arapiraca', 'Agreste Alagoano');
------------------------------------------------------------------------------
8. Permitir deletar uma cidade;
DELETE FROM CIDADES WHERE IBGE_ID = 2700300
------------------------------------------------------------------------------
9. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para
filtrar. retornar assim todos os objetos que contenham tal string;
SELECT column_id, column_name, data_type FROM all_tab_columns
WHERE table_name = 'CIDADES'
ORDER BY column_id; 

SELECT * FROM CIDADES
WHERE ibge_id = 0;
-------------------------------------------------------------------------------
10. Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens
iguais;
SELECT COUNT(COUNT(DISTINCT ibge_id)) AS QUANTIDADE FROM CIDADES
GROUP BY ibge_id;
-------------------------------------------------------------------------------
11. Retornar a quantidade de registros total;
SELECT COUNT(*) AS TOTALREGISTRO FROM CIDADES;
-------------------------------------------------------------------------------
12. Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base
na localização (distância em KM em linha reta);
DROP TABLE tempcidade;
CREATE TABLE tempcidade (
    IBGE_ID_CITY1 NUMBER
    ,IBGE_ID_CITY2 NUMBER
    ,DISTANCIA NUMBER
);

DECLARE
   x1 NUMBER := 0;
   x2 NUMBER := 0;
   y1 NUMBER := 0;
   y2 NUMBER := 0;
   dist NUMBER := 0;

BEGIN
FOR row_city_1 IN ( SELECT * FROM CIDADES ORDER BY uf ) LOOP
      
        BEGIN
        FOR row_city_2 IN ( SELECT * FROM CIDADES WHERE IBGE_ID NOT IN( row_city_1.IBGE_ID ) ) LOOP
           
            x1 := ( ROUND(row_city_1.lon * -1) );
            x2 := ( ROUND(row_city_2.lon * -1) );
            y1 := ( ROUND(row_city_1.lat * -1) );
            y2 := ( ROUND(row_city_2.lat * -1) );
            
            IF(( x1 = x2 ) OR (y1 = y2)) THEN
			
                  IF( x1 != x2 ) THEN
				  
                      IF( x1 > x2) THEN
					  
                        dist := (x1 - x2);
						
                      ELSE
					  
                        dist := (x2 - x1);
						
                      END IF;
                  ELSE
				  
					IF( y1 != y2 ) THEN
					
                          IF( y1 > y2 )THEN
						  
                            dist := (y1 - y2);
							
                          ELSE
						  
                            dist := (y2 - y1);
							
                          END IF;
						  
                    END IF;	
					
				END IF;
			END IF;
            
            IF( dist > 0) THEN              
              INSERT INTO tempcidade(IBGE_ID_CITY1, IBGE_ID_CITY2, DISTANCIA) VALUES( row_city_1.ibge_id, row_city_2.ibge_id, dist);              
              dist := 0;              
            END IF;
           
           END LOOP row_city_2;
           END;
      
END LOOP row_city_1;
END;

SELECT * FROM CIDADES
WHERE ibge_id IN(
(SELECT IBGE_ID_CITY1 FROM tempcidade
WHERE DISTANCIA = ( SELECT MAX(DISTANCIA) FROM tempcidade ) AND ROWNUM <= 1),
(SELECT IBGE_ID_CITY2 FROM tempcidade
WHERE DISTANCIA = ( SELECT MAX(DISTANCIA) FROM tempcidade ) AND ROWNUM <= 1));
---------------------------------------------------------------------------------------------------------------------------------



