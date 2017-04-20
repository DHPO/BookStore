app.directive("bookInfo", function(){
  return { 
    restrict: 'E', 
    scope: { 
      info: '=' 
    }, 
    templateUrl: 'js/bookInfo.html' 
  }; 
});

app.directive("viewMode", function(){
    return {
        restrict: "E",
        scope:{
            info:'='
        },
        templateUrl: 'js/viewMode.html'
    };
})

app.directive("manageMode", function(){
    return {
        restrict: "E",
        scope:{
            info:'='
        },
        templateUrl: 'js/manageMode.html'
    };
})

app.directive("bookManage", function(){
    return {
        restrict: "E",
        scope:{
            info:'='
        },
        templateUrl: 'js/bookManager.html'
    };
})

app.directive("orderManage", function(){
    return {
        restrict: "E",
        scope:{
            info:'='
        },
        templateUrl: 'js/orderManage.html'
    };
})

app.directive("orderItemManage", function(){
    return {
        restrict: "E",
        scope:{
            info:'='
        },
        templateUrl: 'js/orderItemManage.html'
    };
})

app.directive("userManage", function(){
    return {
        restrict: "E",
        scope:{
            info:'='
        },
        templateUrl: 'js/userManager.html'
    };
})