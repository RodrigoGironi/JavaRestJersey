package com.cidades.servicos;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cidades.controles.ControleCidade;
import com.cidades.obj.Cidades;
import com.cidades.obj.ObjetoAuxiliar;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;




@Path("/Servicos")
public class ServicosCidades {
	
	ControleCidade ctr = new ControleCidade();
	Gson gson = new Gson();	
	
	
	@POST
	@Path("/importacaocsv")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ImportacaoCsv(File fl ) {
		boolean resp = false;
		try 
		{
			resp = ctr.LerArquivoCsv(fl);
			return (( resp ) ? Response.ok().build() : Response.ok().status(Status.CONFLICT).build());
			
		}catch(Exception e) {
			return Response.serverError().build();
		}
		
	};
	
	
	@GET
	@Path("/capitaisordenadas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response CapitaisOrdenadasPorNome() {
		try
		{
			Type listType = new TypeToken<List<Cidades>>() {}.getType();							
			return Response.ok(gson.toJson( ctr.getCapitaisOrdenadaPorNomes(), listType)).build();
		
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/maiormenorestado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response MaiorMenorUf() {
		
		try
		{
			Type listType = new TypeToken<List<ObjetoAuxiliar>>() {}.getType();							
			return Response.ok(gson.toJson( ctr.MaiorMenorEstado(), listType)).build();
		
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/quantidadecidadeuf")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response QuantCidadePorUf(@QueryParam("uf") String Estado) {
		
		try
		{
			Type listType = new TypeToken<List<ObjetoAuxiliar>>() {}.getType();							
			return Response.ok(gson.toJson( ctr.QuantidadeCidadePorEstado(Estado), listType)).build();
		
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GET
	@Path("/cidadeporibge")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response CidadePorIdIbge(@QueryParam("idibge") int idibge) {
		
		try
		{
			Type listType = new TypeToken<List<Cidades>>() {}.getType();							
			return Response.ok(gson.toJson( ctr.CidadePorIdIbge(idibge), listType)).build();
		
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/cidadeporuf")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response CidadePorUf(@QueryParam("uf") String Estado) {
		
		try
		{
			Type listType = new TypeToken<List<Cidades>>() {}.getType();							
			return Response.ok(gson.toJson( ctr.CidadePorEstado(Estado), listType)).build();
		
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Path("/cidadeinsert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response CidadeInsert(@QueryParam("cidade") Cidades city) {
		
		try
		{										
			return Response.ok(gson.toJson( ctr.InserirCidade(city), Boolean.class)).build();		
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Path("/cidadedelete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response CidadeDelete(@QueryParam("idibge") int idibge) {
		
		try
		{										
			return Response.ok(gson.toJson( ctr.DeleteCidade(idibge), Boolean.class)).build();		
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/colunascidades")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ColunasCidades() {
		
		try
		{
			Type listType = new TypeToken<List<ObjetoAuxiliar>>() {}.getType();							
			return Response.ok(gson.toJson( ctr.ColunasTabelaCidade(), listType)).build();
		
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/procurarporcolunas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response ProcurarPorColunas(@QueryParam("coluna") String Coluna, @QueryParam("tipo") String Tp, @QueryParam("valor") String Valor) {
		
		try
		{
			Type listType = new TypeToken<List<Cidades>>() {}.getType();							
			return Response.ok(gson.toJson( ctr.SelecionarCidadesPorColunaValor(Coluna, Tp, Valor), listType)).build();
		
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/quantidadescolunas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response QuantidadeColunas(@QueryParam("coluna") String Coluna) {
		
		try
		{
			Type listType = new TypeToken<List<ObjetoAuxiliar>>() {}.getType();							
			return Response.ok(gson.toJson( ctr.QuantidadePorColuna(Coluna), listType)).build();
		
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/totalregistro")
	@Produces(MediaType.APPLICATION_JSON)
	public Response TotalRegistro() {
		
		try
		{	
			String resp = "{ \"Total\":"+ String.valueOf(ctr.TotalRegistroCidades()) + "}";
			return Response.ok(gson.toJson( resp, String.class)).build();
		
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/distancias")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response Distancias() {
		
		try
		{
			Type listType = new TypeToken<List<Cidades>>() {}.getType();							
			return Response.ok(gson.toJson( ctr.CidadesDistantes(), listType)).build();
		
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
