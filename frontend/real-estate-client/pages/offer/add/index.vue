<template>
  <v-container class="box" fluid>
    <div class="text-h5">Nowe ogłoszenie</div>
    <v-form v-model="valid">
      <v-text-field v-model="title" required label="Tytuł"></v-text-field>
      <v-textarea v-model="description" required label="Opis"></v-textarea>
      <v-text-field v-model="town" required label="Miasto"></v-text-field>
      <v-text-field
        v-model="price"
        required
        type="number"
        suffix="zł"
        label="Cena"
      ></v-text-field>
      <v-file-input
        accept="image/*"
        prepend-icon="mdi-camera"
        label="Zdjęcia"
        multiple
        chips
        show-size
      ></v-file-input>
      <v-btn color="success" @click="addOffer"> Dodaj </v-btn>
    </v-form>
  </v-container>
</template>
<script>
export default {
  name: "Login",
  methods: {
    addOffer() {},
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
}
.register {
  text-decoration: underline;
}
.register:hover {
  cursor: pointer;
}
</style>
