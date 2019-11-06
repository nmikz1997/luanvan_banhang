app.controller('StoreController', function($scope, $http, API){
	
	var url = API + 'stores/';
	
	function render(){
		$http.get(url)
		.then(function (res) {
			$scope.data = res.data;
			console.log(res.data);
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
		 		console.log(res);
		 	}).catch(function (err) {
		 		console.log(err);
		 		alert('error');
		 	});
		$("#myModal").modal('hide');
	}
	
	$scope.duyet = function(id,status){
		confirm(id,status,"Bạn muốn duyệt cửa hàng này?","Đã duyệt cửa hàng");
	}
	
	$scope.huy = function(id,status){
		confirm(id,status,"Bạn muốn hủy đơn đăng ký bán hàng này?", "Đã hủy đơn đăng ký");
	}
	
	$scope.khoa = function(id,status){
		confirm(id,status, "Bạn muốn khóa cửa hàng này", "Đã khóa cửa hàng");
	}
	
	$scope.moKhoa = function(id,status){
		confirm(id,status, "Bạn muốn mở khóa cửa hàng này", "Đã mở khóa cửa hàng");
	}
	
	function confirm(id,status,msg,msgSuccess){
		swal({
			  title: msg,
			  text: "",
			  icon: "",
			  buttons: true,
			  dangerMode: false,
			})
			.then((willDelete) => {
			  if (willDelete) {
			    
			    $http.put(`/stores/thay-doi-trang-thai/${id}`,{status:status}).then(function(res){
			    	swal(msgSuccess, {
					   icon: "success",
					});
			    	render();
			    })
			  }
			});
	}
	
});