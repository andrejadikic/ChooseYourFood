<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.rtl.min.css" integrity="sha384-+qdLaIRZfNu4cVPK/PxJJEy0B0f3Ugv8i482AKY7gwXwhaCroABd086ybrVKTa0q" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <title>Document</title>
</head>
<body>
<h1 style="text-align: center;   padding: 1em;">Choose your food</h1>
<h2 style="text-align: center;">Odaberite Vaš ručak</h2>
<form name="form" style="text-align: center;" method="post" action="user">

    <c:forEach items="${food}" var="entry">
        <label class="paylabel" for="${entry.key}">${entry.key}:</label>
        <select id="${entry.key}" style="width: 15em; text-align: center; margin: 1em auto;"  class="form-select" aria-label="Default select example">
            <option value="" selected>Open this select menu</option>
            <c:forEach items="${entry.value}" var="item" varStatus="loop">
                <option value="${item}">${item}</option>
            </c:forEach><br>
        </select>

    </c:forEach>

<%--    <select id="utorak" style="width: 15em; text-align: center; margin: 1em auto;"  class="form-select" aria-label="Default select example">--%>
<%--        <option value="" selected>Open this select menu</option>--%>
<%--        <option value="1">One</option>--%>
<%--        <option value="2">Two</option>--%>
<%--        <option value="3">Three</option>--%>
<%--    </select>--%>
<%--    <label class="paylabel" for="sreda">Sreda:</label>--%>
<%--    <select id="sreda" style="width: 15em; text-align: center; margin: 1em auto;"  class="form-select" aria-label="Default select example">--%>
<%--        <option selected value="">Open this select menu</option>--%>
<%--        <option value="1">One</option>--%>
<%--        <option value="2">Two</option>--%>
<%--        <option value="3">Three</option>--%>
<%--    </select>--%>
<%--    <label class="paylabel" for="cetvrtak">Četvrtak:</label>--%>
<%--    <select id="cetvrtak" style="width: 15em; text-align: center; margin: 1em auto;"  class="form-select" aria-label="Default select example">--%>
<%--        <option selected value="">Open this select menu</option>--%>
<%--        <option value="1">One</option>--%>
<%--        <option value="2">Two</option>--%>
<%--        <option value="3">Three</option>--%>
<%--    </select>--%>
<%--    <label class="paylabel" for="petak">Petak:</label>--%>
<%--    <select id="petak" style="width: 15em; text-align: center; margin: 1em auto;"  class="form-select" aria-label="Default select example">--%>
<%--        <option selected value="">Open this select menu</option>--%>
<%--        <option value="1">One</option>--%>
<%--        <option value="2">Two</option>--%>
<%--        <option value="3">Three</option>--%>
<%--    </select>--%>

    <input id="btn" style="background: blueviolet; color: azure;" type="submit" name="submit" class="button" value="Potvrdite unos" onmouseover="validate()">
</form>
<script>
    const btn = document.querySelector('#btn');
    function validate(){
        const pon = document.querySelector('#ponedeljak')
        const ut = document.querySelector('#utorak')
        const sr = document.querySelector('#sreda')
        const cet = document.querySelector('#cetvrtak')
        const pet = document.querySelector('#petak')

        if(pon.value=="" || ut.value=="" || sr.value==""|| cet.value==""|| pet.value==""){
            alert("Please select food for all days");
        }

    }
</script>

</body>


</html>