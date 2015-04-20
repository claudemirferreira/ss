angular.module('membersService', [ 'ngResource' ]).factory('Members',
		function($resource) {
			return $resource('rest/members/:memberId', {});
		});

angular.module('racaosService', [ 'ngResource' ]).factory('Racaos',
		function($resource) {
			return $resource('rest/racaos/:racaoId', {});
		});