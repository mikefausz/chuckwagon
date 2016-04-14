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

<<<<<<< HEAD
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
=======
    .controller('VendordashdetailCtrl', function($scope, TruckService){
      $scope.currentVendor = JSON.parse(localStorage.currentVendor);
>>>>>>> b7e70cc8eb72151aa481392a12a53107c8f53dcb
    })

    .controller('EditCtrl', function($scope, VendorService){
      // Get current vendor from localStorage, clear tags for edit
      $scope.currentVendor = JSON.parse(localStorage.currentVendor);
<<<<<<< HEAD
      var id = $scope.currentVendor.id;
=======
      $scope.currentVendor.tags = [];

      // Declare edited vendor object, tags as an array
      $scope.editedVendor = {};
      $scope.editedVendor.tags = [];

      $scope.editVendor = function(editedVendor) {
        // Process tag checkboxes
        var tagArr = [];
        editedVendor.tags.forEach(function(tag, idx) {
          // If a tag is selected
          if (tag) {
            // Process for back-end
            tagArr.push(
              {
                id: idx,
                tag: tag
              });
            // Also push to front-end
            $scope.currentVendor.tags.push(tag);
          }
        });

        // Format vendor edit info for back-end
        var processedVendor = {
          vendor: {
            bio: editedVendor.bio,
            profilePictureLocation: editedVendor.profilePictureLocation,
          },
          tags: tagArr,
        };

        // Send processed vendor edit data to server
        var id = $scope.currentVendor.id;
        VendorService.editVendor(processedVendor, id);
>>>>>>> b7e70cc8eb72151aa481392a12a53107c8f53dcb

        // Grab edit data, save changes in localStorage
        $scope.currentVendor.bio = editedVendor.bio;
        $scope.currentVendor.profilePictureLocation = editedVendor.profilePictureLocation;
        localStorage.currentVendor = $scope.currentVendor;
      };
    });
