class ClienteView{
    constructor(seletor){
        this.elemento = document.querySelector(seletor);
    }
    
    atualiza(listaClientes){
        console.log(this.template(listaClientes));
        this.elemento.innerHTML = this.template(listaClientes);
    }
    
    template(listaClientes){
        return `<table border='1px'>
            <thead>
                <tr>
                    <th>Nome Cliente</th>
                    <tr>
             </thead>
             <tbody>
                ${listaClientes.map(cliente =>
                    `<tr>
                        <td>${cliente.nome}</td>
                    </tr>
                    `).join('')}
             </tbody>
        </table>`;
    }
}