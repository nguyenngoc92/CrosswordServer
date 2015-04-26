<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page Manager</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/app.js">
	
</script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>

<script>
	var mainApp = angular.module("mainApp", []);
	mainApp.controller('competitionCtrl', function($scope, $http) {
		var url = "http://localhost:8080/admin/manager/competition/list";
		$http.get(url).success(function(response) {
			$scope.competitions = response;
		});
	});


	mainApp.controller('competitionFormCtrl', function($scope,$rootScope, competitionService){
					
		  // Clears the form
		  $scope.clearForm = function(){

			  $scope.competition = null;
			  $scope.competitionForm.$setPristine();
			// Broadcast the event to also clear the grid selection.
			    $rootScope.$broadcast('clear');
			  };

			// Calls the rest method to save a person.
			$scope.saveCompetition = function(){

				competitionService.
				};


		});
</script>
</head>
<body>
	<

	<div class="form" ng-controller="competitionFormCtrl">
		<!-- Verify person, if there is no id present, that we are Adding a Person -->
		<div ng-if="competition.idCompetition == null">
			<button id="">Add Competition</button>
		</div>
		<!-- Otherwise it's an Edit -->
		<div ng-if="competition.idCompetition != null">
			<h3>Edit Competition</h3>
		</div>
		
		<div>
			<form name="competitionForm" ng-submit="saveCompetition()" novalidate>
			
			 <!-- Display an error if the input is invalid and is dirty (only when someone changes the value) -->
			<div class="form-group" ng-class="{'has-error' : competitionForm.name.$invalid && competitionForm.name.$dirty}">
				<label for="name">Name:</label>
				<!-- Display a check when the field is valid and was modified -->
				<span ng-class="{'glyphicon glyphicon-ok' : competitionForm.name.$valid && competitionForm.name.$dirty}"></span>
				<input id="name" name="name" type="text" class="form-control" maxlength="150"
					ng-model="competition.name" required ng-minlength="2" ng-maxlength="150"/>
				<!-- Validation messages to be displayed on required, minlength and maxlength -->
                <p class="help-block" ng-show="personForm.name.$error.required">Add Name.</p>
                <p class="help-block" ng-show="personForm.name.$error.minlength">Name must be at least 2 characters long.</p>
                <p class="help-block" ng-show="personForm.name.$error.maxlength">Name cannot be longer than 150 characters.</p>
				
			</div>
			
			<div class="form-group" ng-class="{'has-error' : competitionForm.note.$invalid && competitionForm.note.$dirty}" >
			
				<label for="note">Description:</label>
				<span ng-class="{'glyphicon glyphicon-ok' : competitionForm.note.$valid && competitionForm.note.$dirty}"></span>
				<textarea id="note" name="note" type="text" class="form-control" maxlength="150"
					ng-model="competition.note" required ng-minlength="2" ng-maxlength="150"></textarea>
				 <p class="help-block" ng-show="personForm.note.$error.required">Add discription.</p>
				
			</div>
			
			<div class="form-group" ng-class="{'has-error' : competitionForm.gift.$invalid && competitionForm.gift.$dirty}" >
			
				<label for="note">Gift:</label>
				<span ng-class="{'glyphicon glyphicon-ok' : competitionForm.gift.$valid && competitionForm.gift.$dirty}"></span>
				<textarea id="gift" name="gift" type="text" class="form-control" maxlength="150"
					ng-model="competition.gift" required ng-minlength="2" ng-maxlength="150"></textarea>
				 <p class="help-block" ng-show="personForm.gift.$error.required">Add discription.</p>
				
			</div>
			
			<div class="form-group" ng-class="{'has-error' : competitionForm.begin.$invalid && competitionForm.begin.$dirty}" >
				<label for="begin">Start Date:</label>
				<span ng-class="{'glyphicon glyphicon-ok' : competitionForm.begin.$valid && competitionForm.begin.$dirty}"></span>
				<input id="begin" name="begin" type="date" ng-model="competition.begin" required/>
				 <p class="help-block" ng-show="personForm.begin.$error.required">Start date is required</p>
			</div>
			
			
			<div class="form-group" ng-class="{'has-error' : competitionForm.end.$invalid && competitionForm.end.$dirty}" >
			
				<label for="end">End Date:</label>
				<span ng-class="{'glyphicon glyphicon-ok' : competitionForm.end.$valid && competitionForm.end.$dirty}"></span>
				<input id="end" name="end" type="date" ng-model="competition.end" required/>
				 <p class="help-block" ng-show="personForm.end.$error.required">End date is required</p>
			
			</div>
			
			 <!-- Form buttons. The 'Save' button is only enabled when the form is valid. -->
            <div class="buttons">
                <button type="button" class="btn btn-primary" ng-click="clearForm()">Clear</button>
                <button type="submit" class="btn btn-primary" ng-disabled="competitionForm.$invalid">Save</button>
            </div>
			
			</form>
		
		</div>

	</div>
	<h3>Competition List</h3>
	<div ng-app="mainApp" ng-controller="competitionCtrl">

		<table class="tg">
			<tr>
				<th width="20">ID</th>
				<th width="120">Name</th>
				<th width="300">Description</th>
				<th width="30">Start</th>
				<th width="30">End</th>
				<th width="60">Gift</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<tr ng-repeat="competition in competitions">
				<td>{{competition.idCompetition}}</td>
				<td>{{competition.name}}</td>
				<td>{{competition.note}}</td>
				<td>{{competition.begin | date:"dd/MM/yyyy 'at' h:mma"}}</td>
				<td>{{competition.end | date:"dd/MM/yyyy 'at' h:mma"}}</td>
				<td>{{competition.gift}}</td>

				<td><a
					href="<c:url value='/admin/competition/edit/{{competition.id}}' />">Edit</a></td>
				<td><a
					href="<c:url value='/admin/competition/remove/{{competition.id}}' />">Delete</a></td>

			</tr>
		</table>
	</div>



</body>
</html>