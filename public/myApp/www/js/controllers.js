angular.module('starter.controllers', [])

.controller('VendoraccessCtrl', function($scope){

})

.controller('VendorsignupCtrl', function($scope){

})

.controller('VendordashboardCtrl', function($scope){

})

.controller('ListviewCtrl', function($scope){

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

<<<<<<< HEAD

.controller('ListCtrl', function($scope, Chats) {
})
.controller('ChatsCtrl', function($scope, Chats) {
=======
.controller('SearchCtrl', function($scope, Chats) {
>>>>>>> 31de5dd3415484c193f657fca143daa1bc26222a
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
})

.controller('AccountCtrl', function($scope) {
  $scope.settings = {
    enableFriends: true
  };
});
