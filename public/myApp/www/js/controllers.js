angular.module('starter.controllers', [])

.controller('TabCtrl', function($scope, $state, HomeService){
  // Set default to user mode

  $scope.vendorMode = false;
  window.localStorage.vendorLoggedIn = false;

  // Toggles between user and vendor modes on click
  $scope.toggleVendorView = function() {
    // IF toggle clicked from vendor mode, switch to user mode
    if ($scope.vendorMode){
        console.log("VENDOR MODE: " + $scope.vendorMode);
        $scope.vendorMode = false;
        $state.go('tab.map');
    }
    // IF toggle clicked from user mode, switch to vendor mode
    else {
      console.log("VENDOR MODE: " + $scope.vendorMode);

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

.controller('MapCtrl', function($scope, $state, $cordovaGeolocation, $compile, HomeService, $location, $ionicNavBarDelegate) {
  // var path = $location.path();
  // if (path.indexOf('/tab/home') != -1){
  //   console.log("show button");
  //   $ionicNavBarDelegate.showBackButton(true);
  // }
  var options = {timeout: 10000, enableHighAccuracy: true};
  console.log("INITIALIZING MAP");
  $cordovaGeolocation.getCurrentPosition(options).then(function(position){
    console.log("RELOG POS");
    var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    localStorage.setItem('userLocation', JSON.stringify({lat: position.coords.latitude, lng: position.coords.longitude}));
    var mapOptions = {
      center: latLng,
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    $scope.map = new google.maps.Map(document.getElementById("map"), mapOptions);

    var marker = new google.maps.Marker({
      position: latLng,
      map: $scope.map,
      icon: 'logo-pin-here.png'
    });

    marker.setMap($scope.map);

    HomeService.getTrucks().then(function(response) {
      $scope.trucks = response;
      $scope.trucks.forEach(function(truck) {
        var marker = new google.maps.Marker({
          position: truck.location,
          map: $scope.map,
          icon: 'logo-pin-shadow-white-sm.png',
        });

        var contentString = "<div><a ng-href='#/tab/list/" + truck.id + "'>" + truck.vendorName + "</a></div>";
        var compiled = $compile(contentString)($scope);
        var infowindow = new google.maps.InfoWindow({
          content: compiled[0]
        });

        google.maps.event.addListener(marker, 'click', function() {
          infowindow.open($scope.map,marker);
        });

        marker.setMap($scope.map);
      });
    }, function(error){
        console.log("Could not get location");
    });
  });
})

.controller('ListviewCtrl', function($scope, HomeService, FavoritesService){
  HomeService.getTrucks().then(function (trucks) {
    $scope.trucks = trucks;
  });

  $scope.addFavoriteTruck = function (truckId, heart) {
    FavoritesService.addFavoriteTruck(truckId, heart);
  };
  $scope.isFavorites = function(truckId) {
    if (localStorage.favoriteVendors) {
      // console.log("Fav vendors is there");
      return localStorage.favoriteVendors.indexOf(truckId) !== -1;
    } else {
      console.log("Fav vendors not there");
      return false;
    }
  };
})

.controller('DetailviewCtrl', function($scope, $stateParams, HomeService, FavoritesService) {
  $scope.truck = HomeService.getTruck($stateParams.truckId);
  $scope.hasContent = function() {
     if($scope.truck.location) {
       return $scope.truck.location.tweet || $scope.truck.location.imageUrl;
     }
     else {
       return false;
     }
   };

  $scope.addFavoriteTruck = function (truckId, heart) {
    FavoritesService.addFavoriteTruck(truckId, heart);
  };

  $scope.isFavorites = function(truckId) {
    if (localStorage.favoriteVendors) {
      // console.log("Fav vendors is there");
      return localStorage.favoriteVendors.indexOf(truckId) !== -1;
    } else {
      console.log("Fav vendors not there");
      return false;
    }
  };

  var mapOptions = {
    center: JSON.parse(localStorage.userLocation),
    zoom: 15,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  $scope.map = new google.maps.Map(document.getElementById("map-detail"), mapOptions);

  var directionsService = new google.maps.DirectionsService;
  var directionsDisplay = new google.maps.DirectionsRenderer;

  console.log(JSON.parse(localStorage.userLocation));
  console.log({lat: $scope.truck.location.lat, lng: $scope.truck.location.lng });

  directionsDisplay.setMap($scope.map);
  directionsDisplay.setOptions({suppressMarkers: true})
  directionsService.route({
    origin: JSON.parse(localStorage.userLocation),
    destination: {lat: $scope.truck.location.lat, lng: $scope.truck.location.lng },
    travelMode: google.maps.TravelMode.DRIVING
  }, function(response, status) {
    if (status === google.maps.DirectionsStatus.OK) {
      directionsDisplay.setDirections(response);
    } else {
      window.alert('Directions request failed due to ' + status);
    }
  });

  var marker = new google.maps.Marker({
    position: {lat: $scope.truck.location.lat, lng: $scope.truck.location.lng },
    map: $scope.map,
    icon: 'logo-pin-shadow-white-sm.png',
  });

  var user = new google.maps.Marker({
    position: JSON.parse(localStorage.userLocation),
    map: $scope.map,
    icon: 'logo-pin-here.png',
  });

  marker.setMap($scope.map);
  user.setMap($scope.map);

  $scope.truck.location.created = new Date().toLocaleTimeString().replace("/.*(\d{2}:\d{2}:\d{2}).*/", "$1");

});
