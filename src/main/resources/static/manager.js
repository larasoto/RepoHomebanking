var app = new Vue({
  el: '#app',
  data: {
    clients: [],
    firsName: "",
    lastName: "",
    email: "",
    pass: "",
    nuevoCliente: {},
    json:{},
  },
  created() {
   this.loadData()
    
  },
  methods: {
 loadData(){
      axios.get("/rest/clients")

        .then((response) => {
          // handle succ
          this.clients = response.data._embedded.clients
          this.json= response.data
        })
        .catch((error) => {
          // handle error
          console.log(error)
        })
      },
    addClients() {
      this.nuevoCliente = {
        firtName: this.firsName,
        lastName: this.lastName,
        email: this.email,
        password: this.pass
      }
      alert("Cliente agregado con exito!!")
      this.postClient()
    },
    postClient() {
      axios.post("/rest/clients", {
        firtName: this.firsName,
        lastName: this.lastName,
        email: this.email,
        password: this.pass
      })
    },
    eliminar(cli){
      axios.delete(cli)
     
      .then((response) => {
        alert("si deseas eliminar este cliente presiona OK")
        this.loadData() //me recarga la pagina al apretar el boton delete  
      })
   
    }


  }

})