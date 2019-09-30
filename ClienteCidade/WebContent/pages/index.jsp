<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


<title>Xpto Client</title>
</head>
<body ng-controller="initController as vm">

<div class="accordion" id="accordionExample">
  <div class="card">
    <div class="card-header" id="headingOne">
      <h5 class="mb-0">
        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          1 - Ler o arquivo CSV das cidades para a base de dados
        </button>
      </h5>
    </div>

    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
      <div class="card-body">
        <form>
		<div class="form-group">
			<label for="ControlFile1"></label>
			<input type="file" class="form-control-file" id="ControlFile"><br/>
			<button type="button" class="btn btn-primary" ng-click="vm.ImportaFile()" value="Action">Importar</button>
		</div>
		</form>
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingTwo">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" ng-click="vm.CityOrdenadas()">
         2 - Retornar somente as cidades que são capitais ordenadas por nome
        </button>
      </h5>
    </div>
    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
      <table-cidades-ordenadas-por-nome-directive  person="vm.capitais"></table-cidades-ordenadas-por-nome-directive>      
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingThree">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree" ng-click="vm.MaiorMenorEstado()">
          3 - Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades
        </button>
      </h5>
    </div>
    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
      <div class="card-body">
        <table-maior-menor-cidades-directive person="vm.MaiorMenor"></table-maior-menor-cidades-directive>
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingFour">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour" ng-click="vm.listaUf()">
          4 - Retornar a quantidade de cidades por estado
        </button>
      </h5>
    </div>
    <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordionExample">
      <div class="card-body">
        <div class="dropdown">
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		   Escolha um Estado
		  </button>
		  <div>Valor escolhido: {{vm.person.estado}}</div>
		  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		    <a class="dropdown-item" href="#" ng-repeat="uf in vm.listauf" ng-click="vm.setObj(uf.estado)">{{uf.estado}}</a>
		  </div><br/>
		  <div>
		  	<button type="button" class="btn btn-primary" ng-click="vm.QuantidadePorEstado()" value="Action">Localizar</button>
		  </div><br/>
		  <div class="container" style="background-color: aqua;">
		  	<div class="col-sm" ng-repeat="u in vm.listaQuant"><span>UF:{{u.estado}}</span><span> - Quantidade de Cidades: </span><span>{{u.quantidade}}</span></div>
		  </div>
		</div>
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingFive">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
          5 - Obter os dados da cidade informando o id do IBGE
        </button>
      </h5>
    </div>
    <div id="collapseFive" class="collapse" aria-labelledby="headingFive" data-parent="#accordionExample">
     <div class="input-group mb-3">
  			<div class="input-group-prepend">
    			<button class="btn btn-outline-secondary" type="button" id="button-addon1" ng-click="vm.CidadeIdIbge()">Localizar</button>
  			</div>
  			<input type="number" class="form-control" placeholder="Digite o código do IBGE referente a cidades" aria-label="Example text with button addon" aria-describedby="button-addon1" ng-model="vm.person.ibgeid" /><br/>  			
	</div>
	<div>
  		<table-cidades-ordenadas-por-nome-directive person="vm.listaCidadesIbge"></table-cidades-ordenadas-por-nome-directive>
  	</div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingSix">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseSix" aria-expanded="false" aria-controls="collapseSix" ng-click="vm.listaUf()">
          6 - Retornar o nome das cidades baseado em um estado selecionado
        </button>
      </h5>
    </div>
    <div id="collapseSix" class="collapse" aria-labelledby="headingSix" data-parent="#accordionExample">
      <div class="card-body">
        <div class="dropdown">
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		   Escolha um Estado
		  </button>
		  <div>Valor escolhido: {{vm.person.estado}}</div>
		  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		    <a class="dropdown-item" href="#" ng-repeat="uf in vm.listauf" ng-click="vm.setObj(uf.estado)">{{uf.estado}}</a>
		  </div><br/>
		  <div>
		  	<button type="button" class="btn btn-primary" ng-click="vm.CidadePorUf()" value="Action">Localizar</button>
		  </div><br/>
		  <div>
		  	<table-cidades-ordenadas-por-nome-directive person="vm.listaCidadeUf"></table-cidades-ordenadas-por-nome-directive>
		  </div>
		</div>
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingSeven">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
          7 - Permitir adicionar uma nova Cidade
        </button>
      </h5>
    </div>
    <div id="collapseSeven" class="collapse" aria-labelledby="headingSeven" data-parent="#accordionExample">
      <div class="card-body">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModalInserir">Inserir Cidade</button>
		<forms-cidade-directive person="vm.cidade" action="vm.InserirCidade()"></forms-cidade-directive>
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingEight">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseEight" aria-expanded="false" aria-controls="collapseEight">
          8 - Permitir deletar uma cidade
        </button>
      </h5>
    </div>
    <div id="collapseEight" class="collapse" aria-labelledby="headingEight" data-parent="#accordionExample">
      <div class="card-body">
		 <div class="input-group mb-3">
  			<div class="input-group-prepend">
    			<button class="btn btn-outline-secondary" type="button" id="button-addon1" ng-click="vm.DeletarCidade()">Deletar Cidade</button>
  			</div>
  			<input type="number" class="form-control" placeholder="Digite o código do IBGE referente a cidades" aria-label="Example text with button addon" aria-describedby="button-addon1" ng-model="vm.person.ibgeid" /><br/>  			
		</div>      
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingNine">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseNine" aria-expanded="false" aria-controls="collapseNine" ng-click="vm.listaColunas()">
          9 - Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. retornar assim todos os objetos que contenham tal string
        </button>
      </h5>
    </div>
    <div id="collapseNine" class="collapse" aria-labelledby="headingNine" data-parent="#accordionExample">
      <div class="card-body">
        <div class="dropdown">
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		   Escolha uma Coluna
		  </button>
		  <div>Valor escolhido: {{vm.person.coluna}}</div>
		  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		    <a class="dropdown-item" href="#" ng-repeat="c in vm.listacoluna" ng-click="vm.setObjCol(c.columnname, c.datatype)">{{c.columnname}}</a>
		  </div><br/>
		  <div>
		  	<div class="input-group mb-3">
	  			<div class="input-group-prepend">
	    			<button class="btn btn-outline-secondary" type="button" id="button-addon1" ng-click="vm.BuscaPorColuna()">Localizar</button>
	  			</div>
  					<input type="text" class="form-control" placeholder="Digite o valor" aria-label="Example text with button addon" aria-describedby="button-addon1" ng-model="vm.person.valor" /><br/>  			
			</div>		  	
		  </div><br/>
		  <div>
		  	<table-cidades-ordenadas-por-nome-directive person="vm.listaCidadeColuna"></table-cidades-ordenadas-por-nome-directive>
		  </div>
		</div>
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingTen">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTen" aria-expanded="false" aria-controls="collapseTen" ng-click="vm.listaColunas()">
          10 - Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais
        </button>
      </h5>
    </div>
    <div id="collapseTen" class="collapse" aria-labelledby="headingTen" data-parent="#accordionExample">
      <div class="card-body">
        <div class="dropdown">
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		   Escolha uma Coluna
		  </button>
		  <div>Valor escolhido: {{vm.person.coluna}}</div>
		  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		    <a class="dropdown-item" href="#" ng-repeat="c in vm.listacoluna" ng-click="vm.setObjCol(c.columnname, c.datatype)">{{c.columnname}}</a>
		  </div><br/>
		  <div>
			  <div>
			  		<button type="button" class="btn btn-primary" ng-click="vm.CidadeRegistroColuna()" value="Action">Localizar</button>
			  </div><br/>  	
		  </div><br/>
		  <div>
		  	<div class="container" style="background-color: aqua;">
		  	<div class="col-sm" ng-repeat="dt in vm.listadatatype"><span>Tipo da Coluna:{{dt.columnname}}</span><span> - Quantidade: </span><span>{{dt.quantidade}}</span></div>
		  </div>
		  </div>
		</div>
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingEleven">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseEleven" aria-expanded="false" aria-controls="collapseEleven" ng-click="vm.TotalRegistros()">
          11 - Retornar a quantidade de registros total
        </button>
      </h5>
    </div>
    <div id="collapseEleven" class="collapse" aria-labelledby="headingEleven" data-parent="#accordionExample">
      <div class="card-body">
		   <h4>Total de Registro de Cidades:<span>{{ vm.registro }}</span></h4>     
      </div>
    </div>
  </div>
  <div class="card">
    <div class="card-header" id="headingTwoelve">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwelve" aria-expanded="false" aria-controls="collapseTwelve" ng-click="vm.DistanciasCidades()">
          12 - Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base na localização (distância em KM em linha reta)
        </button>
      </h5>
    </div>
    <div id="collapseTwelve" class="collapse" aria-labelledby="headingTwelve" data-parent="#accordionExample">
      <div class="card-body">
          <table-cidades-ordenadas-por-nome-directive person="vm.listacidadesdistancias"></table-cidades-ordenadas-por-nome-directive>
      </div>
    </div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script type="text/javascript" src="./js/app.js" ></script>
<script type="text/javascript" src="./js/initController.js"></script>
<script type="text/javascript" src="./js/modalFileUploadDirective.js"></script>
<script type="text/javascript" src="./js/tableMaiorMenorCidadesDirective.js"></script>
<script type="text/javascript" src="./js/tableCidadesOrdenadasPorNomeDirective.js"></script>
<script type="text/javascript" src="./js/formsCidadeDirective.js"></script>
</body>
</html>