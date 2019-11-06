homepage.controller('HomeController', function($scope, $http, API){
	render();
	
	function render(){
		$http.get('products/san-pham-dang-khuyen-mai')
		.then(function (response) {
			$scope.listSPKM = response.data;
		});
		$http.get('products/san-pham-moi')
		.then(function (response) {
			$scope.listSPNEW = response.data;
			console.log(response.data);
		}).then(function(){
			$(".bg-loadding").css("display","none");
		});
	}
	
	$http.get(API + 'categories')
	.then(function (response) {
		$scope.categories = getNestedChildren(response.data, 0);
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
	
	$scope.banners = [
		{
			id: 1,
			image: "/images/banners/1.jpg",
			promotionId: "1"
		},
		{
			id: 2,
			image: "/images/banners/2.jpg",
			promotionId: "2"
		},
		{
			id:3,
			image: "https://image.freepik.com/free-photo/web-banner-with-eco-natural-products_107592-676.jpg",
			promotionId: "2"
		}
	];
	var items = JSON.parse(localStorage.getItem("items"));
	var countItems = 0;
	if(items != null){
		items.forEach(function(ele){
			return countItems += ele.quantity;
		})
	}
	$('#countItems').text(countItems);
	
	$scope.bestSeller = [];
	
	$http.get('/products/top-ban-chay/3')
	.then(function(res){
		res.data.map(obj => $scope.bestSeller.push(obj.product_id) );
	})
	
});