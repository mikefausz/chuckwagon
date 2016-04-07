angular.module('starter.controllers', [])

.controller('TabCtrl', function($scope){
  $scope.vendorView = false;
  console.log('in tab controller');
  $scope.toggleVendorView = function() {
    if ($scope.vendorView) {
      $scope.vendorView = false;
    } else {
      $scope.vendorView = true;
    }
  };
})

.controller('VendorCtrl', function($scope){

})

.controller('VendorAccessCtrl', function($scope, TruckService){
  $scope.vendor = {};

  $scope.login = function(vendor){
    console.log("LOGGING IN");
    TruckService.login(vendor);
  };
})

.controller('VendorsignupCtrl', function($scope){

})

.controller('VendordashboardCtrl', function($scope){

})

.controller('ListviewCtrl', function($scope, TruckService){
  $scope.trucks = TruckService.all();
  $scope.remove = function(truck) {
    TruckService.remove(truck);
  };
})

.controller('HomeCtrl', function($scope){

})

.controller('EditCtrl', function($scope){

})

.controller('MapCtrl', function($scope, $state, $cordovaGeolocation, TruckService) {
  var options = {timeout: 10000, enableHighAccuracy: true};

  $cordovaGeolocation.getCurrentPosition(options).then(function(position){

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

    // $scope.trucks = TruckService.all();
    TruckService.getTrucks().then(function(trucks) {
      $scope.trucks = trucks;
      $scope.trucks.forEach(function(truck) {
        var marker = new google.maps.Marker({
          position: truck.location,
          map: $scope.map,
          title: truck.name,
          // icon: 'image4388.png',
        });

        marker.setMap($scope.map);
      });
    });

    }, function(error){
    console.log("Could not get location");
  });
})



.controller('SearchCtrl', function($scope, TruckService) {
  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //
  //$scope.$on('$ionicView.enter', function(e) {
  //});

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
})

.controller('FavoritesCtrl', function($scope) {

});
