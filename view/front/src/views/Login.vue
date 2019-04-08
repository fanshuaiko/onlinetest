<template>
    <div>
        <div class="form">
            <div class="authorization">
                <h1>Authorization</h1>
            </div>
            <form class="form-content">
                <div class="form-group username">
                    <input type="username" class="form-control" id="exampleInputUsername" placeholder="Username">
                </div>
                <div class="form-group password">
                    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-success">SUBMIT</button>
            </form>
            <router-link to="/tests">点我</router-link>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Login"
    }
</script>

<style>
    @import "https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css";

    body {
        background-color: seagreen;
    }

    .form {
        background: seagreen;
        position: absolute;
        width: 30%;
        height: 40%;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        border-radius: 20px;
        box-shadow: #42b983 0px 0px 30px 5px;
    }

    .authorization {
        position: relative;
        font-size: 40px;
        font-weight: bold;
        margin: 15px 0 20px 0;
        letter-spacing: 0.05em;
        color: white;
        left: 20%;
        font-weight: 700;
    }

    .btn-success {
        position: relative;
        width: 50%;
        left: 25%;
    }

    .form-group {
        position: relative;
        width: 80%;
        left: 10%;
        top: 20%
    }

</style>
