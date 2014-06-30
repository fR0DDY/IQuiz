angular.module('iquiz').controller('IndexController', 
		['$scope',function ($scope) {
			console.log("Staring this controller")
					var wsocket;
					var serviceLocation = "ws://" + document.location.host
							+ "/IQuiz/iquiz";
					$scope.nickname = "";
					$scope.data="";
					$scope.connectToChatserver = function() {
						wsocket = new WebSocket(serviceLocation + "/" + $scope.nickname);
						wsocket.onmessage = $scope.onMessageReceived;
					};

					$scope.onMessageReceived = function(evt) {
						$scope.$apply(function(){
							$scope.msg = evt.data;
						});

					};

					$scope.enterGame = function() {
						console.log("enter into game");
						console.log($scope.nickname);
						$scope.connectToChatserver();
					};

				} ]);