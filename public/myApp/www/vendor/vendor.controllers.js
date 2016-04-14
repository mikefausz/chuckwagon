angular
  .module('vendor')
    .controller('VendorLoginCtrl', function($scope, $q, $state, VendorService){
      $scope.loginVendor = function(login){
        console.log("LOGGING IN");
        VendorService.loginVendor(login).then(function(vendor){
          $state.go('tab.vendordashboard');
        });
      };
    })


    .controller('VendorsignupCtrl', function($scope, VendorService){
      $scope.signup = function(vendor){
        console.log("SIGN UP");
        VendorService.signup(vendor);
      };
    })


    .controller('VendordashboardCtrl', function($scope, $cordovaGeolocation, VendorService, $q){

      window.scope = $scope;

      $scope.currentVendor = JSON.parse(localStorage.currentVendor);
      console.log($scope.currentVendor);
      $scope.dropPin = function(post, vendorId){
        $cordovaGeolocation.getCurrentPosition().then(function(position){
          console.log("RELOG POS", position);
          var id = $scope.currentVendor.id;

          console.log("SHOW",post);
          post.lat = position.coords.latitude;
          post.lng = position.coords.longitude;
          VendorService.dropPin(post, id)
          .success(function(data) {
            console.log("YAY", data);
          })
          .error(function(err) {
            console.log('err', err);
          });
        });
      };

      $scope.logoutVendor = function() {
        VendorService.logoutVendor();
        $scope.$parent.toggleVendorView();
      };
    })

    .controller('VendordashdetailCtrl', function($scope, VendorService, $stateParams){

      $scope.truck = VendorService.getTruck($stateParams.truckId);
      window.glob = $scope.truck;

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
    })

    .controller('EditCtrl', function($scope, VendorService){
      $scope.currentVendor = JSON.parse(localStorage.currentVendor);
      var id = $scope.currentVendor.id;

      $scope.editVendor = function(vendor) {
        VendorService.editVendor(vendor, id);
        console.log(vendor, id);
        window.editVendor = vendor;
      };
    });
