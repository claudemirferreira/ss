function MembersCtrl($scope, $http, $resource, Members) {
	
    var resource = $resource('/angular/rest/members/:id');
    
 // Define a refresh function, that updates the data from the REST service
    $scope.remove = function(id) {
    	resource.delete({id:id});
    	$scope.members= Members.query();
    	// Update the list of members
        $scope.refresh();  
        $scope.reset();
    };

    // Define a refresh function, that updates the data from the REST service
    $scope.refresh = function() {
        $scope.members = Members.query();
    };

    // Define a clearMessages function that resets the values of the error and
    // success messages.
    $scope.clearMessages = function () {
        $scope.successMessages = '';
        $scope.errorMessages = '';
        $scope.errors = {};
    };

    // Define a reset function, that clears the prototype newMember object, and
    // consequently, the form
    $scope.reset = function() {
        // Sets the form to it's pristine state
        if($scope.regForm) {
            $scope.regForm.$setPristine();
        }
        // Clear input fields. If $scope.newMember was set to an empty object {},
        // then invalid form values would not be reset.
        // By specifying all properties, input fields with invalid values are also reset.
        $scope.newMember = {name: "", email: "", phoneNumber: ""};

        // clear messages
        $scope.clearMessages();
    };
    
    $scope.editar = function(member) {
        // Sets the form to it's pristine state
        if($scope.regForm) {
            $scope.regForm.$setPristine();
        }
        // Clear input fields. If $scope.newMember was set to an empty object {},
        // then invalid form values would not be reset.
        // By specifying all properties, input fields with invalid values are also reset.
        $scope.newMember = member;

        // clear messages
        $scope.clearMessages();
    };

    // Define a register function, which adds the member using the REST service,
    // and displays any error messages
    $scope.register = function() {
        $scope.clearMessages();

        Members.save($scope.newMember, function(data) {

            // Update the list of members
            $scope.refresh();

            // Clear the form
            $scope.reset();

            // mark success on the registration form
            $scope.successMessages = [ 'Member Registered' ];
        }, function(result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
        });

    };
    
    $scope.salvar = function() {
        $scope.clearMessages();
        alert('oiiii');

        Members.put($scope.newMember, function(data) {

            // Update the list of members
            $scope.refresh();

            // Clear the form
            $scope.reset();

            // mark success on the registration form
            $scope.successMessages = [ 'Member Registered' ];
        }, function(result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
        });

    };

    // Call the refresh() function, to populate the list of members
    $scope.refresh();

    // Initialize newMember here to prevent Angular from sending a request
    // without a proper Content-Type.
    $scope.reset();

    // Set the default orderBy to the name property
    $scope.orderBy = 'name';
    
}