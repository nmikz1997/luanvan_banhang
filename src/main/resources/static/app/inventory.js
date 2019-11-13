app.controller('InventoryController', function($scope,$timeout, $http, API){
	
	$scope.chonSP = function(obj){
		render(obj.products.id);
		$scope.inventory = {
			product: {id: obj.products.id}
		}
	}
	
	function render(id){
		$http.get('/products/inventory/'+id).then(function(res){
			$scope.inventories = res.data.inventories;
			$scope.soluongton = res.data.quantity - res.data.sold;
		})
	}
	
	$scope.reset = function (){
		console.log("reset");
	}
	
	$scope.save = function () {
		$http.put('/products/inventory/'+$scope.inventory.product.id,$scope.inventory).then(function(res){
			render($scope.inventory.product.id);
			$scope.inventory.importDate = null;
			$scope.inventory.quantity = null;
			swal("Thành công", "", "success");
		})
	}

	$scope.confirmDelete = function (id) {
		
	}
	
	$http.get('/products').then(function (res) {
		$scope.products = res.data;
	}).then(function(){
	   $timeout( function(){
		   $('.selectpicker').selectpicker('refresh');
        },1);
	});
});