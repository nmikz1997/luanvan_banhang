homepage.controller('ProductDetailController', function($scope,$timeout, $http, API){
	var thisURL = window.location.pathname;
	var productId = thisURL.substring(thisURL.lastIndexOf("/")+1);
	$scope.item = {
		id: null,
		quantity: 1
	}
	
	function countDownTimer(timeBetween){
		let day = timeBetween.getDate();
		let hour = timeBetween.getHours();
		let minute = timeBetween.getMinutes();
		let second = timeBetween.getSeconds();
		document.getElementById("timeCountDown").innerHTML = `${day} ngày ${hour}:${minute}:${second}`
	}
	
	$http.get(API + 'products'+thisURL).then(function(res){
		$scope.product = res.data;
		$scope.anhChinh = res.data.avatar;
		if($scope.product.maxPromotion.dayEnd){
			setInterval(function(){ 
				let timeBetween = new Date (new Date($scope.product.maxPromotion.dayEnd) - new Date() );
				countDownTimer(timeBetween);
			}, 1000);
		}	
	}).then(function(){
		document.getElementById('show').style.display= "block";
		$(".bg-loadding").css("display","none");
		jQuery(function () {
		 	jQuery("#rateYoProduct").rateYo({
		 		starWidth: "20px",
		    	rating: $scope.product.avgStar,
		    	fullStar: true,
		    	readOnly: true
		  	});
		 
		});
		$timeout(function(){
			$("#zoom_01").elevateZoom({scrollZoom : true});
		},1)
	});
	
	$scope.changeAvatar = function(avatar){
		$scope.product.avatar = avatar;
		$timeout(function(){
			$('.zoomContainer div').css("background-image", `url(/picture/${avatar})`);
		},1)
	}
	
	var items = JSON.parse(localStorage.getItem("items"));
	var productSeen = JSON.parse(localStorage.getItem("seen"));
	
	//console.log(productSeen);
	
	if(productSeen){
		
		$http.post('/products/san-pham-da-xem',productSeen)
		.then(function(res){
			$scope.sanPhamDaXem = res.data;
			//console.log($scope.sanPhamDaXem);
		}).then(function(){
			let check = 0;
			for(let i = 0; i < productSeen.length; i++){
				if(productSeen[i].id == productId){
					break;
				}else{
					if(i == productSeen.length - 1 && productSeen.length < 3){
						productSeen.push({id:productId});
						localStorage.setItem("seen", JSON.stringify(productSeen));
					}
					else if(i == productSeen.length - 1)
					{
						productSeen.unshift({id:productId});
						productSeen.pop();
						localStorage.setItem("seen", JSON.stringify(productSeen));
					}
				}
			};
		});
	}else{
		let productSeen = [];
		productSeen.push({id:productId});
		localStorage.setItem("seen", JSON.stringify(productSeen));
	}
	
	resetCart();
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
		resetCart();
		swal("Đã thêm sản phẩm vào giỏ", "", "success");
	}
	
	
	function resetCart(){
		var countItems = 0;
		if(items != null){
			items.forEach(function(ele){
				return countItems += ele.quantity;
			})
		}
		$('#countItems').text(countItems);
	}
	
	$scope.question = function(){
		
		let question = {
			topic: $scope.topic,
			product: {id: productId}
		}
		
		$http.post('/questions',question)
		.then(function(res){
			//console.log(res);
			swal("Đã gửi câu hỏi cho chủ Shop", "", "success");
		}).catch(function(err){
			//console.log(err)
		})
		
	}
	
	$http.get('/questions/product/'+productId).then(function(res){
		//console.log(res);
		$scope.questions = res.data;
	})
	
	$http.get('/reviews/product/'+productId).then(function(res){
		//console.log(res);
		$scope.reviews = res.data;
	})
	
	$http.get('/products/hinh-cua-san-pham-360/'+productId)
		.then(function(res){
			$scope.listImg = res.data;
			if(res.data.length > 0) $scope.coAnh360 = true;
		});
	
	$scope.view360 = function(){
		let imgsrc = [];
		$scope.listImg.forEach(function(ele){
			imgsrc.push('/picture/'+ele.path);
		})
		jQuery('#contentProduct').append('<div class="ajax360">'+
	            '<div class="close360">x</div>'+
	            '<div class="boxpicture360">'+
	                '<h2>Hình 360 độ</h2>'+
	                '<div class="content360">'+
	                    '<div id="mySpriteSpin"></div>'+
	                '</div>'+
	            '</div>'+
	        '</div>');
		jQuery("#mySpriteSpin").spritespin({
				source: imgsrc,
				width: 350,
				height: 600,
		});
	}
	
	jQuery(document).on('click', '.close360' ,function(){
		jQuery('.ajax360').remove()
	})
	
});