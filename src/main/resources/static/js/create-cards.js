var app = new Vue({
  el: '#app',
  data: {
    clients:[],
    cards:[],
    type: [],
    color: [],
    types: [],
    debito:[],
    credito:[],
    accounts:[],
    selectAccount:"",

  },
  created() {
    this.loadData()
    this.loadAccounts()

  },
  methods:{
     loadData() {
      axios.get("http://localhost:8080/api/clients/current")
        .then((response) => {
          this.cards = response.data.cards;
          this.filtradosTrue=this.cards.filter(card => card.status == true );
          this.debito =this.filtradosTrue.filter(card => card.type == 'DEBIT');
          this.credito = this.filtradosTrue.filter(card => card.type == 'CREDIT');


        })
        .catch((error) => {

          console.log(error)
        })
      },
    createCards(){
      axios.post('/api/clients/current/cards', "type=" + this.type + "&color=" + this.color + "&account=" + this.selectAccount)
        .then(response => {
          window.location.href = "http://localhost:8080/web/cards.html"
          console.log(response.status)

        })

    },loadAccounts(){
      axios.get("/api/clients/current/accounts")
      .then((response)=>{
        this.accounts = response.data
      })
    }
 
  }
})


