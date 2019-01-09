<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Please sign in</title>
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/custom.css"/>
    <script src="/webjars/jquery/3.0.0/jquery.min.js"></script>
    <script src="/js/scripts.js"></script>
</head>
<body>
<div class="login-top-bar">
    <h2>Welcome to SportsBet!</h2>
    <p>Sports betting is the activity of predicting sports results and placing a wager on the outcome.</p>
</div>
<div class="container">
    <div class="login-page">
        <form method="post" class="form-signin" action="/login">
            <#if logout!false>
                <div class="alert alert-success" role="alert">
                    You have been logged out successfully!
                </div>
            </#if>

            <#if registered!false>
                <div class="alert alert-success" role="alert">
                    You have been registered, use your data to log in!
                </div>
            </#if>

            <#if error!false>
                <div class="alert alert-danger" role="alert">
                    Invalid Username or Password.
                </div>
            </#if>

            <#if registrationError!false>
                <div class="alert alert-danger" role="alert">
                    Username is not unique.
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
            <button type="submit" class="btn btn-primary">Sign in</button>
            <div class="form-group">
                <p>Don`t have an account? <a href="javascript:void(0);" onclick="switchToRegisterForm()">Register</a>
                </p>
            </div>
        </form>
    </div>
    <div class="registration-page hide">
        <form method="post" class="form-registration" action="/registration">
            <h2>Please fill in information about you</h2>
            <div class="form-group">
                <label for="username" class="sr-only">Username</label>
                <input type="text" class="form-control" name="username" placeholder="Username" required
                    autofocus>
            </div>
            <div class="form-group">
                <label for="password" class="sr-only">Password</label>
                <input type="password" class="form-control" name="password" placeholder="Password">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="name" placeholder="Your real name">
            </div>
            <div class="form-group">
                <input type="text" onfocus="(this.type='date')" onblur="if(this.value===''){this.type='text'}"
                       class="form-control" name="birthDate" placeholder="Date of Birth">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="accountNumber" placeholder="Account number">
            </div>
            <div class="input-group form-group">
                <input type="text" class="form-control" name="balance" placeholder="Balance">
                <select class="form-control currency-selector" name="currency">
                    <option selected>UAH</option>
                    <option>USD</option>
                    <option>EUR</option>
                </select>
            </div>
            <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Register</button>
            <p>Already have an account? <a href="javascript:void(0);" onclick="switchToLoginForm()">Sign In</a></p>
        </form>
    </div>
</div>
</body>
</html>