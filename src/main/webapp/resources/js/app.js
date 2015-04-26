var mainApp = angular.module("mainApp", []);
mainApp.controller('competitionCtrl', function($scope, $http) {
	var url = "http://localhost:8080/admin/manager/competition/list";
	$http.get(url).success(function(response) {
		$scope.competitions = response;
	});
});