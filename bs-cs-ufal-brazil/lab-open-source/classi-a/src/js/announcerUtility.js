function openAnnounceImage(a) {
      window.open('upload//'+a,'AnnounceImage','toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,width=320,height=250');
}

var message="  Classi-A / O Classificado que tem tudo. Tudo!\nDesenvolvido por LX4 Web Group Development\n              Maceió-Alagoas-Brasil";

if (document.layers) {
document.captureEvents(Event.MOUSEDOWN);
}
document.onmousedown=showStatys;

function openAnnounce(a,b) {
      window.open('showAnnounce.php?nocache=99999999999&idAnnounce='+a+'&LXID='+b,'AnnounceImage','toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=no,width=480,height=400');
}
  function openWindow(pg, name, width, height){
        window.open(pg,name,'toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=no,width='+width+',height='+height);
  }
