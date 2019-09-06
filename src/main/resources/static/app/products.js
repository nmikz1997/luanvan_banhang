app.controller('ProductsController', function($scope, $http, API){
	
	var APIResource = API + 'products/';
	
	var method = null;
	
	function AjaxRenderData(){
		$http.get(APIResource)
		.then(function (res) {
			$scope.products = res.data;
		});
	}
	
	AjaxRenderData();
	
	$scope.arrCategory	= [
		{"id": 1, "name": "option 1"},
		{"id": 2, "name": "option 2"},
		{"id": 3, "name": "option 3"}
	];
	$scope.arrOrigin	= [
		{"id": 1, "name": "option 1"},
		{"id": 2, "name": "option 2"},
		{"id": 3, "name": "option 3"}
	];
	$scope.arrMaterial	= [
		{"id": 1, "name": "option 1"},
		{"id": 2, "name": "option 2"},
		{"id": 3, "name": "option 3"}
	];

	$scope.modal = function (state, id) {
		$scope.state = state;
		
		switch (state){
			case "add":
				method = "POST";
				$scope.frmTitle = 'Thêm sản phẩm';
				//$scope.product = null;
				break;
			case "edit":
				method = "PUT";
				$scope.frmTitle = 'Sửa thông tin sản phẩm';
				$http.get(APIResource + id)
				.then(function (res) {
					$scope.product = res.data.product;
				})
				.catch(function (err) {
					console.log(err);
					alert("Không tìm thấy");
				});

				break;
			case "images":
				$scope.frmTitle = 'Cập nhật hình ảnh';
			default:
				break;
		}
		jQuery("#myModal").modal('show');
	}

	$scope.save = function (state, id) {
		console.log($scope.product);
		
		var url = APIResource;
		
	 	if(state == 'edit'){ 
	 		url += APIResource + id; //update
	 	}else if(state == 'add'){
	 		$scope.product.id = null;
	 	}

		$http({
			method: method,
			url: url,
			data: $scope.product,
		})
		.then(function (res){
			console.log(res);
			//$scope.tuychon($scope.bomon);
			//location.reload();
		})
		.then(function (){
			$("#myModal").modal('hide');
			AjaxRenderData();
		}).catch(function (err) {
			console.log(err);
			//$("#myModal").modal('hide');
			//$scope.tuychon($scope.bomon);
			alert('error');
		});
	}

	$scope.confirmDelete = function (id) {
		var isConfirmDelete = confirm('Bạn có chắc muốn xóa dòng dữ liệu này hay không');
		if (isConfirmDelete) {
			$http.delete(APIResource + id)
			.then(function (res) { // .then is sync func
				//async block
				console.log(res);
				//
			})
			.then(function (){ // delete success
				AjaxRenderData();
			})
			.catch(function(err) { //delete fail
				console.log(err);
			});
		} else {
			return false;
		}
	}
});