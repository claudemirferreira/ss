function RacaosCtrl($scope, $http, Racaos) {
	
	var resource = $resource('/angular/rest/racaos/:id');
	
	// Define a refresh function, that updates the data from the REST service
    $scope.remove = function(id) {
    	resource.delete({id: id});
    	$scope.refresh();
    };

    // Define a refresh function, that updates the data from the REST service
    $scope.refresh = function() {
        $scope.racaos = Racaos.query();
    };

    // Define a reset function, that clears the prototype newRacao object, and
    // consequently, the form
    $scope.reset = function() {
        // clear input fields
        $scope.newRacao = {};
    };

    // Define a register function, which adds the racao using the REST service,
    // and displays any error messages
    $scope.register = function() {
        $scope.successMessages = '';
        $scope.errorMessages = '';
        $scope.errors = {};

        Racaos.save($scope.newRacao, function(data) {

            // mark success on the registration form
            $scope.successMessages = [ 'Racao Registered' ];

            // Update the list of racaos
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

    // Call the refresh() function, to populate the list of racaos
    $scope.refresh();

    // Initialize newRacao here to prevent Angular from sending a request
    // without a proper Content-Type.
    $scope.reset();

    // Set the default orderBy to the name property
    $scope.orderBy = 'nome';
}