app.service = ('uploadService', function ($http) {
    //上传文件
    this.uploadFile = function () {
        var formData = new FormData();//用来封装上传文件的二进制数据
        formData.append('file', file.files[0]);//文件上传框的name
        return $http({
            method: 'POST',
            url: "../upload.do",
            data: formData,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity  /*二进制序列化*/


        });
    }

});