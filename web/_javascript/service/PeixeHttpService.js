class PeixeHttpService {
    
    static get URI(){
        return "http://localhost:8080/Aquario/api/peixe";
    }
    
    constructor(){
        console.log("PeixeHttpService");
    }
    
     //ok e err sao funcoes de callback
    atualizaPeixe(id, peixe, ok, err) {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4) {
                if (xhttp.status === 200) {
                    ok();
                } else {
                    const msg = xhttp.statusText;
                    err(msg);
                }
            }
        };
        xhttp.open("PUT", PeixeHttpService.URI+"/"+id, true);
        xhttp.setRequestHeader("content-type", "application/json");
        xhttp.send(JSON.stringify(peixe));
    }
    
    //ok e err sao funcoes de callback
    enviaPeixe(peixe, ok, err) {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4) {
                if (xhttp.status === 201) {
                    ok();
                } else {
                    const msg = xhttp.statusText;
                    err(msg);
                }
            }
        };
        xhttp.open("POST", PeixeHttpService.URI, true);
        xhttp.setRequestHeader("content-type", "application/json");
        xhttp.send(JSON.stringify(peixe));
    }

    buscarPeixes(ok, err) {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4) {
                if (xhttp.status === 200) {
                    //Map é como varrer todos os itens do array e fazer uma ação com ele, retornando um novo array com esses itens modificados
                    //Note que no caso da Arrow Function, está retornando para cada item um novo, um objeto Peixe
                    //Tudo isso para transformar o nosso JSON (que retorna Object) em array de peixes (objeto do tipo Peixe).
                    let listaPeixes = JSON.parse(xhttp.responseText)
                            .map(item => new Peixe(item.especie, item.estoque));
                    ok(listaPeixes);
                } else {                    
                    const msg = xhttp.statusText;
                    err(msg);
                }
            }
        };
        xhttp.open("GET", PeixeHttpService.URI, true);
        xhttp.send();
 
    }
}

