var app = new Vue({
  el: '#app',
  data: {
    clients: [],
    transactions: [],
    accounts: "",
    id: "",
    balance:""


  },
  created() {
 var queryString = window.location.search;
      var urlParams = new URLSearchParams(queryString);
      this.id = urlParams.get(`id`);
    this.loadData()

  },
  methods: {
    loadData() {

      axios.get(`/api/accounts/${this.id}`)

        .then((response) => {

          this.clients = response.data
          this.transactions = response.data.transaction
          ordenarPorId()
        })
        .catch((error) => {

          console.log(error)
        })
    },
    loadAccount() {
      axios.get("/api/clients/current")

        .then((response) => {
          this.clients = response.data
          this.accounts = response.data.accounts
        })
        .catch((error) => {

          console.log(error)
        })
    },
    exit() {
      axios.post('/api/logout')
        .then(response => {
          window.location.href = "/web/index.html"
        })
        .catch((error) => {

          console.log(error)
        })
    },


  }
})

function ordenarPorId() {
  app.transactions.sort((a, b) => {
    if (a.id > b.id) {
      return -1
    }
    if (a.id < b.id) {
      return 1
    }
    return 0;
  })
}

