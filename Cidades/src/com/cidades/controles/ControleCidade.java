package com.cidades.controles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;



import com.cidades.db.*;
import com.cidades.obj.Cidades;
import com.cidades.obj.ObjetoAuxiliar;


public class ControleCidade {
	Dboracle db 			= new Dboracle();
	Connection con 			= null;
	Statement stmt 			= null;
    PreparedStatement pstmt = null;
	ResultSet rs 			= null;
	
	public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private List<Cidades> lCidades;
	private List<ObjetoAuxiliar> lObjAuxilioCidades;
	
	//1. Ler o arquivo CSV das cidades para a base de dados;
	public boolean LerArquivoCsv(File flcsv) throws IOException, ClassNotFoundException, SQLException {
		
		boolean resp = false;
		String line = "";
		String[] dados;
		int countline = 0;
		lCidades = new ArrayList<Cidades>();
		Reader reader = new FileReader(flcsv);
		LineNumberReader lineReader = new LineNumberReader(reader);
		do {
			line = lineReader.readLine();
			if (line != null) { 
				System.out.println(line);
				dados = line.split(",");
			
				if(countline != 0) {
				int ibg = Integer.parseInt(dados[0].toString().isEmpty() ? "0" : dados[0].toString());
			    lCidades.add( new Cidades(ibg, 
		                      			  dados[1].toString(),
		                      			  dados[2].toString(),
		                      			  dados[3].toString(),
		                      			  dados[4].toString(),
		                      			  dados[5].toString(),
		                      			  dados[6].toString(),
		                      			  dados[7].toString(),
		                      			  dados[8].toString(),
		                      			  dados[9].toString()));					
				}
				
				dados = null;
				countline++;
			}
			
		}while (line != null);
		
        //CsvToBean<Cidades> csvToBean = new CsvToBeanBuilder<Cidades>(reader).withType(Cidades.class).build();
        
        //List<Cidades> cidade = csvToBean.parse();

        for (Cidades city : lCidades)
        	resp = InserirCidade(city);
		
        return resp;
	};
	
	//2. Retornar somente as cidades que são capitais ordenadas por nome;
	public List<Cidades> getCapitaisOrdenadaPorNomes() throws ClassNotFoundException, SQLException{
		
		con = db.Conectar();
		String sql = "SELECT * FROM CIDADES WHERE UPPER(CAPITAL) = 'TRUE' ORDER BY NAME";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		lCidades = new ArrayList<Cidades>();
		while(rs.next()){
			lCidades.add( new Cidades(rs.getInt("ibge_id"), 
					                      rs.getString("uf"),
					                      rs.getString("name"),
					                      rs.getString("capital"),
					                      rs.getString("lon"),
					                      rs.getString("lat"),
					                      rs.getString("no_accents"),
					                      rs.getString("alternative_names"),
					                      rs.getString("microregion"),
					                      rs.getString("mesoregion")));
		}
		con.close();
		return lCidades;
	}
	
	//3. Retornar o nome do estado com a maior e menor quantidade de cidades e quantidade de cidades;
		public List<ObjetoAuxiliar> MaiorMenorEstado() throws ClassNotFoundException, SQLException {
			con = db.Conectar();
			con.setAutoCommit(false);
			lObjAuxilioCidades = new ArrayList<ObjetoAuxiliar>();
					
			String sql = "SELECT COUNT(*) AS total FROM all_tables WHERE UPPER(table_name) = UPPER('temp') ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				if(rs.getInt("total") > 0) {
					
					sql = "DROP TABLE temp ";
					pstmt = con.prepareStatement(sql);				
					pstmt.executeUpdate();
					
					sql = "CREATE TABLE temp (myuf VARCHAR2(50), myquant NUMBER) ";
					pstmt = con.prepareStatement(sql);				
					pstmt.executeUpdate();
					
					sql = "SELECT UF, COUNT(UF) AS QUANTIDADE FROM CIDADES GROUP BY  UF ORDER BY QUANTIDADE ASC \n";
					pstmt = con.prepareStatement(sql);
					ResultSet ns = pstmt.executeQuery();
					
					while(ns.next()){
						sql = "INSERT INTO temp  values(?, ?)";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, ns.getString("UF"));
						pstmt.setString(2, ns.getString("QUANTIDADE"));
						pstmt.executeUpdate();
					};
					
