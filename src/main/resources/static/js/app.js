angular.module('app', ['shopList','LikedShop','ngRoute']).config(function($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl : "list.html"
        })
        .when("/like", {
            templateUrl : "liked.html"
        }).otherwise({
        redirectTo  : "/"
        })
});