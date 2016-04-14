angular
  .module('favorites')
  .controller('FavMapCtrl', function($scope, $state, $cordovaGeolocation, FavoritesService) {
    // if(!localStorage.getItem('favoriteVendors')) {
    //   localStorage.setItem('favoriteVendors', []);
    // }

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


      FavoritesService.getFavoriteTrucks().then(function(trucks) {
        $scope.trucks = trucks;
        $scope.trucks.forEach(function(truck) {
          var marker = new google.maps.Marker({
            position: truck.location,
            map: $scope.map,
            icon: 'icon-tutone.png',
          });

          var contentString = "<div><a ng-href='#/tab/list/" + truck.id + "'>" + truck.name + "</a></div>";
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
      console.log($scope.trucks);
      window.favorits = $scope.trucks;
      $scope.$on('favorite:added', function () {
        FavoritesService.getFavoriteTrucks().then(function(trucks) {
          $scope.trucks = trucks;
        })      })
      $scope.removeFavoriteTruck = function(truckId) {
        FavoritesService.removeFavoriteTruck(truckId)
      };
      $scope.$on('favorite:removed', function () {
        FavoritesService.getFavoriteTrucks().then(function(trucks) {
          $scope.trucks = trucks;
        })
      })
  })

  .controller('FavDetailviewCtrl', function($scope, $stateParams, FavoritesService) {
    // $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
    //   viewData.enableBack = true;
    // });
    $scope.truck = FavoritesService.getFavoriteTruck($stateParams.truckId);

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
