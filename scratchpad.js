//////App.js

.state('tab.vendorlogin', { ////the rabbit hole that you need to get to
  url: '/vendorlogin', ////the url that the state requires
  views: {
    'tab-vendor': { ////what you call the view you are looking at
      templateUrl: 'templates/tab-vendorlogin.html',////the template that will be swapped into the ion-content
      controller: 'VendorLoginCtrl'////the controller that the html will reference
    }
  }
})


/////Controllers.js
              ////controller name         ////controller dependencies
.controller('VendorLoginCtrl', function($scope, $state, TruckService){
  //scope is the current thing we are on, equivalent to var this
  $scope.loginVendor = function(login){ ////login is the model we are passing into the ng-model
    //.loginVendor is a function we are calling in html, so it must be defined in the controller. we use the same name that will be used in the service
    console.log("LOGGING IN");
                                  //.then says after loginVendor is fired, do this.
    TruckService.loginVendor(login).then(function(vendor){//vendor is the data that is gotten back after loginVendor was fired
      $state.go('tab.vendordashboard');//state.go is how you route to different tabs.
      console.log("VENDOR", vendor);
      $scope.sayMyName = vendor;
    });
  };
})

/////Services.js

loginVendor: function(login){   //loginurl is the destination of the post request
  var currentVendor = $http.post(loginUrl, login); //login is the content you are sending
  console.log("currentVendor", currentVendor)
  //cacheEngine is defined in the TruckService. It
  cacheEngine.put('currentVendor',  currentVendor);
  return currentVendor;
},
