//index.jsp
function checkLoginFields() {
    if(document.loginform.usernameLogin.value === ""){
        document.getElementById("error").innerHTML = "Account is empty";
        return false;
    }
    if(document.loginform.passwordLogin.value === ""){
        document.getElementById("error").innerHTML = "Password is empty";
        return false;
    }
    document.loginform.submit();
}
function checkRegistrationFields() {
    if(document.regform.usernameRegistration.value === ""){
        document.getElementById("error").innerHTML = "Account is empty";
        return false;
    }
    if(document.regform.passwordRegistration.value === ""){
        document.getElementById("error").innerHTML = "Password is empty";
        return false;
    }
    if(document.regform.email.value === ""){
        document.getElementById("error").innerHTML = "EMail is empty";
        return false;
    }
    document.regform.submit();
}

//main.jsp
function doClearChecks() {
    var checks = document.getElementsByName('idTask');
    var isChecked = false;
    for(var i=0; i<checks.length; i++){
        if(checks[i].checked){
            checks[i].checked = false;
        }
    }
}
function submitForm(value) {
    document.form_tasks = document.getElementById('form_tasks');
    document.form_tasks.pressed_button = document.getElementById('pressed_button');
    document.form_tasks.pressed_button.value = value;
    document.form_tasks.action = '/web/action' + value;
    document.form_tasks.submit();
}
function doAction(id, value) {
    doClearChecks();
    var currentCheck = document.getElementById('check'+id);
    currentCheck.checked = true;
    submitForm(value);
}
function doActions(value) {
    var checks = document.getElementsByName('idTask');
    var isChecked = false;
    for(var i=0; i<checks.length; i++){
        if(checks[i].checked){
            isChecked = true;
            break;
        }
    }
    if(isChecked == true){
        submitForm(value);
    }else{
        var error = document.getElementById('error');
        error.innerHTML = "Please check any task and try again.";
    }
}
function addTask(value) {
    document.form_add = document.getElementById('form_add');
    document.form_add.pressed_button = document.getElementById('pressed_button');
    document.form_add.pressed_button.value = value;
    document.form_add.submit();
}

//action.jsp
function confirmAdding(value) {
    var name = document.getElementById('taskName');
    var error = document.getElementById('error');
    if(name === ''){
        error.innerHTML = "Please enter task";
        return;
    }
    //need to add check date
    document.form_change = document.getElementById('form_change');
    document.form_change.pressed_button = document.getElementById('pressed_button');
    document.form_change.pressed_button.value = value;
    document.form_change.action = '/web/action' + value;
    document.form_change.submit();
}
function confirmChanges(value) {
    document.form_change = document.getElementById('form_change');
    document.form_change.pressed_button = document.getElementById('pressed_button');
    document.form_change.pressed_button.value = value;
    document.form_change.action = '/web/action' + value;
    document.form_change.submit();
}

