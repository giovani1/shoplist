angular.module('shopList', [])
    .controller('shopList', function($scope, $http) {
        var urlBase="http://localhost:8080/ressource";

        $http.get(urlBase+"/").then(function (response) {
            console.log(response, 'res');
            $scope.shops = response.data;
        },function (error){
            console.log(error, 'can not get data.');
        });

        $scope.submit1 = function (shop) {
            $http.post(urlBase+'/like',shop.id).then(function (response) {
                console.log(response);
                console.log("like success");
                $scope.shops.splice($scope.shops.indexOf(shop), 1);
            },function (error){
                console.log(error, 'can not get data.');
            });
        };

        $scope.submit2 = function (shop) {
            $http.post(urlBase+'/dislike',shop.id).then(function (response) {
                console.log(response);
                console.log("dislike success");
                $scope.shops.splice($scope.shops.indexOf(shop), 1);
            },function (error){
                console.log(error, 'can not get data.');
            });
        };
    });