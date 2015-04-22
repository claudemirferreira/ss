function CiclosCtrl($scope, $http, Ciclos) {

    // Define a refresh function, that updates the data from the REST service
    $scope.refresh = function() {
        $scope.ciclos = Ciclos.query();
        $scope.dataInicio = new Date(1984, 4, 15);
        	
    };

    // Define a reset function, that clears the prototype newCiclo object, and
    // consequently, the form
    $scope.reset = function() {
        // clear input fields
        $scope.newCiclo = {};
    };

    // Define a register function, which adds the ciclo using the REST service,
    // and displays any error messages
    $scope.register = function() {
        $scope.successMessages = '';
        $scope.errorMessages = '';
        $scope.errors = {};

        Ciclos.save($scope.newCiclo, function(data) {

            // mark success on the registration form
            $scope.successMessages = [ 'Ciclo Registered' ];

            // Update the list of ciclos
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

    // Call the refresh() function, to populate the list of ciclos
    $scope.refresh();

    // Initialize newCiclo here to prevent Angular from sending a request
    // without a proper Content-Type.
    $scope.reset();

    // Set the default orderBy to the name property
    $scope.orderBy = 'periodo';
}