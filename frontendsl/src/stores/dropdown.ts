import { defineStore } from "pinia";

export const useDropdownStore = defineStore("dropdownStore", {
  state() {
    return {
      commandName: "", // command name
      uid: 0, // uid
    };
  },

  actions: {
    setCommand(name: string): void {
      this.commandName = name;
    },

    setUid(): number {
      this.uid = Date.now();
      return this.uid;
    },
  },
});
