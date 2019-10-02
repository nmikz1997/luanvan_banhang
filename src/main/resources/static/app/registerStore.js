homepage.controller('RegisterStoreController', function($scope,$http){
	$scope.submit = function(){
		$http.post('/users/dang-ky-ban-hang', $scope.obj)
		.then(function (res) {
			if(res.data.code != 10002){
				console.log(res);
				alert('Đăng ký thành công');
				window.location.href = "/";
			}else if(res.data.code == 10002){
				$scope.validate = "was-validated";
				let theString = '^(?!'+ $scope.obj.user.email +'$).*';
			    $('#Email').attr("pattern", theString);
			}
	 	}).catch(function (err) {
	 		$scope.validate = "was-validated";
	 		$scope.errors.concat(err.data.errors);
	 	});
	}
	
});