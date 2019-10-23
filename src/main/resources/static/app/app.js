var app = angular.module('luanvan',["ngCkeditor","datatables"]).constant('API', 'http://localhost:8080/');

var homepage = angular.module('homepage',['ngCart','ngSanitize']).constant('API', 'http://localhost:8080/');


homepage.config(['$locationProvider', function($locationProvider) {
    $locationProvider.html5Mode({ enabled: true, requireBase: false, rewriteLinks: false });
}]);

app.directive('ckEditor', function () {
	  return {
	    require: '?ngModel',
	    link: function (scope, elm, attr, ngModel) {
	      var ck = CKEDITOR.replace(elm[0]);
	      if (!ngModel) return;
	      ck.on('instanceReady', function () {
	        ck.setData(ngModel.$viewValue);
	      });
	      function updateModel() {
	        scope.$apply(function () {
	          ngModel.$setViewValue(ck.getData());
	        });
	      }
	      ck.on('change', updateModel);
	      ck.on('key', updateModel);
	      ck.on('dataReady', updateModel);

	      ngModel.$render = function (value) {
	        ck.setData(ngModel.$viewValue);
	      };
	    }
	};
});

homepage.directive("rateYo", function() {
	return {
	    restrict: "E",
	    scope: {
	        rating: "="
	    },
	    template: "<span id='rate'></span>",
	    link: function( scope, ele, attrs ) {
	        $(ele).rateYo({
	            rating: scope.rating,
	            starWidth: "20px",      
	            ratedFill: "#ffce59",
	            readOnly: true       
	        });
	    }
	  };
	  
});

homepage.directive("rateYoHome", function() {
	return {
	    restrict: "E",
	    scope: {
	        rating: "="
	    },
	    template: "<div style='padding:0px;margin:0px' id='rate'></div>",
	    link: function( scope, ele, attrs ) {
	        $(ele).rateYo({
	            rating: scope.rating,
	            starWidth: "13px",      
	            ratedFill: "#ffce59",
	            readOnly: true       
	        });
	    }
	  };
	  
});