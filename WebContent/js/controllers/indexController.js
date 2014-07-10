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
					$scope.index = -1;
					$scope.questions;
					$scope.opponentName = "...";
					$scope.msg = "";

					$scope.onMessageReceived = function(evt) {
						$scope.$apply(function(){
							$scope.msg = JSON.parse(evt.data);
							console.log($scope.msg);
							console.log($scope.msg.messageCode);
							if($scope.msg.messageCode === 'PlayerFound'){
								playGame();
							}
							else if($scope.msg.messageCode === 'StartGame'){
								startGame();
							}
						});
					};
					
					startGame = function() {
						console.log("staring game");
						$scope.index = 1;
					};

					$scope.enterGame = function() {
						console.log("enter into game");
						console.log($scope.nickname);
						$scope.connectToChatserver();
					};
					
					playGame = function() {
						console.log("Caliing playgame functions");
						$scope.opponentName = $scope.msg.message;
						$scope.questions = $scope.msg.questions;
						var msg = '{"message":"'+ $scope.nickname+'", "messageCode": "PlayerReady","matchId":"'+$scope.msg.matchId+'"}';
						console.log("PlayGame() ->"+msg)
						wsocket.send(msg);
					};

				} ]);