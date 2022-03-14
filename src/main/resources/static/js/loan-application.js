var app = new Vue({
    el: '#app',
    data: {
      loans:[],
      payments:[],
      cuotas:"",
      name:"",
      select:"Cards",
      accounts:[],
      accountDestiny:"",
      montoCuotas:"",
      loansFiltrados:[],
      amount:"",
      client:[]
    },
    created() {
      this.loadLoans()
      this.loadAccounts()
      this.loadClient()
  
    },
    methods: {
      loadLoans() {
        axios.get("/api/loans")
          .then((response) => {
            this.loans = response.data
          
                   
          })
          .catch((error) => {
  
            console.log(error)
          })
          
        },
         loadAccounts(){
         axios.get("/api/clients/current/accounts")
         .then((response)=>{
             this.accounts = response.data
         }) .catch((error) => {
            console.log(error)
           })

    },
    loadClient(){
      axios.get("/api/clients/current")
      .then((response)=>{
        this.client= response.data
      }). catch((error)=>{
        console.log(error)
      })
    },
    paymentsFunction(){
     this.payments = this.loans.filter(loan => loan.name == this.name)
     this.payments = this.payments[0].payments;
    },
    postLoan(){
      swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover this imaginary file!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      
      .then((willDelete) => {
        if (willDelete) {
            axios.post("/api/loans",{"amount":this.amount,"payments":this.cuotas,"accountDestiny":this.accountDestiny,"name":this.name})
      .then((response)=>{
           swal("the transaction has been successfully completed", {  
            icon: "success",
          });
          window.location.reload()
      }) .catch((error) =>{
         swal("Los datos son incorrectos");
      })   
        }     
      })
  //     axios.post(,)
  //     .then((response)=>{
  //       swal("the transaction has been successfully completed", {  
  //        icon: "success",
        
  //      })
  //       window.location.reload() 
  //       //
  //  })
     
     },  exit() {
      axios.post('/api/logout')
        .then(response => {
          window.location.href = "/web/index.html"
        })
        .catch((error) => {

          console.log(error)
        })
    }

    }})
    
    
   


//         },
//         exit(){
//           axios.post('/api/logout')
//           .then(response => {
//               window.location.href ="/web/index.html"
//           })
//           .catch((error) => {
        
//             console.log(error)
//           })
//             }
  
//     },
//     computed:{
    
//       filterCards(){
//           return this.cards.filter(card =>{
//               if(card.type == this.select || this.select == "Cards"){
//                   return card
//               }
//           }) }
//       }
//   })

//   function tipoTarjeta() {
//    app.cards.forEach(e => {
//      app.types.push(e.type)
//    });
    
//   }

//   function alerta() {
//     if(app.cards.length == 0){
//       swal({
//         text: "He does not yet have cards. You can request one at 0800 456 334 234",
//       })
//     }
//   }

//   function typesRepetidos() {
//     for (let i = 0; i < app.types.length; i++) {
//        for (let l = i + 1; l < app.types.length; l++) {
//         if (app.types[i] == app.types[l]) {
//            app.types.splice(l,1)         }

//        }      }
//   }
  