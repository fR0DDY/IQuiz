//Setting up route
angular.module('iquiz')

.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'index.html'
	}).otherwise({
		redirectTo : '/'
	});
} ]);