function TanquesCtrl($scope, $http, Tanques) {

	// Define a refresh function, that updates the data from the REST service
	$scope.refresh = function() {
		$scope.tanques = Tanques.query();
	};

	// Define a reset function, that clears the prototype newTanque object, and
	// consequently, the form
	$scope.reset = function() {
		// clear input fields
		$scope.newTanque = {};
	};

	// Define a register function, which adds the tanque using the REST service,
	// and displays any error messages
	$scope.register = function() {
		$scope.successMessages = '';
		$scope.errorMessages = '';
		$scope.errors = {};

		Tanques.save($scope.newTanque, function(data) {

			// mark success on the registration form
			$scope.successMessages = [ 'Tanque Registered' ];

			// Update the list of tanques
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

	// Call the refresh() function, to populate the list of tanquesf
	$scope.refresh();

	// Initialize newTanque here to prevent Angular from sending a request
	// without a proper Content-Type.
	$scope.reset();

	// Set the default orderBy to the name property
	$scope.orderBy = 'nome';
}