package com.cidades.obj;

import com.opencsv.bean.CsvBindByName;

public class Cidades {
	@CsvBindByName(column = "ibge_id")
	private int ibgeid;
	
	@CsvBindByName(column = "uf")
	private String uf;
	
	@CsvBindByName(column = "name")
	private String name;
	
	@CsvBindByName(column = "capital")
	private String capital;
	
	@CsvBindByName(column = "lon")
	private int lon;
	
	@CsvBindByName(column = "lat")
	private int lat;
	
	@CsvBindByName(column = "no_accents")
	private String noaccents;
	
	@CsvBindByName(column = "alternative_names")
	private String alternativenames;
	
	@CsvBindByName(column = "microregion")
	private String microregion;
	
	@CsvBindByName(column = "mesoregion")
	private String mesoregion;
	
	public Cidades() {};
	
	public Cidades(int ibgeid, 
			           String uf, 
			           String name, 
			           String capital, 
			           int lon, 
			           int lat,
			           String noaccents,
			           String alternativenames,
			           String microregion,
			           String mesoregion){
		
		this.ibgeid = ibgeid;
		this.uf = uf;
		this.name = name;
		this.capital = capital;
		this.lon = lon;
		this.lat = lat;
		this.noaccents = noaccents;
		this.alternativenames = alternativenames;
		this.microregion = microregion;
		this.mesoregion = mesoregion;
				
	};
	

	public int getIbgeid() {
		return ibgeid;
	}

	public void setIbgeid(int ibgeid) {
		this.ibgeid = ibgeid;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public int getLon() {
		return lon;
	}

	public void setLon(int lon) {
		this.lon = lon;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public String getNoaccents() {
		return noaccents;
	}

	public void setNoaccents(String noaccents) {
		this.noaccents = noaccents;
	}

	public String getAlternativenames() {
		return alternativenames;
	}

	public void setAlternativenames(String alternativenames) {
		this.alternativenames = alternativenames;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public String getMesoregion() {
		return mesoregion;
	}

	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}
}
