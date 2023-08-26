/**
 * A plugin used to set css
 * @type {{plugins: *[], purge: string[], theme: {extend: {boxShadow: {ob: string}, spacing: {"12/10": string, 97: string, 98: string}, colors: {"ob-deep": {800: string, 900: string}, ob: string, "ob-trans": string, "ob-secondary": string, "ob-dim": string, "ob-accent-55": string, "ob-bright": string, "ob-normal": string}}}, darkMode: boolean, variants: {extend: {backgroundColor: string[], backgroundOpacity: string[]}}}}
 */

module.exports = {
  purge: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  darkMode: false,
  theme: {
    extend: {
      spacing: {
        97: "26rem",
        98: "28rem",
        "12/10": "120%",
      },
      colors: {
        ob: "var(--text-accent)",
        "ob-normal": "var(--text-normal)",
        "ob-trans": "var(--background-trans)",
        "ob-accent-55": "var(--bg-accent-55)",
        "ob-secondary": "var(--text-sub-accent)",
        "ob-bright": "var(--text-bright)",
        "ob-dim": "var(--text-dim)",
        "ob-deep": {
          800: "var(--background-secondary)",
          900: "var(--background-primary)",
        },
      },
      boxShadow: {
        ob: "var(--accent-shadow)",
      },
    },
  },
  variants: {
    extend: {
      backgroundColor: ["hover"],
      backgroundOpacity: ["hover"],
    },
  },
  plugins: [],
};
