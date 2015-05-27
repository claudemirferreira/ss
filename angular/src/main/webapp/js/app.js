angular.module(
		'kitchensink',
		[ 'membersService', 'racaosService', 'ciclosService',
				'usuariosService', 'tanquesService', 'cicloTanquesService',
				'ui.bootstrap.modal' ]).config(
		[ '$routeProvider', function($routeProvider) {
			$routeProvider.
			// if URL fragment is /home, then load the home partial, with the
			// MembersCtrl controller
			when('/member/pesquisa', {
				templateUrl : 'partials/member/pesquisa.html',
				controller : MembersCtrl
			// Add a default route
			}).when('/member/cadastro', {
				templateUrl : 'partials/member/cadastro.html',
				controller : MembersCtrl
			// Add a default route
			}).when('/racao/pesquisa', {
				templateUrl : 'partials/racao/pesquisa.html',
				controller : RacaosCtrl
			// Add a default route
			}).when('/ciclo/pesquisa', {
				templateUrl : 'partials/ciclo/pesquisa.html',
				controller : CiclosCtrl
			// Add a default route
			}).when('/usuario/pesquisa', {
				templateUrl : 'partials/usuario/pesquisa.html',
				controller : UsuariosCtrl
			// Add a default route
			}).when('/tanque/pesquisa', {
				templateUrl : 'partials/tanque/pesquisa.html',
				controller : TanquesCtrl
			// Add a default route
			}).when('/cicloTanque', {
				templateUrl : 'partials/cicloTanque/pesquisa.html',
				controller : CicloTanquesCtrl
			// Add a default route
			}).when('/login', {
				templateUrl : 'partials/login.html',
				controller : LoginCtrl
			// Add a default route
			}).otherwise({

				redirectTo : '/member/pesquisa'
			});
		} ]);