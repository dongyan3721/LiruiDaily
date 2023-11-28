<template>
  <div>
    <form>
      用户名：<input type="text" v-model="form.username">
      密码：<input type="password" v-model="form.password">
      确认密码：<input type="password" v-model="repeat">
    </form>
    <button @click="certainRegister">确认注册</button>
    <p v-show="showEmpty">用户名或密码不能为空！</p>
    <p v-show="showRepeat">两次密码输入不一致！</p>
  </div>
</template>

<script>
import {getCurrentInstance} from "vue";

export default {
  name: "Register",
  data(){
    return {
      axios: getCurrentInstance().appContext.config.globalProperties.$axios,
      form: {
        username: null,
        password: null,
      },
      repeat: null,
      showRepeat: false,
      showEmpty: false
    }
  },
  methods:{
    certainRegister(){
      this.showEmpty = false;
      this.showRepeat = false;
      if(this.form.username===''||this.form.password===''){
        this.showEmpty = true;
      }else if(this.form.password!==this.repeat){
        this.showRepeat = true;
      }else{
        this.axios.get("/register", {params: {...this.form}}).then(res=>{
          if(res.data.code==200){
            alert("注册成功，将在3秒后跳转到登录界面！");
            setTimeout(()=>{
              this.$router.push("/login");
            }, 3000)
          }
        })
      }
    }
  }
}
</script>

<style scoped>
p{
  color: red;
}
</style>