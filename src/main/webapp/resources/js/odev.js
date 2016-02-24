$(document).ready(function () {
    $('.phoneNumber').mask('(000) 000-0000');
    initCapta();

    $('#addUserBtn').click(function (e) {
        e.preventDefault();
        addUser();
    })

});

$(function () {
    initUserList();

    $("#deleteUserModal").on('show.bs.modal', function (e) {
        $(this).find('#userId').text($(e.relatedTarget).data('id'));
    });

    $("#editUserModal").on('show.bs.modal', function (e) {
        $(this).find('#uId').text($(e.relatedTarget).data('id'));
        $(this).find('#uName').text($(e.relatedTarget).data('name'));
        $(this).find('#uLastName').text($(e.relatedTarget).data('lastname'));
        $(this).find('#uPhoneNumber').text($(e.relatedTarget).data('phonenumber'));

    });


});

function initUserList() {
    $.ajax({
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        },
        url: 'rest/api/userlist',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json; charset=UTF-8',
        success: function (response) {
            var table = $('#userList tbody');
            var columns = ["name", "lastName", "phoneNumber"];
            table.html("");
            $.each(response, function (i, user) {
                var row;
                row =
                    '<tr>'
                    + '<td>' + user.name + '</td>'
                    + '<td>' + user.lastName + '</td>'
                    + '<td>' + user.phoneNumber + '</td>'
                    + '<td>'
                    + '<a href="#editUserModal" id="editButton" type="button" class="btn btn-primary btn-xs" data-toggle="modal" '
                    + 'data-id= "' + user.id + '" data-name="' + user.name + '" data-lastname="' + user.lastName + '" data-phonenumber="' + user.phoneNumber + '">' +
                    '  <label for="editButton">Edit User</label> '
                    + '</a>'
                    + '</td>'
                    + '<td>'
                    + '<a href="#deleteUserModal" id="deleteButton" type="button" class="btn btn-danger btn-xs" data-toggle="modal" '
                    + 'data-id="' + user.id + '" >' +
                    '  <label for="deleteButton">Delete User</label> '
                    + '</a>'
                    + '</td>'
                    + '</tr>';
                table.append(row);
            });
        }
    });
}

function addUser() {
    //initCapta();
    var name = $('#newUserForm #name').val();
    var lastName = $('#newUserForm #lastName').val();
    var valid = true;
    // insert a warning
    if (!name) {
        valid = false;
        document.getElementById('nameWarning').innerHTML = "Please enter name";
    }
    // insert a warning
    if (!lastName) {
        document.getElementById('lastNameWarning').innerHTML = "Please enter lastname";
        valid = false;
    }
    if (valid) {
            $.post("rest/api/addUser",
                {
                    name: $('#newUserForm #name').val(),
                    lastName: $('#newUserForm #lastName').val(),
                    phoneNumber: $('#newUserForm #phoneNumber').val(),
                    recaptcha_challenge_field: Recaptcha.get_challenge(),
                    recaptcha_response_field: Recaptcha.get_response()
                },
                function (resonse) {
                    if (resonse == "SUCCESS") {
                        initUserList();
                        $('#newUserModal').modal('hide');
                    } else if (resonse == "CAPTA_FAIL") {
                        document.getElementById('captaWarning').innerHTML = "Capta is wrong you robot!";
                        initCapta();
                    }
                    else {
                        showError();
                    }
                });


    }
    // Clear warning when modal is closing
}

function editUser() {

    var userId = $('#editUserModal #uId').text();

    var name = $('#uName').val();
    var lastName = $('#uLastName').val();
    var valid = true;
    // insert a warning
    if (!name) {
        valid = false;
        document.getElementById('editNameWarning').innerHTML = "Please enter name";
    }
    // insert a warning
    if (!lastName) {
        document.getElementById('editLastNameWarning').innerHTML = "Please enter lastname";
        valid = false;
    }
    if (valid) {
        $.post("rest/api/editUser",
            {
                id: userId,
                name: $('#uName').val(),
                lastName: $('#uLastName').val(),
                phoneNumber: $('#uPhoneNumber').val()
            }, function (response) {
                if (response == "SUCCESS") {
                    initUserList();
                } else {
                    showError();
                }
            });
        $('#editUserModal').modal('hide');
    }
}

function deleteUser() {
    var userId = $('#deleteUserModal #userId').text();
    $.post("rest/api/deleteUser",
        {
            id: userId
        },
        function (data) {
            if (data == "SUCCESS") {
                initUserList();
            } else {
                showError;
            }
        });
}

function clearInputs() {
    $('#name').val("");
    $('#lastName').val("");
    $("#phoneNumber").val("");
    jQuery('#nameWarning').html('');
    jQuery('#lastNameWarning').html('');
    jQuery('#captaWarning').html('');

}

function showError() {
    $.growl({
            title: "<strong> Error : </strong> ",
            message: "Opss something went wrong.We Logged the problem and fix it right away.",
        }
    );
}

function initCapta() {
    Recaptcha.create("6LedHBkTAAAAAJ8mp7jrPDUpDx1TF4aWdlnDYKwD", "captaDiv", {
        theme: "clean",
    });
}


