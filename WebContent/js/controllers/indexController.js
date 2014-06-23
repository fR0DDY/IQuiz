angular.module('iquiz').controller('IndexController', 
		['$scope',function ($scope) {
    
			var wsocket;
		    var serviceLocation = "ws://" + document.location.host + "/ChatServer/chat/"; 
			$scope.nickname  = "a";
			var message;
			var chatWindow;
			var room = '';
			
			$scope.connectToChatserver = function() {
				
				wsocket = new WebSocket(serviceLocation + room);
				wsocket.onmessage = $scope.onMessageReceived;
			};
			
			$scope.onMessageReceived= function(evt) {
				var msg = JSON.parse(evt.data); // native API
				var messageLine = $('<tr><td class="received">' + msg.received
						+ '</td><td class="user label label-info">' + msg.sender
						+ '</td><td class="message badge">' + msg.message
						+ '</td></tr>');
				
			};

			
			$scope.enterGame = function(){
				console.log("enter into game");
				console.log($scope.nickname);
				$scope.connectToChatserver();
			};
    
}]);