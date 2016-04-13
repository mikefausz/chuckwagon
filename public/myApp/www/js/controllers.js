angular.module('starter.controllers', [])

.controller('TabCtrl', function($scope, $state, HomeService){
  // Set default to user mode

  $scope.vendorMode= false;
  $scope.vendorLoggedIn= false;

  $scope.logoutVendor = function() {
    HomeService.logoutVendor();
    $scope.toggleVendorView;
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

.controller('MapCtrl', function($scope, $state, $cordovaGeolocation, HomeService) {
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

    HomeService.getTrucks().then(function(response) {
      $scope.trucks = response;
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

.controller('ListviewCtrl', function($scope, HomeService, FavoritesService){
  HomeService.getTrucks().then(function (truckys) {
    console.log(truckys);
    $scope.trucks = truckys;
  });
  console.log('trucks', $scope.trucks);
  $scope.remove = function(truck) {
    HomeService.remove(truck);
  };
  $scope.addFavoriteTruck = function (truckId) {
    FavoritesService.addFavoriteTruck(truckId)
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

});
