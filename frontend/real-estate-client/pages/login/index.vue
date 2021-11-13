<template>
  <v-container class="box" fluid>
    <div class="text-h5">Logowanie</div>
    <v-form>
      <LoginForm />
    </v-form>
    <div
      class="mt-5 text-subtitle-1 grey-text register"
      @click="routerPushToRegister"
    >
      Nie masz jeszcze konta? Załóż je teraz
    </div>
  </v-container>
</template>
<script>
import LoginForm from "@/components/Forms/LoginForm.vue";
export default {
  name: "Login",
  components: {
    LoginForm,
  },
  methods: {
    routerPushToRegister() {
      this.$router.push("/register");
    },
  },
};
</script>
<style scoped>
.box {
  background-color: #ebebeb;
  padding-top: 32px;
  padding-bottom: 96px;
  border: 1px solid #ebebeb;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  width: 500px;
}
.register {
  text-decoration: underline;
}
.register:hover {
  cursor: pointer;
}
</style>
