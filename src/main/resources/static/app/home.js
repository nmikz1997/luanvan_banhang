homepage.controller('HomeController', function($scope, $http, API){
	render();
	
	function render(){
		$http.get('products/san-pham-dang-khuyen-mai')
		.then(function (response) {
			$scope.listSPKM = response.data;
		});
	}
	
	$http.get(API + 'categories')
	.then(function (response) {
		console.log(response.data);
		$scope.categories = getNestedChildren(response.data, 0);
	});

	$http.get(API + 'products').then(function (response) { 
		$scope.products = response.data; 
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
			image: "https://picsum.photos/id/200/598/366",
			promotionId: "1"
		},
		{
			id: 2,
			image: "https://picsum.photos/id/128/598/366",
			promotionId: "2"
		},
		{
			id: 3,
			image: "https://picsum.photos/id/384/598/366",
			promotionId: "3"
		}
	];
});