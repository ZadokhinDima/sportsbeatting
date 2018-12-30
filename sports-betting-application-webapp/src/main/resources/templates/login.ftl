<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Please sign in</title>
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/custom.css"/>
</head>
<body>
<div class="container">
    <form method="post" class="form-signin" action="/login">
        <#if logout>
            <div class="alert alert-success" role="alert">
                You have been logged out successfully!
            </div>
        </#if>
        <#if error>
            <div class="alert alert-danger" role="alert">
                Invalid Username or Password.
            </div>
        </#if>
        <h2 class="">Please sign in</h2>
        <div class="form-group">
            <label for="username" class="sr-only">Username</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="Username" required
                   autofocus>
        </div>
        <div class="form-group">
            <label for="password" class="sr-only">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Password">
        </div>
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</body>
</html>