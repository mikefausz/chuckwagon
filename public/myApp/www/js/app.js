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

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider

  // setup an abstract state for the tabs directive
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

  // .state('tab.home', {
  //   url: '/home',
  //   abstract: true,
  //   views: {
  //     'tab-home': {
  //       templateUrl: 'templates/tab-home.html',
  //       controller: 'HomeCtrl'
  //     }
  //   }
  // })

  .state('tab.map', {
    url: '/home',
    views: {
      'tab-home': {
        templateUrl: 'templates/tab-map.html',
        controller: 'MapCtrl'
      }
    }
  })

  .state('tab.list', {
    url: '/list',
    views: {
      'tab-home': {
        templateUrl: 'templates/tab-listview.html',
        controller: 'ListviewCtrl'
      }
    }
  })

  .state('tab.detailview', {
    url: '/list/:truckId',
    views: {
      'tab-home': {
        templateUrl: 'templates/tab-detailview.html',
        controller: 'DetailviewCtrl'
      }
    }
  })

  /////FAVORITES/////
  // .state('tab.favorites', {
  //   url: '/favorites',
  //   abstract: true,
  //   views: {
  //     'tab-favorites': {
  //       templateUrl: 'templates/tab-favorites.html',
  //       controller: 'FavoritesCtrl'
  //     }
  //   }
  // })

  .state('tab.fav-map', {
    url: '/favorites',
    views: {
      'tab-favorites': {
        templateUrl: 'templates/tab-favorites.html',
        controller: 'MapCtrl'
      }
    }
  })

  .state('tab.fav-list', {
    url: '/fav-list',
    views: {
      'tab-favorites': {
        templateUrl: 'templates/tab-favorites-listview.html',
        controller: 'ListviewCtrl'
      }
    }
  })

  .state('tab.fav-detailview', {
    url: '/fav-list/:truckId',
    views: {
      'tab-favorites': {
        templateUrl: 'templates/tab-detailview.html',
        controller: 'DetailviewCtrl'
      }
    }
  })

/////VENDOR/////

  .state('tab.vendoraccess', {
    url: '',
    views: {
      'tab-vendor': {
        templateUrl: 'templates/tab-vendoraccess.html',
        controller: 'VendorAccessCtrl'
      }
    }
  })

  .state('tab.vendorsignup', {
    url: '/vendorsignup',
    views: {
      'tab-vendor': {
        templateUrl: 'templates/tab-vendorsignup.html',
        controller: 'VendorsignupCtrl'
      }
    }
  })
