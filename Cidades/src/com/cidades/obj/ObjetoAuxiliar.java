package com.cidades.obj;

public class ObjetoAuxiliar extends Cidades{
     
	private String estado;
	private int quantidade;
	private int columnid;
	private String columnname;
	private String datatype;
	 
     public ObjetoAuxiliar() {};
	 
	 public ObjetoAuxiliar(String estado, int quantidade, int columnid, String columnname, String datatype) {
		 this.estado = estado;
		 this.quantidade = quantidade;
		 this.columnid = columnid;
		 this.columnname = columnname;
		 this.datatype = datatype;
	 }
	 
	 public String getEstado() {
			return estado;
    }

	public void setEstado(String estado) {
			this.estado = estado;
	}
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getColumnid() {
		return columnid;
	}

	public void setColumnid(int columnid) {
		this.columnid = columnid;
	}

	public String getColumnname() {
		return columnname;
	}

	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
}
