homepage.controller('SearchController', function($scope,$http,$location,API){
	//$scope.data = /*[[${result}]]*/;
	
	$http.get('/materials').then(function(res){
		$scope.materials = res.data;
	});
	$http.get('/origins').then(function(res){
		$scope.origins = res.data;
	});
	$http.get('/producers').then(function(res){
		$scope.producers = res.data;
	});
	
	$scope.bestSeller = [];
	
	$http.get('/products/top-ban-chay/3')
	.then(function(res){
		//console.log(res);
		res.data.map(obj => $scope.bestSeller.push(obj.product_id) );
	})
	
	var searchParams = new URLSearchParams(window.location.search);

	$("#name").val(searchParams.get("name"));
	
	//$scope.desc = true;
	
	$scope.redirect = function(param, value){
		if(param != "page"){
			searchParams.delete("page");
		}
		if(searchParams.has(param)){
			searchParams.set(param, value);
		}else{
			searchParams.append(param, value);
		}
		
		window.history.pushState(null, 'Page Title', "tim-kiem?"+searchParams);
		timKiemNangCao();
		//window.location.href = "http://localhost:8080/tim-kiem?"+searchParams;
	}
	
	timKiemNangCao();
	
	function timKiemNangCao(){
		searchParams = new URLSearchParams(window.location.search);
		$http.get('/query?'+searchParams).then(function(res){
			$scope.data = res.data;
			$('.pagination').twbsPagination('destroy');
		}).then(function(){
			console.log($scope.data);
			if($scope.data.content.length){
				$('#pagination-demo').twbsPagination({
			        totalPages: $scope.data.totalPages,
			        startPage: $scope.data.number+1,
			        visiblePages: 3,
			        initiateStartPageClick: false,
			        first: '',
					prev: '<',
					next: '>',
					last: '',
			        onPageClick: function (event, page) {
			        	$scope.redirect("page", page);
			        }
			    });
			}
			$scope.filterArray = [];
			
			
			if( searchParams.has("material") ){
				 pushFilterArray ($scope.materials, "material");
			};
			if( searchParams.has("origin") ){
				pushFilterArray ($scope.origins, "origin");
			};
			if( searchParams.has("producer") ){
				 pushFilterArray ($scope.producers, "producer");
			};
			
			if(searchParams.has("ratting")){
				let value = {
					param: "ratting",
					name: searchParams.get("ratting") + " sao"
				}
				$scope.filterArray.push( value );
			}
		}).catch(function(err){
			console.log("Không tìm thấy");
		})
		$("html, body").animate({ scrollTop:0 }, "slow");
	}
	
	function pushFilterArray (array, param){
		let value = array.find(function(ele){
			return ele.id == searchParams.get(param);
		})
		value.param = param;
		$scope.filterArray.push( value );
	}
	
	$scope.closeParam = function (param){
		searchParams.delete(param);
		window.history.pushState(null, 'Page Title', "tim-kiem?"+searchParams);
		timKiemNangCao();
	}
	
	window.onpopstate = function(event) {
		timKiemNangCao();
	};
	
	$scope.hidden = true;
	$scope.hiddenOrigin = true;
	/*$('#pagination-demo').twbsPagination({
		totalPages: 5,
		startPage: 1,
		visiblePages: 2,
		initiateStartPageClick: true,
		href: true,
		hrefVariable: '{{number}}',
		first: 'First',
		prev: 'Previous',
		next: 'Next',
		last: 'Last',
		loop: false,
		onPageClick: function (event, page) {
			$('.page-active').removeClass('page-active');
		 	$('#page'+page).addClass('page-active');
		},
		paginationClass: 'pagination',
		nextClass: 'next',
		prevClass: 'prev',
		lastClass: 'last',
		firstClass: 'first',
		pageClass: 'page',
		activeClass: 'active',
		disabledClass: 'disabled'
		});*/
		$(".bg-loadding").css("display","none");
		document.getElementById('show').style.display= "block";
		var items = JSON.parse(localStorage.getItem("items"));
		var countItems = 0;
		if(items != null){
			items.forEach(function(ele){
				return countItems += ele.quantity;
			})
		}
		$('#countItems').text(countItems);
		
		function getNestedChildren(arr, parent) {
			var out = [];
			for(var i in arr) {
				if(arr[i].parentId == parent) {
					var categories = getNestedChildren(arr, arr[i].id);
					if(categories.length) {
						arr[i].categories = categories;
					}
					out.push(arr[i]);
				}
			}
			return out;
		}
		
//		$http.get(API + 'categories')
//		.then(function (response) {
//			$scope.categories = getNestedChildren(response.data, 0);
//			
//		}).then(function(response){
//			console.log($scope.categories);
//		});
		
		$http.get(API + 'categories')
		.then(function (response) {
			$scope.categories = getNestedChildren(response.data, 0);
			document.getElementsByClassName("menu-mobi")[0].style.display = "block";
		});

		function getNestedChildren(arr, parent) {
			var out = [];
			for(var i in arr) {
				if(arr[i].parentId == parent) {
					var categories = getNestedChildren(arr, arr[i].id);
					if(categories.length) {
						arr[i].categories = categories;
					}
					out.push(arr[i]);
				}
			}
			return out;
		}
});
