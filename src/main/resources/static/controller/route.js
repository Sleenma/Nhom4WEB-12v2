    var app = angular.module("myApp",["ngRoute"])
    app.config(function($routeProvider){
        $routeProvider
        .when("/dienthoai",{
            templateUrl: "../templates/sanpham/chip.html",
        })

        .otherwise({
            redirectTo: "/dienthoai"
        })
    })