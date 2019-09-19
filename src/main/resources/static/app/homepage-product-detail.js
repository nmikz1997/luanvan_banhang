homepage.controller('ProductDetailController', function($scope,$timeout, $http, API){
	var thisURL = window.location.pathname;
	
	$http.get(API + 'products/'+thisURL).then(function(res){
		$scope.product = res.data;
	}).then(function(){
		document.getElementById('show').style.display= "block";
	});
	
	$scope.changeAvatar = function(avatar){
		$scope.product.avatar = avatar;
	}
	
	var items = JSON.parse(localStorage.getItem("items"));
	console.log(items);
	$scope.addToCart = function(id){
		var check = false;
		$scope.item.id = id;
		if(items === null ){
			items = [];
			check = true;
			items.push({id: $scope.item.id, quantity: $scope.item.quantity});
		}else{
			items.forEach(function(item){
				if(item.id == id){
					item.quantity += $scope.item.quantity;
					check = true;
				}
			});
		}
		if(check == false) items.push({id: $scope.item.id, quantity: $scope.item.quantity});
		localStorage.setItem("items", JSON.stringify(items));
	}
	
	
});