app.controller('Controller', function($scope, $http, API){
	
	var url = API + 'members-type/';
	
	function render(){
		$http.get(url)
		.then(function (res) {
			$scope.data = res.data;
		});
	}
	
	$scope.reset = function (){
		$scope.obj = {
				"id":null,
				"duration":1,
				"price": 0
			}
	}
	$scope.reset();
	
	render();

	$scope.modal = function (state, id) {
		$scope.state = state;
		
		switch (state){
			case "add":
				$scope.reset();
				$scope.frmTitle = 'Thêm nhà cung cấp';
				break;
			case "edit":
				$scope.frmTitle = 'Sửa thông tin nhà cung cấp';
				console.log(url+id);
				$http.get(url + id)
					.then(function (res) {
						$scope.obj = res.data;
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
			.then(function (res) { // .then is sync func
				//async block
				console.log(res);
				//
			})
			.then(function (){ // delete success
				render();
			})
			.catch(function(err) { //delete fail
				console.log(err);
			});
		} else {
			return false;
		}
	}
});