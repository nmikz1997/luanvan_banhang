var app = angular.module('luanvan',['datatables']).constant('API', 'http://localhost:8080/');

var homepage = angular.module('homepage',['ngCart','ngSanitize']).constant('API', 'http://localhost:8080/');

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
