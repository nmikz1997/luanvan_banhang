app.controller('ProductsController', function($scope, $http,$timeout, API){
	
	var APIResource = API + 'products/';
	
	var method = null;
	
	function AjaxRenderData(){
		$http.get(APIResource)
		.then(function (res) {
			$scope.products = res.data;
		});
	}
	
	AjaxRenderData();

	$scope.modal = function (state, id) {
		$scope.state = state;

		switch (state){
			case "add":
				method = "POST";
				$scope.product = null;
				$scope.anhAvatar = '';
				console.log($scope.anhAvatar);
				console.log("ok");
				$scope.frmTitle = 'Thêm sản phẩm';
				break;
			case "edit":
				method = "PUT";
				$scope.frmTitle = 'Sửa thông tin sản phẩm';
				$http.get(APIResource + id)
				.then(function (res) {
					//console.log(res.data.product.origin.id);
					$scope.product = res.data.product;
				})
				.then(function(){
					$scope.anhAvatar = '/picture/'+$scope.product.avatar;
					//console.log($scope.product);
				})
				.catch(function (err) {
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
	
	jQuery("#submit").on("click", function(event){
		event.preventDefault();
		$scope.product.status = 1;
		
		var model = {
			product: $scope.product
		}
		var formData = new FormData();

		//nếu có ảnh thì append
		if($('#image').get(0).files[0]){
			formData.append('file', $('#image').get(0).files[0]);
		}
		formData.append('model',JSON.stringify(model));

		if($('#image').val() != "" || $scope.state == "edit"){
			
		 	jQuery.ajax({
		 		url: "/products",
		 		type: "POST",
		 		data: formData,
		 		enctype: 'multipart/form-data',
		 		processData: false,
		 		contentType: false,
		 		cache: false,
		 		success: function (res) {
		 			jQuery("#myModal").modal('hide');
		 			AjaxRenderData();
		 		},
		 		error: function (err) {
		 			console.log(err.responseText);
		 			console.log("not ok");
		 			AjaxRenderData();
		 		}
		 	});
		 }
		 else
		 {
		 	$scope.warning=true;
		 }     
	});


	// 	$scope.confirmDelete = function (id) {
	// 		var isConfirmDelete = confirm('Bạn có chắc muốn xóa dòng dữ liệu này hay không');
	// 		if (isConfirmDelete) {
	// 			$http.delete(APIResource + id)
	// 		.then(function (res) { // .then is sync func
	// 			//async block
	// 			console.log(res);
	// 			//
	// 		})
	// 		.then(function (){ // delete success
	// 			AjaxRenderData();
	// 		})
	// 		.catch(function(err) { //delete fail
	// 			console.log(err);
	// 		});
	// 	} else {
	// 		return false;
	// 	}
	// }
	
	$http.get(API + 'origins').then(function (response) {
		$scope.origins = response.data;
	});
	
	$http.get(API + 'producers').then(function (response) {
		$scope.producers = response.data;
	});
	
	$http.get(API + 'materials').then(function (response) {
		$scope.materials = response.data;
	});
	
	$http.get(API + 'categories').then(function (response) {
		$scope.categories = getNestedChildren(response.data, 0);
	}).then(function(){
		console.log($scope.categories);
	});
	
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
	var file;
	
	document.getElementById("image").onchange = function () {
	    var reader = new FileReader();

	    reader.onload = function (e) {
	        document.getElementById("avatar").src = e.target.result;
	    };
	    reader.readAsDataURL(this.files[0]);
	    return reader;
	};
	
	$scope.dachon = function(){
		console.log("đã chọn");
	};
	
	
});