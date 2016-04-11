// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'starter.controllers', 'starter.services', 'ngCordova'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
  });
})

.config(function($stateProvider, $urlRouterProvider) {

  $stateProvider

  // Abstract state for the tabs directive
    .state('tab', {
    url: '/tab',
    abstract: true,
    views: {
      'stuff': {
        templateUrl: 'templates/tabs.html',
        controller: 'TabCtrl'
      }
    }
  })

  // Each tab has its own nav history stack:

  ///// USER MODE /////

  // HOME TAB -- DEFAULT
  // Map of all trucks in database centered on user's location
  .state('tab.map', {
    url: '/home',
    views: {
      'tab-home': {
        templateUrl: 'templates/tab-map.html',
        controller: 'MapCtrl'
      }
    }
  })

  // LIST VIEW TAB
  // List view of all trucks in database
  .state('tab.list', {
    url: '/list',
    views: {
      'tab-home': {
        templateUrl: 'templates/tab-listview.html',
        controller: 'ListviewCtrl'
      }
    }
  })

  // DETAIL VIEW TAB
  // Detailed profile view of selected truck
  .state('tab.detailview', {
    url: '/list/:truckId',
    views: {
      'tab-home': {
        templateUrl: 'templates/tab-detailview.html',
        controller: 'DetailviewCtrl'
      }
    }
  })

  // ADVANCED SEARCH TAB
  // Advanced search form
  .state('tab.search', {
      url: '/search',
      views: {
        'tab-search': {
          templateUrl: 'templates/tab-search.html',
          controller: 'SearchCtrl'
        }
      }
    })

  // SEARCH MAP TAB
  // Map of trucks that fit search criteria centered on user's location
  .state('tab.search-map', {
    url: '/search-map',
    views: {
      'tab-search': {
        templateUrl: 'templates/tab-search-map.html',
        controller: 'SearchMapCtrl'
      }
    }
  })

  // SEARCH LIST VIEW TAB
  // List view of trucks that fit search criteria
  .state('tab.search-list', {
    url: '/search-list',
    views: {
      'tab-search': {
        templateUrl: 'templates/tab-search-listview.html',
        controller: 'ListviewCtrl'
      }
    }
  })

  // SEARCH DETAIL VIEW TAB
  // Detailed profile view of selected truck that fits search criteria
  .state('tab.search-detailview', {
    url: '/search-list/:truckId',
    views: {
      'tab-search': {
        templateUrl: 'templates/tab-detailview.html',
        controller: 'DetailviewCtrl'
      }
    }
  })

  // FAVORITES MAP TAB
  // Map of user's favorite trucks centered on user's location
  .state('tab.fav-map', {
    url: '/favorites',
    views: {
      'tab-favorites': {
        templateUrl: 'templates/tab-favorites.html',
        controller: 'FavMapCtrl'
      }
    }
  })

  // LIST VIEW TAB
  // List view of user's favorite trucks
  .state('tab.fav-list', {
    url: '/fav-list',
    views: {
      'tab-favorites': {
        templateUrl: 'templates/tab-favorites-listview.html',
        controller: 'ListviewCtrl'
      }
    }
  })

  // DETAIL VIEW TAB
  // Detailed profile view of selected favorite truck
  .state('tab.fav-detailview', {
    url: '/fav-list/:truckId',
    views: {
      'tab-favorites': {
        templateUrl: 'templates/tab-detailview.html',
        controller: 'DetailviewCtrl'
      }
    }
  })

  ///// VENDOR MODE /////

  // VENDOR LOGIN TAB
  // Vendor login form and signup button
  .state('tab.vendorlogin', {
    url: '/vendorlogin',
    views: {
      'tab-vendor': {
        templateUrl: 'templates/tab-vendorlogin.html',
        controller: 'VendorLoginCtrl'
      }
    }
  })

  // VENDOR SIGNUP TAB
  // Create new vendor form
  .state('tab.vendorsignup', {
    url: '/vendorsignup',
    views: {
      'tab-vendor': {
        templateUrl: 'templates/tab-vendorsignup.html',
        controller: 'VendorsignupCtrl'
      }
    }
  })

  // VENDOR DASHBOARD TAB
  // Vendor pin drop and optional CRUD form
  .state('tab.vendordashboard', {
    url: '/vendordashboard',
    views: {
      'tab-vendor': {
        templateUrl: 'templates/tab-vendordashboard.html',
        controller: 'VendordashboardCtrl'
      }
    }
  })

  // VENDOR EDIT TAB
  // Vendor profile edit form
  .state('tab.vendoredit', {
    url: '/vendoredit/:truckId',
    views: {
      'tab-vendoredit': {
        templateUrl: 'templates/tab-vendoredit.html',
        controller: 'EditCtrl'
      }
    }
  });

  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/tab/home');

});
