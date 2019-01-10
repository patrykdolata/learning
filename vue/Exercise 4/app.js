new Vue({
  el: '#exercise',
  data: {
  	progres: 0,
  	showCss: false,
    myClass: {
    	highlight: false,
      shrink: true
    },
    userClass: 'yellow',
    isShow: true,
    userStyle: {
    	height: '250px',
      width: '50px',
      backgroundColor: 'red'
    },
    loadingProgres: {
    	height: '25px',
      backgroundColor: 'red',
      width: '0px'
    }
  },
  methods: {
  	startEffect: function(){
    	var vm = this;
    	setInterval(function(){
      	vm.myClass.highlight = !vm.myClass.highlight;
        vm.myClass.shrink = !vm.myClass.shrink;
      },1000);
    },
    startProgress: function(){
    var vm = this;
    	setInterval(function(){
      	if(vm.progres < 300){
      		vm.progres += 15;
      	}
        var newWidth = vm.progres + 'px';
      	vm.loadingProgres.width = newWidth;
      },1000);
    }
  }
});
