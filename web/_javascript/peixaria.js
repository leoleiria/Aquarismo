$(document).ready(function(){

    loadJson();

});

function loadJson(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
        trataJson(JSON.parse(this.responseText));
    }
  };
  xhttp.open("GET", "http://localhost:8080/Aquario/api/peixe", true);
  xhttp.send();
}

function trataJson(jsonObject){

    for(i=0;i<jsonObject.length; i+=3){
    var section = $("<section>")
    for(var pos=0; pos<3; pos++){
      var item = i+pos;
        if(jsonObject[item] == undefined) {continue}

        var content = $("<dl class'"+((pos==1) ? "central" : "") + "'>")
        section.append(content)
        var dd = $("<dd>")
        var par = $("<p>");
        var figure = $("<figure>")
        par.append(figure)
        var imagem = $("<img alt ='"+jsonObject[item].especie + "' src='__imagens/00"+(pos+1)+".jpg'>")
        figure.append(imagem);
        dd.append(figure);
        var dt = $("<dt><a href='#'>"+jsonObject[item].especie+"</a>")
        var ddDesc = $("<dd>Estoque:"+jsonObject[item].estoque+"</dd>")
        var hr = $("<hr>");
        content.append(hr);
        content.append(dd);
        content.append(dt);
        content.append(ddDesc);
        montaclick(content, jsonObject[item]);

      }
  $("main").append(section)
    }

}
 function montaclick(content,object){
   content.click(function(){
     $("#cEspcAtual").val(object.especie);
     $("#cID").val(object.id)
   })
 }
