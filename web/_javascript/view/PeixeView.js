class PeixeView{
    constructor(seletor){
        this.elemento = document.querySelector(seletor);
    }
    
    atualiza(listaPeixes){
        console.log(this.template(listaPeixes));
        this.elemento.innerHTML = this.template(listaPeixes);
    }
    
    template(listaPeixes){
        return `<table border='1px'>
            <thead>
                <tr>
                    <th>Especie</th>
                    <th>Estoque</th>
                <tr>
             </thead>
             <tbody>
                ${listaPeixes.map(peixe =>
                    `<tr>
                        <td>${peixe.especie}</td>
                        <td>${peixe.estoque}</td>
                    </tr>
                    `).join('')}
             </tbody>
        </table>`;
    }
}