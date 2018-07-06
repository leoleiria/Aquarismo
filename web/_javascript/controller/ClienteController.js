class ClienteController {
    constructor() {
        this._service = new ClienteHttpService();
        this.clienteView = new ClienteView("#clientes");
        
    }
    
    atualizar(event) {
        event.preventDefault(); 
        let nome = document.querySelector("#nomecli").value;
        let id = document.querySelector("#cID").value;
        let cliente = new Cliente(nome);
        
        const self = this;
        
        this._service.atualizaCliente(id, cliente,
            function(){
                self.limparCamposFormulario();
                location.reload();
                
            },
            function(msg) {
                console.log(msg);
            }
        );
    }

    salvar(event) {
        event.preventDefault(); 
        let nome = document.querySelector("#nomecli").value;
        let cliente = new Cliente(nome);
        
        const self = this;
        
        this._service.enviaCliente(cliente,
            function(){
                self.limparCamposFormulario();
                self.carregaClientes();
            },
            function(msg) {
                console.log(msg);
            }
        );
    }

    carregaClientes() {
        console.log("Carrega Clientes");
        const self = this;
        this._service.buscarClientes(
            function(listaCliente){
                console.log(listaCliente);
                self.clienteView.atualiza(listaCliente);
            },
            function(msg){
                console.log(msg);
            }
        );
    }
    
    montarHTML(listaCliente) {
        document.querySelector("table").innerHTML =
                `<tr>
                <th>Nome</th>
             </tr> `;
        for (let ind in listaCliente) {
            let tr = document.createElement("tr");
            let linha =
                    `   <td>${listaCliente[ind].nome}</td>
                        
                `;
            tr.innerHTML = linha;
            document.querySelector("table").appendChild(tr);
        }
    }

    limparCamposFormulario() {
        document.querySelector("#nomecli").value = "";
        
    }
}

