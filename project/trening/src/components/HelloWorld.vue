<template>
  <div class="hello">
    <table>
      <tr>
        <td @click="clicked('HIIT')">HIIT</td>
        <td @click="clicked('PULL_UPS')">Podciąganie</td>
        <td @click="clicked('PUSH_UPS')">Pompki</td>
        <td>Pompki w staniu</td>
      </tr>
      <tr>
        <td  v-if="show" colspan="4"><span v-html="trenningContent"></span></td>
      </tr>
    </table>
  </div>
</template>

<script>
const TRENNING = {
  HIIT: "HIIT",
  PUSH_UPS: "PUSH_UPS",
  PULL_UPS: "PULL_UPS",
  HAND_UPS: "HAND_UPS",
  UNDEFINED: ""
};

const PULL_UPS_ROW = 'Powtórzeń: <input type="text">';

export default {
  name: "HelloWorld",
  data() {
    return {
      show: false,
      trenningContent: "<p>o co chodzi</p>",
      trenningType: TRENNING.UNDEFINED,
      trenningData: [20, 50]
    };
  },
  methods: {
    clicked(type){
      this.show = true;
      switch(type){
        case 'HIIT': this.hiit();
        break;
        case 'PULL_UPS':this.pullUps();
        break;
      }
    },
    hiit(){
      this.trenningType = TRENNING.HIIT;
      this.trenningContent = "<p>Wybrano hiit</p>";
    },
    pullUps(){
      this.trenningType = TRENNING.PULL_UPS;
      this.trenningContent = "<p>Wybrano pull ups</p>";
      this.trenningContent += '<div v-for="el in trenningData"></div>';
    },
    addPullUpsRow(){
      this.trenningContent += PULL_UPS_ROW
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
td {
  height: 50px;
  width: 100px;
  background-color: bisque;
}
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
