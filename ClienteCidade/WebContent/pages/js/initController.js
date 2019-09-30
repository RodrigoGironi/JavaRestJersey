angular.module('app').controller('initController', [ '$scope','$timeout','$q','$http', function ($scope, $window, $http, myService) { 
      
	
	var vm = this;

    vm.person = {
        estado: '',
        ibgeid: '',
        coluna:'',
        datatype:'',
        tipo: '',
        valor: ''
    };
    
    vm.cidade = {
    		ibgeid: "",
    		uf: "",
    		name: "",
    		capital: "",
    		lon:"",
    		lat:"",
    		noaccents:"",
    		alternativenames:"",
    		microregion: "",
    		mesoregion: ""    		
    };

    vm.setObj = function(m) {
		vm.person.estado = m;
	}
    
    vm.setObjCol = function(c,d) {
		vm.person.coluna = c;
		vm.person.datatype = d;
		vm.person.tipo = (d == 'VARCHAR2' ? 'text' : 'number');
	}
    
    vm.show = function(){
        alert(JSON.stringify(vm.person));
    };
    
    vm.CityOrdenadas = function(){
    	myService.get("http://localhost:8080/Cidades/rest/Servicos/capitaisordenadas").then(function(resposta){    		
    		vm.capitais = resposta.data;
    	})
    };
    
    vm.MaiorMenorEstado = function(){
    	myService.get("http://localhost:8080/Cidades/rest/Servicos/maiormenorestado").then(function(resposta){
    		vm.MaiorMenor = resposta.data;
    	})
    }
    
    vm.listaUf = function(){
    	myService.get("http://localhost:8080/Cidades/rest/Servicos/listauf").then(function(resposta){
    		vm.listauf = resposta.data;
    	})
    }
    
    vm.QuantidadePorEstado = function(){
    	myService.get("http://localhost:8080/Cidades/rest/Servicos/quantidadecidadeuf?uf="+vm.person.estado).then(function(resposta){
    		vm.listaQuant = resposta.data;
    	})
    }
    
    vm.CidadeIdIbge = function(){
		myService.get("http://localhost:8080/Cidades/rest/Servicos/cidadeporibge?idibge=" + vm.person.ibgeid ).then(function(resposta) {
			vm.listaCidadesIbge = resposta.data;
		})
	}
    
    vm.CidadePorUf = function(){
    	myService.get("http://localhost:8080/Cidades/rest/Servicos/cidadeporuf?uf=" + vm.person.estado).then(function(resposta){
    		vm.listaCidadeUf = resposta.data;
    	})
    }
    
    vm.InserirCidade = function(){ 
    	
        var query = "?ibgeid=" + vm.cidade.ibgeid 
        			+ "&uf=" + vm.cidade.uf 
        			+ "&name="+vm.cidade.name
        			+ "&capital="+vm.cidade.capital
        			+ "&lon="+vm.cidade.lon
        			+ "&lat="+vm.cidade.lat
        			+ "&noaccents="+vm.cidade.noaccents
        			+ "&alternativenames=" + vm.cidade.alternativenames
        			+ "&microregion=" + vm.cidade.microregion
        			+ "&mesoregion=" + vm.cidade.mesoregion + "";
        alert(vm.cidade.uf + "/n/r" + vm.cidade.uf.length);
    	myService.post("http://localhost:8080/Cidades/rest/Servicos/cidadeinsert" + query).then(function(resposta) {	
			if(!vm.Ok){
				alert("Dados não inserido, tente mais tarde ou entre em contato com o suporte.");
			}else{
				alert("Cidade Registrada com Sucesso.");
			}
		})
    }
    
    vm.DeletarCidade = function(){
    	myService.post("http://localhost:8080/Cidades/rest/Servicos/cidadedelete?idibge=" + vm.person.ibgeid).then(function(resposta){
			vm.Ok = resposta.data;
			if(!vm.Ok){
				alert("Dados não deletado, tente mais tarde ou entre em contato com o suporte.");
			}else{
				alert("Cidade (" + vm.person.ibgeid + ") Excluída com Sucesso.");
			}								
		})
    }
    
    vm.listaColunas = function(){
    	myService.get("http://localhost:8080/Cidades/rest/Servicos/colunascidades").then(function(resposta){
    		vm.listacoluna = resposta.data;
    	})
    }
    
    vm.BuscaPorColuna = function(){
    	myService.get("http://localhost:8080/Cidades/rest/Servicos/procurarporcolunas?coluna=" + vm.person.coluna +"&tipo=" + vm.person.datatype +"&valor=" + vm.person.valor).then(function(resposta){
    		vm.listaCidadeColuna = resposta.data;
    	})
    }
    
    vm.CidadeRegistroColuna = function(){
    	myService.get("http://localhost:8080/Cidades/rest/Servicos/quantidadescolunas?coluna=" + vm.person.coluna).then(function(resposta){
    		vm.listadatatype = resposta.data;
    	})
    }
    
    vm.TotalRegistros = function(){
    	myService.get("http://localhost:8080/Cidades/rest/Servicos/totalregistro").then(function(resposta){
    		vm.registro = resposta.data;
    	})
    }
    
    vm.DistanciasCidades = function(){
    	myService.get("http://localhost:8080/Cidades/rest/Servicos/distancias").then(function(resposta){
    		vm.listacidadesdistancias = resposta.data;
    	})
    }
    
    vm.ImportaFile = function(){
    	myService.post("http://localhost:8080/Cidades/rest/Servicos/importacaocsv", document.getElementById("ControlFile").files[0]).then(function(resposta){
    		vm.Ok = resposta.dada;
    	})
    }
    
    

    app.service('myService', function($http){    	
    	
    	this.lista = function(){    	
    		return 	$http.get('http://localhost:8080/Cidades/rest/Servicos/capitaisordenadas');    	                         	
        }
    });
    
  
   } 
]);