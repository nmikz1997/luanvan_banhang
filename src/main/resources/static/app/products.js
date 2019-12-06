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
		$scope.product.status = 1;
		
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


	 $scope.confirmDelete = function (id,status) {
 		var product = {};
 		product.id = id;
 		if(status == 2){
 			var msg = "Bạn muốn ngừng kinh doanh sản phẩm này?";
 			var msgSuccess = "Đã ẩn sản phẩm";
 			product.status = 2;
 		}else{
 			var msg = "Bạn muốn mở bán sản phẩm này?";
 			var msgSuccess = "Đã mở bán sản phẩm";
 			product.status = 1;
 		}
 		
 		swal({
			  title: msg,
			  text: "",
			  icon: "",
			  buttons: true,
			  dangerMode: false,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  
				$http.put('/products/update-by-store',product).then(function(res){
					swal(msgSuccess, {
						icon: "success",
					});
		 			AjaxRenderData();
		 		}).catch(function(err){
		 			console.log(err);
		 		});
			  }
			});
	 }
	
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
	$scope.modalView360 = function(productId){
		// Xử lí modal Img
		jQuery("#imgview360").fileinput({
		    theme: 'fa',
		    showUpload: false,
		    showCaption: false,
		    browseClass: "btn btn-primary",
		    allowedFileExtensions: ['jpg', 'png', 'gif'],
		    previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
		    overwriteInitial: false,
		    initialPreviewAsData: true
		});
		jQuery("#mySpriteSpin").spritespin("destroy");
		$scope.productId = productId;
		let sourceImgs = []
		$http.get('/products/hinh-cua-san-pham-360/'+productId)
		.then(function(response){
			
			$scope.listImage360 = response.data;
			
			$scope.listImage360.forEach(function(img){
				sourceImgs.push('/picture/'+img.path);
			})
		})
		.then(function(){
			if($scope.listImage360.length > 0){
				jQuery("#mySpriteSpin").spritespin({
					source: sourceImgs,
					width   : 480,  // width in pixels of the window/frame
					height  : 327,  // height in pixels of the window/frame
				});	
			}
			
		});
		jQuery('#modalView360').modal('show');
	}
	
	jQuery("#frmImg360").on("submit", function(event){
		var formData = new FormData(this);
        formData.append("product",$scope.productId);
        if(jQuery("#imgview360").val() != ""){
        	 jQuery.ajax({
                url: "/products/mutiple-image-360",
                method: "POST",
                data: formData,
                contentType: false,
                cache: false,
                enctype: 'multipart/form-data',
                processData: false,
                success: function(response)
                {
                	swal('Cập nhật hình ảnh 360 thành công', "", "success");
                	jQuery('#modalView360').modal('show');
                      
                },
                error: function(response)
                {
                	console.log(response);
                    //toastr.error('Có lỗi trong quá trình cập nhật', 'Gặp lỗi!',{timeOut: 3000, escapeHtml: true});
                }

          });
        }
        else
        {
         $scope.warning=true;
        }
    });
	
	$scope.update360STT = function(){
		var statuses = [];
		jQuery('#frmEditImg360 input[name="stt360[]"]').each(function() {
		  	statuses.push(jQuery(this).val());
		});
		
		for(var i = 0; i < $scope.listImage360.length ; i++){
			$scope.listImage360[i].status = statuses[i];
		}
		
		var url = '/products/update-stt-image-360/'+$scope.productId;
		var data = $scope.listImage360;
		$http({
			method : 'POST',
			url : url,
			data : data,
			headers : {'Content-type' : 'application/json'}
		})
		.then(function (response){
			swal('Cập nhật thứ tự hình ảnh 360 thành công', '','success');
			jQuery('#modalView360').modal('show');
		})
		.catch(function (response){
			//toastr.error('Có lỗi trong quá trình cập nhật', 'Gặp lỗi!',{timeOut: 3000, escapeHtml: true});
			console.log(response);
		});
	}
	
	//Modal ->form xóa hình ảnh
	jQuery("#frmEditImg360").on("submit", function(event){

  		var data = [];
		jQuery('#frmEditImg360 input[name="deleteImg360[]"]:checked').each(function() {
		  data.push(jQuery(this).val());
		});
		
		swal({
			  title: "Bạn có chắc?",
			  text: "Khi đã xóa thì sẽ không thể phục hồi!",
			  icon: "warning",
			  buttons: ["Không!, thoát", "Đồng ý!"],
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  $http.post("/products/image/deleteSelect-360/"+$scope.productId,data)
				  .then(function(res){
					  swal("Đã xóa hình ảnh!", {
					      icon: "success",
					  });
				  })
				  .catch(function(res){
					  console.log(res);
				  })
			  }
			});
		
		 
		
 		/*Swal.fire({
			  title: 'Bạn có chắc?',
			  text: "Xóa những hình ảnh này !!!",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Đồng ý!',
			  cancelButtonText: 'Không, thoát!',
			}).then((result) => {
				if (result.value) {
					 jQuery.ajax({
			            url:"/products/image/deleteSelect-360/"+$scope.productId,
			            method: "POST",
			            data: JSON.stringify(data),
			            dataType: 'JSON',
			            success: function(response)
			            {
			                toastr.success('Xóa hình ảnh 360 thành công', 'Thành công',{timeOut: 3000, escapeHtml: true});
			                $http.get('/products/hinh-cua-san-pham-360/'+$scope.productId)
							.then(function(response){
								$scope.listImage360 = response.data;
							});

			                
			            },
			            error: function(response)
			            {
			            	console.log(response);
			                //toastr.error('Có lỗi trong quá trình cập nhật', 'Gặp lỗi!',{timeOut: 3000, escapeHtml: true});
			            }

			        });
				}
			})*/
	});
	
});