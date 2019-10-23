app.controller('ProductsController', function($scope, $http,$timeout, API){
	
	var APIResource = API + 'products/';
	
	var method = null;

	$scope.giagoc = {
		id: null,
		root: 1
	}

	$scope.giaban = {
		id: null,
		root: 0
	}
	
	function AjaxRenderData(){
		$http.get(APIResource)
		.then(function (res) {
			$scope.products = res.data;
		});
	}
	
	AjaxRenderData();


	$scope.modal = function (state, id) {
		
		$scope.state = state;
		$scope.product = null;

		switch (state){
			case "add":
				method = "POST";
				$scope.product = null;
				$scope.anhAvatar = '';
				console.log($scope.anhAvatar);
				$scope.frmTitle = 'Thêm sản phẩm';
				$scope.giagoc = {
						id: null,
						root: 1
					}

				$scope.giaban = {
					id: null,
					root: 0
				}
				$scope.anGiaBan = true;
				
				break;
			case "edit":
				console.log(APIResource + id);
				method = "PUT";
				$scope.frmTitle = 'Sửa thông tin sản phẩm';
				$scope.anGiaBan = false;
				$http.get(APIResource + id)
				.then(function (res) {
					console.log(res);
					$scope.product = res.data.product;
					$scope.giagoc.id = res.data.product.prices[0].id;
					$scope.giagoc.unitPrice = res.data.product.prices[0].unitPrice;
					$scope.giaban.id = res.data.product.prices[res.data.product.prices.length-1].id;
					$scope.giaban.unitPrice = res.data.product.prices[res.data.product.prices.length-1].unitPrice;
				})
				.then(function(){
					$scope.anhAvatar = '/picture/'+$scope.product.avatar;
				})
				.catch(function (err) {
					//alert("Không tìm thấy");
				});

			break;
			case "images":
				$scope.frmTitle = 'Cập nhật hình ảnh';
			default:
			break;
		}
		jQuery("#myModal").modal('show');
	}
	
	var idProductImg = 0;
	
	$scope.modalImg = function (state, id) {
		idProductImg = id;
		jQuery("#modalImg").modal('show');
	}
	
	
	
	jQuery("#submit").on("click", function(event){
		event.preventDefault();
		
		jQuery('#description').each(function () {
	           for(var description in CKEDITOR.instances)
	                CKEDITOR.instances[description].updateElement();
        });
        
		
		delete $scope.product["priceNew"];delete $scope.product["price"];delete $scope.product["createdAt"];
		delete $scope.giaban["createdAt"];delete $scope.product["priceApply"];delete $scope.product["sold"];
		$scope.product.status = 0;
		
		if($scope.anGiaBan){
			$scope.giaban.unitPrice = $scope.giagoc.unitPrice;
		}
		
		$scope.product.prices = [
			$scope.giagoc,
			$scope.giaban
		]
		
		var model = {
			product: $scope.product
		}
		console.log(model);
		var formData = new FormData();

//		nếu có ảnh thì append
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
		 			swal("Thành công", "", "success");
		 			jQuery("#myModal").modal('hide');
		 			AjaxRenderData();
		 			$scope.giagoc = {id: null,root: 1}
	 				$scope.giaban = {id: null,root: 0}
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
	
	$scope.thayDoiGiaBan = function(){
		$scope.giaban.id = null;
	}
	
	
	jQuery("#frmImg").on("submit", function(event){
		var formData = new FormData(this);
		console.log(idProductImg);
        formData.append("productId",idProductImg);
        if(jQuery("#imgs").val() != ""){
        	 jQuery.ajax({
                url: "/products/mutiple-image",
                method: "POST",
                data:formData,
                contentType: false,
                cache: false,
                enctype: 'multipart/form-data',
                processData: false,
                success: function(response)
                {
                	jQuery("#imgs").val('').clone(true);
                      
                },
                error: function(response)
                {
                    console.log(response);
                    alert("Thao tác bị lỗi");
                }

          });
        }
        else
        {
         $scope.warning=true;
        }
    });
	
	$scope.thayDoiSL = function(id,$index,thayDoi){

		check = $scope.products[$index].quantity - $scope.products[$index].sold - thayDoi;
		
		let slGoc = $scope.products[$index].quantity - check;
		
		let data = {
			id: id,
			quantity: slGoc = slGoc || 0
		}

		if(slGoc > 0){
			
			let SLTon = slGoc - $scope.products[$index].sold;
			
			$http.post(APIResource+"thay-doi-gia", data )
			.then(function (res) {
				AjaxRenderData();
				swal("Thay đổi thành công", `số lượng còn lại: ${SLTon}`, "success");
		 	}).catch(function (err) {
		 		console.log(err);
		 		alert('error');
		 	});
		}
		

	}
	
});