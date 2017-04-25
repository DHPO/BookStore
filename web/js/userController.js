app.controller("userController",function($scope, $http, $timeout){
    var json2kv = function(dict){
        var ret = [];
        for(var key in dict){
            ret.push(key + "=" + dict[key]);
        }
        console.log(ret);
        return ret.join("&");
    };

    var responseMsgReset = function () {
        $scope.responseMsg = "";
    };

    var post = function(url, postBody){
        $http.post(url, json2kv(postBody),{headers:{'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}}).then(function(response){
            if(response.data >= 1){
                $scope.responseMsg = "succeed";
                $scope.getUsers();
                $scope.create = false;
                $scope.edit = false;
            }
            else{$scope.responseMsg = "failed";$scope.fail = true;}
            $timeout(responseMsgReset,2000);
        });
    }
    $scope.getUsers = function(){
        $http.get("/getUser").then(function(response){
            $scope.users = response["data"];
        });
    }
    $scope.edit = false;
    $scope.create = false;
    $scope.responseMsg = "";
    $scope.editBtn = function(id, name, email){
        $scope.edit = true;
        $scope.create = false;
        $scope.id = id;
        $scope.name = name;
        $scope.email = email;
    }
    $scope.cancelBtn = function(){
        $scope.edit = false;
        $scope.create = false;
        $scope.fail = false;
    }
    $scope.createBtn = function(){
        $scope.create = true;
        $scope.edit = false;
        $scope.id = "auto";
        $scope.name = "";
        $scope.email = "";
    }
    $scope.submitBtn = function(){
        if($scope.create){var url = "/insert";}
        else if($scope.edit){var url = "/update";}
        var postBody = {
            "id": $scope.id,
            "name": $scope.name,
            "email": $scope.email,
            "table":"user"
        };
       post(url, postBody);
    }
    $scope.deleteBtn = function(id){
        if(!confirm("确定删除?"))return;
        $scope.sure = "";
        var postBody = {
            "id": id,
            "table": "user"
        };
        post("/delete",postBody);
        
    }
})