app.controller('OrderDetailController', function($scope, $http, API){
	
	var APIResource = API + 'orders/';
	
	var method = null;
	
	function AjaxRenderData(){
		$http.get(APIResource)
		.then(function (res) {
			$scope.data = res.data;
			console.log($scope.data);
		});
	}
	
	AjaxRenderData();

	$scope.modal = function (state, id) {
		$scope.state = state;
		
		switch (state){
			case "edit":
				method = "PUT";
				$scope.frmTitle = 'Chi tiết đơn hàng';
				$http.get(APIResource + 'order-detail/' + id)
				.then(function (res) {
					console.log(res);
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

	$scope.save = function (state, id,status) {
		var status = {id: Number(status) };
		var msg = "Bạn đồng ý "+state+" đơn hàng này";
		if( confirm(msg) ){
			$http.put(APIResource + 'order-detail/' + id,status).then(function (res) {
				AjaxRenderData();
				jQuery("#myModal").modal('hide');
			});
		}
	}
});