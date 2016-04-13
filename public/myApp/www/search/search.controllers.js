angular
  .module('search')
  .controller('SearchCtrl', function($scope, SearchService) {
    console.log('searchOptions: ' + searchOptions);
    window.search = searchOptions;

    // CUT THIS:
    $scope.trucks = SearchService.all();

    // SEND SEARCH OPTIONS TO SERVER, CACHE RESPONSE FOR MAP
    // $scope.sendsearchOptions = function(searchOptions) {
    //   SearchService.sendsearchOptions(searchOptions).then(function(response) {
    //     $scope.trucks = response;
    //   }, function(error) {
    //     console.log("ERROR", error);
    //   });
    // };

  })

  .controller('SearchMapCtrl', function($scope, $state, $cordovaGeolocation, SearchService) {
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

      $scope.map = new google.maps.Map(document.getElementById("search-map"), mapOptions);
      console.log('map',$scope.map);
      var marker = new google.maps.Marker({
        position: latLng,
        map: $scope.map,
        title: 'You are here',
        icon: 'http://www.euroheat.co.uk/images/you-are-here-icon.png'
      });

      marker.setMap($scope.map);

      // SEARCH VENDORS SHOULD BE IN CACHE
      SearchService.getTrucks().then(function(trucks) {
        $scope.trucks = trucks.data;
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

  .controller('SearchListviewCtrl', function($scope, SearchService){
    $scope.trucks = SearchService.all();

  })

  .controller('SearchDetailviewCtrl', function($scope, $stateParams, SearchService) {
    // $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
    //   viewData.enableBack = true;
    // });
    $scope.truck = SearchService.get($stateParams.truckId);

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
