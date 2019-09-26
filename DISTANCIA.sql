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
