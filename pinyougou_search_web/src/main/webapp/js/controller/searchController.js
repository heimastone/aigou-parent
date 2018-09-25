app.controller('searchController', function ($scope, searchService) {
//定义搜索对象的结构，category:商品分类，brand:品牌，spec:规格
    $scope.searchMap = {'keywords':'','category':'','brand':'','spec':{}};


    $scope.search = function () {
        // alert($scope.resultMap);
        searchService.search($scope.searchMap).success(function (response) {

            $scope.resultMap = response;



        })
    }
//添加搜索项，改变searchMap的值
    $scope.addSearchItem = function (key, value) {
        if (key == 'category' || key == 'brand') {//如果用户点击的是分类或者品牌

            $scope.searchMap[key] = value;
        } else {
            //如果用户点击的是规格
          $scope.searchMap.spec[key] = value;

        }
        $scope.search();
    };


    //撤销搜索项，移除searchMap的值
    $scope.removeSearchItem=function (key) {
        if (key == 'category' || 'brand') {//如果用户点击的是分类或者品牌

            $scope.searchMap[key] = '';
        } else {//如果用户点击的是规格

           delete $scope.searchMap.spec[key] ;
        }
        $scope.search();
    }
})