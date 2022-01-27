<template>
  <v-row align="baseline">
    <v-col
      v-for="(photo, index) in photos"
      :key="index"
      class="d-flex justify-center"
      @mouseenter="removeIconVisible = true"
      @mouseleave="removeIconVisible = false"
    >
      <div class="tile">
        <img :src="`data:image/png;base64,${photo.bytes}`" class="mx-4 photo" />
        <v-icon
            v-if="editable && removeIconVisible"
            class="trash-ico"
            @click="removePhoto(photo.id)"
            >delete</v-icon
          >
      </div>
    </v-col>
  </v-row>
</template>
<script>
export default {
  name: "PhotoCarousel",
  props: {
    photos: {
      type: Array,
      required: true,
    },
    editable: {
      type: Boolean,
      required: false,
      default: false,
    },
  },
  data() {
    return {
      removeIconVisible: false,
    };
  },
  methods: {
    removePhoto(id) {
      this.$emit("removePhoto", id);
    },
  },
};
</script>
<style lang="scss" scoped>
// .tile {
//   height: 220px;
//   width: 420px;
// }
.photo {
  width: 280px;
}
.trash-ico {
  &:hover {
    color: red;
  }
}
</style>
