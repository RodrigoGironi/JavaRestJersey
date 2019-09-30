angular.module('app').directive('modalFileUploadDirective', function () {
    return {
        restrict: 'E',
        replace: true,
        scope: {
            person: '=',
            action: '&',
            title: '@',
            label: '@'
        },
        template:   '<!-- Modal -->' +
					'<div class="modal fade" id="modalCSV" tabindex="-1" role="dialog" aria-labelledby="lbmodal" aria-hidden="true">'+
					'<div class="modal-dialog" role="document">'+
					'<div class="modal-content">'+
					'<div class="modal-header">'+
						'<h5 class="modal-title" id="lbmodal">{{title}}</h5>'+
							'<button type="button" class="close" data-dismiss="modal" aria-label="Close">'+
								'<span aria-hidden="true">&times;</span>'+
							 '</button>'+
					'</div>'+
					'<div class="modal-body">'+
						'<form>'+
							'<div class="form-group">'+
								'<label for="exampleFormControlFile1">{{label}}</label>'+
								'<input type="file" class="form-control-file" id="exampleFormControlFile1">'+
							'</div>'+
						'</form>' +
					'</div>'+
					'<div class="modal-footer">'+
						'<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>'+
						'<button type="button" class="btn btn-primary" ng-click="action()" value="Action">Importar</button>'+
					'</div>'+
					'</div>'+
					'</div>'+
					'</div>'
    };
});