
angular
  .module('vendor', [
    'ui.router'
  ])
  .config(function ($stateProvider) {
      $stateProvider

      // VENDOR LOGIN TAB
      // Vendor login form and signup button
      .state('tab.vendorlogin', {
        url: '/vendorlogin',
        views: {
          'tab-vendor': {
            templateUrl: 'vendor/views/tab-vendorlogin.html',
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
            templateUrl: 'vendor/views/tab-vendorsignup.html',
            controller: 'VendorsignupCtrl'
          }
        }
      })

      // VENDOR DASHBOARD TAB
      // Vendor pin drop and optional CRUD form
      .state('tab.vendordashboard', {
        cache: false,
        url: '/vendordashboard',
        views: {
          'tab-vendor': {
            templateUrl: 'vendor/views/tab-vendordashboard.html',
            controller: 'VendordashboardCtrl'
          }
        }
      })

      // SEARCH DETAIL VIEW TAB
      // Detailed profile view of selected truck that fits search criteria
      .state('tab.dashboard-vendordetail', {
        cache: false,
        url: '/vendordetail/:truckId',
        views: {
          'tab-vendordetail': {
            templateUrl: 'vendor/views/tab-dashdetail.html',
            controller: 'VendordashdetailCtrl'
          }
        }
      })

      // VENDOR EDIT TAB
      // Vendor profile edit form
      .state('tab.vendoredit', {
        cache: false,
        url: '/vendoredit',
        views: {
          'tab-vendoredit': {
            templateUrl: 'vendor/views/tab-vendoredit.html',
            controller: 'EditCtrl'
          }
        }
      });

    });
