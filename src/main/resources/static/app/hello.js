/**
 * 
 */

angular.module('hello', [ 'ngRoute' ])
		.config(
				function($routeProvider, $httpProvider) {

					$routeProvider.when('/', {
						templateUrl : 'home.html',
						controller : 'home'
					}).when('/login', {
						templateUrl : 'login.html',
						controller : 'navigation'
					}).otherwise('/');

					$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

				}).controller('home', function($scope, $http) {
			$http.get('/user').success(function(data) {
				$scope.greeting = data;
			})
		}).controller(
				'navigation',
				function($rootScope, $scope, $http, $location) {

					var authenticate = function(credentials, callback) {

						var headers = credentials ? {
							authorization : "Basic "
									+ btoa(credentials.email + ":"
											+ credentials.password)
						} : {};

						$http.get('/user', {
							headers : headers
						}).success(function(data) {
							if (data.name) {
								$rootScope.authenticated = true;
							} else {
								$rootScope.authenticated = false;
							}
							callback && callback();
						}).error(function() {
							$rootScope.authenticated = false;
							callback && callback();
						});
					}

					authenticate();
					$scope.credentials = {};

					$scope.login = function() {
						
						$http.post('login',$.param($scope.credentials),{
							headers : {
							"content-type" : "application/x-www-form-urlencoded"
							}
							}).success(function(data){
								
								authenticate($scope.credentials, function() {
									if ($rootScope.authenticated) {
										$location.path("/");
										$scope.error = false;
									} else {
										$location.path("/login");
										$scope.error = true;
									}
								});
								
								
							}).error(function(data) {
								$location.path("/login");
								$scope.error = true;
								$rootScope.authenticated = false;
								})
						
						
						
					
					};

				});