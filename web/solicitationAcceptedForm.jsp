<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <script  src="js/jquery.js"></script>
        <script  src="js/timepicker.js"></script>
        <script  src="js/jquery/js/jquery-ui.js"></script>        
        <link type="text/css" href="js/jquery/css/south-street/jquery-ui.css" rel="stylesheet" />

        <script type="text/javascript">
            $(document).ready(function() {
                $("#startDate").datepicker({dateFormat: 'dd/mm/yy', minDate: 'today',
                    yearRange: '-0:+10', changeMonth: true});
                $("#finishDate").datepicker({dateFormat: 'dd/mm/yy', minDate: 'today',
                    yearRange: '-0:+10', changeMonth: true});
                                 
                $("#finishDate").change(function() {
                    checkDates();
                });
                $("#finishDate").focusout(function() {
                    checkDates();
                });
              
                
                $("#price").focusin(function(){
                   $("#priceSpan").show();
                });
                
                  
            });
            function checkDates() {
                var start = $("#startDate").val();
                var finish = $("#finishDate").val();
                if (start > finish) {
                    $("#submit").hide();
                    $("#dateSpan").show();
                }
                else {
                    $("#submit").show();
                    $("#dateSpan").hide();
                }
            }
        </script>
    </head>

    <body>      

        <form id="acceptForm" action="Controller" method="GET">
            <table>
                <tr>
                    <td>Data e Hora de Inicio</td>
                    <td><input type="text" id="startDate" name="startDate"/> </td>           
                </tr>
                <tr>
                    <td>Data e Hora de Termino</td>
                    <td><input type="text" id="finishDate" name="finishDate"/>
                        <br/> <span id="dateSpan" hidden='hidden' style='color: red;'>A data inicial não pode ser maior que a final</span>
                    </td>
                    <td>
                        <input type="text" id="finishHour" name="finishHour"/>
                    </td>
                </tr>
                <tr>
                    <td>Preço</td>
                    <td><input type="text" id="price" name="price"/>
                     <br/> <span id="priceSpan" hidden='hidden' style='color: red;'>Formato do preço 9,99</span>
                    </td>
                </tr>
            </table>
            <input type="hidden" name="c" value="doSolicitationAction"/>
            <input type="hidden" name="m" value="accept"/>
            <input type="submit"  id="submit" value="Enviar"/>
            <input type="reset" value="Limpar Campos"/>
            <input type="button" value="Cancelar" onclick="javascript:window.close();"/>
        </form>

    </body>
</html>