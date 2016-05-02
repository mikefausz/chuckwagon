angular.module('starter.services', [])

.factory('HomeService', function($http, $q, $cacheFactory) {
  var cacheEngine = $cacheFactory('starter');

  // var ip = "http://10.0.10.70:8080";
  var ip = "http://107.170.8.42:8080";
  // var ip = "http://localhost:8080";

  var vendorsURL = ip + "/vendor/location";
  function getTrucks() {
      var defer = $q.defer();
      var cache = cacheEngine.get('vendors');
      // IF cache already contains vendors, use those
      if(cache) {
        console.log('found trucks in the cache');
        defer.resolve(cache);
      }
      // ELSE get vendors from server, put them in cache
      else {
        console.log('no trucks in cache. getting from service');
        // $http.get(vendorsURL).then(function(response) {
          // var trucks = response.data;
          var trucks = dummyTrucks;
          var favArr = [];
          if (localStorage.getItem("favoriteVendors")) {
            favArr = JSON.parse(localStorage.getItem("favoriteVendors"));
          }
          trucks.forEach(function(truck) {
            if(localStorage.getItem("favoriteVendors")) {
              if(favArr.indexOf(truck.id)>-1) {
                truck.heart = true;
              } else {
                truck.heart = false;
              }
            } else {
              localStorage.setItem("favoriteVendors", "[]");
            }
          // });

          cacheEngine.put('vendors',  trucks);
          defer.resolve(trucks);
      });
      }
      return defer.promise;
  }

  function getTruck(truckId) {
    var vendors = cacheEngine.get("vendors");
    for (var i = 0; i < vendors.length; i++) {
      if (vendors[i].id === parseInt(truckId)) {
        return vendors[i];
      }
    }
    return null;
  }

  // Dummy data for development

  var dummyTrucks = [{
    id: 10,
    vendorName: 'Bon Banh Mi',
    tagsList: ['Asian', 'Sandwiches'],
    bio: 'Bright counter-serve eatery offering build-your-own banh mi sandwiches, tacos & salads.',
    location: {
      lat: 32.788642,
      lng: -79.950876,
      tweet: "Come get a crispy pork banh mi!",
      imageUrl: "http://img2.10bestmedia.com/Images/Photos/302505/603717-546559605444931-3152538788265396320-n_54_990x660.jpg",
      created: "11:24 AM"},
    profilePictureLocation: 'https://media-cdn.tripadvisor.com/media/photo-s/03/be/da/13/bon-banh-mi.jpg'
  }, {

    id: 11,
    vendorName: 'The Immortal Lobster',
    tagsList: ['Seafood', 'Sandwiches'],
    bio: "We're a family-owned food truck that uses delicious, sustainable Maine Lobster & fresh local produce in hopes to bring New England, down South!!",
    location: {
      lat:32.788097,
      lng:-79.937689,
      tweet: "The best lobster roll in town!!!",
      imageUrl: "http://s3-media4.fl.yelpcdn.com/bphoto/_v5xUva29s1lG415KO4mbA/o.jpg",
      created: "11:24 AM"},
    profilePictureLocation: 'https://static.wixstatic.com/media/95a1a8_f29aff97f9f04fd18e8ab01ae5f8a9d0.jpg/v1/fill/w_1258,h_944,al_c,q_90,usm_0.66_1.00_0.01/95a1a8_f29aff97f9f04fd18e8ab01ae5f8a9d0.jpg'
  }, {
    id: 12,
    vendorName: 'Pink Bellies',
    tagsList: ['BBQ', 'Sandwiches'],
    bio: "We prepare our Vietnamese dishes with modern techniques, old family recipes, and local ingredients. Close relationships with our baker, farmers, and butchers help us share the comfort food we grew up eating in our mom's kitchen.",
    location: {
      lat:32.785400,
      lng: -79.937957,
      tweet: "Calhoun and St. Phillip -- drop by on your study break",
      imageUrl: "https://cdn3.vox-cdn.com/thumbor/VTLQKuFL8MpIzMyEtrQSruwfA2g=/0x0:4896x2754/1050x591/cdn0.vox-cdn.com/uploads/chorus_image/image/47495951/pinkbelliesam.0.0.0.0.jpg",
      created: "11:24 AM"},
    profilePictureLocation: 'https://scontent-iad3-1.xx.fbcdn.net/hprofile-xla1/v/t1.0-1/p320x320/10553630_1042977579099905_4035506777989318956_n.jpg?oh=b8e8ee92c7f4b412a96f86adc387351e&oe=57870715'
  }, {
    id: 13,
    vendorName: 'AutoBanh',
    tagsList: ['Asian', 'Sandwiches'],
    bio: "Auto Banh Food Truck has been slinging delicious and creative Banh Mi sandwiches and salads all over the Charleston area since 2012.",
    location: {
      lat:32.796632,
      lng:-79.944514,
      tweet: "Outside of Tin Roof!",
      imageUrl: "http://media1.fdncms.com/charleston/imager/autobanh-food-truck/u/magnum/4579326/automag.jpg",
      created: "11:24 AM"},
    profilePictureLocation: 'https://scontent-iad3-1.xx.fbcdn.net/hprofile-xtf1/v/t1.0-1/p320x320/12745605_447338082127982_4569082262294463804_n.jpg?oh=4c5791756a9b47f7684ade17805d670c&oe=5776E4E4'
  }, {
    id: 14,
    vendorName: 'SassYass Coffee',
    tagsList: ['Breakfast'],
    bio: "Welcome to Sassyass Coffee! Home of the Espresso-A-Go-Go, Charleston's first and only bike driven mobile coffee shop.",
    location: {
      lat:32.779323,
      lng: -79.931469,
      tweet: "Time for an Espresso-A-Go-Go",
      imageUrl: "http://sassyass.coffee/img/foam_fern.jpg",
      created: "11:24 AM"},
    profilePictureLocation: 'https://d13yacurqjgara.cloudfront.net/users/272561/screenshots/2212995/screen_shot_2015-08-25_at_5.04.04_pm.png'
  }];

  return {
    getTrucks: getTrucks,
    getTruck: getTruck,
  };
});
