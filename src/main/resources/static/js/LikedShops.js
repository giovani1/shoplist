angular.module('LikedShop', [])
    .controller('LikedShop', function($scope,$http) {
        var urlBase="http://localhost:8080/ressource";

        $http.get(urlBase+"/like").then(function (response) {
            console.log(response, 'res');
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
                console.log(response);
                $scope.shops.splice($scope.shops.indexOf(shop), 1);
            },function (error){
                console.log(error, 'can not get data.');
            });
        };
    });