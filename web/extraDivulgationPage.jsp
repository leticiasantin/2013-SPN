<script type="text/javascript">
    function solicitation() {
        decisao = confirm("Realizar solicita��o para " +${dPage.profileId} + "?");
        if (decisao) {
            var desc;
            do {
                desc = prompt("Insira a descri��o do servi�o desejado");
            } while (desc === null || desc === "");
            $.ajax({
                        type: "POST",
                        url: "Controller",
                        data: {
                            c: "doSolicitationAction",
                            m: "request",
                            providerId: ${dPage.providerId} ,
                            description: desc
                        }
                    }).success(function(data) {
                        alert('Solicita��o enviada com sucesso. Voc� pode verificar o status da sua solicita��o em Pend�ncias');
                    }).error(function() {
                        alert('Falha ao salvar');
                    });
        }
    }
</script>

<input type="button" onclick="javascript:solicitation()" value="Solicitar Servi�o de ${dPage.profileId}"/>

