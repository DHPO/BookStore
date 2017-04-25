app.controller("orderController",function($scope, $http, $timeout){
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
                $scope.getOrders();
                $scope.create = false;
                $scope.edit = false;
                $scope.success = true;
                $scope.fail = false;
            }
            else{
                $scope.responseMsg = "failed";
                $scope.fail = true;
            }
            $timeout(responseMsgReset,2000);
        });
    }
    $scope.getOrders = function(){
        $http.get("/getOrder").then(function(response){
            $scope.orders = response["data"];
        });
    }
    $scope.edit = false;
    $scope.create = false;
    $scope.responseMsg = "";
    $scope.editBtn = function(orderid, bookid, userid, amount){
        $scope.edit = true;
        $scope.create = false;
        $scope.orderid = orderid;
        $scope.userid = userid;
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
        $scope.orderid = "auto";
        $scope.userid = "";
    }
    $scope.submitBtn = function(){
        if($scope.create){var url = "/insert";}
        else if($scope.edit){var url = "/update";}
        var postBody = {
            "orderid": $scope.orderid,
            "userid": $scope.userid,
            "table":"orders"
        };
        console.log(postBody);
       post(url, postBody);
    }
    $scope.deleteBtn = function(orderid){
        if(!confirm("确定删除?"))return;
        $scope.sure = "";
        var postBody = {
            "orderid": orderid,
            "table": "orders"
        };
        post("/delete",postBody);
        
    }
})