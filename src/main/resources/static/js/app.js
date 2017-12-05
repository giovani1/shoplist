var app=angular.module('app', ['ngRoute']);
var urlBase="http://localhost:8080/ressource";
app.config(function($routeProvider) {
    $routeProvider
        .when("/", {
            //load list.html in ngView at location '/'
            templateUrl : "list.html"
        })
        .when("/like", {
            templateUrl : "liked.html"
        }).otherwise({
        redirectTo  : "/"
        })
});

app.controller('LikedShop', function($scope,$http) {
    $http.get(urlBase+"/like").then(function (response) {
        //hold the list in scope
        $scope.shops = response.data;
    },function (error){
        console.log(error, 'can not get data.');
    });

    $scope.delete = function (shop) {
        $http({
            url: urlBase+'/like',
            method: 'DELETE',
            data:shop.id,
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        }).then(function (response) {
            $scope.shops.splice($scope.shops.indexOf(shop), 1);
        },function (error){
            console.log(error, 'can not get data.');
        });
    };
});
app.controller('shopList', function($scope, $http) {
    $http.get(urlBase+"/").then(function (response) {
        $scope.shops = response.data;
    },function (error){
        console.log(error, 'can not get data.');
    });

    $scope.submit1 = function (shop) {
        $http.post(urlBase+'/like',shop.id).
        then(function (response) {
            $scope.shops.splice($scope.shops.indexOf(shop), 1);
        },function (error){
            console.log(error, 'can not get data.');
        });
    };

    $scope.submit2 = function (shop) {
        $http.post(urlBase+'/dislike',shop.id).
        then(function (response) {
            $scope.shops.splice($scope.shops.indexOf(shop), 1);
        },function (error){
            console.log(error, 'can not get data.');
        });
    };
});