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
<div>
	<div class="container">
		<h2>XPTO: Opções de Escolha</h2>
		<p>Abaixo segue as opções que condicionaram o uso da ferramenta conforme o esperado</p>
		<form>
			<div class="radio">
			  <label><input type="radio" id="opt1" name="optradio" data-toggle="modal" data-target="#modalCSV">1 - Ler o arquivo CSV das cidades para a base de dados</label>
			</div>
			<div class="radio">
			  <label><input type="radio" id="opt2" name="optradio"  data-toggle="modal" data-target="#modalgrid" ng-click="vm.CityOrdenadas()">2 - Retornar somente as cidades que são capitais ordenadas por nome</label>
			</div>
			<div class="radio">
			  <label><input type="radio" id="opt3" name="optradio">3 - Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades</label>
			</div>
			<div class="radio">
			  <label><input type="radio" id="opt4" name="optradio">4 - Retornar a quantidade de cidades por estado</label>
			</div>
			<div class="radio">
			  <label><input type="radio" id="opt5" name="optradio">5 - Obter os dados da cidade informando o id do IBGE</label>
			</div>
			<div class="radio">
			  <label><input type="radio" id="opt6" name="optradio">6 - Retornar o nome das cidades baseado em um estado selecionado</label>
			</div>
			<div class="radio">
			  <label><input type="radio" id="opt7" name="optradio">7 - Permitir adicionar uma nova Cidade</label>
			</div>
			<div class="radio">
			  <label><input type="radio" id="opt8" name="optradio">8 - Permitir deletar uma cidade</label>
			</div>
			<div class="radio">
			  <label><input type="radio" id="opt9" name="optradio">9 - Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. retornar assim todos os objetos que contenham tal string</label>
			</div>
			<div class="radio">
			  <label><input type="radio" id="opt10" name="optradio">10 - Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais</label>
			</div>
			<div class="radio">
			  <label><input type="radio" id="opt11" name="optradio">11 - Retornar a quantidade de registros total</label>
			</div>
			<div class="radio">
			  <label><input type="radio" id="opt12" name="optradio">12 - Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base na localização (distância em KM em linha reta)</label>
			</div>
		</form>
	</div>
	<div class="container">
	<modal-file-upload-directive title="Importação de Arquivo" label="Escolha um arquivo .CSV para importação" person="vm.person" action="vm.show()"></modal-file-upload-directive>		
	</div>
	<div class="container">
	<modal-cidades-ordenadas-por-nome-directive title="Capitais Ordenadas Por Nome" label="ert" person="vm.capitais"></modal-cidades-ordenadas-por-nome-directive>
	</div> 
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script type="text/javascript" src="./js/app.js" ></script>
<script type="text/javascript" src="./js/initController.js"></script>
<script type="text/javascript" src="./js/modalFileUploadDirective.js"></script>
<script type="text/javascript" src="./js/modalCidadesOrdenadasPorNomeDirective.js"></script>
</body>
</html>