//////////////////////NEED TO ADJUST TAB VIEW
  .state('tab.vendordashboard', {
    url: '/vendordashboard',
    views: {
      'tab-vendor': {
        templateUrl: 'templates/tab-vendordashboard.html',
        controller: 'VendordashboardCtrl'
      }
    }
  })

  .state('tab.edit', {
    url: '/edit',
    views: {
      'tab-edit': {
        templateUrl: 'templates/tab-edit.html',
        controller: 'EditCtrl'
      }
    }
  })

  .state('tab.search', {
      url: '/search',
      views: {
        'tab-search': {
          templateUrl: 'templates/tab-search.html',
          controller: 'SearchCtrl'
        }
      }
    })
    .state('tab.truck-detail', {
      url: '/trucks/:truckId',
      views: {
        'tab-search': {
          templateUrl: 'templates/tab-detailview.html',
          controller: 'DetailviewCtrl'
        }
      }
    })

    // .state('tab.trucks', {
    //     url: '/trucks',
    //     views: {
    //       'tab-trucks': {
    //         templateUrl: 'templates/tab-trucks.html',
    //         controller: 'trucksCtrl'
    //       }
    //     }
    //   })
    //   .state('tab.truck-detail', {
    //     url: '/trucks/:truckId',
    //     views: {
    //       'tab-trucks': {
    //         templateUrl: 'templates/truck-detail.html',
    //         controller: 'truckDetailCtrl'
    //       }
    //     }
    //   })

  // .state('tab.account', {
  //   url: '/account',
  //   views: {
  //     'tab-account': {
  //       templateUrl: 'templates/tab-account.html',
  //       controller: 'AccountCtrl'
  //     }
  //   }
  // });

  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/tab/home');

});
// // Ionic Starter App
//
// // angular.module is a global place for creating, registering and retrieving Angular modules
// // 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// // the 2nd parameter is an array of 'requires'
// // 'starter.services' is found in services.js
// // 'starter.controllers' is found in controllers.js
// angular.module('starter', ['ionic', 'starter.controllers', 'starter.services', 'ngCordova'])
//
// .run(function($ionicPlatform) {
//   $ionicPlatform.ready(function() {
//     // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
//     // for form inputs)
//     if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
//       cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
//       cordova.plugins.Keyboard.disableScroll(true);
//
//     }
//     if (window.StatusBar) {
//       // org.apache.cordova.statusbar required
//       StatusBar.styleDefault();
//     }
//   });
// })
//
// .config(function($stateProvider, $urlRouterProvider) {
//
//   // Ionic uses AngularUI Router which uses the concept of states
//   // Learn more here: https://github.com/angular-ui/ui-router
//   // Set up the various states which the app can be in.
//   // Each state's controller can be found in controllers.js
//   $stateProvider
//
//   // setup an abstract state for the tabs directive
//     .state('tab', {
//     url: '/tab',
//     abstract: true,
//     templateUrl: 'templates/tabs.html'
//   })
//
//   // Each tab has its own nav history stack:
//
//   .state('tab.home', {
//     url: '/home',
//     abstract: true,
//     views: {
//       'tab-home': {
//         templateUrl: 'templates/tab-home.html',
//         controller: 'HomeCtrl'
//       }
//     }
//   })
//
//   .state('tab.home.map', {
//     url: '',
//     views: {
//       'tab-content': {
//         templateUrl: 'templates/tab-map.html',
//         controller: 'MapCtrl'
//       }
//     }
//   })
//
//   .state('tab.home.list', {
//     url: '/list',
//     views: {
//       'tab-content': {
//         templateUrl: 'templates/tab-listview.html',
//         controller: 'ListviewCtrl'
//       }
//     }
//   })
//
//   .state('tab.home.detailview', {
//     url: '/list/:truckId',
//     views: {
//       'tab-content': {
//         templateUrl: 'templates/tab-detailview.html',
//         controller: 'DetailviewCtrl'
//       }
//     }
//   })
//
//   /////FAVORITES/////
//   .state('tab.favorites', {
//     url: '/favorites',
//     abstract: true,
//     views: {
//       'tab-favorites': {
//         templateUrl: 'templates/tab-favorites.html',
//         controller: 'FavoritesCtrl'
//       }
//     }
//   })
//
//   .state('tab.favorites.map', {
//     url: '',
//     views: {
//       'tab-content': {
//         templateUrl: 'templates/tab-map.html',
//         controller: 'MapCtrl'
//       }
//     }
//   })
//
//   .state('tab.favorites.list', {
//     url: '/list',
//     views: {
//       'tab-content': {
//         templateUrl: 'templates/tab-favorites-listview.html',
//         controller: 'ListviewCtrl'
//       }
//     }
//   })
//
//   .state('tab.favorites.detailview', {
//     url: '/list/:truckId',
//     views: {
//       'tab-content': {
//         templateUrl: 'templates/tab-detailview.html',
//         controller: 'DetailviewCtrl'
//       }
//     }
//   })
//
//
//
//
//
//   .state('tab.vendoraccess', {
//     url: '/vendoraccess',
//     views: {
//       'tab-vendoraccess': {
//         templateUrl: 'templates/tab-vendoraccess.html',
//         controller: 'VendorAccessCtrl'
//       }
//     }
//   })
//
//   .state('tab.vendorsignup', {
//     url: '/vendorsignup',
//     views: {
//       'tab-vendorsignup': {
//         templateUrl: 'templates/tab-vendorsignup.html',
//         controller: 'VendorsignupCtrl'
//       }
//     }
//   })
//
//   .state('tab.vendordashboard', {
//     url: '/vendordashboard',
//     views: {
//       'tab-vendordashboard': {
//         templateUrl: 'templates/tab-vendordashboard.html',
//         controller: 'VendordashboardCtrl'
//       }
//     }
//   })
//
//   .state('tab.edit', {
//     url: '/edit',
//     views: {
//       'tab-edit': {
//         templateUrl: 'templates/tab-edit.html',
//         controller: 'EditCtrl'
//       }
//     }
//   })
//
//   .state('tab.search', {
//       url: '/search',
//       views: {
//         'tab-search': {
//           templateUrl: 'templates/tab-search.html',
//           controller: 'SearchCtrl'
//         }
//       }
//     })
//     .state('tab.truck-detail', {
//       url: '/trucks/:truckId',
//       views: {
//         'tab-search': {
//           templateUrl: 'templates/tab-detailview.html',
//           controller: 'DetailviewCtrl'
//         }
//       }
//     })
//
//     // .state('tab.trucks', {
//     //     url: '/trucks',
//     //     views: {
//     //       'tab-trucks': {
//     //         templateUrl: 'templates/tab-trucks.html',
//     //         controller: 'trucksCtrl'
//     //       }
//     //     }
//     //   })
//     //   .state('tab.truck-detail', {
//     //     url: '/trucks/:truckId',
//     //     views: {
//     //       'tab-trucks': {
//     //         templateUrl: 'templates/truck-detail.html',
//     //         controller: 'truckDetailCtrl'
//     //       }
//     //     }
//     //   })
//
//   // .state('tab.account', {
//   //   url: '/account',
//   //   views: {
//   //     'tab-account': {
//   //       templateUrl: 'templates/tab-account.html',
//   //       controller: 'AccountCtrl'
//   //     }
//   //   }
//   // });
//
//   // if none of the above states are matched, use this as the fallback
//   $urlRouterProvider.otherwise('/tab/home');
//
// });
