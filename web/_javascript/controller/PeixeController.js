class PeixeController {
    constructor() {
        this._service = new PeixeHttpService();
        this.peixeView = new PeixeView("#peixes");
        
    }
    
    atualizar(event) {
        event.preventDefault(); 
        let especie = document.querySelector("#txtespecie").value;
        let estoque = 10;
        let id = document.querySelector("#cID").value;
        let peixe = new Peixe(especie, estoque);
        
        const self = this;
        
        this._service.atualizaPeixe(id, peixe,
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
        let especie = document.querySelector("#txtespecie").value;
        let estoque = document.querySelector("#txtestoque").value;
        let peixe = new Peixe(especie, estoque);
        
        const self = this;
        
        this._service.enviaPeixe(peixe,
            function(){
                self.limparCamposFormulario();
                self.carregaPeixes();
            },
            function(msg) {
                console.log(msg);
            }
        );
    }

    carregaPeixes() {
        console.log("Carrega Peixes");
        const self = this;
        this._service.buscarPeixes(
            function(listaPeixe){
                console.log(listaPeixe);
                self.peixeView.atualiza(listaPeixe);
            },
            function(msg){
                console.log(msg);
            }
        );
    }
    
    montarHTML(listaPeixe) {
        document.querySelector("table").innerHTML =
                `<tr>
                <th>Especie</th>
                <th>Estoque</th>
             </tr> `;
        for (let ind in listaPeixe) {
            let tr = document.createElement("tr");
            let linha =
                    `   <td>${listaPeixe[ind].especie}</td>
                        <td>${listaPeixe[ind].quantidade}</td>
                `;
            tr.innerHTML = linha;
            document.querySelector("table").appendChild(tr);
        }
    }

    limparCamposFormulario() {
        document.querySelector("#txtespecie").value = "";
        document.querySelector("#txtestoque").value = "";
    }
}

