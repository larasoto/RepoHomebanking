var app = new Vue ({
    el:'#app',
    data:{
       checkboxPropia:false,
       checkboxTercero:false,
       accounts:[],
       select1:"",
       select2:"",
       accountsFiltradas:[],
       nombreCuenta:"",
       amount:"",
       descripcion:"",
       balances:"",
    },
    created(){
        this.loadAccounts()
    },
    methods:{
        loadAccounts(){
            axios.get("/api/clients/current/accounts")
            .then((response)=>{
                this.accounts = response.data
                console.log(this.accountsFiltradas)
            }) .catch((error) => {
                console.log(error)
              })

        },
        checkboxPropias(){
            if(this.checkboxPropia == false){
                this.checkboxPropia = true
            }
        },
        checkboxTerceros(){
            if(this.checkboxTercero == false){
                this.checkboxTercero = true
            }
        },
        filtro(){
                this.accountsFiltradas = this.accounts.filter(account => account.number != this.select1)
                this.balances = this.accounts.filter(account => account.number == this.select1)
             
        },
        transaccion(){
             swal({
                    title: "Are you sure?",
                    text: "Once deleted, you will not be able to recover this imaginary file!",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true,
                  })
                  
                  .then((willDelete) => {
                    if (willDelete) {
                        axios.post("/api/transactions", "amount="+this.amount+"&description="+this.descripcion+"&accountOrigen="+this.select1+"&accountDestiny="+this.select2)
                  .then((response)=>{
                       swal("the transaction has been successfully completed", {  
                        icon: "success",
                      });
                      window.location.reload()
                  })   
                    } else {
                      swal("Your imaginary file is safe!");
                    }
                  }); 
                  },
                  exit(){
                    axios.post('/api/logout')
                    .then(response => {
                        window.location.href ="/web/index.html"
                    })
                    .catch((error) => {
                  
                      console.log(error)
                    })
                      }
                 }
                }) 
