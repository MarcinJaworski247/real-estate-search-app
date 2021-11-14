<template>
  <v-container class="box" fluid>
    <div class="text-h5">Rejestracja</div>
    <v-form>
      <RegisterForm />
    </v-form>
  </v-container>
</template>
<script>
import RegisterForm from "@/components/Forms/RegisterForm.vue";
export default {
  name: "Register",
  components: {
    RegisterForm,
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
</style>
