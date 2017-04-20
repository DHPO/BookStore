var app = angular.module("main",[]);

app.controller("mainController",function($scope, $http){
    $scope.test = "hello";

    $scope.mode = false;
    $scope.switchBookstore  = function(){
        $scope.mode = false;
    };
    $scope.switchManage  = function(){
        $scope.mode = true;
    };
    $scope.getBooks = function(){
        $http.get("/getBook").then(function(response){
            console.log(response["data"]);
            $scope.books = response["data"];
        });
    };
    $scope.manageMode = 0;
    $scope.modeToBook = function(){$scope.manageMode = 0;};
    $scope.modeToUser = function(){$scope.manageMode = 1;};
    $scope.modeToOrder = function(){$scope.manageMode = 2;};
    $scope.modeToOrderItem = function(){$scope.manageMode = 3;}
});

