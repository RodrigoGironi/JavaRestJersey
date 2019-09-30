angular.module('app').directive('tableMaiorMenorCidadesDirective', function () {
    return {
        restrict: 'E',
        replace: true,
        scope: {
            person: '=',
            action: '&'
        },
        template:   '<table class="table table-sm table-success table-hover">'+
		      				'<thead>'+
		      				'<tr>'+
		      				'<th scope="col">UF</th>'+
		      				'<th scope="col">QUANTIDADE</th>'+		      				
		      				'</tr>'+
		      				'</thead>'+
		      				'<tbody>'+
		      				'<tr ng-repeat="c in person">'+
		      				'<td>{{ c.estado }}</td>'+
		      				'<td>{{ c.quantidade }}</td>'+		      		
		      				'</tr>'+
		      				'</tbody>'+
		      				'</table>'
    };
});