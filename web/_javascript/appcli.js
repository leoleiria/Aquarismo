let controller = new ClienteController();
console.log("Teste");
//bind: associa o controller como this. Se nao tiver, ele vai achar que a referencia é o document e não o controller
//Ou seja,quando chamarmos o carregaClientes e este chamar this._service, ele vai pensar que o this é referente ao controller e não ao document.
window.addEventListener('load',controller.carregaClientes.bind(controller));

//bind: associa o controller como this. Se nao tiver, ele vai achar que a referencia é o document e não o controller
//Ou seja,quando chamarmos o salvar e este chamar this._service, ele vai pensar que o this é referente ao controller e não ao document.
document.querySelector("#formulariocli")
        .addEventListener('submit',controller.salvar.bind(controller));

//bind: associa o controller como this. Se nao tiver, ele vai achar que a referencia é o document e não o controller
//Ou seja,quando chamarmos o salvar e este chamar this._service, ele vai pensar que o this é referente ao controller e não ao document.
document.querySelector("#editar")
        .addEventListener('submit',controller.atualizar.bind(controller));













