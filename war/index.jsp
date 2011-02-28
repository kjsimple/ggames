<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
  <meta name="keywords" content="象棋,打谱,江湖残局" />
  <link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/themes/ui-lightness/jquery-ui.css" rel="stylesheet" />
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js"></script>
  <style type="text/css">
  <%@ include file="css/gxj-game.css" %>
  </style>
</head>

<body>
  <table border="0" cellspacing="0" height="100%" cellpadding="0" align="center" width="960px" style="background-color: #FFFFFF;">
    <tr>
      <td height="40px" style="padding-top: 4px;">
        <span style="padding-left: 8px; font-weight: bold; font-size: 26px; color: green;">游民象棋</span>
        <hr style="color: #B0B0B0;">
      </td>
    </tr>
    <tr>
      <td style="vertical-align: top; padding-left: 2px;" id="mainBody">
        <table>
          <tr>
            <td>新建:</td>
            <td><button id="createNew">全局</button><button id="partialButton">残局</button></td>
          </tr>
        </table>
        <div id="chessCanvas">
          
        </div>
      </td>
    </tr>
    <tr>
      <td height="40px"><div id="test"></div></td>
    </tr>
  </table>
</body>
<script>
    $(document).ready(function() {
        $('button').button()
        $('#partialButton').click(function() {
            $.getScript("js/cchess.js", function() {});
        })
        $('#createNew').click(function() {
            $.getScript('js/cchess.js', function() {
                $.post("cchess/createNew", function(data) {createNew(data)})
            })
        })
    })
    $(document).ajaxError(function(e, xhr, settings, exception) {
        alert("ajax request failed : " + xhr.status)
    })
</script>
</html>
