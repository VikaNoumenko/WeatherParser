<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        .hidden {
            visibility: hidden;
        }
    </style>
    <title>Погода</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script type="text/javascript">
        function fetchWeather() {


            var selectedCity = $('#selectedCity').val();

            if (selectedCity == '') {
                $('#wait').html("Выбор")
                wipeTable();

            } else {
                $('#wait').html("Подождите")
                $.ajax({

                    type : "POST",
                    url : "/current-weather",
                    data : "city=" + selectedCity,
                    success : function(response) {

                        $('#wait').html("<br>")
                        $('#cityRow').html(selectedCity)

                        $('#tempRow').html(response.temperature + "&deg;" + "C")

                        $('#weather').removeClass("hidden")
                    },
                    error : function(){
                        $('#wait').html("Error ")
                        wipeTable();
                    }
                });
            }
        }
        function wipeTable(){
            $('#cityRow').html("")

            $('#tempRow').html("" + "&deg;" + "C")

        }
    </script>
    <body>
<button onclick="fetchWeather();"> Погода</button>
</body>
</head>
<body>
<form:form commandName="wb">
    <table>
        <tr>
            <td>Город</td>
            <td><form:select path="city" id="selectedCity"
                             >
                <form:option value="" label="Выбор" />
                <form:options items="${cityList}" />
            </form:select></td>
        </tr>
    </table>
    <div id="wait" style="color: green;"></div>
</form:form>
<br>
<table border="1" class="hidden" id="weather">
    <tr>
        <td>Город</td>
        <td id="cityRow"></td>
    </tr>

    <tr>
        <td>Температура</td>
        <td id="tempRow"></td>
    </tr>


</table>
</body>
</html>
