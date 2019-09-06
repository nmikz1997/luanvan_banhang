app.controller('CategoryController', function($scope, $http, API){
	// $http.get(mainURL + 'bomon').then(function (response) {
	// 	$scope.arrBoMon 	= angular.fromJson(response.data.message.jsonBoMon);
	// });
	
	var categories	= [
		{"id": 1, "name": "menu 1", "parent_id": 0},
		{"id": 2, "name": "menu 2", "parent_id": 0},
		{"id": 3, "name": "menu 3", "parent_id": 0},
		{"id": 4, "name": "menu 4", "parent_id": 1},
		{"id": 5, "name": "menu 5", "parent_id": 4},
		{"id": 6, "name": "menu 6", "parent_id": 4},
		{"id": 7, "name": "menu 7", "parent_id": 2},
		{"id": 8, "name": "menu 8", "parent_id": 3}
	];
	
	function getNestedChildren(arr, parent) {
	    var out = [];
	    for(var i in arr) {
	        if(arr[i].parent_id == parent) {
	            var categories = getNestedChildren(arr, arr[i].id);
	            if(categories.length) {
	                arr[i].categories = categories;
	            }
	            out.push(arr[i]);
	        }
	    }
	    return out;
	}
	$scope.categories = getNestedChildren(categories, 0);
	
	$scope.getCate = function search(id){
		var category = categories.find( cate => cate.id === id );
		console.log(category);
		if(category.parent_id != 0){
			search(category.parent_id);
		}
	};
	
//	$scope.getCateParent = function(id){
//		console.log( categories.find( cate => cate.id === id).parent_id );
//	};
	
	
	
//	$scope.arrOrigin	= [
//		{"id": 1, "name": "option 1"},
//		{"id": 2, "name": "option 2"},
//		{"id": 3, "name": "option 3"}
//	];
//	$scope.arrMaterial	= [
//		{"id": 1, "name": "option 1"},
//		{"id": 2, "name": "option 2"},
//		{"id": 3, "name": "option 3"}
//	];

	$scope.modal = function (state, id) {
		console.log(state);
		$scope.state = state;
		//$scope.makeReadOnly = false;
		
//		if (state == "edit") { 
//			$scope.makeReadOnly = true; 
//		}
		switch (state){
			case "add":
				console.log
				$scope.frmTitle = 'Thêm sản phẩm';
//				$scope.canbo = null;
				break;
			case "edit":
				$scope.frmTitle = 'Sửa thông tin sản phẩm';
//				$http.get(mainURL+ "canbo/"+id)
//				.then(function (response){
//					$scope.canbo = response.data;
//				}).catch(function (err) {
//					console.log(err);
//					alert("Không tìm thấy cán bộ");
//				});

				break;
			default:
				break;
		}
		jQuery("#myModal").modal('show');
	}

	$scope.save = function (state, id) {
		console.log($scope.product);

	// 	var url = mainURL + "canbo"; //add
	// 	var data = $scope.canbo;
	// 	var method = "POST";

	// 	if(state == 'edit'){ 
	// 		url+= "/"+id; //update
	// 		method = "PUT";
	// 	}

	// 	$http({
	// 		method: method,
	// 		url: url,
	// 		data: $.param(data),
	// 		headers: { 'Content-Type' : 'application/x-www-form-urlencoded' }
	// 	})
	// 	.then(function (res){
	// 		console.log(res);
	// 		$("#myModal").modal('hide');
	// 		$scope.tuychon($scope.bomon);
	// 		//location.reload();
	// 	}).catch(function (err) {
	// 		console.log(err);
	// 		//$("#myModal").modal('hide');
	// 		$scope.tuychon($scope.bomon);
	// 		alert('error');
	// 	});
	}

	$scope.confirmDelete = function (id) {
		var isConfirmDelete = confirm('Bạn có chắc muốn xóa dòng dữ liệu này hay không');
//		if (isConfirmDelete) {
//			$http.delete(mainURL + 'canbo/' + id)
//			.then(function (res) {
//				console.log(res);
//				$("#myModal").modal('hide');
//				$scope.tuychon($scope.bomon);
//				//location.reload();
//			})
//			.catch(function(err) {
//				console.log(err);
//				$("#myModal").modal('hide');
//				$scope.tuychon($scope.bomon);
//				alert('Xảy ra lỗi vui lòng kiểm tra log');
//			});
//		} else {
//			return false;
//		}
	}
});