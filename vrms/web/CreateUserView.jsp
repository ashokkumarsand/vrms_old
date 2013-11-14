<%-- 
    Document   : CreateUserView
    Created on : Nov 13, 2013, 6:33:37 PM
    Author     : Ashok
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="PDT50">
    <div class="control-group">
        <label for="officeId" class="control-label">Offical ID</label>
        <div class="control"><input id="officeId" type="textbox" class="span3"></div>
    </div>
    <div class="control-group">
        <label for="name" class="control-label">Name</label>
        <div class="control"><input name="" id="name" type="textbox" class="span3"></div>
    </div>
    <div class="control-group">
        <label for="email" class="control-label">Email</label>
        <div class="control"><input name="" id="email" type="textbox" class="span3"></div>
    </div>
    <div class="control-group">
        <label for="mobile" class="control-label">Mobile No</label>
        <div class="control"><input name="" id="mobile" type="textbox" class="span3"></div>
    </div>
    <div class="control-group">
        <label for="mobile" class="control-label">Extension No</label>
        <div class="control"><input name="" id="mobile" type="textbox" class="span3"></div>
    </div>
    <div class="control-group">
        <label for="dept" class="control-label">Depratement</label>
        <div class="control"><input name="" id="dept" type="textbox" class="span3"></div>
    </div>
    <div class="control-group">
        <label for="roles" class="control-label">Role</label>
        <div class="control">
            <select id="roles">
                <option value="">SELECT</option>
                <option value="1">SUPER_USER</option>
                <option value="2">FSG</option>
                <option value="3">FSG_HEAD</option>
                <option value="4">APPROVING_AUTHORITY</option>
                <option value="5">EMPLOYEE</option>
            </select>
        </div>
    </div>
    <div class="control-group">
        <label for="manager" class="control-label">Manager</label>
        <div class="control"><input name="" id="manager" type="textbox" class="span3"></div>
    </div>
    <div class="control-group">
        <div class="control"><button type="submit" id="createUser" onclick="javascript:CreateUser()" class="btn">Create User</button></div>
    </div>
</div>