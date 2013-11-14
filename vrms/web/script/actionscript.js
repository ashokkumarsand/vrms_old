
$(document).ready(function() {
    $("#USER_MANAGE").click(function() {
        $.ajax({
            url: 'ManageUser',
            dataType: 'json',
            success: function(obj) {
                if (obj.login) {
                    if (obj.ADD_USER.exist) {

                        $(document.createElement("div"))
                        var _officeid = $(document.createElement("label")).text("Offical ID").attr("for", "officeId").addClass("control-label")
                        var _name = $(document.createElement("label")).text("Name").attr("for", "name").addClass("control-label")
                        var _email = $(document.createElement("label")).text("Email").attr("for", "email").addClass("control-label")
                        var _mobile = $(document.createElement("label")).text("Mobile No").attr("for", "mobile").addClass("control-label")
                        var _ext = $(document.createElement("label")).text("Extension No").attr("for", "mobile").addClass("control-label")
                        var _dept = $(document.createElement("label")).text("Depratement").attr("for", "dept").addClass("control-label")
                        var _manager = $(document.createElement("label")).text("Manager").attr("for", "manager").addClass("control-label")
                        var _rol = $(document.createElement("label")).text("Role").attr("for", "roles").addClass("control-label")
                        var officeid = $(document.createElement("input")).attr({id: "officeId", type: "textbox"}).addClass("span3")
                        var name = $(document.createElement("input")).attr({"name": "", id: "name", type: "textbox"}).addClass("span3")
                        var email = $(document.createElement("input")).attr({"name": "", id: "email", type: "textbox"}).addClass("span3")
                        var mobile = $(document.createElement("input")).attr({"name": "", id: "mobile", type: "textbox"}).addClass("span3")
                        var ext = $(document.createElement("input")).attr({"name": "", id: "mobile", type: "textbox"}).addClass("span3")
                        var dept = $(document.createElement("input")).attr({"name": "", id: "dept", type: "textbox"}).addClass("span3")
                        var manager = $(document.createElement("input")).attr({"name": "", id: "manager", type: "textbox"}).addClass("span3")
                        var roles = $(document.createElement("select")).attr("id", "roles")
                        roles.append($(document.createElement("option")).val(undefined).text("SELECT"))
                        var _sys_role = eval("(" + obj.ADD_USER.roles + ")");
                        for (var role in _sys_role) {
                            roles.append($(document.createElement("option")).val(_sys_role[role].roleid).text(_sys_role[role].rolename))
                        }
                        $("#container").html("").append($(document.createElement("div")).addClass("PDT50")
                                .append($(document.createElement("div")).addClass("control-group")
                                .append(_officeid)
                                .append($(document.createElement("div"))
                                .addClass("control")
                                .append(officeid))).append($(document.createElement("div")).addClass("control-group")
                                .append(_name)
                                .append($(document.createElement("div"))
                                .addClass("control")
                                .append(name))).append($(document.createElement("div")).addClass("control-group")
                                .append(_email)
                                .append($(document.createElement("div"))
                                .addClass("control")
                                .append(email))).append($(document.createElement("div")).addClass("control-group")
                                .append(_mobile)
                                .append($(document.createElement("div"))
                                .addClass("control")
                                .append(mobile))).append($(document.createElement("div")).addClass("control-group")
                                .append(_ext)
                                .append($(document.createElement("div"))
                                .addClass("control")
                                .append(ext))).append($(document.createElement("div")).addClass("control-group")
                                .append(_dept)
                                .append($(document.createElement("div"))
                                .addClass("control")
                                .append(dept))).append($(document.createElement("div")).addClass("control-group")
                                .append(_rol)
                                .append($(document.createElement("div"))
                                .addClass("control")
                                .append(roles))).append($(document.createElement("div")).addClass("control-group")
                                .append()
                                .append($(document.createElement("div"))
                                .addClass("control")
                                .append($(document.createElement("button")).attr({type: "submit", id: "createUser", onclick: "javascript:CreateUser()"}).addClass("btn").text("Create User")))));




                    }
                } else {
                    $("#alert-error").show();
                    $("#alert-error #msg").text("Login again to perfrom action");
                }
            }
        })
    });


})

function CreateUser() {
    $.ajax({
        url: 'CreateUser',
        type: 'post',
        data:{name:""},
        success: function(obj) {
            console.log(obj);
        }

    })
}