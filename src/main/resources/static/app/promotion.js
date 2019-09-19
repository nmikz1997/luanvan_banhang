app.controller('PromotionController', function($scope,$timeout, $http, API){
	
	var url = API + 'promotions/';
	
	function render(){
		$http.get(url)
		.then(function (res) {
			console.log(res.data);
			$scope.data = res.data;
		});
	}
	
	$scope.reset = function (){
		$scope.obj = {};
		console.log("reset");
	}
	
	render();

	$scope.modal = function (state, id) {
		$scope.state = state;
		
		switch (state){
			case "add":
				console.log(id);
				$scope.reset();
				$scope.frmTitle = 'Thêm khuyến mãi';
				break;
			case "edit":
				$scope.frmTitle = 'Sửa thông tin khuyến mãi';
				console.log(url+id);
				$http.get(url + id)
					.then(function (res) {
						$scope.obj = res.data;
						$scope.obj.dayStart = new Date($scope.obj.dayStart);
						$scope.obj.dayEnd = new Date($scope.obj.dayEnd);
						$timeout(function(){
						     $('.selectpicker').selectpicker('refresh');
						},1)
					})
					.then(function(){
						var selectedValue = [];
						$scope.obj.products.map(product => selectedValue.push(product.id));
						$scope.obj.products = selectedValue;
					})
					.catch(function (err) {
						console.log(err);
						alert("Không tìm thấy");
					});
				break;
			default:
				break;
		}
		jQuery("#myModal").modal('show');
	}
	
	$scope.save = function (state) {
		var newArr = [];
		$scope.obj.products.map(value => newArr.push( {id:value} ) );
		$scope.obj.products = newArr;
		
		console.log(JSON.stringify($scope.obj));
		
		$http.post(url, $scope.obj )
			.then(function (res) {
		 		render();
		 		console.log(res);
		 	}).catch(function (err) {
		 		console.log(err);
		 		alert('error');
		 	});
		$("#myModal").modal('hide');
	}

	$scope.confirmDelete = function (id) {
		var isConfirmDelete = confirm('Bạn có chắc muốn xóa dòng dữ liệu này hay không');
		if (isConfirmDelete) {
			$http.delete(url + id)
			.then(function (res) {
				console.log(res);
			})
			.then(function (){
				render();
			})
			.catch(function(err) {
				console.log(err);
			});
		} else {
			return false;
		}
	}
	$http.get('/products').then(function (res) {
		$scope.products = res.data;
	});
});