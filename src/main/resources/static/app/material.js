app.controller('MaterialController', function($scope, $http, API){
	var url =  API + 'materials';
	
	render();
	
	$scope.reset = function(){
		$scope.obj = {
			"status":true
		};
	}
	
	function render(){
		$http.get(API + 'materials')
		.then(function (response) {
			$scope.data = response.data;
		});
	}
	
	$scope.get = function (id){
		$http.get(API + 'materials/'+id)
		.then(function (response) {
			$scope.obj = response.data;
		});
	};

	$scope.save = function (state) {
		$http.post( API + 'materials', $scope.obj )
			.then(function (res) {
		 		render();
		 		console.log(res);
		 	}).catch(function (err) {
		 		console.log(err);
		 		alert('error');
		 	});
	}

	$scope.confirmDelete = function (id) {
		
		var isConfirmDelete = confirm('Bạn có chắc muốn xóa dòng dữ liệu này hay không');
		if (isConfirmDelete) {
			$http.delete(API + 'materials/' + id)
			.then(function (res) {
				console.log(res);
				render();
			})
			.catch(function(err) {
				console.log(err);
				alert('Xảy ra lỗi vui lòng kiểm tra log');
			});
		} else {
			return false;
		}
	}
});