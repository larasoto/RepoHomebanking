var app = new Vue({
  el: '#app',
  data: {
    clients: [],
    cards: [],
    types: [],
    select: "Cards",
    delete: false,
    filtradosTrue:[],
    fecha:"",
    debito:[],
    credito:[]

  },
  created() {
    this.loadData()

  },
  methods: {
    loadData() {
      axios.get("/api/clients/current")
        .then((response) => {
          this.clients = response.data
          this.cards = response.data.cards
          this.filtradosTrue=this.cards.filter(card => card.status == true )
          this.credito= this.filtradosTrue.filter(card => card.type == 'CREDIT')
          this.debito = this.filtradosTrue.filter(card =>card.type == 'DEBIT' )
          tipoTarjeta()
          alerta()
          typesRepetidos()
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
    patchCard(id) {
      axios.patch(`/api/clients/current/cards/${id}`,'status='+this.delete)
        .then(response => {
          window.location.reload()
        }).catch((error)=> {
          console.log(error)
        })},
  computed: {
    filterCards() {
      return this.cards.filter(card => {
        if (card.type == this.select || this.select == "Cards") {
          return card
        }
      })
    }
  }
  }})

function tipoTarjeta() {
  app.cards.forEach(e => {
    app.types.push(e.type)
  });

}

function alerta() {
  if (app.cards.length == 0) {
    swal({
      text: "He does not yet have cards. You can request one at 0800 456 334 234",
    })
  }
}

function typesRepetidos() {
  for (let i = 0; i < app.types.length; i++) {
    for (let l = i + 1; l < app.types.length; l++) {
      if (app.types[i] == app.types[l]) {
        app.types.splice(l, 1)
      }

    }
  }
}
//año,mes,dia
app.fecha = new Date()
app.fecha = app.fecha.toISOString()
console.log(fecha)