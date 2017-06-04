var app = angular.module("main",["ui.bootstrap"]);

app.controller("mainController",function($scope, $http, $uibModal){
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

    $scope.uibModalTest = function(){
        $uibModal.open({
            template:"<div id='modal-header'><h3 id='modal-title'>Hello</h3></div>",
            ariaLabelledBy:"head"
        });
    }
});

