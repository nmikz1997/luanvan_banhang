homepage.controller('RegisterStoreController', function($scope,$http,$timeout){
	$scope.submit = function(){
		$scope.obj.customer.phoneNumber = $scope.obj.store.phoneNumber;
		console.log($scope.obj);
		$http.post('/users/dang-ky-ban-hang', $scope.obj)
		.then(function (res) {
			if(res.data.code != 10002){
				console.log(res);
				alert('Đăng ký thành công');
				//window.location.href = "/";
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
	var items = JSON.parse(localStorage.getItem("items"));
	var countItems = 0;
	if(items != null){
		items.forEach(function(ele){
			return countItems += ele.quantity;
		})
	}
	$('#countItems').text(countItems);
	
	$http.get('/provinces').then(function (response) {
		$scope.provinces = response.data;
	});
	
	function getDistricts(provinceid){
		console.log(provinceid);
		$http.get('/districts/findby?provinceid='+provinceid)
		.then(function (response) {
			$scope.districts = response.data;
			$timeout(function(){
			     $('.selectpicker').selectpicker('refresh');
			},1)
		});
	}
	
	function getWards(districtid){
		$http.get('/wards/findbydistrict?districtid='+districtid)
		.then(function (response) {
			$scope.wards = response.data;
			$timeout(function(){
			     $('.selectpicker').selectpicker('refresh');
			},1)
		});
	}
	
	$scope.getId = function(state) {
		switch(state) {
		  case 'province':
			getDistricts($scope.obj.store.province.provinceid);
		    break;
		  case 'district':
			getWards($scope.obj.store.district.districtid);
		    break;
		  default:
		    break;
		}
	}
	
});