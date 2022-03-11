var app = new Vue ({
    el:'#app',
    data:{
       cards:[],
       cardsFiltradas:[],
       number:"",
        accounts:[],
        amountCards:[],
        descripcion:"",
        monto:0,
        accountDestiny:"",
        cvv:""
        
    },
   
    created(){
    this.loadData()
    
   
    },
    methods: {
        loadData() {
          axios.get("/api/clients/current")
            .then((response) => {
              this.clients = response.data
              this.cards = response.data.cards
              this.cardsFiltradas=this.cards.filter(card => card.status == true && card.type =='DEBIT' )
           
            })
            .catch((error) => {
              console.log(error)
            })
         
         },
         functionAmountCard(){
         this.amountCards = this.cards.filter(card =>card.number == this.number)
         this.amountCards = this.amountCards[0].amount

         },
         postnet(){
             axios.post("/api/transactions/postnet",{"number":this.number,"amount":this.monto,"description":this.descripcion,"accountDestiny":this.accountDestiny,"cvv":this.cvv})
             .then((response)=>{
                 console.log(response)
                 window.location.reload()
             })
              .catch((error) => {
                console.log(error)
              })
         },
         exit(){
          axios.post('/api/logout')
          .then(response => {
              window.location.href ="/web/index.html"
          })
          .catch((error) => {
        
            console.log(error)
          })}

}})