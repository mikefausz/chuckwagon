angular.module('starter.controllers', [])

.controller('TabCtrl', function($scope, $state, HomeService){
  // Set default to user mode
  $scope.vendorMode= false;
  $scope.vendorLoggedIn= false;

  $scope.logoutVendor = function() {
<<<<<<< HEAD
    TruckService.logoutVendor();
    $scope.toggleVendorView;
=======
    HomeService.logoutVendor();
>>>>>>> 0dfd8b9f25e0c09bbff624932fa15091779df95f
  };

  // Toggles between user and vendor modes on click
  $scope.toggleVendorView = function() {
    // IF toggle clicked from vendor mode, switch to user mode
    if ($scope.vendorMode){
      // $scope.logoutVendor();
        $scope.vendorLoggedIn = false;
        $scope.vendorMode = false;
        $state.go('tab.map');
    }
    // IF toggle clicked from user mode, switch to vendor mode
    else {
      $state.go('tab.vendorlogin');
      $scope.vendorMode = true;
    }
  };

  // Return current mode
  $scope.isVendor = function() {
      if ($scope.vendorMode) {
        return true;
      } else {
        return false;
      }
    };

  // Return current mode
  $scope.isLoggedIn = function() {
      if (localStorage.vendorLoggedIn === "true") {
        return true;
      } else {
        return false;
      }
    };
})

<<<<<<< HEAD
.controller('VendorLoginCtrl', function($scope, $q, $rootScope, $state, TruckService){
  $scope.loginVendor = function(login){

    console.log("LOGGING IN");
    TruckService.loginVendor(login).then(function(vendor){
      $state.go('tab.vendordashboard');

      // $rootScope.vendorLoggedIn = true;
      console.log("VENDOR logged in", vendor);
    });
  };
})

.controller('VendorsignupCtrl', function($scope, TruckService){
  $scope.signup = function(vendor){
    console.log("SIGN UP");
    TruckService.signup(vendor);
  };
})

.controller('VendordashboardCtrl', function($scope, $cordovaFileTransfer, TruckService, $q, $cordovaFileTransfer){

  $scope.currentVendor = JSON.parse(localStorage.currentVendor);



  $scope.upload = function(){
    var options = {
      fileKey: "avatar",
      fileName: "image.png",
      chunkedMode: "false",
      mimeType: "image/png"
    };
    $cordovaFileTransfer.upload("http://tiny-tiny.herokuapp.com/collections/chuckwagon", "img/adam.jpg", options).then(function(result){
      console.log("success: " + JSON.stringify(result.response));
    }, function(error){
      console.log("error: " + JSON.stringify(error));
    });
  };
})

.controller('ListviewCtrl', function($scope, TruckService){
  $scope.trucks = TruckService.all();
  $scope.remove = function(truck) {
    TruckService.remove(truck);
  };

  ////////SCRATCHPAD FOR LOCAL STORAGE FAVORITES

  $scope.addFavorites = function () {
  var favStr = "this is the string in the array"
  window.localStorage.setItem("favorites", favStr)

}
})

.controller('HomeCtrl', function($scope){

})

.controller('EditCtrl', function($scope, $state, TruckService){
  $scope.vendor = JSON.parse(localStorage.currentVendor);
  console.log($scope.vendor, "VENDOR");
  $scope.editVendor = function (editedVendor) {
    TruckService.editVendor(editedVendor);
    console.log(editedVendor, "EDITED VENDOR");
    $state.go('tab.vendordashboard');
    }
  window.glob = $scope.editVendor;
})

.controller('MapCtrl', function($scope, $state, $cordovaGeolocation, TruckService) {
=======
.controller('MapCtrl', function($scope, $state, $cordovaGeolocation, HomeService) {
>>>>>>> 0dfd8b9f25e0c09bbff624932fa15091779df95f
  var options = {timeout: 10000, enableHighAccuracy: true};
  console.log("INITIALIZING MAP");
  $cordovaGeolocation.getCurrentPosition(options).then(function(position){
    console.log("RELOG POS");
    var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

    var mapOptions = {
      center: latLng,
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    $scope.map = new google.maps.Map(document.getElementById("map"), mapOptions);

    var marker = new google.maps.Marker({
      position: latLng,
      map: $scope.map,
      title: 'You are here',
      icon: 'http://www.euroheat.co.uk/images/you-are-here-icon.png'
    });

    marker.setMap($scope.map);

<<<<<<< HEAD
    // TruckService.getTrucks().then()

    $scope.trucks = TruckService.all();
    $scope.trucks.forEach(function(truck) {
      var marker = new google.maps.Marker({
        position: truck.location,
        map: $scope.map,
        title: truck.name,
      });

      marker.setMap($scope.map);
    });
  }, function(error){
    console.log("Could not get location");
  });
})

.controller('FavMapCtrl', function($scope, $state, $cordovaGeolocation, TruckService) {
  var options = {timeout: 10000, enableHighAccuracy: true};
  console.log("INITIALIZING MAP");
  $cordovaGeolocation.getCurrentPosition(options).then(function(position){
    console.log("RELOG POS");
    var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

    var mapOptions = {
      center: latLng,
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    $scope.map = new google.maps.Map(document.getElementById("fav-map"), mapOptions);
    console.log('map',$scope.map);
    var marker = new google.maps.Marker({
      position: latLng,
      map: $scope.map,
      title: 'You are here',
      icon: 'http://www.euroheat.co.uk/images/you-are-here-icon.png'
    });

    marker.setMap($scope.map);

    // $scope.trucks = TruckService.all();
    TruckService.getTrucks().then(function(trucks) {



      $scope.trucks = trucks;
=======
    HomeService.getTrucks().then(function(response) {
      $scope.trucks = response;
>>>>>>> 0dfd8b9f25e0c09bbff624932fa15091779df95f
      $scope.trucks.forEach(function(truck) {
        var marker = new google.maps.Marker({
          position: truck.location,
          map: $scope.map,
          title: truck.name,
        });
        marker.setMap($scope.map);
      });
    }, function(error){
        console.log("Could not get location");
    });
  });
})

.controller('ListviewCtrl', function($scope, HomeService){
  $scope.trucks = HomeService.getTrucks();
  $scope.remove = function(truck) {
    HomeService.remove(truck);
  };
})

.controller('DetailviewCtrl', function($scope, $stateParams, HomeService) {
  // $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
  //   viewData.enableBack = true;
  // });
  $scope.truck = HomeService.getTruck($stateParams.truckId);

  var mapOptions = {
    center: $scope.truck.location,
    zoom: 15,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  $scope.map = new google.maps.Map(document.getElementById("map-detail"), mapOptions);

  var marker = new google.maps.Marker({
    position: $scope.truck.location,
    map: $scope.map,
    title: 'Truck name'
  });

  marker.setMap($scope.map);
<<<<<<< HEAD
})
=======
});
>>>>>>> 0dfd8b9f25e0c09bbff624932fa15091779df95f
