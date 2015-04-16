/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Define any routes for the app
// Note that this app is a single page app, and each partial is routed to using the URL fragment. For example, to select the 'home' route, the URL is http://localhost:8080/jboss-as-kitchensink-angularjs/#/home
angular.module('kitchensink', [ 'membersService', 'ngRoute', 'ngAnimate' ])
		.controller('MainController',
				function($scope, $route, $routeParams, $location) {
					$scope.$route = $route;
					$scope.$location = $location;
					$scope.$routeParams = $routeParams;
				})

		.controller('BookController', function($scope, $routeParams) {
			$scope.name = "BookController";
			$scope.params = $routeParams;
		})

		.controller('ChapterController', function($scope, $routeParams) {
			$scope.name = "ChapterController";
			$scope.params = $routeParams;
		})

		.config([ '$routeProvider', function($routeProvider) {
			$routeProvider.
			// if URL fragment is /home, then load the home partial, with the
			// MembersCtrl controller
			when('/home', {
				templateUrl : 'partials/home.html',
				controller : MembersCtrl
			// Add a default route
			}).when('/about', {
				templateUrl : 'partials/page-about.html',
				controller : 'aboutController'
			}).when('/contact', {
				templateUrl : 'partials/page-contact.html',
				controller : 'contactController'
			}).otherwise({
				redirectTo : '/home'
			});
		} ]);

animateApp.controller('mainController', function($scope) {
	$scope.pageClass = 'page-home';
});

animateApp.controller('aboutController', function($scope) {
	$scope.pageClass = 'page-about';
});

animateApp.controller('contactController', function($scope) {
	$scope.pageClass = 'page-contact';
});
