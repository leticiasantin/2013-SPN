/*
 * Limpa todos os elementos do select
 * @param {type} selectId
 * @returns {undefined}
 * 
 */

function clearOption(selectId) {
    var x = document.getElementById(selectId)
    while (x.length > 0)
    {
        x.remove(x.length - 1);
    }
}

/*
 * Abre uma janela com os tamanhos definidos e abre a 'URL' passada
 * como parametro 
 *   <a href="javascript:openPopup('testeUpload.jsp?type=s&id=42');">Clique Aqui</a>   
 */
function openPopup(URL) {
    var width = 400;
    var height = 250;
    var left = 99;
    var top = 99;
    window.open(URL, 'janela', 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left + ', scrollbars=yes, status=no, toolbar=no, location=no, directories=no, menubar=no, resizable=no, fullscreen=no');
}
   