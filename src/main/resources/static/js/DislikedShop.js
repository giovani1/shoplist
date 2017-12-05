angular.module('DislikedShop', [])
    .controller('DislikedShop', function($scope,$http) {
        $scope.submit = function () {
            $http.post('/dislike',$scope.text);
        };

    });