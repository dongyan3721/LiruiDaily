<template>
  <div>
    <form>
      用户名<input type="text" v-model="form.username">
      密码&nbsp;&nbsp;<input type="password" v-model="form.password">
    </form>
    <button @click="submit">提交</button>
  </div>
  去
  <router-link to="register">注册账号</router-link>
  <p v-show="showTip">用户名或密码不正确！</p>
</template>

<script>
import {getCurrentInstance} from "vue";

export default {
  name: "Login",
  data() {
    return {
      axios: getCurrentInstance().appContext.config.globalProperties.$axios,
      form: {
        username: null,
        password: null
      },
      showTip: false
    }
  },
  methods: {
    submit() {
      this.axios.get("/login", {
            params:
                {
                  username: this.form.username,
                  password: this.form.password
                }
          }).then(res => {
        if (res.data.code === 200) {
          this.$router.push({name: 'mark'})
        } else {
          this.showTip = true;
        }
      })
    }
  }
}
</script>

<style scoped>
p {
  color: red;
}
</style>