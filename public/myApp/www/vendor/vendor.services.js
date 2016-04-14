angular
    .module('vendor')
.factory('VendorService', function($http, $q, $cacheFactory) {
  var cacheEngine = $cacheFactory('vendor');
  var ip = "http://10.0.10.70:8080";
  // var ip = "http://localhost:8080";
  var loginUrl = ip + "/vendor/login";
  var signupUrl = ip + "/vendor";

  function signup(vendor){
    var currentVendor = $http.post(signupUrl, vendor);
    window.localStorage.setItem( 'currentVendor', JSON.stringify(vendor) );
    window.localStorage.setItem( 'vendorLoggedIn', true );
    return currentVendor;
  }

  function dropPin(post, vendorId){
    var url = ip + "/vendor/" + vendorId + "/location";
    // post.expiresString =  new Date().toISOString().slice(0, 19);
    console.log("OBJECT BEING SENT", post);
    return $http.post(url, post);
  }

  function loginVendor(login){
    var defer = $q.defer();

    $http.post(loginUrl, login).then(function(response) {
      defer.resolve(response.data);
      window.localStorage.currentVendor = JSON.stringify(response.data);
      window.localStorage.vendorLoggedIn = true;
    });

    return defer.promise;
  }

  function logoutVendor(){
    window.localStorage.vendorLoggedIn = false;
    window.localStorage.currentVendor = '';
    var vendor = JSON.parse(localStorage.currentVendor);
    var logoutUrl = "/vendor/" + vendor.id + "/logout";
    // Hit logout route
    $http.post(logoutUrl);

    console.log("in localStorage: " + JSON.stringify(localStorage.currentVendor));
  }

  function editVendor(editedVendor, vendorId){
    var url = ip + "/vendor/" + vendorId;
    $http.put(url, editedVendor);
  }


  return {
    signup: signup,
    dropPin: dropPin,
    loginVendor: loginVendor,
    logoutVendor: logoutVendor,
    editVendor: editVendor,
  };
});
