(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
angular.module('starter', [
  'ionic',
  'ngMessages',
  'ngSanitize',
  'starter.controllers',
  'starter.services',
  'ngCordova',
  'search',
  'favorites',
  'vendor',
  'ui.bootstrap'
  // 'ngAnimate'
])

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

.config(function($stateProvider, $urlRouterProvider, $ionicConfigProvider) {
  $ionicConfigProvider.backButton.previousTitleText(false).text('');
  $stateProvider

  // Abstract state for the tabs directive
    .state('tab', {
    url: '/tab',
    abstract: true,
    views: {
      'stuff': {
        templateUrl: 'js/views/tabs.html',
        controller: 'TabCtrl'
      }
    }
  })

  // Each tab has its own nav history stack:

  ///// USER MODE /////

  // HOME TAB -- DEFAULT
  // Map of all trucks in database centered on user's location
  .state('tab.map', {
    cache: false,
    url: '/home',
    views: {
      'tab-home': {
        templateUrl: 'js/views/tab-map.html',
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
        templateUrl: 'js/views/tab-listview.html',
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
        templateUrl: 'js/views/tab-detailview.html',
        controller: 'DetailviewCtrl'
      }
    }


  })
  ////Clears back button text
  // .config(function($ionicConfigProvider) {
  // // Remove back button text completely
  // $ionicConfigProvider.backButton.previousTitleText(false).text('');
  // });

  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/tab/home');

})

},{}]},{},[1]);
