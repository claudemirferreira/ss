function CicloTanquesCtrl($scope, $http, CicloTanques) {

	// Define a refresh function, that updates the data from the REST service
	$scope.refresh = function() {
		$scope.cicloTanques = CicloTanques.query();
	};

	// Define a reset function, that clears the prototype newCicloTanque object,
	// and
	// consequently, the form
	$scope.reset = function() {
		// clear input fields
		$scope.newCicloTanque = {};
	};

	// Define a register function, which adds the cicloTanque using the REST
	// service,
	// and displays any error messages
	$scope.register = function() {
		$scope.successMessages = '';
		$scope.errorMessages = '';
		$scope.errors = {};

		CicloTanques.save($scope.newCicloTanque, function(data) {

			// mark success on the registration form
			$scope.successMessages = [ 'CicloTanque Registered' ];

			// Update the list of cicloTanques
			$scope.refresh();

			// Clear the form
			$scope.reset();
		}, function(result) {
			if ((result.status == 409) || (result.status == 400)) {
				$scope.errors = result.data;
			} else {
				$scope.errorMessages = [ 'Unknown  server error' ];
			}
			$scope.$apply();
		});

	};

	// Call the refresh() function, to populate the list of cicloTanquesf
	$scope.refresh();

	// Initialize newCicloTanque here to prevent Angular from sending a request
	// without a proper Content-Type.
	$scope.reset();

	// Set the default orderBy to the name property
	$scope.orderBy = 'ciclo';
}