angular.module('starter.controllers', [])

.controller('VendoraccessCtrl', function($scope){

})

.controller('VendorsignupCtrl', function($scope){

})

.controller('VendordashboardCtrl', function($scope){

})

.controller('ListviewCtrl', function($scope, Chats){
  $scope.chats = Chats.all();
  $scope.remove = function(chat) {
    Chats.remove(chat);
  };
})

.controller('HomeCtrl', function($scope){

})

.controller('EditCtrl', function($scope){

})

.controller('MapCtrl', function($scope, $state, $cordovaGeolocation) {
  var options = {timeout: 10000, enableHighAccuracy: true};

  $cordovaGeolocation.getCurrentPosition(options).then(function(position){

    var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

    var mapOptions = {
      center: latLng,
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    $scope.map = new google.maps.Map(document.getElementById("map"), mapOptions);

  }, function(error){
    console.log("Could not get location");
  });

})


.controller('SearchCtrl', function($scope, Chats) {
  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //
  //$scope.$on('$ionicView.enter', function(e) {
  //});

  $scope.chats = Chats.all();
  $scope.remove = function(chat) {
    Chats.remove(chat);
  };
})

.controller('DetailviewCtrl', function($scope, $stateParams, Chats) {
  $scope.chat = Chats.get($stateParams.chatId);
  var mapOptions = {
    // center: {lat: -34.397, lng: 150.644},
    center: $scope.chat.location,
    zoom: 15,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  $scope.map = new google.maps.Map(document.getElementById("map-detail"), mapOptions);

  var marker = new google.maps.Marker({
    position: $scope.chat.location,
    // position: {lat: -34.397, lng: 150.644},
    map: $scope.map,
    title: 'Truck name'
  });

  marker.setMap($scope.map);
})

.controller('AccountCtrl', function($scope) {
  $scope.settings = {
    enableFriends: true
  };
});
