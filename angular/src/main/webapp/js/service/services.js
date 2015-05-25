angular.module('membersService', [ 'ngResource' ]).factory('Members',
		function($resource) {
			return $resource('rest/members/:memberId', {});
		});

angular.module('racaosService', [ 'ngResource' ]).factory('Racaos',
		function($resource) {
			return $resource('rest/racaos/:racaoId', {});
		});

angular.module('ciclosService', [ 'ngResource' ]).factory('Ciclos',
		function($resource) {
			return $resource('rest/ciclos/:cicloId', {});
		});

angular.module('usuariosService', [ 'ngResource' ]).factory('Usuarios',
		function($resource) {
			return $resource('rest/usuarios/:usuarioId', {});
		});

angular.module('tanquesService', [ 'ngResource' ]).factory('Tanques',
		function($resource) {
			return $resource('rest/tanques/:tanqueId', {});
		});

angular.module('cicloTanquesService', [ 'ngResource' ]).factory('CicloTanques',
		function($resource) {
			return $resource('rest/cicloTanques/:cicloTanqueId', {});
		});