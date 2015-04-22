angular.module('kitchensink', ['membersService','racaosService','ciclosService']).config(
        [ '$routeProvider', function($routeProvider) {
            $routeProvider.
            // if URL fragment is /home, then load the home partial, with the
            // MembersCtrl controller
            when('/home', {
                templateUrl : 'partials/home.html',
                controller : MembersCtrl
            // Add a default route
            }). when('/racao', {
                templateUrl : 'partials/racao.html',
                controller : RacaosCtrl
            // Add a default route
            }). when('/ciclo', {
                templateUrl : 'partials/ciclo.html',
                controller : CiclosCtrl
            // Add a default route
            }).otherwise({
                redirectTo : '/home'
            });
        } ]);