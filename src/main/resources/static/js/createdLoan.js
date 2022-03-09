var app = new Vue({
    el: '#app',
    data: {
    maxAmount:0,
    name:"",
   cuota1:"",
    paymentArray:[],
    cuotas:[]
     
  
    },
  created() {
 
},
 methods:{
        agregarCuenta(cuota){
       if(cuota != ""){
         this.paymentArray = cuota.split(",")
       }
         },
        creationLoan(){
            axios.post('/api/loans/admin',{"name":this.name,"maxAmount":this.maxAmount,"payment":this.paymentArray})
            .then((response)=>{
                console.log(response) 
            }).catch((error) => {
                console.log(error)
              })
        
    }}})

