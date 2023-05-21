<template>
  <div
    class="ob-dropdown relative z-50"
    @click="toggle"
    @mouseover="hoverHandler"
    @mouseleave="leaveHandler"
    v-click-away="onClickAway"
  >
    <slot />
  </div>
</template>

<script lang="ts">
import { defineComponent, provide, reactive, ref, toRefs, watch } from "vue";
import { useDropdownStore } from "@/stores/dropdown";
import { useCommonStore } from "@/stores/common";

export default defineComponent({
  // 声明触发的事件
  emits: ["command"],

  name: "Dropdown",

  props: {
    hover: {
      type: Boolean,
      default: false,
    },
  },
  setup(props, { emit }) {
    const commonStore = useCommonStore();

    const dropdownStore = useDropdownStore();

    const mouseHover = toRefs(props).hover;

    const eventId = ref(0);

    let sharedState = reactive({
      active: false,
    });

    /**
     * Provide sharedState for
     * the child component
     */
    provide("sharedState", sharedState);

    /**
     * watch the commandName
     */
    watch(
      () => dropdownStore.commandName,
      (newName, oldName) => {
        const name = newName ? newName : oldName;
        if (eventId.value === dropdownStore.uid) emit("command", name);
      }
    );

    /**
     * set uid when active is false and you click that
     */
    const toggle = () => {
      // if active is false, setuid
      if (!sharedState.active) eventId.value = dropdownStore.setUid();
      // if mouse don't hover, active = !active
      if (!mouseHover.value) sharedState.active = !sharedState.active;
    };

    /**
     * when clicked away
     */
    const onClickAway = () => {
      if (!mouseHover.value && !commonStore.isMobile) {
        // when clicking away, if hover === false and not mobile
        sharedState.active = false;
        eventId.value = 0;
      }
    };

    /**
     * when hovered, active need to be set true
     * and eventId is set timestamp
     */
    const hoverHandler = () => {
      if (!sharedState.active) eventId.value = dropdownStore.setUid();
      if (mouseHover.value) sharedState.active = true;
    };

    /**
     * active need to be set false when you leave it
     * eventId need to set 0
     */
    const leaveHandler = () => {
      if (mouseHover.value) {
        sharedState.active = false;
        eventId.value = 0;
      }
    };

    return { toggle, onClickAway, hoverHandler, leaveHandler };
  },
});
</script>
