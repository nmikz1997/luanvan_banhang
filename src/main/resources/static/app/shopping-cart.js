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
		paid(1);
	}
	function paid(type){
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
				paymentType: {id: type},
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
		
		let request = {
				orderGroup:{
					address:"116A, Mạc thiên tích",
					paymentType: {id: 1}
				},
				orders:listOrders
			};
		
		$http.post('/orders/v2',request)
		.then(function(res){
			console.log(res);
//			if(res.data.Success){
//				alert("Đặt hàng thành công");
//				localStorage.removeItem("items");
//				$scope.items = null;
//				$scope.total = 0;
//				resetCart();
//			}else{
//				alert("Số lượng không đủ đáp ứng");
//				refreshData();
//			}
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
	
	
	
	//paypal
	
	paypal.Button.render({
        env: 'sandbox',
        style: {
            color:  'gold',
            shape:  'rect',
            label:  'paypal',
            height: 44,
            tagline : false,
            size:'responsive',
        },

        funding: {
            allowed: [
            paypal.FUNDING.CARD,
            paypal.FUNDING.CREDIT
            ],
            disallowed: []
        },

        client: {
            sandbox: 'AcY_RqiSajU7n4gcqvWS4wtQNRU2vYG1WZwi7rIpcf1U1-PXyPogoQ6rTBUPuNE6qfLOAkX4iRrlaQH8',
            production: ''
        },

        
        payment: function(data, actions) {
            return actions.payment.create({
                "transactions": [
                   {
                     "amount": {
                       "total": "1.00",
                       "currency": "USD",
                     },
                     "description": "The payment transaction description.",
                     "payment_options": {
                       "allowed_payment_method": "INSTANT_FUNDING_SOURCE"
                     },
                     "soft_descriptor": "ECHI5786786",
                     "item_list": {
                       "items": [
                         {
                           "name": "hat",
                           "quantity": "1",
                           "price": "1.00",
                           "currency": "USD"
                         },
                       ],
                       "shipping_address": {
                         "recipient_name": "Brian Robinson",
                         "line1": "4th Floor",
                         "line2": "Unit #34",
                         "city": "San Jose",
                         "country_code": "US",
                         "postal_code": "95131",
                         "phone": "011862212345678",
                         "state": "CA"
                       }
                     }
                   }
                 ]
            });
        },


        onAuthorize: function (data, actions) {
            return actions.payment.execute()
            .then(function () {
            	paid(2);
            });
        }
    }, '#paypal-button');
	
	
	
});