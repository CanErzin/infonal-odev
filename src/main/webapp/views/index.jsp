<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="iso-8859-9" %>
<!DOCTYPE html>
<html>
<head lang="tr">
    <title>User Administration</title>

    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-1.10.2.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery-ui-1.10.4.custom.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.mask.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap-growl.min.js"/>"></script>
    <script src="<c:url value="/resources/js/odev.js"/>"></script>
    <script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>

    <script src="<c:url value="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"/>"></script>

</head>

<body>
<div class="container" style="margin-top: 140px">

    <div class="panel panel-success" id="table-panel">
        <div class="panel-heading" id="panelheading">User List</div>

        <div class="panel-body">
            <table id="userList" class="table table-responsive col-md-12">
                <thead>
                <tr>
                    <th> Name</th>
                    <th> Last Name</th>
                    <th> Phone Number</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            </br>

            <div class="hl-default-actions">

                <button type="button" id="newUserModalBtn" class="btn btn-success btn-sm" data-toggle="modal"
                        data-target="#newUserModal"
                        onclick="clearInputs()">
                    <span class='glyphicon glyphicon-plus'>Add</span>
                </button>

            </div>
        </div>
    </div>

    <!-- New User Modal -->
    <div class="modal fade" id="newUserModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">

        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="addModalTitle">Add New User </h4>
                </div>
                <div class="modal-body">
                    <form accept-charset="UTF-8" id="newUserForm" action="/rest/api/addUser">
                        <div class="row">
                            <label class="col-md-5 control-label">Name :</label>

                            <div class="form-group  col-md-4">
                                <input type="text" id="name" class="form-control">
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group  col-md-6">
                                <div class="alert-danger" id="nameWarning">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <label class="col-md-5 control-label">Last Name :</label>

                            <div class="form-group  col-md-6">
                                <input type="text" id="lastName" class="form-control">
                            </div>

                        </div>

                        <div class="row">
                            <div class="form-group  col-md-6">
                                <div class="alert-danger" id="lastNameWarning">
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <label class="col-md-5 control-label">Phone Number :</label>

                            <div class="form-group  col-md-6">
                                <input type="text" id="phoneNumber" name="phoneNumber" class="form-control phoneNumber"
                                       placeholder="(___) __ __-__">
                            </div>
                        </div>

                        <div class="row" id="captaDiv">
                            <div class="g-recaptcha" data-sitekey="6LedHBkTAAAAAJ8mp7jrPDUpDx1TF4aWdlnDYKwD"></div>
                        </div>

                        <div class="row">
                            <div class="form-group  col-md-6">
                                <div class="alert-danger" id="captaWarning">
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>

                            <button type="button" id="addUserBtn" class="btn btn-primary">
                                <span class='glyphicon glyphicon-save'>Add</span>
                            </button>
                        </div>
                    </form>
                </div>
            </div>


        </div>
    </div>
    <%--Edit User Modal--%>
    <div class="modal fade" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">

        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="editModalTitle"> Edit User </h4>
                </div>

                <div class="modal-body">
                    <input id="uId" style="visibility: hidden"></input>

                    <div class="row">
                        <label class="col-md-5 control-label">Name :</label>
                        <div class="form-group  col-md-4">
                            <input type="text" id="uName" class="form-control">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group  col-md-6">
                            <div class="alert-danger" id="editNameWarning">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-md-5 control-label">Last Name :</label>

                        <div class="form-group  col-md-6">
                            <input type="text" id="uLastName" class="form-control">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group  col-md-6">
                            <div class="alert-danger" id="editLastNameWarning">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <label class="col-md-5 control-label">Phone Number :</label>

                        <div class="form-group  col-md-6">
                            <input type="text" id="uPhoneNumber" name="phoneNumber" class="form-control phoneNumber"
                                   placeholder="(___) __ __-__">
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>

                    <button type="button" id="editUserBtn" class="btn btn-primary"
                            onclick="editUser()">
                        <span class='glyphicon glyphicon-edit'>Edit</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <%--Delete User Modal--%>

    <div class="modal fade" id="deleteUserModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">

        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="deleteModalTitle">User Delete Confirmation</h4>
                </div>

                <div class="modal-body">
                    <input id="userId" style="visibility: hidden"/>
                    <div class="row">
                        <div class="col-lg-12">
                            <p>This user will be deleted. Are you sure?</p>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>

                    <button id="deleteUserBtn" type="button" class="btn btn-danger" data-dismiss="modal"
                            onclick="deleteUser()">
                        <span class='glyphicon glyphicon-'>Delete</span>
                    </button>
                </div>
            </div>
        </div>
    </div>


</div>
</div>
</body>
</html>
