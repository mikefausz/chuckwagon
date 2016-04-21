angular
  .module('favorites')
  .controller('FavMapCtrl', function($scope, $state, $cordovaGeolocation, FavoritesService, $compile) {


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

      $scope.map = new google.maps.Map(document.getElementById("fav-map"), mapOptions);
      console.log('map',$scope.map);
      var marker = new google.maps.Marker({
        position: latLng,
        map: $scope.map,
        icon: 'logo-pin-here.png'
      });

      marker.setMap($scope.map);


      FavoritesService.getFavoriteTrucks().then(function(trucks) {
        $scope.trucks = trucks;
        $scope.trucks.forEach(function(truck) {
          var marker = new google.maps.Marker({
            position: truck.location,
            map: $scope.map,
            icon: 'logo-pin-shadow-white-sm.png',
          });

          var contentString = "<div><a ng-href='#/tab/fav-list/" + truck.id + "'>" + truck.vendorName + "</a></div>";
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
      })
  })

  .controller('FavListviewCtrl', function($scope, FavoritesService){

      FavoritesService.getFavoriteTrucks().then(function(trucks) {
        $scope.trucks = trucks;
      })
      $scope.$on('favorite:added', function () {
        FavoritesService.getFavoriteTrucks().then(function(trucks) {
          console.log("FAV LIST VIEW CHANGED");
          $scope.trucks = trucks;
          })
      });
        $scope.addFavoriteTruck = function (truckId, heart) {
          FavoritesService.addFavoriteTruck(truckId, heart)
        };

  })

  .controller('FavDetailviewCtrl', function($scope, $stateParams, FavoritesService, HomeService) {

  $scope.truck = HomeService.getTruck($stateParams.truckId);

  $scope.hasContent = function() {
    return $scope.truck.location.tweet || $scope.truck.location.imageUrl;
  };

    $scope.$on('favorite:added', function () {
      FavoritesService.getFavoriteTrucks().then(function(trucks) {
        $scope.trucks = trucks;
        })
      });
      $scope.addFavoriteTruck = function (truckId, heart) {
        FavoritesService.addFavoriteTruck(truckId, heart)
      };

    var mapOptions = {
      // center: {lat: -34.397, lng: 150.644},
      center: $scope.truck.location,
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
