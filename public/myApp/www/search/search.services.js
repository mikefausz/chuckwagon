angular
    .module('search')
    .factory('SearchService', function($http, $q, $cacheFactory) {
      var cacheEngine = $cacheFactory('search');
      var ip = "http://10.0.10.70:8080";
      // var ip = "http://localhost:8080";

      function getTrucks() {
          var defer = $q.defer();
          var cache = cacheEngine.get('searchVendors');
          // IF cache already contains vendors, use those
          if(cache) {
            console.log('found trucks in the cache');
            defer.resolve(cache);
          }
          // ELSE get vendors from server, put them in cache
          else {
            console.log('no trucks in cache. getting from service');
            // $http.get(vendorsURL).then(function(response) {
            //  cacheEngine.put('searchVendors',  response);
            //  defer.resolve(response);
            // });

            // CUT THIS:
            var vendors = trucks;
            cacheEngine.put('searchVendors',  vendors);
            defer.resolve(vendors);
            //
          }
          return defer.promise;
      }

      function getTruck(truckId) {
        for (var i = 0; i < trucks.length; i++) {
          if (trucks[i].id === parseInt(truckId)) {
            return trucks[i];
          }
        }
        return null;
      }

      // Dummy data for development
      var trucks = [{
        id: 10,
        name: 'Bon Banh Mi',
        tags: 'Vietnamese, Sandwiches',
        bio: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        location: {lat:32.788642, lng:-79.950876},
        profileImg: 'https://media-cdn.tripadvisor.com/media/photo-s/03/be/da/13/bon-banh-mi.jpg'
      }, {
        id: 11,
        name: 'The Immortal Lobster',
        tags: 'Seafood, Sandwiches',
        bio: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        location: {lat:32.788097, lng:-79.937689},
        profileImg: 'https://static.wixstatic.com/media/95a1a8_f29aff97f9f04fd18e8ab01ae5f8a9d0.jpg/v1/fill/w_1258,h_944,al_c,q_90,usm_0.66_1.00_0.01/95a1a8_f29aff97f9f04fd18e8ab01ae5f8a9d0.jpg'
      }, {
        id: 12,
        name: 'Pink Bellies',
        tags: 'Barbecue, Sandwiches',
        bio: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        location: {lat:32.785400, lng: -79.937957},
        profileImg: 'https://scontent-iad3-1.xx.fbcdn.net/hprofile-xla1/v/t1.0-1/p320x320/10553630_1042977579099905_4035506777989318956_n.jpg?oh=b8e8ee92c7f4b412a96f86adc387351e&oe=57870715'
      }, {
        id: 13,
        name: 'Autobanh',
        tags: 'Vietnamese, Sandwiches',
        bio: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        location: {lat:32.796632, lng:-79.944514},
        profileImg: 'https://scontent-iad3-1.xx.fbcdn.net/hprofile-xtf1/v/t1.0-1/p320x320/12745605_447338082127982_4569082262294463804_n.jpg?oh=4c5791756a9b47f7684ade17805d670c&oe=5776E4E4'
      }, {
        id: 14,
        name: 'The Coffee Cart',
        tags: 'Coffee, Drinks',
        bio: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
        location: {lat:32.779323, lng: -79.931469},
        profileImg: 'https://scontent-iad3-1.xx.fbcdn.net/hprofile-xtf1/v/t1.0-1/p320x320/12745605_447338082127982_4569082262294463804_n.jpg?oh=4c5791756a9b47f7684ade17805d670c&oe=5776E4E4'
      }];

      return {
        getTrucks: getTrucks,
        getTruck: getTruck,
      };
    });
