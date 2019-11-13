app.controller('ProducerController', function($scope, $http, API){
	
	var url = API + 'questions/';
	
	function render(){
		$http.get(url)
		.then(function (res) {
			$scope.data = res.data;
		});
	}
	
	$scope.reset = function (){
		$scope.obj = {
			"id":null,
			"status":1,
		}
	}
	
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
		let data = {
			content: $scope.obj.replyFirst,
			question: {id: $scope.obj.id}
		};
		
		console.log(data);
		
		$http.post('/replies', data )
			.then(function (res) {
		 		render();
		 		swal("Thành công", "", "success");
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
});