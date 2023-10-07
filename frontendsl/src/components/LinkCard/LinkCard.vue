<template>
  <div
    class="linkcard overflow-hidden rounded-2xl my-4 p-4 relative h-36 flex items-center"
  >
    <div>
      <a :href="data.url" target="_blank">
        <div>
          <h5 class="text-2xl overflow-hidden">{{ data.name }}</h5>
        </div>
      </a>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "LinkCard",
  components: {},
  props: {
    data: {
      required: true,
    },
  },
  setup() {
    /**
     * get the image and title by url
     */
    // async function fetchData() {
    //   try {
    //     const response = await fetch(
    //       `https://api.linkpreview.net/?key=e3bd4818db7e0a1a5d93fc405b543069&q=${props.data.url}`
    //     );
    //     const data = await response.json();
    //     reactiveData.title = data.title;
    //     reactiveData.description = data.description;
    //     reactiveData.image = data.image;
    //   } catch (error) {
    //     console.error(error);
    //   }
    // }
  },
});
</script>

<style lang="scss" scoped>
.linkcard {
  transition: transform 0.5s ease;
  transform-style: preserve-3d;
  opacity: 30;
  box-shadow: 2px 2px 5px #888;

  &:hover {
    background: #e8f3ff;
  }
}
</style>
