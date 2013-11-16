
$(document).ready(function() {
    $("#USER_MANAGE").click(function() {
        if (!$("#USER_MANAGE").hasClass("active")) {
            $.ajax({
                url: 'manageUserView.jsp',
                dataType: 'html',
                success: function(obj) {
                    $("#container").html(obj);
                }
            });
        }
    });

    $("#CAB_MANAGE").click(function() {
        if (!$("#CAB_MANAGE").hasClass("active")) {
            $.ajax({
                url: 'manageCabView.jsp',
                dataType: 'html',
                success: function(obj) {
                    $("#container").html(obj);
                }
            });
        }
    });

    $(".myTask").click(function() {
        $(".myTask").removeClass("active");
        $(this).addClass("active");
    });

});
var o;
function CreateUser() {
    $.ajax({
        url: 'CreateUser',
        type: 'post',
        data: {
            officeId: $("#officeId").val(),
            name: $("#name").val(),
            email: $("#email").val(),
            ext: $("#ext").val(),
            mobNo: $("#mobile").val(),
            managerId: $("#manager").val(),
            roleId: $("#roles :selected").val(),
            deptId: $("#dept").val()
        },
        dataType: 'json',
        success: function(obj) {
            console.log(obj);
            if ("errors" in obj) {
                $("#putError").html("");
                for (var e in obj.errors) {
                    console.log("here i am with erro");
                    errorDisplay("Error!!", obj.errors[e].message);
                }
            } else {
                if ("status" in obj && obj.status) {
                    informationDisplay("User created", "password for user is " + obj.password);
                } else {
                    errorDisplay("User creation faild !!", '');
                }
            }

        }

    })
}

function CreateCab(obj) {

}




var makeRequest = angular.module('makeRequest', [])

makeRequest.factory('reqestFactory', function($http) {
    return {
        getRequestsAsync: function(callback) {
            $http.get('RequestView.jsp').success(callback);
        }
    };
});

makeRequest.controller('requestViewCtrl', function($scope, reqestFactory) {
    reqestFactory.getRequestsAsync(function(obj) {

        $scope.newRquest = function() {

            $("#container").html(obj);
            angular.bootstrap($("#container"));
        }
    });
});




function PrsnCtrl($scope) {
    $scope.persons = [];

    $scope.addPrsn = function() {
        console.log("name : " + $scope.prnsName + ", mob : " + $scope.prnsMobile);
        if ($scope.prnsName == undefined || $scope.prnsName.trim() == "" || $scope.prnsMobile == undefined || $scope.prnsMobile.trim() == "") {
            return;
        }
        $scope.persons.push({name: $scope.prnsName, mobile: $scope.prnsMobile});
        $scope.prnsName = '';
        $scope.prnsMobile = '';
    };

    $scope.remPrsn = function(person) {
        console.log(person)
        $scope.persons.splice($scope.persons.indexOf(person), 1);
    }
}

function LocCtrl($scope) {
    $scope.locations = [];
    $scope.addLoc = function() {
        $scope.locations.push({name: $scope.locName, streetName: $scope.streetName, areaName: $scope.areaName, city: $scope.city, state: $scope.state});
        $scope.locName = '';
        $scope.streetName = '';
        $scope.areaName = '';
        $scope.city = '';
        $scope.state = '';
    }
    $scope.remLoc = function(loc) {
        $scope.locations.splice($scope.locations.indexOf(loc), 1);
    }
}


function errorDisplay(type, msg) {
    console.log(msg)
    $("#putError").prepend('<div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert">×</button><strong style="display: inline-block">' + type + '  </strong><div id="msg" style="display: inline-block">&nbsp;' + msg + '</div></div>')
}

function informationDisplay(type, msg) {
    $("#putError").prepend('<div class="alert alert-info alert-dismissable"><button type="button" class="close" data-dismiss="alert">×</button><strong style="display: inline-block">' + type + '  </strong><div id="msg" style="display: inline-block">&nbsp;' + msg + '</div></div>')
}
