app.controller('ComponentController', function($scope, $http,$timeout, API){
	
	jQuery("#frmobj").on("submit", function(event){	
        event.preventDefault();
        
        var file = $('#image').get(0).files;
        console.log(file);
//        var formData = new FormData();
//
//        formData.append('file', file);
//    	formData.append('model',JSON.stringify($scope.obj));
//    	
//         if(jQuery("#image").val() != ""){
//        	 jQuery.ajax({
//        		 url: "/upload/",
//        		 type: "POST",
//        		 data: formData,
//        		 enctype: 'multipart/form-data',
//        		 processData: false,
//        		 contentType: false,
//        		 cache: false,
//        		 success: function (res) {
//        		      //console.log(res);
//        		 },
//        		 error: function () {
//        			 console.log("not ok");
//        		 }
//
//          });
//        }
//        else
//        {
//         $scope.warning=true;
//        }     
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
	
	$http.get(API + 'categories').then(function (response) {
		console.log(response);
		$scope.categories = getNestedChildren(response.data, 0);
	});
	
	$scope.reset = function reset(){
		$scope.category = null;
	};
	
	$scope.getCate = function search(id){
		$http.get(API + 'categories/'+id)
		.then(function (response) {
			$scope.category = response.data;
		})
	};
	
	$http.get(API + 'provinces').then(function (response) {
		$scope.provinces = response.data;
	});
	
	$http.get(API + 'attributes').then(function (response) {
		$scope.attributes = response.data;
		$timeout(function(){
		     $('.selectpicker').selectpicker('refresh');
		     jQuery('#color').selectpicker('val', [1,2]);
			 function groupBy(objectArray, property) {
				 return objectArray.reduce(function (acc, obj) {
					 var key = obj[property].code;
						if (!acc[key]) {
							acc[key] = [];
						}
						acc[key].push(obj.id);
						return acc;
				 }, {});
			 }
			 var groupeId = groupBy(arrAttribute, "attribute");
			 //console.log(groupeId);
		},1)
	});
	
    jQuery(function(){
    	jQuery('#save_value').click(function(){
          var val = [];
          jQuery('select[name=color]').each(function(i){
            val[i] = $(this).val();
          });
          var newArr =[];
          val.flat().map(value => newArr.push({id:value}) );
          	$scope.product = {
          		name: "product 1",
          		color: newArr
          	}
          	//console.log($scope.product);
        });
      });
	
	function getDistricts(provinceid){
		$http.get(API + 'districts/findby?provinceid='+provinceid)
		.then(function (response) {
			$scope.districts = response.data;
			$timeout(function(){
			     $('.selectpicker').selectpicker('refresh');
			},1)
		});
	}
	
	function getWards(districtid){
		$http.get(API + 'wards/findbydistrict?districtid='+districtid)
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
			getDistricts($scope.selectedProvince);
		    break;
		  case 'district':
			getWards($scope.selectedDistrict);
		    break;
		  default:
		    break;
		}
	}
	
	$scope.hello = "hello my html component";
	var arrAttribute = [
		{
			"id":1,
			"attributeValue":"Đỏ",
			"attribute":{"code":"color"},
			"count":2
		},
		{
			"id":2,
			"attributeValue":"Xanh",
			"attribute":{"code":"color"},
			"count":1
		},
		{
			"id":3,
			"attributeValue":"XL",
			"attribute":{"code":"size"},
			"count":5
		},
		{
			"id":4,
			"attributeValue":"L",
			"attribute":{"code":"size"},
			"count":9
		}
		];
});