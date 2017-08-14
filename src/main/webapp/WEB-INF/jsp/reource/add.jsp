<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>上传图片</title>
</head>
<body>
<form action="upload" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="file" placeholder="请选择文件"/></br>
    描述：<textarea name="description"></textarea></br>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
