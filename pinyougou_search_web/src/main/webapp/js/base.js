var app = angular.module("pinyougou", []);//定义品优购模块,不分页的
/*定义过滤器
* */
app.filter('trustHtml', ['$sce', function ($sce) {
    return function (data) {//传入参数时被过滤的内容
        return $sce.trustAsHtml(data);//返回的是被过滤后的内容（新人HTML的转换）
    }
}])
