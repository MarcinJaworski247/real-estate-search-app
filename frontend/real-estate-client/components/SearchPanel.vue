<template>
  <v-container class="search" fluid>
    <h3>Szukaj</h3>
    <v-row align="center">
      <v-col classs="d-flex" cols="12" sm="3">
        <v-select label="test" :items="items"></v-select>
      </v-col>
      <v-col classs="d-flex" cols="12" sm="3">
        <v-select label="test" :items="items"></v-select>
      </v-col>
      <v-col classs="d-flex" cols="12" sm="3">
        <v-select label="test" :items="items"></v-select>
      </v-col>
      <v-col classs="d-flex" cols="12" sm="3">
        <v-select label="test" :items="items"></v-select>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  name: "SearchPanel",
  data() {
    return {
      items: [
        {
          text: "Test1",
          value: "1",
        },
        {
          text: "Test2",
          value: "2",
        },
        {
          text: "Test3",
          value: "3",
        },
        {
          text: "Test4",
          value: "4",
        },
      ],
    };
  },
};
</script>
<style scoped>
.search {
  background-color: #ebebeb;
  padding-top: 32px;
  padding-bottom: 96px;
}
</style>
