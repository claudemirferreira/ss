angular.module('kitchensink', ['membersService']).config(
        [ '$routeProvider', function($routeProvider) {
            $routeProvider.
            // if URL fragment is /home, then load the home partial, with the
            // MembersCtrl controller
            when('/home', {
                templateUrl : 'partials/home.html',
                controller : MembersCtrl
            // Add a default route
            }).otherwise({
                redirectTo : '/home'
            });
        } ]);