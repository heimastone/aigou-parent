app.controller('searchController', function ($scope, searchService) {


    $scope.search = function () {
       // alert($scope.resultMap);
        searchService.search($scope.searchMap).success(function (response) {
            $scope.resultMap = response;

        })
    }
})