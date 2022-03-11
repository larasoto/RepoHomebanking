var app = new Vue({
    el: '#app',
    data: {
      clients: [],
      email:"",
      password:"",
      registro:true,
      firstName:"",
      lastName:"",
    },
    methods: {
      loginData() {
        axios.post('/api/login',"email="+this.email+"&password="+this.password)
         
          .then((response) => {
           window.location.href ="/web/accounts.html"
          

          })
          .catch((error) => {
             if(error.response.status == 401){
              swal({
                title: "You are registered?",
                text: "Do not appear as a customer please register!",
                icon: "warning",
                buttons: true,
                 dangerMode: true,
              })
            
}
            
          })
      },
      botonRegistro(){
          if(this.registro == true){
              this.registro = false
          }},
        Register(){
            axios.post('/api/api/clients',"firstName="+this.firstName+"&lastName="+this.lastName+"&email="+this.email+"&password="+this.password )
            .then(response =>  this.loginData()) 
             .catch(error => {
                swal({
                  title: "Existing mail",
                  text: "The mail already exists. Please enter another!",
                  icon: "warning",
                   buttons: true,
                    dangerMode: true,
             
            })
           
        }
)}}})
 
 