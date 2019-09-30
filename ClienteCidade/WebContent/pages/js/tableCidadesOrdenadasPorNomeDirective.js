angular.module('app').directive('tableCidadesOrdenadasPorNomeDirective', function () {
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
		      				'<th scope="col">IBGEID</th>'+
		      				'<th scope="col">UF</th>'+
		      				'<th scope="col">NOME</th>'+
		      				'<th scope="col">CAPITAL</th>'+
		      				'<th scope="col">LONGITUDE</th>'+
		      				'<th scope="col">LATITUDE</th>'+
		      				'<th scope="col">SEM ACIDENTES</th>'+
		      				'<th scope="col">NOMES ALTERNATIVOS</th>'+
		      				'<th scope="col">MICRO REGIÃO</th>'+
		      				'<th scope="col">MESO REGIÃO</th>'+
		      				'</tr>'+
		      				'</thead>'+
		      				'<tbody>'+
		      				'<tr ng-repeat="c in person">'+
		      				'<td>{{ c.ibgeid }}</td>'+
		      				'<td>{{ c.uf }}</td>'+
		      				'<td>{{ c.name }}</td>'+
		      				'<td>{{ c.capital }}</td>'+
		      				'<td>{{ c.lon }}</td>'+
		      				'<td>{{ c.lat }}</td>'+
		      				'<td>{{ c.noaccents }}</td>'+
		      				'<td></td>'+
		      				'<td>{{ c.microregion }}</td>'+
		      				'<td>{{ c.mesoregion }}</td>'+
		      				'</tr>'+
		      				'</tbody>'+
		      				'</table>'
    };
});