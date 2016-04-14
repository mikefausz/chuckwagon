angular
    .module('search')
    .factory('SearchService', function($http, $q, $cacheFactory) {
      var cacheEngine = $cacheFactory('search');
      var ip = "http://10.0.10.70:8080";
      // var ip = "http://localhost:8080";
      var searchURL = ip + '/search';

      function sendSearchOptions(processedOptions) {
        var defer = $q.defer();
        $http.post(searchURL, processedOptions).then(function(response) {
          if(trucks) {
            console.log("got trucks from server");
          } else {
            console.log("no trucks from server");
          }
          cacheEngine.put('searchVendors',  vendors);
          defer.resolve(response.data);
        }, function(err) {
          console.log(err);
        });
        return defer.promise;
      }

      function getTrucksFromCache() {
        var defer = $q.defer();
        var trucks = cacheEngine.get('searchVendors').then(function(trucks) {
          if(trucks) {
            console.log("found trucks in cache");
          } else {
            console.log("no trucks found in cache");
          }
          defer.resolve(trucks);
        }, function(err) {
          console.log(err);
        });
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
