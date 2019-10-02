homepage.controller('ShoppingCartController', function($scope, $http, API){
	var thisURL = window.location.pathname;
	var cart = JSON.parse(localStorage.getItem("items"));
	
	if(cart != null) refreshData();
	
	function refreshData(){
		resetCart();
		$http.post('/products/gio-hang',cart).then(function(res){
			$scope.items = res.data;
			$scope.total = 0;
			res.data.forEach(function(ele){
				$scope.total = $scope.total + ele.priceNew*ele.soLuongMua;
			})
			
		});
	}
	
	$scope.xoa = function(id){
		console.log(id);
	}	
	
	$scope.tangMot = function(id){
		cart.forEach(function(pro){
			if(pro.id == id){
				pro.quantity += 1;
			}
		})
		localStorage.setItem("items", JSON.stringify(cart));
		refreshData();
	}
	
	$scope.giamMot = function(id){
		for (var i =0; i< cart.length; i++) {
		    if (cart[i].id == id) {
		    	cart[i].quantity -= 1;
		    	if(cart[i].quantity == 0){
		    		cart.splice(i, 1);
		    	}
		    }
		}
		localStorage.setItem("items", JSON.stringify(cart));
		refreshData();
	}
	$scope.thanhtoan = false;
	$scope.thanhToan = function(){
		//luu don hang
		$scope.thanhtoan = true;
		//console.log($scope.items);
		var listOrders = [];
		var grouped = groupBy($scope.items, "store");
		for (var property in grouped) {
			//tạo 1 order
			var order = {
				address:"116A, Mạc thiên tích",
				customer: {id: 1},
				paymentType: {id: 1},
				store: {id: Number(property)},
				ordersDetailDTO: []
			}

			grouped[property].forEach(function(ele){
				//tạo order detail
				var orderDetails = { product:{id: null}, quantity: null, price:{id: null},promotion:{id: null} };
				orderDetails.product.id = ele.id;
				orderDetails.quantity = ele.soLuongMua;
				orderDetails.price.id = ele.priceApply.id;
				orderDetails.promotion.id = ele.maxPromotion.id;
				order.ordersDetailDTO.push(orderDetails);
			});
			listOrders.push(order);
		}
		console.log(listOrders);
		$http.post('/orders',listOrders)
		.then(function(res){
			if(res.data.Success){
				alert("Đặt hàng thành công");
				localStorage.removeItem("items");
				$scope.items = null;
				$scope.total = 0;
				resetCart();
			}else{
				alert("Số lượng không đủ đáp ứng");
				refreshData();
			}
		})
	}
	
	function resetCart(){
		var countItems = 0;
		if(cart != null){
			cart.forEach(function(ele){
				return countItems += ele.quantity;
			})
		}
		$('#countItems').text(countItems);
	}

	//group order theo store
	function groupBy(objectArray, property) {
		return objectArray.reduce(function (acc, obj) {
			var key = obj[property].id;
			if (!acc[key]) {
				acc[key] = [];
			}
			acc[key].push(obj);
			return acc;
		}, {});
	}
});