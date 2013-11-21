
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
    $.ajax({
        url: 'AddCab',
        type: 'post',
        data: {
            typeid:$("#addCab #cabTypeId").val(),
            name: $("#addCab #name").val(),
            vno: $("#addCab #vehicleNo").val(),
            desc: $("#addCab #desc").val()
        },
        success: function(obj) {
            obj = eval("(" + obj + ")");
            if (obj.added) {
                informationDisplay("Congrates", "CAB added successfully");
                $("#addCab #name").val('');
                $("#addCab #vehicleNo").val('')
                $("#addCab #desc").val('')
            } else {
                errorDisplay("Sorry !!", obj.resion);
            }
        }
    });
}

function createVehical() {
    $.ajax({
        url: 'AddVehicle',
        type: 'post',
        data: {
            vehicleNo: $("#addVehicleToogle #vehicleNo").val(),
            vehicleModel: $("#addVehicleToogle #vehicleModel").val(),
            capacity: $("#addVehicleToogle #capacity").val(),
            contractorName: $("#addVehicleToogle #contractorName").val(),
            desc: $("#addVehicleToogle #desc").val()
        },
        success: function(obj) {
            obj = eval("(" + obj + ")");
            if (obj.added) {
                informationDisplay("Congrates", "vehicle added successfully");
                $("#addVehicleToogle #vehicleNo").val('');
                $("#addVehicleToogle #vehicleModel").val('')
                $("#addVehicleToogle #capacity").val('')
                $("#addVehicleToogle #contractorName").val('')
                $("#addVehicleToogle #desc").val('')
            } else {
                errorDisplay("Sorry !!", "try again")
            }
        }
    });

}


//angular code

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



var request = angular.module('request', []);
request.service('DataShare', ['$rootScope', function($rootScope) {
        var service = {
            persons: [],
            locations: [],
            locationType: ['PICK', 'DROP', 'VISIT'],
            points: [],
            addPrsn: function(prsn) {
                service.persons.push(prsn);
                $rootScope.$broadcast('person.update');
            },
            remPrsn: function(person) {
                service.persons.splice(service.persons.indexOf(person), 1);
                $rootScope.$broadcast('person.update');
            },
            addLoc: function(loc) {
                service.locations.push(loc);
                $rootScope.$broadcast('loc.update');
            },
            remLoc: function(loc) {
                service.locations.splice(service.locations.indexOf(loc), 1);
                $rootScope.$broadcast('loc.update');
            },
            addPoint: function(point) {
                service.points.push(point);
                $rootScope.$broadcast('point.update');
            },
            remPoint: function(point) {
                service.points.splice(service.points.indexOf(point), 1);
                $rootScope.$broadcast('point.update');
            }

        };
        return service;
    }]);


var PrsnCtrl = ['$scope', 'DataShare', function($scope, $service) {
        $scope.persons = [];
        $scope.$on('person.update', function(event) {
            $scope.persons = $service.persons;
        })
        $scope.addPrsn = function() {
            if ($scope.prnsName == undefined || $scope.prnsName.trim() == "" || $scope.prnsMobile == undefined || $scope.prnsMobile.trim() == "") {
                errorDisplay("!!", "All filed are mandatory")
                return;
            }
            $service.addPrsn({name: $scope.prnsName, mobile: $scope.prnsMobile});
            $scope.prnsName = '';
            $scope.prnsMobile = '';
        };
        $scope.remPrsn = function(person) {
            $service.remPrsn(person);
        };
    }];

var LocCtrl = ['$scope', 'DataShare', function($scope, $service) {
        $scope.locations = [];
        $scope.$on('loc.update', function(event) {
            console.log("loc updated")
            $scope.locations = $service.locations;
        })
        $scope.addLoc = function() {
            $service.addLoc({
                locName: $scope.locName,
                streetName: $scope.streetName,
                areaName: $scope.areaName,
                city: $scope.city,
                state: $scope.state
            });
            $scope.locName = '';
            $scope.streetName = '';
            $scope.areaName = '';
            $scope.city = '';
            $scope.state = '';
        };
        $scope.remLoc = function(loc) {
            $service.remLoc(loc);
        };
    }];

var PointCtrl = ['$scope', 'DataShare', function($scope, $service) {
        $scope.persons = [];
        $scope.locations = [];
        $scope.points = [];
        $scope.$on('person.update', function(event) {
            $scope.persons = $service.persons;
        })
        $scope.$on('loc.update', function(event) {
            $scope.locations = $service.locations;
        })
        $scope.$on('point.update', function(event) {
            console.log("point updated")
            $scope.points = $service.points;
        })
        $scope.addPoint = function() {
            $service.addPoint({loc: $scope.loc, prsn: $scope.prsn, type: $scope.pointType})
            console.log({loc: $scope.loc, prsn: $scope.prsn, type: $scope.pointType});
        }
    }];
request.controller("PrsnCtrl", PrsnCtrl);
request.controller("LocCtrl", LocCtrl);
request.controller("PointCtrl", PointCtrl);

//amgular js cantroller for fetch request
var ReqCtrl = ['$scope', '$http', 'DataShare', function($scope, $http, $service) {

//        $scope.fetchRequest = function() {
//            var postdata = {
//                st: $("#starttime").val(),
//                et: $("#endtime").val(),
//                purpose: $("#purpose").val(),
//                person: $service.persons,
//                location: $service.locations,
//                points: $service.points
//            }
//            console.log(postdata);
//            $http({method: 'post', url: 'SubmitRequest', params: postdata}).success(function(data) {
//                console.log("data");
//                console.log(data);
//            }).error(function(data) {
//                console.log(data);
//            });
//        }
    }];

request.controller("ReqCtrl", ReqCtrl);
//angular js request for the load request view dynamically
request.factory('reqestFactory', function($http) {
    return {
        getRequestsAsync: function(callback) {
            $http.get('RequestView.jsp').success(callback);
        }
    };
});

var requestViewCtrl = ['$scope', '$compile', '$http', function($scope, $compile, $http) {
        $scope.newRquest = function() {
            $http.get('RequestView.jsp').success(function(data) {
                $("#container").html($compile(data)($scope))
            })
        }

    }];
request.controller("requestViewCtrl", requestViewCtrl);


function errorDisplay(type, msg) {
    console.log(msg)
    $("#putError").prepend('<div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert">×</button><strong style="display: inline-block">' + type + '  </strong><div id="msg" style="display: inline-block">&nbsp;' + msg + '</div></div>')
}

function informationDisplay(type, msg) {
    $("#putError").prepend('<div class="alert alert-info alert-dismissable"><button type="button" class="close" data-dismiss="alert">×</button><strong style="display: inline-block">' + type + '  </strong><div id="msg" style="display: inline-block">&nbsp;' + msg + '</div></div>')
}
