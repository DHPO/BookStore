app.controller("bookController",function($scope, $http, $timeout){
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
                $scope.getBooks();
                $scope.create = false;
                $scope.edit = false;
            }
            else{
                $scope.responseMsg = "failed";
                $scope.fail = true;
            }
            $timeout(responseMsgReset,2000);
        });
    }
    $scope.getBooks = function(){
        $http.get("/getBook").then(function(response){
            $scope.books = response["data"];
        });
    }
    $scope.edit = false;
    $scope.create = false;
    $scope.responseMsg = "";
    $scope.editBtn = function(id, name, price){
        $scope.edit = true;
        $scope.create = false;
        $scope.bookID = id;
        $scope.bookName = name;
        $scope.price = price;
    }
    $scope.cancelBtn = function(){
        $scope.edit = false;
        $scope.create = false;
        $scope.fail = false;
    }
    $scope.createBtn = function(){
        $scope.create = true;
        $scope.edit = false;
        $scope.bookID = "auto";
        $scope.bookName = "";
        $scope.price = "";
    }
    $scope.submitBtn = function(){
        if($scope.create){
            //var url = "/insertBook";
            var postBody = {
                "name": $scope.bookName,
                "price": $scope.price
            };
            post("/insertBook", postBody);
        }
        else if($scope.edit){
            //var url = "/updateBook";
            var postBody = {
                "id": $scope.bookID,
                "name": $scope.bookName,
                "price": $scope.price
            };
            post("/updateBook", postBody);
        }
    }
    $scope.deleteBtn = function(id){
        if(!confirm("确定删除?"))return;
        $scope.sure = "";
        var postBody = {
            "id": id
        };
        post("/deleteBook",postBody);
        
    }
})