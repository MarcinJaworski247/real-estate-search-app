<template>
  <v-container class="search" fluid>
    <div class="text-h5">Szukaj</div>
    <v-row align="center">
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-select label="test" :items="items" clearable></v-select>
      </v-col>
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-select label="test" :items="items" clearable></v-select>
      </v-col>
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-select label="test" :items="items" clearable></v-select>
      </v-col>
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-select label="test" :items="items" clearable></v-select>
      </v-col>
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-select label="test" :items="items" clearable></v-select>
      </v-col>
      <v-col class="d-flex justify-center" cols="12" sm="2">
        <v-btn fab large>
          <v-icon>search</v-icon>
        </v-btn>
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
  border: 1px solid #ebebeb;
  border-bottom-left-radius: 5px;
  border-bottom-right-radius: 5px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
</style>
