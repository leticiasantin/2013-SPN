<script type="text/javascript">
    function solicitation() {
        decisao = confirm("Realizar solicitação para " +${dPage.profileId} + "?");
        if (decisao) {
            var desc;
            do {
                desc = prompt("Insira a descrição do serviço desejado");
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
                        alert('Solicitação enviada com sucesso. Você pode verificar o status da sua solicitação em Pendências');
                    }).error(function() {
                        alert('Falha ao salvar');
                    });
        }
    }
</script>

<input type="button" onclick="javascript:solicitation()" value="Solicitar Serviço de ${dPage.profileId}"/>

