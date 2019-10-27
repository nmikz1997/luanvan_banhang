homepage.controller('ShoppingCartController', function($scope, $http,$timeout, API){
	$(".bg-loadding").css("display","none");
	$('#countItems').text(0);
	$scope.myForm = true;
	$scope.total = 0;
	$("#flip").click(function(){
	    $("#panel").slideToggle("slow");
	});

	var thisURL = window.location.pathname;
	var cart = JSON.parse(localStorage.getItem("items"));
	$scope.diaChi = JSON.parse(localStorage.getItem("infoShip"));
	
	if(cart != null) {
		refreshData();
	}
	
	
	$http.get('https://api.exchangerate-api.com/v4/latest/USD?fbclid=IwAR130H7vwYrcV1Aa1LAcs4LlpnrNEUqEaV2Zi5EPRNn1G1vCkKBW5RNvZmw').then(function(res){
		$scope.currency = res.data.rates.VND;
	});
	
	function getNgoaiTe(){
		
	}
	
	
	function refreshData(){
		resetCart();
		$http.post('/products/gio-hang',cart)
		.then(function(res){
			$scope.items = res.data;
			$scope.total = 0;
			res.data.forEach(function(ele){
				$scope.total = $scope.total + ele.priceNew*ele.soLuongMua;
			})
			
		})
		.then(function(){
			$http.get('orders/exchange').then(function(res){
				let tiGia = Number(res.data.bantienmat);
				$scope.totalPaypal = Math.round($scope.total/tiGia*100)/100;
			})
		})
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
		
		localStorage.setItem("infoShip", JSON.stringify($scope.diaChi));
		
		$scope.thanhtoan = true;
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
				address:$scope.diaChi,
				paymentType: {id: type}
			},
			orders:listOrders
		};
		
		$http.post('/orders/v2',request)
		.then(function(res){
			//console.log(res);
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
	
	$timeout( function()
	{
		if($scope.authen){
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
	            sandbox: 'ASlF80g16AWLwAnslzRi18WwaS81Uda-aV0ldWjJE02u8fPvchMdCMNKM7fj40j66gekc2tZSOsicGzF',
	            production: ''
	        },
	
	        
	        payment: function(data, actions) {
	        	
	            return actions.payment.create({
	                "transactions": [
	                   {
	                     "amount": {
	                       "total": $scope.totalPaypal,
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
	                           "name": "Tổng số tiền hóa đơn",
	                           "quantity": "1",
	                           "price": $scope.totalPaypal,
	                           "currency": "USD"
	                         },
	                       ],
	                       
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
		}
	},5);
	
	
	$http.get(API + 'provinces').then(function (response) {
		$scope.provinces = response.data;
	});
	
	function getDistricts(provinceid){
		$http.get(API + 'districts/findby?provinceid='+provinceid)
		.then(function (response) {
			$scope.districts = response.data;
			$timeout(function(){
			     $('.selectpicker').selectpicker('refresh');
			},1)
		});
	}
	
	function getWards(districtid){
		$http.get(API + 'wards/findbydistrict?districtid='+districtid)
		.then(function (response) {
			$scope.wards = response.data;
			$timeout(function(){
			     $('.selectpicker').selectpicker('refresh');
			},1)
		});
	}
	
	$scope.getId = function(state) {
		
		$(".dropdown-menu").on('click',function(e){
			this.classList.remove("show");
		});
		
		switch(state) {
		  case 'province':
			$scope.tinh = JSON.parse($scope.selectedProvince);
			getDistricts($scope.tinh.provinceid);
			$scope.diaChi = `${$scope.tinh.name}`;
		    break;
		  case 'district':
			$scope.quan = JSON.parse($scope.selectedDistrict);
			getWards($scope.quan.districtid);
			$scope.diaChi += `, ${$scope.quan.name}`;
		    break;
		  case 'ward':
			  $scope.phuong = JSON.parse($scope.selectedWard);
			  $scope.diaChi += `, ${$scope.phuong.name}`;
			  break;
		  default:
		    break;
		}
		
		$scope.disabled = true;
		
	}
	
	if($scope.diaChi){
		$scope.disabled = false;
	}
	
	$scope.thayDoiChiTiet = function(){
		$scope.diaChi = `${$scope.chiTiet}, ${$scope.phuong.name}, ${$scope.quan.name}, ${$scope.tinh.name}`;
		$scope.disabled = false;
		if(!$scope.chiTiet){
			$scope.disabled = true;
		}
	}
	
	
});