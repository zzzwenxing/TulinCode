<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>tuling blog</title>
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/semantic-ui/2.3.1/semantic.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/semantic-ui/2.3.1/semantic.min.js"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/simplemde/latest/simplemde.min.css">
    <script src="https://cdn.jsdelivr.net/simplemde/latest/simplemde.min.js"></script>

</head>
<body>
<div class="top ui segment">
    <div class="ui secondary  menu">
        <div class="ui container">
            <a class=" item">
                LOGO
            </a>
            <div class="item">
                <div class="ui mini icon input">
                    <input type="text" placeholder="搜索...">
                    <i class="search link icon"></i>
                </div>
            </div>
            <a class="active primary item" href="/list">
                首页
            </a>
            <a class="item" href="/list?author=鲁班">
                鲁班专栏
            </a>
            <a class="item" href="/list?author=悟空">
                悟空专栏
            </a>
            <a class="item" href="/list?author=诸葛">
                诸葛专栏
            </a>
            <div class="right menu">
                <a class="ui   item" href="/edit?user=鲁班">
                    <i class="green add icon  "></i>
                </a>
                <a class="ui icon dropdown item">
                    <i class="user icon"></i>
                ${user!}
                </a>
            </div>
        </div>
    </div>
</div>


<!--内容主体-->
<div class="ui   container  ">
    <!-- 中间内容 -->
    <h3 class="ui center aligned top attached block header">添加新内容</h3>
    <div class="ui attached segment">
        <form class="ui form" action="/save" method="post">
            <div class="field required">
                <label class="label">标题:</label>
                <input type="text" name="title" placeholder="输入项目名称">
            </div>
            <input type="hidden" name="author" value="${authorName}">
            <div class="field ">
                <label class="label">内容:</label>
                <textarea id="doc-edit" name="content" style="display: none"></textarea>
            </div>
            <div class="ui" style="float: right">
                <button class="ui button positive" type="submit">创建新的项目</button>
                <button class="ui button" type="reset">取消</button>
            </div>
            <br>
            <br>
        </form>
    </div>
</div>

<script type="text/javascript">
    var simplemde = new SimpleMDE({element: $("#doc-edit")[0]});
</script>
</body>
</html>