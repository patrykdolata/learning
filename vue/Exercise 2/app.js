new Vue({
	el: '#exercise',
  data: {
  	key: ''
  },
  methods: {
  	buttonClick: function(){
    	alert("Clicked!")
    },
    keyDown: function(event){
 			this.key = event.target.value	
    }
  }
})