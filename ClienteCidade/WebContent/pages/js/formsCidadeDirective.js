angular.module('app').directive('formsCidadeDirective', function () {
    return {
        restrict: 'E',
        replace: true,
        scope: {
            person: '=',
            action: '&'
        },
        template:   '<div class="modal fade" id="myModalInserir" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">'+
          				'<div class="modal-dialog" role="document">'+
          					'<div class="modal-content">'+
          						'<div class="modal-header">'+
          							'<h5 class="modal-title" id="exampleModalLabel">New message</h5>'+
          								'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'+
          									'<span aria-hidden="true">&times;</span>'+
          								'</button>'+
          						 '</div>'+
          						 '<div class="modal-body">'+
          						'<!-- forms -->'+
          						'<form>'+
          						  '<div class="form-group">'+
          							'<label for="formGroupExampleInput">Cod.IBGE:</label>'+
          							'<input type="number" ng-model="person.ibgeid" class="form-control" id="formGroupExampleInput" placeholder="entre com o código do ibge">'+
          						  '</div>'+
          						  '<div class="form-group">'+
          							'<label for="formGroupExampleInput">Unidade Federal:</label>'+
          							'<input type="text" ng-model="person.uf" class="form-control" id="formGroupExampleInput" placeholder="entre com a unidade federal">'+
          						  '</div>'+
          						  '<div class="form-group">'+
          							'<label for="formGroupExampleInput">Nome da Cidade:</label>'+
          							'<input type="text" ng-model="person.name" class="form-control" id="formGroupExampleInput" placeholder="entre com o nome da cidade">'+
          						  '</div>'+
          						  '<div class="form-group">'+
          						  	'<label for="formGroupExampleInput">Capital:</label>'+
          						  	'<input type="text" ng-model="person.capital" class="form-control" id="formGroupExampleInput" placeholder="entre com o nome da cidade">'+
          						  '</div>'+
          						  '<div class="form-group">'+
          							'<label for="formGroupExampleInput">Longitude:</label>'+
          							'<input type="text" ng-model="person.lon" class="form-control" id="formGroupExampleInput" placeholder="entre com a longitude Ex:-63.9638527411">'+
          						  '</div>'+
          						  '<div class="form-group">'+
          							'<label for="formGroupExampleInput">Latitude:</label>'+
          							'<input type="text" ng-model="person.lat" class="form-control" id="formGroupExampleInput" placeholder="entre com a latitude Ex:-63.9638527411">'+
          						  '</div>'+
          						  '<div class="form-group">'+
          							'<label for="formGroupExampleInput">Sem Acidentes:</label>'+
          							'<input type="text" ng-model="person.noaccents" class="form-control" id="formGroupExampleInput" placeholder="">'+
          						  '</div>'+
          						  '<div class="form-group">'+
          							'<label for="formGroupExampleInput">Nome Alternativo:</label>'+
          							'<input type="text" ng-model="person.alternativenames" class="form-control" id="formGroupExampleInput" placeholder="entre com um nome alternativo">'+
          						  '</div>'+
          						  '<div class="form-group">'+
          							'<label for="formGroupExampleInput">Micro Região:</label>'+
          							'<input type="text" ng-model="person.microregion" class="form-control" id="formGroupExampleInput" placeholder="entre com a Micro Região">'+
          						  '</div>'+
          						  '<div class="form-group">'+
          							'<label for="formGroupExampleInput">Meso Região:</label>'+
          							'<input type="text" ng-model="person.mesoregion" class="form-control" id="formGroupExampleInput" placeholder="entre com a meso região">'+
          						  '</div>'+
          						  '<div class="form-group">'+
          							'<div class="col-sm-10">'+
          								'<button type="button" class="btn btn-primary" ng-click="action()" value="Action">Registrar</button>'+
          							'</div>'+
          						  '</div>'+
          						'</form>'+
          						 '</div>'+
              '<div class="modal-footer">'+
                '<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>'+
              '</div>'+
            '</div>'+
          '</div>'+
        '</div>'
    };
});