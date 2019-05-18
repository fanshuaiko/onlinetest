<template>
  <div class="app">
    <div class="form">
      <div class="authorization">
        <h1>登录</h1>
      </div>
      <form class="form-content">
        <div class="form-group username">
          <input type="username" v-model="username" class="form-control" id="exampleInputUsername"
                 placeholder="Username">
        </div>
        <div class="form-group password">
          <input type="password" v-model="password" class="form-control" id="exampleInputPassword1"
                 placeholder="Password">
        </div>
        <button type="submit" class="btn btn-info" v-on:click="submit()">SUBMIT</button>
      </form>
    </div>
  </div>
</template>

<script>


  export default {
    name: "Login",

    data() {
      return {
        username: '',
        password: ''
      }

    },
    methods: {
      submit() {

        //打印用户名密码
        // console.log(this.username,'::',this.password);

        let postData = this.$qs.stringify({username: this.username, password: this.password});

        if (this.username == '') {
          alert('用户名不能为空');
          return false
        }
        if (this.password == '') {
          alert('密码名不能为空');
          return false
        }
        this.$axios.post('/auth-api/auth/login', postData)
          .then(res => {
            console.log(res.data)
            if (res.status == 200 && res.data['code'] == '0') {
              sessionStorage.setItem('AUTHORIZATION',res.data['data'])
              // 登录成功查询该考生的所有考试信息
                this.$axios.get('/front-api/test' + '/student' + '/' + this.username
                  ,{headers:{'AUTHORIZATION':res.data['data']}})
                  .then(response => {
                    console.log(response.data)
                  if (response.status == 200 && response.data['code'] == '0') {
                    this.$router.push({
                      path: '/tests',
                      query: {
                        // testVoList: JSON.stringify(res.data['data'])
                        testVoList: response.data['data'],
                        AUTHORIZATION:res.data['data'],
                        username:this.username
                      }
                    })
                  } else {
                    alert(res.data['message'])
                  }
                })


            } else {
              alert(res.data['message'])
            }
          })
          .catch(err => {
            console.log(err)
          })

        // this.$axios.get('/front-api/test' + '/student' + '/' + this.username
        //   , {headers: {'AUTHORIZATION': '14206c73-fbf8-4feb-80eb-38a61673bd16'}})
        //   .then(res => {
        //     console.log(res)
        //   })
      }

    }
  }

</script>

<style scoped>
  @import "https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css";

  body {
    /*background-color: seagreen;*/
  }

  .form {
    background: white;
    position: absolute;
    width: 30%;
    height: 30%;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    border-radius: 20px;
    box-shadow: darkgrey 0px 0px 30px 5px;
  }

  .authorization {
    /*position: relative;*/
    text-align: center;
    font-size: 40px;
    font-weight: bold;
    /*margin: 15px 0 20px 0;*/
    letter-spacing: 0.05em;
    color: #5bc0de;
    left: 20%;
    font-weight: 700;
  }

  .btn-info {
    position: relative;
    margin-top: 10%;
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
