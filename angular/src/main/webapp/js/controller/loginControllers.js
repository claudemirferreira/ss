function UsuariosCtrl($scope, $http, Usuarios) {

    // Define a refresh function, that updates the data from the REST service
    $scope.refresh = function() {
        $scope.usuarios = Usuarios.query();
    };

    // Define a reset function, that clears the prototype newUsuario object, and
    // consequently, the form
    $scope.reset = function() {
        // clear input fields
        $scope.newUsuario = {};
    };

    // Define a register function, which adds the usuario using the REST service,
    // and displays any error messages
    $scope.register = function() {
        $scope.successMessages = '';
        $scope.errorMessages = '';
        $scope.errors = {};

        Usuarios.save($scope.newUsuario, function(data) {

            // mark success on the registration form
            $scope.successMessages = [ 'Usuario Cadastrado com sucesso' ];

            // Update the list of usuarios
            $scope.refresh();

            // Clear the form
            $scope.reset();
        }, function(result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Ocorreu um erro no servidor' ];
            }
            $scope.$apply();
        });

    };

    // Call the refresh() function, to populate the list of usuarios
    $scope.refresh();

    // Initialize newUsuario here to prevent Angular from sending a request
    // without a proper Content-Type.
    $scope.reset();

    // Set the default orderBy to the name property
    $scope.orderBy = 'nome';
}