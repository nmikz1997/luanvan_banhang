app.controller('CategoryController', function($scope, $http, API){
	renderCategory();
	function getNestedChildren(arr, parent) {
	    var out = [];
	    for(var i in arr) {
	        if(arr[i].parentId == parent) {
	            var categories = getNestedChildren(arr, arr[i].id);
	            if(categories.length) {
	                arr[i].categories = categories;
	            }
	            out.push(arr[i]);
	        }
	    }
	    return out;
	}
	
  	$scope.myFunction = function (parentId) {
  		console.log(parentId);
  		$scope.reset(parentId);
        $("#categoryName").focus(); 
    }
	
	var categories = [];
	function renderCategory(){
		$http.get(API + 'categories').then(function (response) {
			$scope.categories = getNestedChildren(response.data, 0);
		})
		.then(function(){
			console.log($scope.categories[3].categories);
		});
	}
	
	
	$scope.reset = function (parentId){
		$scope.category = {
				"status": true,
				"parentId":parentId
		};
	};
	
	$scope.getCate = function search(id){
		$http.get(API + 'categories/'+id)
		.then(function (response) {
			$scope.category = response.data;
		})
		.then(function (){
		});
	};

	$scope.modal = function (state, id) {
		console.log(state);
		$scope.state = state;

		switch (state){
			case "add":
				console.log
				$scope.frmTitle = 'Thêm sản phẩm';
				break;
			case "edit":
				$scope.frmTitle = 'Sửa thông tin sản phẩm';
				break;
			default:
				break;
		}
		jQuery("#myModal").modal('show');
	}

	$scope.save = function (state) {
		console.log($scope.category);

	 	$http({
	 		method: "POST",
	 		url: API + 'categories',
	 		data: $scope.category
	 	})
	 	.then(function (res){
	 		renderCategory();
	 		console.log(res);
	 	}).catch(function (err) {
	 		console.log(err);
	 		alert('error');
	 	});
	}

	$scope.confirmDelete = function (id) {
		
		var isConfirmDelete = confirm('Bạn có chắc muốn xóa dòng dữ liệu này hay không');
		if (isConfirmDelete) {
			$http.delete(API + 'categories/' + id)
			.then(function (res) {
				renderCategory();
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