class ClienteHttpService {
    
    static get URI(){
        return "http://localhost:8080/Aquario/api/cliente";
    }
    
    constructor(){
        console.log("ClienteHttpService");
    }
    
     //ok e err sao funcoes de callback
    atualizaCliente(id, cliente, ok, err) {
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
        xhttp.open("PUT", ClienteHttpService.URI+"/"+id, true);
        xhttp.setRequestHeader("content-type", "application/json");
        xhttp.send(JSON.stringify(cliente));
    }
    
    //ok e err sao funcoes de callback
    enviaCliente(cliente, ok, err) {
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
        xhttp.open("POST", ClienteHttpService.URI, true);
        xhttp.setRequestHeader("content-type", "application/json");
        xhttp.send(JSON.stringify(cliente));
    }

    buscarClientes(ok, err) {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4) {
                if (xhttp.status === 200) {
                    //Map é como varrer todos os itens do array e fazer uma ação com ele, retornando um novo array com esses itens modificados
                    //Note que no caso da Arrow Function, está retornando para cada item um novo, um objeto Cliente
                    //Tudo isso para transformar o nosso JSON (que retorna Object) em array de clientes (objeto do tipo Cliente).
                    let listaClientes = JSON.parse(xhttp.responseText)
                            .map(item => new Cliente(item.nome));
                    ok(listaClientes);
                } else {                    
                    const msg = xhttp.statusText;
                    err(msg);
                }
            }
        };
        xhttp.open("GET", ClienteHttpService.URI, true);
        xhttp.send();
 
    }
}

