homepage.controller('GiaHanController', function($scope, $http, API){
	
	var url = API + 'members-type/';
	function getTiGia(){
		$http.get('https://api.exchangerate-api.com/v4/latest/USD').then(function(res){
			$scope.currency = res.data.rates.VND;
		})	
	}
	
	$http.get('/members/history-member').then(function(res){
		$scope.historyMember = res.data;
		console.log(res.data);
	})
	
	var totalPaypal = 0;
	var memberId;
	
	$http.get(url).then(function (res) {
		getTiGia();
		$scope.membertype = res.data;
		$scope.type = res.data[0];
		totalPaypal = Math.round(res.data[0].price/$scope.currency*100)/100;
		memberId = res.data[0].id;
	});
	
	getTiGia();
	
	$scope.chonGoi = function(type){
		$scope.type = type;
		totalPaypal = Math.round(type.price/$scope.currency*100)/100;
		memberId = type.id;
	}
	
	paypal.Button.render({
        env: 'sandbox',
        style: {
            color:  'gold',
            shape:  'rect',
            label:  'paypal',
            height: 44,
            tagline : false,
            size:'responsive',
        },

        funding: {
            allowed: [
            paypal.FUNDING.CARD,
            paypal.FUNDING.CREDIT
            ],
            disallowed: []
        },

        client: {
            sandbox: 'ASlF80g16AWLwAnslzRi18WwaS81Uda-aV0ldWjJE02u8fPvchMdCMNKM7fj40j66gekc2tZSOsicGzF',
            production: ''
        },

        
        payment: function(data, actions) {
        	
            return actions.payment.create({
                "transactions": [
                   {
                     "amount": {
                       "total": totalPaypal,
                       "currency": "USD",
                     },
                     "description": "The payment transaction description.",
                     "payment_options": {
                       "allowed_payment_method": "INSTANT_FUNDING_SOURCE"
                     },
                     "soft_descriptor": "ECHI5786786",
                     "item_list": {
                       "items": [
                         {
                           "name": "Tổng số tiền hóa đơn",
                           "quantity": "1",
                           "price": totalPaypal,
                           "currency": "USD"
                         },
                       ],
                       
                     }
                   }
                 ]
            });
        },
        onAuthorize: function (data, actions) {
            return actions.payment.execute()
            .then(function () {
            	giaHan();
            });
        }
    }, '#paypal-button');
	
	function giaHan(){
		let data = {
			memberType:{id:memberId}
		}
		$http.post('/members',data).then(function(res){
			console.log(res);
		}).catch(function(res){
			console.log(res);
		})
	}

});