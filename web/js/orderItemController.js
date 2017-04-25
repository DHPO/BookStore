app.controller("orderItemController",function($scope, $http, $timeout){
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
            if(response.data == 1){
                $scope.responseMsg = "succeed";
                $scope.getOrderItem();
                $scope.create = false;
                $scope.edit = false;
            }
            else{$scope.responseMsg = "failed";$scope.fail = true;}
            $timeout(responseMsgReset,2000);
        });
    }
    $scope.getOrderItem = function(){
        $http.get("/getOrderItem").then(function(response){
            $scope.orderItems = response["data"];
        });
    }
    $scope.edit = false;
    $scope.create = false;
    $scope.responseMsg = "";
    $scope.editBtn = function(orderid, bookid,amount){
        $scope.edit = true;
        $scope.create = false;
        $scope.orderid = orderid;
        $scope.bookid = bookid;
        $scope.amount = amount;
    }
    $scope.cancelBtn = function(){
        $scope.edit = false;
        $scope.create = false;
        $scope.fail = false;
    }
    $scope.createBtn = function(){
        $scope.create = true;
        $scope.edit = false;
        $scope.orderid = "";
        $scope.bookid = "";
        $scope.amount = "";
    }
    $scope.submitBtn = function(){
        if($scope.create){var url = "/insert";}
        else if($scope.edit){var url = "/update";}
        var postBody = {
            "orderid": $scope.orderid,
            "bookid": $scope.bookid,
            "amount": $scope.amount,
            "table":"orderItem"
        };
        console.log(postBody);
        post(url, postBody);
    }
    $scope.deleteBtn = function(orderid, bookid){
        if(!confirm("确定删除?"))return;
        $scope.sure = "";
        var postBody = {
            "orderid": orderid,
            "bookid": bookid,
            "table": "orderItem"
        };
        post("/delete",postBody);

    }
})