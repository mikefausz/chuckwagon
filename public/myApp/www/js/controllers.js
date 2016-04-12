angular.module('starter.controllers', [])

.controller('TabCtrl', function($scope, $state, TruckService){
  // Set default to user mode
  $scope.vendorMode= false;
  $scope.vendorLoggedIn= false;

  $scope.logoutVendor = function() {
    TruckService.logoutVendor();
  };

  // Toggles between user and vendor modes on click
  $scope.toggleVendorView = function() {
    // IF toggle clicked from vendor mode, switch to user mode
    if ($scope.vendorMode){
      $scope.logoutVendor();
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
      }
      return false;
    };

  // Return current mode
  $scope.isLoggedIn = function() {
      if (localStorage.vendorLoggedIn === "true") {
        return true;
      }
      return false;
    };
})

.controller('MapCtrl', function($scope, $state, $cordovaGeolocation, TruckService) {
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

.controller('ListviewCtrl', function($scope, TruckService){
  $scope.trucks = TruckService.all();
  $scope.remove = function(truck) {
    TruckService.remove(truck);
  };
})

.controller('DetailviewCtrl', function($scope, $stateParams, TruckService) {
  // $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
  //   viewData.enableBack = true;
  // });
  $scope.truck = TruckService.get($stateParams.truckId);

  var mapOptions = {
    // center: {lat: -34.397, lng: 150.644},
    center: $scope.truck.location,
    zoom: 15,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  $scope.map = new google.maps.Map(document.getElementById("map-detail"), mapOptions);

  var marker = new google.maps.Marker({
    position: $scope.truck.location,
    // position: {lat: -34.397, lng: 150.644},
    map: $scope.map,
    title: 'Truck name'
  });

  marker.setMap($scope.map);
});
