app.controller('baseController',function($scope){
/* 
		分页控件配置
		currentPage当前页码数，totalItems总记录数，itemsPerPage每页记录数
		perPageOptions分页选项
		onChange:function()页码变更时自动触发的方法
		 */
		$scope.paginationConf = {
			currentPage : 1,
			totalItems : 10,
			itemsPerPage : 10,
			perPageOptions : [ 10, 20, 30, 40, 50 ],
			onChange : function() {
				$scope.reloadList();//重新加载 
			}

		};
		$scope.reloadList= function() {
			$scope.search($scope.paginationConf.currentPage,
					$scope.paginationConf.itemsPerPage);
		};
		
		
		$scope.selectIds = [];// 用户勾选的ID集合
		//用戶勾选複選框
		$scope.updateSelection = function($event, id) {

			if ($event.target.checked) {
				$scope.selectIds.push(id);
			} else {
				var index=$scope.selectIds.indexOf(id);//查找值的位置；
				$scope.selectIds.splice(index,1);//参数1，移除的位置，参数2，移除的个数
			}
		};
		
	$scope.jsonToString=function(jsonString,key){
		var json=JSON.parse(jsonString);
		var value="";
		for(var i=0;i<json.length;i++){
			if(i>0){
				value+=",";
			}
			value+=json[i][key];	
		}
		return value;
		}

    //从集合中按照 key 查询对象
    $scope.searchObjectByKey=function(list,key,keyValue){
        for(var i=0;i<list.length;i++){
            if(list[i][key]==keyValue){
                return list[i];
            }
        }
        return null;
    }
});