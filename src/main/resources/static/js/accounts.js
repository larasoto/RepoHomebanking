var app = new Vue({
  el: '#app',
  data: {
    clients: [],
    accounts: [],
    loans: [],
    email: "",
    password: "",
    cuotas:"",
    amount:"",
    montoCuotas:"",
    delete: false,
    filtradosTrue:[],
    selectType:""

  },
  created() {
    this.loadData()
    // this.pdf()

  },
  methods: {
    loadData() {
      axios.get("/api/clients/current")

        .then((response) => {

          this.clients = response.data
          this.accounts = response.data.accounts
          this.loans = response.data.loans
          this.filtradosTrue=this.accounts.filter(account => account.accountStatus == true )
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
  })},
//    pdf(){
//  axios.get("api/users/export/pdf")
//  .then(response => {
//    console.log(response)
//  }) 
//  .catch((error) => {

//    console.log(error) })
//   },
     request(){ 
      swal("Good job!", "You clicked the button!", "success");
     },
     createAccount(){
      axios.post("/api/clients/current/accounts", "accountType="+this.selectType)
     .then(response =>{
       console.log(response.status)
         window.location.reload()
     } ) 
      },
       patchAccount(id){
        axios.patch(`/api/clients/current/accounts/${id}`,'accountStatus='+this.delete)
          .then(response => {
            window.location.reload()
          }).catch((error)=> {
            console.log(error)
          })
      }
    }})