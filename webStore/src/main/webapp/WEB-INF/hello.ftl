<html>
<head>
<title>Hello World</title>

</head>
 
<body>
  <form name="user" action="/hello" method="post">
    RealName: <input type="text" name="realName" /> <br/>
    UserName: <input type="text" name="userName" /> <br/>
    <input type="submit" value="Save" />
  </form>
 
  <table class="datatable">
    <tr>
        <th>Firstname</th>  <th>Lastname</th>
    </tr>
    <#list users as user>
    <tr>
        <td>${user.firstname}</td> <td>${user.lastname}</td>
    </tr>
    </#list>
  </table>
</body>
</html>