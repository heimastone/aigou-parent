app.controller('brandcontroller', function($scope, $http,$controller, brandService) {
	$controller('baseController',{$scope:$scope});//继承
	
	// 查询品牌列表
	$scope.findAll = function() {
		brandServie.findAll().success(function(response) {
			$scope.list = response;
		}

		);
	}

	$scope.findPage = function(page, size) {
		brandService.findPage(page, size).success(function(response) {
			$scope.list = response.rows;// 显示当前页面的数据
			$scope.paginationconf.totalItems = response.total;// 更新总记录数

		})

	};
	$scope.save = function() {
		var Object = null;
		if ($scope.entity.id != null) {
			Object = brandService.update($scope.entity);
		} else {
			Object = brandService.add($scope.entity);
		}

		Object.success(function(response) {
			if (response.success) {
				$scope.reloadList();// 刷新列表
			} else {
				alert(response.message);
			}
		})
	}

	$scope.findOne = function(id) {
		brandService.findOne(id).success(function(response) {
			$scope.entity = response;

		})
	}

	$scope.dele = function() {
		brandService.dele($scope.selectIds).success(function(response) {
			if (response.success) {
				$scope.reloadList();
			} else {
				alert(response.message);
			}

		})
	}
	$scope.searchEntity = {};// 初始化
	$scope.search = function(page, size) {
		brandService.search(page, size, $scope.searchEntity).success(
				function(response) {
					$scope.list = response.rows;// 显示当前页面的数据
					$scope.paginationConf.totalItems = response.total;// 更新总记录数

				})

	};

});