					ns.close();
									
					
					sql = " SELECT myuf, myquant FROM temp WHERE myquant = ( SELECT MAX(myquant) FROM temp ) \n"
					    + " UNION \n" 
					    + " SELECT myuf, myquant FROM temp WHERE myquant = ( SELECT MIN(myquant) FROM temp ) ";
					
					pstmt = con.prepareStatement(sql);
					ResultSet rss = pstmt.executeQuery();

					while(rss.next()){
						lObjAuxilioCidades.add(new ObjetoAuxiliar(rss.getString("myuf"),rss.getInt("myquant"), 0 , null, null));
					};
					
					rss.close();
				}
				else {
					
					sql = "CREATE GLOBAL temporary table temp (myuf VARCHAR2(50), myquant NUMBER) \n";
					pstmt = con.prepareStatement(sql);				
					pstmt.executeUpdate();					
					
					sql = "SELECT UF, COUNT(UF) AS QUANTIDADE FROM CIDADES GROUP BY  UF ORDER BY QUANTIDADE ASC \n";
					pstmt = con.prepareStatement(sql);
					ResultSet ns = pstmt.executeQuery();			
					
					while(ns.next()){
						sql = "INSERT INTO temp  VALUES(?, ?)";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, ns.getString("UF"));
						pstmt.setString(2, ns.getString("QUANTIDADE"));
						pstmt.executeUpdate();						
					};
					
					ns.close();
									
					
					sql = " SELECT myuf, myquant FROM temp WHERE myquant = ( SELECT MAX(myquant) FROM temp ) \n"
					    + " UNION \n" 
					    + " SELECT myuf, myquant FROM temp WHERE myquant = ( SELECT MIN(myquant) FROM temp ) ";
					
					pstmt = con.prepareStatement(sql);
					ResultSet rss = pstmt.executeQuery();					
					
					while(rss.next()){
						lObjAuxilioCidades.add(new ObjetoAuxiliar(rss.getString("myuf"),rss.getInt("myquant"), 0 , null, null));
					};
					
					rss.close();
				}
			};
			con.commit();			
			con.close();		
			return lObjAuxilioCidades;
		}
	
	//4. Retornar a quantidade de cidades por estado;
	public  List<ObjetoAuxiliar> QuantidadeCidadePorEstado(String Uf) throws ClassNotFoundException, SQLException{
		
		con = db.Conectar();
		String sql = "SELECT UF, COUNT(UF) AS QUANTIDADE FROM CIDADES WHERE UF = UPPER('"+Uf+"') GROUP BY  UF ORDER BY QUANTIDADE ASC";
		
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		lObjAuxilioCidades = new ArrayList<ObjetoAuxiliar>();
		while(rs.next()){
			lObjAuxilioCidades.add(new ObjetoAuxiliar(rs.getString("UF"),rs.getInt("QUANTIDADE"), 0 , null, null));
		}
		con.close();
		return lObjAuxilioCidades;
	};
	
	//5. Obter os dados da cidade informando o id do IBGE;
	public List<Cidades> CidadePorIdIbge(int idbge) throws ClassNotFoundException, SQLException {
		con = db.Conectar();
		String sql = "SELECT * FROM CIDADES WHERE ibge_id = ? ORDER BY NAME";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, idbge);
		rs = pstmt.executeQuery();
		lCidades = new ArrayList<Cidades>();
		while(rs.next()){
			lCidades.add( new Cidades(rs.getInt("ibge_id"), 
					                      rs.getString("uf"),
					                      rs.getString("name"),
					                      rs.getString("capital"),
					                      rs.getString("lon"),
					                      rs.getString("lat"),
					                      rs.getString("no_accents"),
					                      rs.getString("alternative_names"),
					                      rs.getString("microregion"),
					                      rs.getString("mesoregion")));
		}
		con.close();
		return lCidades;
	};
	
	//6. Retornar o nome das cidades baseado em um estado selecionado;
	public List<Cidades> CidadePorEstado(String Uf) throws ClassNotFoundException, SQLException {
		con = db.Conectar();
		String sql = "SELECT * FROM CIDADES WHERE upper(uf) = upper('"+Uf+"') ORDER BY NAME";
		pstmt = con.prepareStatement(sql);	
		rs = pstmt.executeQuery();
		lCidades = new ArrayList<Cidades>();
		while(rs.next()){
			lCidades.add( new Cidades(rs.getInt("ibge_id"), 
					                      rs.getString("uf"),
					                      rs.getString("name"),
					                      rs.getString("capital"),
					                      rs.getString("lon"),
					                      rs.getString("lat"),
					                      rs.getString("no_accents"),
					                      rs.getString("alternative_names"),
					                      rs.getString("microregion"),
					                      rs.getString("mesoregion")));
		}
		con.close();
		return lCidades;
	};
	
	//7. Permitir adicionar uma nova Cidade;
	public boolean InserirCidade(Cidades city) throws ClassNotFoundException, SQLException {
		
		con = db.Conectar();
		String sql = "INSERT INTO CIDADES (IBGE_ID, UF, NAME, CAPITAL, LON, LAT, NO_ACCENTS, ALTERNATIVE_NAMES, MICROREGION, MESOREGION) " + 
					 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, city.getIbgeid());
		pstmt.setString(2, city.getUf());
		pstmt.setString(3, city.getName());
		pstmt.setString(4, city.getCapital());
		pstmt.setString(5, city.getLon());
		pstmt.setString(6, city.getLat());
		pstmt.setString(7, city.getNoaccents());
		pstmt.setString(8, city.getAlternativenames());
		pstmt.setString(9, city.getMicroregion());
		pstmt.setString(10, city.getMesoregion());
		System.out.println(sql);
		boolean resp = ( pstmt.executeUpdate() > 0 );
		con.close();
		return resp;
	};
	
	//8. Permitir deletar uma cidade;
	public boolean DeleteCidade(int ibgeid) throws ClassNotFoundException, SQLException{
		con = db.Conectar();
		String sql = "DELETE FROM CIDADES WHERE IBGE_ID = ? ";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, ibgeid);
		boolean resp = ( pstmt.executeUpdate() > 0 );
		con.close();
		return resp;
	};
	
	//9. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. retornar assim todos os objetos que contenham tal string;
	public List<ObjetoAuxiliar> ColunasTabelaCidade() throws ClassNotFoundException, SQLException{
		lObjAuxilioCidades = new ArrayList<ObjetoAuxiliar>();
		con = db.Conectar();
		String sql = " SELECT column_id, column_name, data_type FROM all_tab_columns" + 
					 " WHERE table_name = 'CIDADES' " + 
					 " ORDER BY column_id  ";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			lObjAuxilioCidades.add(new ObjetoAuxiliar(null, 0, rs.getInt("column_id") , rs.getString("column_name"), rs.getString("data_type")));
		};
		con.close();
		return lObjAuxilioCidades;
	};
	
	public List<Cidades> SelecionarCidadesPorColunaValor(String Coluna, String Tipo, String Valor) throws ClassNotFoundException, SQLException{
		
		con = db.Conectar();
		String sql = "SELECT * FROM CIDADES WHERE ";
		
		int tp = ( (Tipo.equalsIgnoreCase("NUMBER")) ? 0 : (Tipo.equalsIgnoreCase("VARCHAR2")) ? 1 : 2 );
		switch (tp) {
		case 0:
				sql += " " + Coluna + " = " + Valor;
			break;
		case 1:
				sql += " " + Coluna + " like '%" + Valor + "%'";
			break;
		default:
			break;
		}
		
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		//pstmt.setString(1, Valor);
		rs = pstmt.executeQuery();
		lCidades = new ArrayList<Cidades>();
		while(rs.next()){
			lCidades.add( new Cidades(rs.getInt("ibge_id"), 
					                      rs.getString("uf"),
					                      rs.getString("name"),
					                      rs.getString("capital"),
					                      rs.getString("lon"),
					                      rs.getString("lat"),
					                      rs.getString("no_accents"),
					                      rs.getString("alternative_names"),
					                      rs.getString("microregion"),
					                      rs.getString("mesoregion")));
		}
		con.close();
		return lCidades;
		
	};
	
	//10. Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais;
	public List<ObjetoAuxiliar> QuantidadePorColuna(String Coluna) throws ClassNotFoundException, SQLException{
		lObjAuxilioCidades = new ArrayList<ObjetoAuxiliar>();
		con = db.Conectar();
		String sql = "SELECT COUNT(COUNT(DISTINCT "+Coluna+")) AS QUANTIDADE FROM CIDADES " + 
					 "GROUP BY " + Coluna;
		pstmt = con.prepareStatement(sql);
		//pstmt.setString(1, Coluna);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			lObjAuxilioCidades.add(new ObjetoAuxiliar(null, rs.getInt("QUANTIDADE"), 0, Coluna, null));
		};
		con.close();
		return lObjAuxilioCidades;
		
	};
	
	//11. Retornar a quantidade de registros total;
	public int TotalRegistroCidades() throws ClassNotFoundException, SQLException{
		int quant = 0;
		con = db.Conectar();
		String sql = "SELECT COUNT(*) AS TOTALREGISTRO FROM CIDADES ";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			quant = rs.getInt("TOTALREGISTRO");
		};
		con.close();
		return quant;
	};
	
	//12. Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base na localização (distância em KM em linha reta);
	public List<Cidades> CidadesDistantes() throws ClassNotFoundException, SQLException{
				con = db.Conectar();
				int dist = 0;
				int x1 = 0;
				int x2 = 0;
				int y1 = 0;
				int y2 = 0;
				int ibgeid1 = 0;
				int ibgeid2 = 0;
				ResultSet rsn1 = null;
				ResultSet rsn2 = null;
				
				con.setAutoCommit(false);
				String sql = "select count(*) as total from all_tables WHERE UPPER(table_name) = UPPER('tempcidade')";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				System.out.println(sql);
				
				while(rs.next()){
					if(rs.getInt("total") > 0) {
						
						sql = " DROP TABLE tempcidade";
						pstmt = con.prepareStatement(sql);
						pstmt.executeUpdate();
						
						System.out.println(sql);
						
						sql = " CREATE TABLE tempcidade (IBGE_ID_CITY1 NUMBER, IBGE_ID_CITY2 NUMBER , DISTANCIA NUMBER ) \n";				   
						pstmt = con.prepareStatement(sql);
						pstmt.executeUpdate();
						
						System.out.println(sql);
					}
					else 
					{
						sql = " CREATE TABLE tempcidade (IBGE_ID_CITY1 NUMBER, IBGE_ID_CITY2 NUMBER , DISTANCIA NUMBER ) \n";				   
						pstmt = con.prepareStatement(sql);
						pstmt.executeUpdate();
						
						System.out.println(sql);
					}
				};
				
				rs.close();
				
				sql = "SELECT * FROM CIDADES ORDER BY uf ";
				pstmt = con.prepareStatement(sql);
				rsn1 = null;
				rsn1 = pstmt.executeQuery();
				
				System.out.println(sql);
				
				while(rsn1.next()){
					
					sql = "SELECT * FROM CIDADES ORDER BY uf ";
					pstmt = con.prepareStatement(sql);
					rsn2 = null;
					rsn2 = pstmt.executeQuery();
					
					
					
					while(rsn2.next()){
						x1 = ( rsn1.getInt("LON") * -1 );
						x2 = ( rsn2.getInt("LON") * -1 );
						y1 = ( rsn1.getInt("LAT") * -1 );
						y2 = ( rsn2.getInt("LAT") * -1 );
						
						if( ( x1 == x2 ) || (y1 == y2) )
						{
							if( x1 != x2 ){
								if( x1 > x2)
								{
								  dist = (x1 - x2);	
								}
								else
								{
								  dist = (x2 - x1);		
								}
							}
							else
							{
								if( y1 != y2 )
								{
									if( y1 > y2)
									{
									  dist = (y1 - y2);	
									}
									else
									{
									  dist = (y2 - y1);	
									}
								}											
							}
						}						
						
						System.out.println(dist);
						 
						if(dist > 0)
						{
							sql = "INSERT INTO tempcidade (IBGE_ID_CITY1, IBGE_ID_CITY2, DISTANCIA) VALUES( ?, ?, ?) \n";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, rsn1.getInt("IBGE_ID"));
							pstmt.setInt(2, rsn2.getInt("IBGE_ID"));
							pstmt.setInt(3, dist);
							pstmt.executeUpdate();
							dist = 0;
							System.out.println(sql);
						}												
					};
					
				
				};
				

				sql = "SELECT IBGE_ID_CITY1 FROM tempcidade WHERE DISTANCIA = ( SELECT MAX(DISTANCIA) FROM tempcidade ) AND ROWNUM <= 1";
				pstmt = con.prepareStatement(sql);
				rsn1 = null;
				rsn1 = pstmt.executeQuery();
				
				while(rsn1.next()){
					ibgeid1 = rsn1.getInt("IBGE_ID_CITY1");
				};
				
				System.out.println(sql);
				rsn1.close();
				
				
				sql = "SELECT IBGE_ID_CITY2 FROM tempcidade WHERE DISTANCIA = ( SELECT MAX(DISTANCIA) FROM tempcidade ) AND ROWNUM <= 1";
				pstmt = con.prepareStatement(sql);
				rsn2 = null;
				rsn2 = pstmt.executeQuery();
				
				while(rsn2.next()){
					ibgeid2 = rsn2.getInt("IBGE_ID_CITY2");
				};
				System.out.println(sql);
				rsn2.close();
				
				sql = "SELECT * FROM CIDADES WHERE ibge_id IN(?,?)";
				
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ibgeid1);
				pstmt.setInt(2, ibgeid2);
				ResultSet rsa = pstmt.executeQuery();
				
				while(rsa.next()){
							lCidades.add( new Cidades(rsa.getInt("ibge_id"), 
				                      rsa.getString("uf"),
				                      rsa.getString("name"),
				                      rsa.getString("capital"),
				                      rsa.getString("lon"),
				                      rsa.getString("lat"),
				                      rsa.getString("no_accents"),
				                      rsa.getString("alternative_names"),
				                      rsa.getString("microregion"),
				                      rsa.getString("mesoregion")));
				};
				
				rsa.close();
				con.commit();
				con.close();
				return lCidades;
			};
			
	//lista de estados	
	public  List<ObjetoAuxiliar> ListaEstados() throws ClassNotFoundException, SQLException{
				
				con = db.Conectar();
				String sql = "SELECT DISTINCT UF FROM CIDADES ORDER BY UF ASC";
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				lObjAuxilioCidades = new ArrayList<ObjetoAuxiliar>();
				while(rs.next()){
					lObjAuxilioCidades.add(new ObjetoAuxiliar(rs.getString("UF"),0, 0 , null, null));
				}
				con.close();
				return lObjAuxilioCidades;
			};
	
}
