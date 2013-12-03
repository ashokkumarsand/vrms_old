<%-- 
    Document   : RequestView
    Created on : Nov 15, 2013, 10:52:09 AM
    Author     : Ashok
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.vrms.authentication.core.Constants"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.vrms.connection.JDBCConnectionPool"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="panel">
    <table class="table">
        <thead>
            <tr>
                <th>S.No</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Purpose</th>
                <th>Status</th>
            </tr>
            <%
                JDBCConnectionPool pool = new JDBCConnectionPool();
                Connection con = pool.checkOut();
                PreparedStatement ps = con.prepareStatement("SELECT request.start_time, request.end_time, request.purpose, request_status.request_status_values FROM public.request,   public.request_status WHERE  request.request_status = request_status.request_status_id AND request.user_id = ? order by request.timestamp desc");
                ps.setInt(1, Integer.parseInt(session.getAttribute(Constants.USERID).toString()));
                ResultSet rs = ps.executeQuery();
                int i = 1;
                while (rs.next()) {

            %>
            <tr>
                <td><%=i++%></td>
                <td><%
                    Timestamp stsm = rs.getTimestamp("start_time");
                    Date std = new Date(stsm.getTime());
                    DateFormat df = new SimpleDateFormat("dd/MM/YYYY HH:MM");
                    String startDate = df.format(std);
                    log("sdate :: " + startDate);
                    %>
                    <%=stsm%> </td>
                <td>
                    <%
                        Timestamp etsm = rs.getTimestamp("end_time");
                        Date etd = new Date(etsm.getTime());
                        String endDate = df.format(etd);
                        log("edate :: " + endDate);
                    %>

                    <%=etsm%> 
                </td>
                <td><%= rs.getString("purpose")%></td>
                <td><%= rs.getString("request_status_values")%></td>
            </tr>
            <%
                }
                if (i == 1) {
            %>
            <tr>
                <td colspan="5">
                    <label> No request found </label>
                </td>

            </tr>
            <%                                                }
            %>
        </thead>
    </table>
</div>
<div class="panel">
    <div class="panel-heading well-sm label label-primary">
        <strong>Request</strong>
    </div>
    <div class="panel-body">
        <div class="row well">
            <div class="row">
                <div class="panel">
                    <div class="panel-heading well-sm label label-primary"><strong> Details</strong></div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="control-group col-md-4">
                                <label for="starttime" class="control-label">From</label>
                                <div  id="st" class="input-group input-append date" >
                                    <input type="text" data-format="dd/MM/yyyy HH:mm" id="starttime"  ng-model="st" class="form-control" placeholder="DATE" />
                                    <span class="add-on input-group-addon" id="stIcon"> <i data-time-icon="icon-time" data-date-icon="icon-calendar" class="glyphicon glyphicon-calendar"></i></span>
                                </div>
                            </div>
                            <div  class="control-group col-md-offset-3 col-md-4">
                                <label for="endtime" class="control-label">Till</label>
                                <div id="et"  class="input-group input-append date">
                                    <input type="text" data-format="dd/MM/yyyy HH:mm" id="endtime"  ng-model="et"class="form-control" placeholder="DATE" />
                                    <span class="add-on input-group-addon" id="etTime"><i data-time-icon="icon-time" data-date-icon="icon-calendar" class="glyphicon glyphicon-calendar"></i></span>
                                </div>
                            </div>
                        </div>

                        <script type="text/javascript">
                            $('#starttime').datetimepicker({
                                format: 'd/m/Y H:i',
                                step: 10
                            });
                            $('#stIcon').click(function() {
                                $('#starttime').datetimepicker('show'); //support hide,show and destroy command
                            });

                            $('#endtime').datetimepicker({
                                format: 'd/m/Y H:i',
                                step: 10
                            });
                            $('#etTime').click(function() {
                                $('#endtime').datetimepicker('show'); //support hide,show and destroy command
                            });
                        </script>
                        <div class="row  mgt10">
                            <div class="control-group col-md-11">
                                <label for="purpose" class="control-label">Purpose</label>
                                <textarea style="resize: vertical;" id="purpose" ng-model="purpose" maxlength="200" id="purpose" type="" class="form-control" placeholder="Purpose"></textarea>
                            </div>
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
                <div class="panel">
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
            <div ng-controller="PointCtrl" class="row">
                <div class="panel" >
                    <div class="panel-heading well-sm label label-success">
                        <strong> {{points.length}}&nbsp;&nbsp; <span class="glyphicon glyphicon-flag"></span></strong>
                    </div>
                    <div class="panel-body">
                        <div class="col-sm-6 col-md-7" >
                            <div class="thumbnail form-inline" ng-repeat="point in points">
                                <label class=" well-sm label label-primary">{{points.indexOf(point)+1}}</label>
                                <div class="caption">
                                    <h3>{{locations[point.loc].locName}}</h3>
                                    <h4>{{point.type}}</h4>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 col-md-5">
                            <form class="thumbnail" ng-submit="addPoint()" class="form-group">
                                <label class="well-sm label label-primary">{{points.length+1}}</label>
                                <div class="caption">
                                    <div class="row mgt10">
                                        <div class="col-md-10 col-md-offset-1">
                                            <div>
                                                <div class="control-group">
                                                    <label for="selectLocation" class="control-label">Location</label>
                                                    <select id="selectLocation" ng-model="loc" class="form-control">
                                                        <option selected="selected">Select </option>
                                                        <option  ng-show="!!locations.length" ng-repeat="location in locations" value="{{locations.indexOf(location)}}"><script>console.log(location)</script>{{location.locName}}</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mgt10">
                                        <div class="col-md-10 col-md-offset-1">
                                            <div>
                                                <div class="control-group">
                                                    <label for="selectPerson" class="control-label">Person</label>
                                                    <select id="selectPerson" multiple="" class="form-control" ng-model="prsn">
                                                        <option ng-repeat="person in persons" value="{{persons.indexOf(person)}}">{{person.name}}</option>
                                                    </select><br />
                                                    <input type="radio" name="pointType" ng-model="pointType" id="pick" value="pick"/>&nbsp;&nbsp;Pick <br />
                                                    <input type="radio" name="pointType" ng-model="pointType" id="visit" value="visit"/>&nbsp;&nbsp;Visit<br />
                                                    <input type="radio" name="pointType" ng-model="pointType" id="drop" value="drop"/>&nbsp;&nbsp;Drop
                                                </div> 
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top: 15px;">
                                        <div class="col-md-8 col-md-offset-3">
                                            <div>
                                                <div class="control-group">
                                                    <button class="form-control btn btn-primary" type="submit" value="add" class="form-control btn btn-primary"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;Point</button>
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
            <div class="row">
                <button class="form-control btn btn-primary" value="submit"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;Request</button>
            </div>
        </div>
    </div>

