<%-- 
    Document   : RequestView
    Created on : Nov 15, 2013, 10:52:09 AM
    Author     : Ashok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<div class="panel">
    <div class="panel-heading">
        <strong>Request</strong>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="panel">
                <div class="row">
                    <div class="col-md-3 input-group">
                        <input type="text" class="form-control" placeholder="Start Time">
                        <span class="input-group-addon glyphicon glyphicon-calendar" ></span>
                    </div>
                    <div class="col-md-offset-4 col-md-3 input-group">
                        <input type="text" class="form-control" placeholder="End Time">
                        <span class="input-group-addon glyphicon" >Hello</span>
                    </div>
                </div>
            </div>
        </div>
        <div ng-controller="PrsnCtrl" class="row">
            <div class="panel">
                <div class="panel-heading well-sm label label-primary"><strong> {{persons.length}} Person</strong></div>
                <div class="panel-body">
                    <div class="col-sm-6 col-md-7" >
                        <div class="thumbnail form-inline" ng-repeat="person in persons">
                            <label class=" well-sm label label-primary">{{persons.indexOf(person)+1}}</label>
                            <div class="caption">
                                <h3>{{person.name}}</h3>
                                <p ng-model="mobileNo">{{person.mobile}}</p>
                                <span class="label label-danger btn-block" ng-click="remPrsn(person)">Remove</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-5">
                        <form class="thumbnail" ng-submit="addPrsn()">
                            <label class="well-sm label label-primary">{{persons.length+1}}</label>
                            <div class="caption">
                                <div class="row mgt10">
                                    <div class="col-md-10 col-md-offset-1">
                                        <div>
                                            <div class="control-group">
                                                <label for="name" class="control-label">Name</label>
                                                <input type="text" ng-model="prnsName"  size="30" class="form-control"
                                                       placeholder="Name">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mgt10">
                                    <div class="col-md-10 col-md-offset-1">
                                        <div>
                                            <div class="control-group">
                                                <label for="mobileNo" class="control-label">Mobile No</label>
                                                <input type="text" ng-model="prnsMobile"  size="30" class="form-control"
                                                       placeholder="Mobile No">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row" style="margin-top: 15px;">
                                    <div class="col-md-7 col-md-offset-4">
                                        <div>
                                            <div class="control-group">
                                                <button class="form-control btn btn-primary" type="submit" value="add" class="form-control btn btn-primary"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;Person</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div ng-controller="LocCtrl" class="row">
            <div class="panel" style="background-color: whitesmoke;">
                <div class="panel-heading well-sm label label-primary"><strong> {{locations.length}} Location</strong></div>
                <div class="panel-body">

                    <div class="col-sm-6 col-md-7" >
                        <div class="thumbnail form-inline" ng-repeat="location in locations">
                            <label class=" well-sm label label-primary">{{locations.indexOf(location)+1}}</label>
                            <div class="caption">
                                <h3>{{location.locName}}</h3>
                                <p >{{location.streetName}}</p>
                                <p >{{location.areaName}}</p>
                                <p>{{location.city}}</p>
                                <p>{{location.state}}</p>
                                <span class="label label-danger btn-block" ng-click="remLoc(location)">Remove</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-5">
                        <form class="thumbnail" ng-submit="addLoc()" class="form-group">
                            <label class="well-sm label label-primary">{{location.length+1}}</label>
                            <div class="caption">
                                <div class="row mgt10">
                                    <div class="col-md-10 col-md-offset-1">
                                        <div>
                                            <div class="control-group">
                                                <label for="name" class="control-label">Name</label>
                                                <input type="text" ng-model="locName"  size="30" class="form-control"
                                                       placeholder="Location">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mgt10">
                                    <div class="col-md-10 col-md-offset-1">
                                        <div>
                                            <div class="control-group">
                                                <label for="name" class="control-label">Street</label>
                                                <input type="text" ng-model="streetName"  size="30" class="form-control"
                                                       placeholder="Street">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mgt10">
                                    <div class="col-md-10 col-md-offset-1">
                                        <div>
                                            <div class="control-group">
                                                <label for="name" class="control-label">Area</label>
                                                <input type="text" ng-model="areaName"  size="30" class="form-control"
                                                       placeholder="Area Name">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mgt10">
                                    <div class="col-md-10 col-md-offset-1">
                                        <div>
                                            <div class="control-group">
                                                <label for="name" class="control-label">City</label>
                                                <input type="text" ng-model="city"  size="30" class="form-control"
                                                       placeholder="City">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mgt10">
                                    <div class="col-md-10 col-md-offset-1">
                                        <div>
                                            <div class="control-group">
                                                <label for="name" class="control-label">State</label>
                                                <input type="text" ng-model="state"  size="30"  class="form-control"
                                                       placeholder="State">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                               

                                <div class="row" style="margin-top: 15px;">
                                    <div class="col-md-8 col-md-offset-3">
                                        <div>
                                            <div class="control-group">
                                                <button class="form-control btn btn-primary" type="submit" value="add" class="form-control btn btn-primary"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;Location</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
