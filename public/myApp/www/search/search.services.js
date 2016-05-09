angular
    .module('search')
    .factory('SearchService', function($http, $q, $cacheFactory) {
      var cacheEngine = $cacheFactory('search');

      // var ip = "http://10.0.10.70:8080";
      // var ip = "http://107.170.8.42:8080";
      // var ip = "http://localhost:8080";
      var ip = "https://chuckwagonapi.tech";

      var searchURL = ip + '/search';

      function sendSearchOptions(processedOptions) {
        var defer = $q.defer();
        $http.post(searchURL, processedOptions).then(function(response) {
          if(response) {
            console.log("got trucks from server");
          } else {
            console.log("no trucks from server");
          }
          var trucks = response.data;
          var favArr = [];

          if (localStorage.getItem("favoriteVendors")) {
            favArr = JSON.parse(localStorage.getItem("favoriteVendors"));
          }
          else {
            localStorage.setItem("favoriteVendors", "[]");
          }

          trucks.forEach(function(truck) {
            if(favArr.indexOf(truck.id)>-1) {
                truck.heart = true;
              }
            else {
              truck.heart = false;
            }
          });

          cacheEngine.put('searchVendors',  trucks);
          defer.resolve(trucks);
        }, function(err) {
          console.log(err);
        });
        return defer.promise;
      }

      function getTrucksFromCache() {
        var defer = $q.defer();
        var trucks = cacheEngine.get('searchVendors');
        defer.resolve(trucks);
        return defer.promise;
      }

      function getTruck(truckId) {
        var trucks = cacheEngine.get('searchVendors');
        for (var i = 0; i < trucks.length; i++) {
          if (trucks[i].id === parseInt(truckId)) {
            return trucks[i];
          }
        }
        return null;
      }

      return {
        getTrucksFromCache: getTrucksFromCache,
        getTruck: getTruck,
        sendSearchOptions: sendSearchOptions,
      };
    });
