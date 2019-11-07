app.controller('IndexController', function($scope, $http, API){
	
	$scope.tongDoanhThu = 0;
	
	$http.get('/orders-details/tong-doanh-thu').then(function(res){
		$scope.tongDoanhThu = res.data[0].total;
	}).then(function(){
		
	})
	
	setTimeout(function(){
		document.getElementById('show').style.display= "block";
	}, 100);
	
	$scope.thang = [];
	
	
	$scope.year	= [
		{id:2019, name:"2019"},
		{id:2018, name:"2018"}
	];
	
	$scope.nam = $scope.year[0];
	
	countMonth = new Date().getMonth()+1;
	
	for(var i = 1; i<= countMonth; i++){
		$scope.thang.push( {id: i, name:"Tháng "+i} );
	}
	
	
	$scope.chonThang = function (){
		$scope.labels = [];
		$scope.data = [];
		var tempData = [];

		$http.get('/orders-details/thong-ke/'+$scope.nam.id+'/'+$scope.thang.id)
		.then(function(res){
			tempData = res.data;
		})
		.then(function(){
			var dayMax = new Date($scope.nam.id, $scope.thang.id, 0).getDate();
			var thangHienTai = new Date().getMonth()+1;
			if (thangHienTai == $scope.thang.id){
				var dayMax = new Date().getDate();
			}
			
			for(let i = 1; i< dayMax+1; i++){
				$scope.labels.push(i);
				$scope.data.push(0);
			}
			for(let j=0; j< tempData.length; j++){
				let index = tempData[j].created_at;
				$scope.data[index-1] = tempData[j].total;
			}
		})
		.then(function(){
			renderChart();
		})
	}
	
	changeYear();
	
	$scope.chonNam = function(){
		changeYear();
		var countMonth = 12;
		$scope.thang = [];
		if($scope.nam.id == new Date().getFullYear()){
			countMonth = new Date().getMonth()+1;
		}
		for(var i = 1; i<= countMonth; i++){
			$scope.thang.push( {id: i, name:"Tháng "+i} );
		}
	}
	
	function changeYear(){
		$scope.labels = [];
		$scope.data = [];
		var tempData = [];

		$http.get('/orders-details/thong-ke/'+$scope.nam.id)
		.then(function(res){
			tempData = res.data;
		})
		.then(function(){
			for(let i = 1; i< 13; i++){
				$scope.labels.push(i);
				$scope.data.push(0);
			}
			for(let j=0; j< tempData.length; j++){
				let index = tempData[j].created_at;
				$scope.data[index-1] = tempData[j].total;
			}
		})
		.then(function(){
			//console.log(tempData);
			renderChart();
		})
	}
	
	var data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
	var labels = $scope.thang.map(thang => thang.name);
	
	function renderChart(){
		myLineChart.data.labels =  $scope.labels;
		myLineChart.data.datasets[0].data = $scope.data;
		myLineChart.update();
	}
	
	$scope.theoThang = function(thang=10, nam=2019){
		$http.get('/orders-details/thong-ke/'+nam+'/'+thang)
		.then(function(res){
			//console.log(res.data);
		})
	}

	Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
	Chart.defaults.global.defaultFontColor = '#858796';

	function number_format(number, decimals, dec_point, thousands_sep) {
	  // *     example: number_format(1234.56, 2, ',', ' ');
	  // *     return: '1 234,56'
	  number = (number + '').replace(',', '').replace(' ', '');
	  var n = !isFinite(+number) ? 0 : +number,
	    prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
	    sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
	    dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
	    s = '',
	    toFixedFix = function(n, prec) {
	      var k = Math.pow(10, prec);
	      return '' + Math.round(n * k) / k;
	    };
	  // Fix for IE parseFloat(0.55).toFixed(0) = 0;
	  s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
	  if (s[0].length > 3) {
	    s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
	  }
	  if ((s[1] || '').length < prec) {
	    s[1] = s[1] || '';
	    s[1] += new Array(prec - s[1].length + 1).join('0');
	  }
	  return s.join(dec);
	}

	// Area Chart Example
	var ctx = document.getElementById("myAreaChart");
	var myLineChart = new Chart(ctx, {
	  type: 'line',
	  data: {
	    labels: labels,
	    datasets: [{
	      label: "Doanh thu: ",
	      lineTension: 0.3,
	      backgroundColor: "rgba(78, 115, 223, 0.05)",
	      borderColor: "rgba(78, 115, 223, 1)",
	      pointRadius: 3,
	      pointBackgroundColor: "rgba(78, 115, 223, 1)",
	      pointBorderColor: "rgba(78, 115, 223, 1)",
	      pointHoverRadius: 3,
	      pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
	      pointHoverBorderColor: "rgba(78, 115, 223, 1)",
	      pointHitRadius: 10,
	      pointBorderWidth: 2,
	      data: data,
	    }],
	  },
	  options: {
	    maintainAspectRatio: false,
	    layout: {
	      padding: {
	        left: 10,
	        right: 25,
	        top: 25,
	        bottom: 0
	      }
	    },
	    scales: {
	      xAxes: [{
	        time: {
	          unit: 'date'
	        },
	        gridLines: {
	          display: false,
	          drawBorder: false
	        },
	        ticks: {
	          maxTicksLimit: 7
	        }
	      }],
	      yAxes: [{
	        ticks: {
	          maxTicksLimit: 5,
	          padding: 10,
	          // Include a dollar sign in the ticks
	          callback: function(value, index, values) {
	            return number_format(value)+'vnđ';
	          }
	        },
	        gridLines: {
	          color: "rgb(234, 236, 244)",
	          zeroLineColor: "rgb(234, 236, 244)",
	          drawBorder: false,
	          borderDash: [2],
	          zeroLineBorderDash: [2]
	        }
	      }],
	    },
	    legend: {
	      display: false
	    },
	    tooltips: {
	      backgroundColor: "rgb(255,255,255)",
	      bodyFontColor: "#858796",
	      titleMarginBottom: 10,
	      titleFontColor: '#6e707e',
	      titleFontSize: 14,
	      borderColor: '#dddfeb',
	      borderWidth: 1,
	      xPadding: 15,
	      yPadding: 15,
	      displayColors: false,
	      intersect: false,
	      mode: 'index',
	      caretPadding: 10,
	      callbacks: {
	        label: function(tooltipItem, chart) {
	          var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
	          return datasetLabel + number_format(tooltipItem.yLabel)+ 'vnđ';
	        }
	      }
	    }
	  }
	});
	
	var dataCircle = [0, 0, 0, 0];
	var totalCircle = 0;
	var tempData = 0;
	
	
	$http.get('/orders/chart-circle').then(function(res){
		totalCircle = 0;
		tempData = res.data;
		tempData.forEach(function(ele){
			return totalCircle += ele.total;
		});
	}).then(function(){
		var dulieu1 = 0;
		var dulieu2	= 0;
		var dulieu3 = 0;
		var dulieu4 = 0;
		var tong = 0;
		console.log(tempData);
		tempData.forEach(function(status){
			if(status.created_at == 1){
				dulieu1 += status.total;
			}
			else if(status.created_at >1 && status.created_at < 5){
				dulieu2 += status.total;
			}
			else if(status.created_at == 5){
				dulieu3 += status.total;
			}
			else if(status.created_at >5){
				dulieu4 += status.total;
			}
			tong += status.total;
		})
		dataCircle = [(dulieu1*100/tong).toFixed(2),(dulieu2*100/tong).toFixed(2),(dulieu3*100/tong).toFixed(2),(dulieu4*100/tong).toFixed(2)];;
	}).then(function(){
		var ctxx = document.getElementById("myPieChart");
		var myPieChart = new Chart(ctxx, {
		  type: 'doughnut',
		  data: {
		    labels: ["Đang chờ", "Đang xử lý", "Hoàn thành", "Hủy"],
		    datasets: [{
		      data: dataCircle,
		      backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
		      hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
		      hoverBorderColor: "rgba(234, 236, 244, 1)",
		    }],
		  },
		  options: {
		    maintainAspectRatio: false,
		    tooltips: {
		      backgroundColor: "rgb(255,255,255)",
		      bodyFontColor: "#858796",
		      borderColor: '#dddfeb',
		      borderWidth: 1,
		      xPadding: 15,
		      yPadding: 15,
		      displayColors: false,
		      caretPadding: 10,
		    },
		    legend: {
		      display: false
		    },
		    cutoutPercentage: 80,
		    centerText: {
		        display: true,
		        text: "280"
		    }
		  },
		});
	})
	
	

});
